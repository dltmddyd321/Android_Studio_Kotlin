package com.sycompany.roomsave

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContractDao {
    @Query("SELECT * FROM tb_contracts")
    fun getAll(): List<Contract>

    //가변 인자
    @Insert
    fun insertAll(vararg contacts: Contract)

    @Delete
    fun delete(contacts: Contract)
}