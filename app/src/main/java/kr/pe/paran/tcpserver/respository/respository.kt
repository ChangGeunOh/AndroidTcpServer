package kr.pe.paran.tcpserver.respository

import kr.pe.paran.tcpserver.respository.database.LocalDatabase
import kr.pe.paran.tcpserver.respository.database.model.TestData

class Repository(
    private val localDatabase: LocalDatabase,
) {

    suspend fun insertTestData(testData: TestData) {
        localDatabase.testDao().insert(testData)
    }

    suspend fun getTestData(uid: String): TestData? {
        return localDatabase.testDao().getTestData(uid = uid)
    }
}