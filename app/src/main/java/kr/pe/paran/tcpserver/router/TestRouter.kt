package kr.pe.paran.tcpserver.router

import android.util.Log
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kr.pe.paran.tcpserver.respository.Repository
import kr.pe.paran.tcpserver.respository.database.model.TestData
import java.util.*

fun Route.test(
    repository: Repository
) {
    route("/test") {
        get("/{uuid}") {
            val uid = call.parameters["uuid"] ?: UUID.randomUUID().toString()
            val testData = repository.getTestData(uid = uid) ?: TestData()
            call.respond(testData.toString())
        }

//        post {
//            val testData = call.receive<TestData>()
//            repository.insertTestData(testData = testData)
//            call.respond(testData)
//        }
    }
}