package com.example.martianrobotcommander.robot

import com.example.martianrobotcommander.command.MovementCommand
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RobotCommandExecutorTest {

    private val subject = RobotCommandExecutor()

    @Test
    @DisplayName("")
    fun itShouldExecuteAnyCommand() {
        val command = MovementCommand(Location(1, 1, Orientation.E), "RRR")

        subject.execute(command)
    }
}