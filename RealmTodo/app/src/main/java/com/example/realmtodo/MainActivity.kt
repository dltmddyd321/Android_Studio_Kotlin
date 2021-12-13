package com.example.realmtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    // Results obtained from a Realm are live, and can be observed on looper threads (like the UI thread).
    // Note that if you want to observe the RealmResults for a long time, then it should be a field reference.
    // Otherwise, the RealmResults can no longer be notified if the GC has cleared the reference to it.
    private lateinit var persons: RealmResults<Person>

    // OrderedRealmCollectionChangeListener receives fine-grained changes - insertions, deletions, and changes.
    // If the change set isn't needed, then RealmChangeListener can also be used.
    private val realmChangeListener =
        OrderedRealmCollectionChangeListener { people: RealmResults<Person?>?, changeSet: OrderedCollectionChangeSet ->
            val insertions =
                if (changeSet.insertions.isEmpty()) "" else "\n - Insertions: " + Arrays.toString(
                    changeSet.insertions
                )
            val deletions =
                if (changeSet.deletions.isEmpty()) "" else "\n - Deletions: " + Arrays.toString(
                    changeSet.deletions
                )
            val changes =
                if (changeSet.changes.isEmpty()) "" else "\n - Changes: " + Arrays.toString(
                    changeSet.changes
                )
            showStatus("Person Database State : ${changeSet.state}")
            if (insertions != "" || deletions != "" || changes != "") {
                showStatus("Person was loaded, or written to. $insertions$deletions$changes")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container.removeAllViews()

        // Clear the Realm if the example was previously run.
        Realm.deleteRealm(Realm.getDefaultConfiguration()!!)

        // Create the Realm instance
        realm = Realm.getDefaultInstance()

        // Asynchronous queries are evaluated on a background thread,
        // and passed to the registered change listener when it's done.
        // The change listener is also called on any future writes that change the result set.
        persons = realm.where(Person::class.java).findAllAsync()

        // The change listener will be notified when the data is loaded,
        // or the Realm is written to from any threads (and the result set is modified).
        persons.addChangeListener(realmChangeListener)

        // These operations are small enough that
        // we can generally safely run them on the UI thread.
        basicCRUD(realm)
        basicQuery(realm)
        basicLinkQuery(realm)

        // More complex operations can be executed on another thread.
        ComplexBackgroundOperations(WeakReference(this)).execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        persons.removeAllChangeListeners() // Remove the change listener when no longer needed.
        realm.close() // Remember to close Realm when done.
    }

    private fun showStatus(text: String) {
        Log.i(TAG, text)
        val textView = TextView(this)
        textView.text = text
        container.addView(textView)
    }

    private fun basicCRUD(realm: Realm) {
        showStatus("Perform basic Create/Read/Update/Delete (CRUD) operations...")

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction {
            // Add a person
            // RealmObjects with primary keys created with 'createObject()' must specify the primary key value as an argument.
            val person = it.createObject(Person::class.java, 1).apply {
                name = "Young Person"
                age = 14

                // Even young people have at least one phone in this day and age.
                // Please note that this is a RealmList that contains primitive values.
                phoneNumber?.add("+1 123 4567")
            }
        }

        // Find the first person (no query conditions) and read a field
        val person = realm.where(Person::class.java).findFirst()
        showStatus("${person?.name} : ${person?.age}")

        // Update person in a transaction
        realm.executeTransaction {
            // Managed objects can be modified inside transactions.
            person?.name = "Senior Person"
            person?.age = 99
            showStatus("${person?.name} got older : ${person?.age}")
        }

        // Delete all persons
        showStatus("Deleting all persons")
        realm.executeTransaction { r -> r.delete(Person::class.java) }
    }

    private fun basicQuery(realm: Realm) {
        showStatus("\nPerforming basic Query operation...")

        // Let's add a person so that the query returns something.
        realm.executeTransaction { r ->
            val oldPerson = Person().apply {
                id = 99
                age = 99
                name = "George"
            }
            realm.insertOrUpdate(oldPerson)
        }

        showStatus("Number of persons: " + realm.where(Person::class.java).count())

        val filed = "age"
        val age = 99

        val results: RealmResults<Person> =
            realm.where(Person::class.java).equalTo(filed, age).findAll()

        showStatus("Size of result set: " + results.size)
    }

    private fun basicLinkQuery(realm: Realm) {
        showStatus("\nPerforming basic Link Query operation...")

        // Let's add a person with a cat so that the query returns something.
        realm.executeTransaction {
            val catLady = realm.createObject(Person::class.java, 24).apply {
                age = 52
                name = "Mary"

                val tiger = realm.createObject(Cat::class.java)
                tiger.name = "Tiger"
                this.cats?.add(tiger)
            }

            showStatus("Number of persons : ${realm.where(Person::class.java).count()}")

            val results = realm.where(Person::class.java).equalTo("cats.name", "Tiger").findAll()

            showStatus("Size of result set : ${results.size}")
        }
    }

    // This AsyncTask shows how to use Realm in background thread operations.
    //
    // AsyncTasks should be static inner classes to avoid memory leaks.
    // In this example, WeakReference is used for the sake of simplicity.
    class ComplexBackgroundOperations(
        private val weakReference: WeakReference<MainActivity>
    ) : AsyncTask<Void, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            val activity = weakReference.get() ?: return
            activity.showStatus("\n\nBeginning complex operations on background thread.")
        }

        override fun doInBackground(vararg p0: Void?): String {
            val activity = weakReference.get() ?: return ""

            // Open the default realm. Uses `try-with-resources` to automatically close Realm when done.
            // All threads must use their own reference to the realm.
            // Realm instances, RealmResults, and managed RealmObjects can not be transferred across threads.
            Realm.getDefaultInstance().use { realm ->
                var info: String?
                info = activity.complexReadWrite(realm)
                info += activity.complexQuery(realm)
                return info
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val activity = weakReference.get() ?: return
            activity.showStatus(result ?: "result is null.")
        }
    }

    private fun complexReadWrite(realm: Realm): String {
        var status = "\nPerforming complex Read/Write operation..."

        // Add ten persons in one transaction
        realm.executeTransaction { r ->
            val fido = r.createObject(Dog::class.java)
            fido.name = "fido"
            for (i in 0..9) {
                val person = r.createObject(Person::class.java, i)
                person.name = ("no.$i")
                person.age = i
                person.dog = fido

                // The field tempReference is annotated with @Ignore.
                // This means setTempReference sets the Person tempReference
                // field directly. The tempReference is NOT saved as part of
                // the RealmObject:
                person.tempReference = 42
                for (j in 0 until i) {
                    val cat = r.createObject(Cat::class.java)
                    cat.name = "Cat_$j"
                    person.cats?.add(cat)
                }
            }
        }

        // Implicit read transactions allow you to access your objects
        status += "\nNumber of persons: ${realm.where(Person::class.java).count()}"

        status += "\n| Name \t\t\t\t| Age\t | Dog name\t\t| Cats size | id"
        // Iterate over all objects, with an iterator
        for (person in realm.where(Person::class.java).findAll()) {
            val dogName: String? = person.dog?.name
            status += "\n| ${person.name}\t\t\t| ${person.age}\t| $dogName\t| ${person.cats?.size}| ${person.id}"
        }

        // Sorting
        val sortedPersons = realm.where(Person::class.java).sort("age", Sort.DESCENDING).findAll()
        status += "\nSorting ${sortedPersons.last()?.name} == ${
            realm.where(Person::class.java).findFirst()?.name
        }"

        return status
    }

    private fun complexQuery(realm: Realm): String {
        var status = "\n\nPerforming complex Query operation..."
        status += "\nNumber of persons: ${realm.where(Person::class.java).count()}"

        // Find all persons where age between 7 and 9 and name begins with "Person".
        val results = realm.where(Person::class.java)
            .between("age", 7, 9) // Notice implicit "and" operation
            .beginsWith("name", "no").findAll()
        status += "\nSize of result set: ${results.size}"

        return status
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}