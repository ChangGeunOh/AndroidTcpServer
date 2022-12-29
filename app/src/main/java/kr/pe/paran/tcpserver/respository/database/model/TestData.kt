package kr.pe.paran.tcpserver.respository.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "test_table")
data class TestData(
    @PrimaryKey
    var uid: String = "",
    var memo: String = ""
) {
    init {
        uid = UUID.randomUUID().toString()
    }
}
