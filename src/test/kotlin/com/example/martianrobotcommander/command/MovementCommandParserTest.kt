package com.example.martianrobotcommander.command

import com.example.martianrobotcommander.mars.Mars
import com.example.martianrobotcommander.robot.Location
import com.example.martianrobotcommander.robot.Orientation
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MovementCommandParserTest {

    private val subject = MovementCommandParser()

    @Test
    @DisplayName("it extracts the planetary surface of mars")
    fun itShouldSetMarsSurfaceGrid() {
        val commands = subject.invoke("""53
            1 1 E 
            RFRFRFRF
            3 2 N
            FRRFLLFFRRFLL
            0 3 W 
            LLFFFLFLFL""".trimIndent()
        )

        val command1 = MovementCommand(Location(1, 1, Orientation.E), "RFRFRFRF")
        val command2 = MovementCommand(Location(3, 2, Orientation.N), "FRRFLLFFRRFLL")

        Assertions.assertThat(commands).isNotEmpty
        Assertions.assertThat(commands.size).isEqualTo(3)

        Assertions.assertThat(commands[0].start).isEqualTo(command1.start)
        Assertions.assertThat(commands[0].movementInputs).isEqualTo(command1.movementInputs)
        Assertions.assertThat(commands[1].start).isEqualTo(command2.start)
        Assertions.assertThat(commands[1].movementInputs).isEqualTo(command2.movementInputs)
    }

    @Test
    @DisplayName("It creates the mars surface grid")
    fun itCreates2dMarsArray() {
        subject.invoke("""53
            1 1 E 
            RFRFRFRF
            """.trimIndent()
        )

        Assertions.assertThat(Mars.surfaceGrid).isEqualTo(Array(6) { IntArray(4) {it} })
    }

    @Test
    @DisplayName("Given unknown format it fails gracefully")
    fun itFailsWithNonsenseFormat() {
        assertThrows<Exception> { subject.invoke("""
            this is some random text
            that should fail to create commands
        """.trimIndent()
            )
        }
    }
}