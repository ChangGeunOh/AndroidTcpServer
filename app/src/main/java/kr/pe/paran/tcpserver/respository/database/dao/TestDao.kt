package kr.pe.paran.tcpserver.respository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.pe.paran.tcpserver.respository.database.model.TestData
import java.net.IDN

@Dao
interface TestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(testDataList: List<TestData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(testData: TestData)

    @Query("select * from test_table")
    suspend fun selectAll(): List<TestData>

    @Delete
    suspend fun delete(testData: TestData)

    @Query("select * from test_table where uid = :uid")
    suspend fun getTestData(uid: String): TestData?
}