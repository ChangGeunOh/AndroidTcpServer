package kr.pe.paran.tcpserver.server

import android.content.Context
import androidx.room.Room

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kr.pe.paran.tcpserver.common.Constants
import kr.pe.paran.tcpserver.respository.Repository
import kr.pe.paran.tcpserver.respository.database.LocalDatabase
import kr.pe.paran.tcpserver.server.plugins.configureHTTP
import kr.pe.paran.tcpserver.server.plugins.configureRouting
import kr.pe.paran.tcpserver.server.plugins.configureSerialization

class Server(context: Context) {

    private var repository: Repository

    init {
        val localDatabase = Room
            .databaseBuilder(context, LocalDatabase::class.java, Constants.LOCAL_DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        repository = Repository(context, localDatabase)

    }

    private var server: NettyApplicationEngine? = null

    fun startServer(staticContentDirectory: String, staticContentURL: String) {

        server = embeddedServer(
            Netty,
            port = 8080,
            host = "0.0.0.0",
            module = {
                module(repository = repository)
            })

        CoroutineScope(Dispatchers.Default).launch {
            server?.start(wait = true)
        }

    }


    fun stopServer() {
        CoroutineScope(Dispatchers.Default).launch {
            server?.stop(1000, 1000)
        }
    }
}


fun Application.module(repository: Repository) {
    configureHTTP()
    configureRouting(repository = repository)
    configureSerialization()
}
