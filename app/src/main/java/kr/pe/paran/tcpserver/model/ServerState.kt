package kr.pe.paran.tcpserver.model

enum class ServerState(val message: String) {
    INIT(message= "Initializing..."),
    RUN(message = "Running..."),
    STOP(message = "Stop...")
}