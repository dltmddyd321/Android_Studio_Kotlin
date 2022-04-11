package com.example.contacts

import android.arch.persistence.room.*
import androidx.lifecycle.LiveData

//OnConflictStrategy 인터페이스를 호출해 REPLACE, IGNORE, ABORT, FAIL, ROLLBACK 등의 액션이 지정 가능하다.
@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll() : LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}