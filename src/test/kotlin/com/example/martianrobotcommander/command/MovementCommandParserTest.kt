package com.example.martianrobotcommander.command

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MovementCommandParserTest {

    private val subject = MovementCommandParser()

    @Test
    @DisplayName("it extracts the planetary surface of mars")
    fun itShouldSetMarsSurfaceGrid() {
        val commands = subject.invoke(
            """
            53
            1 1 E 
            RFRFRFRF
            3 2 N
            FRRFLLFFRRFLL
            0 3 W LLFFFLFLFL"""
        )

        Assertions.assertThat(commands).isNotEmpty
        Assertions.assertThat(commands.size).isEqualTo(3)

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