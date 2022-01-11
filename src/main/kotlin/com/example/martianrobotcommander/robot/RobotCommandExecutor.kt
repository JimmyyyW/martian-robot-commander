package com.example.martianrobotcommander.robot

import com.example.martianrobotcommander.command.Command
import com.example.martianrobotcommander.command.MovementCommand

class RobotCommandExecutor(
    val marsSurface: Array<IntArray>? = null
) {

    fun execute(command: Command<*>): Any {
        return when (command.javaClass) {
            MovementCommand::class.java -> command.invoke() as Any
            else -> throw Exception("Unknown command type")
        }
    }
}