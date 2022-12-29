package kr.pe.paran.tcpserver.server.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kr.pe.paran.tcpserver.respository.Repository
import kr.pe.paran.tcpserver.server.routers.static
import kr.pe.paran.tcpserver.server.routers.test

fun Application.configureRouting(repository: Repository) {

    routing {
        get("/") {
            call.respondText("Welcome to Android TCP Server...")
        }

        val staticFileDirectory = repository.context.filesDir.absolutePath
        static(staticFileDirectory, staticFileDirectory, "")
        test(repository = repository)
    }
}