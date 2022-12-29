package kr.pe.paran.tcpserver.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@kotlinx.serialization.Serializable
@Entity(tableName = "test_table")
data class TestData(
    @PrimaryKey
    var uid: String = UUID.randomUUID().toString(),
    var memo: String = ""
)