package kr.pe.paran.tcpserver.respository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.pe.paran.tcpserver.common.Constants
import kr.pe.paran.tcpserver.respository.database.dao.TestDao
import kr.pe.paran.tcpserver.respository.database.model.TestData

@Database(
    entities = [TestData::class],
    version = Constants.LOCAL_DATABASE_VERSION,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
}