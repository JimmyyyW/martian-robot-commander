package com.example.martianrobotcommander.controller

import com.example.martianrobotcommander.command.MovementCommandParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class RemoteRobotControllerTest {

    private val movementCommandParser: MovementCommandParser = mock(MovementCommandParser::class.java)
    private val subject = RemoteRobotController(movementCommandParser)

    @Test
    @DisplayName("when invoked with valid text input it responds with text")
    fun submitMovementCommands() {
        val actual = subject.submitMovementCommands(
            """
            53
            11E RFRFRFRF
            32N FRRFLLFFRRFLL
            03W LLFFFLFLFL
        """
        )

        assertThat(actual).isNotNull
        assertThat(actual.javaClass).isEqualTo(String::class.java)
        assertThat(actual).isNotEqualTo("Error Parsing Movement commands")
    }

    @Test
    @DisplayName("when invoked with invalid input it responds with failure message")
    fun submitMovementCommandsFailure() {
        `when`(movementCommandParser.invoke(anyString())).thenThrow(Exception::class.java)

        val actual = subject.submitMovementCommands(
            """
            some random nonsense
            amd stuff
        """
        )

        assertThat(actual).isNotNull
        assertThat(actual.javaClass).isEqualTo(String::class.java)
        assertThat(actual).isEqualTo("Error Parsing Movement commands")
    }
}