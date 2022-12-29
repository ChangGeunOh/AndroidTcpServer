package kr.pe.paran.tcpserver.server.routers

import android.util.Log
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kr.pe.paran.tcpserver.respository.Repository
import kr.pe.paran.tcpserver.model.TestData
import java.util.*

fun Route.test(
    repository: Repository
) {
    route("/test") {
        get("/{uuid}") {
            val uid = call.parameters["uuid"] ?: UUID.randomUUID().toString()
            val testData = repository.getTestData(uid = uid) ?: TestData(uid = "")
            call.respond(testData)
        }

        post {
            val testData = call.receive<TestData>()
            repository.insertTestData(testData = testData)
            call.respond(testData)
        }

        get {
            val list = repository.getTestList()
            call.respond(list)
        }
    }
}