package com.example.martianrobotcommander.controller

import com.example.martianrobotcommander.command.MovementCommandParser
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RemoteRobotController(val movementCommandParser: MovementCommandParser) {

    @PostMapping("/movement", produces = [MediaType.TEXT_PLAIN_VALUE], consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun submitMovementCommands(@RequestBody commandInput: String): String {
        return try {
            val commands = movementCommandParser.invoke(commandInput)
            "success!"
        } catch (e: Exception) {
            "Error Parsing Movement commands"
        }
    }
}