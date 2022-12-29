package kr.pe.paran.tcpserver.plugins

import android.content.Context
import androidx.room.Room
import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.*
import kr.pe.paran.tcpserver.common.Constants
import kr.pe.paran.tcpserver.respository.Repository
import kr.pe.paran.tcpserver.respository.database.LocalDatabase
import kr.pe.paran.tcpserver.router.static
import kr.pe.paran.tcpserver.router.test

class Router(context: Context) {

    private var repository: Repository

    init {
        val localDatabase = Room
            .databaseBuilder(context, LocalDatabase::class.java, Constants.LOCAL_DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        repository = Repository(localDatabase)

    }

    private var server: NettyApplicationEngine? = null

    fun startServer(staticContentDirectory: String, staticContentURL: String) {

        server = embeddedServer(Netty, port = 8080) {
            installCors(this)

            routing {
                static(staticContentDirectory, staticContentDirectory, staticContentURL)
                route("/") {
                    get {
                        call.respond("Welcome Android Server....")
                    }
                }
                test(repository = repository)
            }
        }

        server?.start()
    }

    private fun installCors(server: Application) {
        server.install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            method(HttpMethod.Patch)
            anyHost()
        }
    }

    fun stopServer() {
        server?.stop(1000, 1000)
    }
}