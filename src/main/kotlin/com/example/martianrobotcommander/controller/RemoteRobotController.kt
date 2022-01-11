package com.example.martianrobotcommander.controller

import com.example.martianrobotcommander.command.MovementCommand
import com.example.martianrobotcommander.command.MovementCommandParser
import com.example.martianrobotcommander.robot.RobotCommandExecutor
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RemoteRobotController(
    val movementCommandParser: MovementCommandParser
) {

    @PostMapping("/movement", produces = [MediaType.TEXT_PLAIN_VALUE], consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun submitMovementCommands(@RequestBody commandInput: String): String {
        return try {
            val commands = movementCommandParser.invoke(commandInput)
            val commandExecutor = RobotCommandExecutor()
            var executionResponse = ""
            commands.stream()
                .map { commandExecutor.execute(it) }
                .forEach { executionResponse += "$it\n" }
            //To reset scent feature on each request
            MovementCommand.scents = mutableListOf()
            return executionResponse
        } catch (e: Exception) {
            "Error Parsing Movement commands"
        }
    }
}