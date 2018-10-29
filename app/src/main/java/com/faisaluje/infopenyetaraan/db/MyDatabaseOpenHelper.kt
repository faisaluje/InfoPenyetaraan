package com.faisaluje.infopenyetaraan.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "Riwayat.db"){
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if(instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }

            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Riwayat.TABLE_GURU, true,
                Riwayat.PTK_ID to TEXT + PRIMARY_KEY + UNIQUE,
                Riwayat.NO_BERKAS to TEXT,
                Riwayat.NUPTK to TEXT,
                Riwayat.NAMA to TEXT,
                Riwayat.JENJANG_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Riwayat.TABLE_GURU, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)