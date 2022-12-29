package kr.pe.paran.tcpserver.respository

import android.content.Context
import kr.pe.paran.tcpserver.respository.database.LocalDatabase
import kr.pe.paran.tcpserver.model.TestData

class Repository(
    val context: Context,
    private val localDatabase: LocalDatabase,
) {


    suspend fun insertTestData(testData: TestData) {
        localDatabase.testDao().insert(testData)
    }

    suspend fun getTestData(uid: String): TestData? {
        return localDatabase.testDao().getTestData(uid = uid)
    }

    suspend fun getTestList(): List<TestData> {
        return localDatabase.testDao().selectAll()

    }
}