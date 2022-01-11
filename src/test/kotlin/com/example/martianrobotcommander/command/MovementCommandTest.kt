package com.example.martianrobotcommander.command

import com.example.martianrobotcommander.mars.Mars
import com.example.martianrobotcommander.robot.Location
import com.example.martianrobotcommander.robot.Orientation
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
internal class MovementCommandTest {

    @Test
    @DisplayName("given a known mars surface it can traverse grid twice North")
    fun itShouldTraverseNorth() {
        Mars.surfaceGrid = Array(10) { IntArray(10) {it} }
        val command = MovementCommand(Location(0, 0, Orientation.N), "FF")

        val endLocation = "0 2 N"

        assertEquals(endLocation, command.invoke().toString())
    }

    @Test
    @DisplayName("it should return lost if it goes out of bounds")
    fun itShouldFailGracefully() {
        Mars.surfaceGrid = Array(6) { IntArray(4) {it} }
        val command = MovementCommand(Location(3, 2, Orientation.N), "FRRFLLFFRRFLL")

        val endLocation = "3 3 N LOST"

        assertEquals(endLocation, command.invoke().toString())
        assertEquals(MovementCommand.scents[0], Location(3, 3, Orientation.N))
    }

    @Test
    @DisplayName("it should use scent to path correctly")
    fun itShouldNotGetLost() {
        Mars.surfaceGrid = Array(6) { IntArray(4) {it} }

        val command1 = MovementCommand(Location(3, 2, Orientation.N), "FRRFLLFFRRFLL")
        command1.invoke()

        val command2 = MovementCommand(Location(0, 3, Orientation.W), "LLFFFLFLFL")

        val endLocation = "2 3 S"

        assertEquals(endLocation, command2.invoke().toString())
    }

}

