package com.plcoding

import io.ktor.server.application.*
import com.plcoding.plugins.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val mongoPw = System.getenv("MONGO_PW")
    val dbName = "ktor-auth"
    val db = KMongo.createClient(
        connectionString = "mongodb+srv://martinwainaina:$mongoPw@test.tcxls.mongodb.net/$dbName?retryWrites=true&w=majority\n"
    ).coroutine
        .getDatabase(dbName)

    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
