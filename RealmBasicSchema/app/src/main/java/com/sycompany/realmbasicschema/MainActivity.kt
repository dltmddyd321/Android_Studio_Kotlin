package com.sycompany.realmbasicschema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.where

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val realm = io.realm.Realm.getDefaultInstance()

        fun insert(user: User) {
            realm?.executeTransactionAsync {
                it.insertOrUpdate(user)
            }
        }

        fun insertPet(pet: Pet) {
            realm?.executeTransactionAsync {
                it.insertOrUpdate(pet)
            }
        }

        fun getAllUser(): RealmResults<User> {
            return realm.where<User>()
                .sort("id", Sort.ASCENDING)
                .findAll()
        }

        fun getAllPet(): RealmResults<Pet> {
            return realm.where<Pet>()
                .sort("id", Sort.ASCENDING)
                .findAll()
        }

        fun getUser(id: Int): User? = realm.where<User>()
            .equalTo("id", id)
            .findFirst()

        fun deleteAllUser() {
            realm.executeTransaction {
                it.where<User>().findAll().deleteAllFromRealm()
            }
        }

        fun deleteUser(id: Int) {
            realm.executeTransaction {
                it.where<User>().equalTo("id", id).findAll().deleteAllFromRealm()
            }
        }

        val newUser = User(1, "Korea","LEESY", 100)
        val newPet = Pet(1, 2009,"Puppy", 10)

        insert(newUser)
        insertPet(newPet)

        getAllUser().forEach {
            Log.d("Realm-Test : User -> ", it.name.toString())
        }

        getAllPet().forEach {
            Log.d("Realm-Test : Pet -> ", it.name.toString())
        }
    }
}