package com.example.martianrobotcommander.command

import com.example.martianrobotcommander.mars.Mars
import com.example.martianrobotcommander.robot.Location
import com.example.martianrobotcommander.robot.Orientation

class MovementCommand(val start: Location, val movementInputs: String) : Command<Location> {

    companion object {
        var scents: MutableList<Location> = mutableListOf()
    }

    override fun invoke(): Location {
        if (Mars.surfaceGrid == null) {
            throw Exception("We must know the grid surface of Mars to move our robots! >:( ")
        } else {
            val robotLocation = start
            for (char in movementInputs) {
                if (char == ' ' || scents.contains(robotLocation) && char == 'F') {
                    continue
                }
                if (Mars.surfaceGrid!![0].size - 1 < robotLocation.y || Mars.surfaceGrid!!.size - 1 < robotLocation.x) {
                    getLastKnownLocation(robotLocation)
                    robotLocation.locationUnknown = true
                    scents.add(robotLocation)
                    return robotLocation

                }
                when (char) {
                    'R' -> robotLocation.orientation = right(robotLocation.orientation)
                    'L' -> robotLocation.orientation = left(robotLocation.orientation)
                    'F' -> moveLocation(robotLocation)
                }

            }
            return robotLocation
        }
    }

    private fun getLastKnownLocation(current: Location) {
        when (current.orientation) {
            Orientation.N -> current.y--
            Orientation.S -> current.y++
            Orientation.E -> current.x--
            Orientation.W -> current.x++
        }
    }

    private fun moveLocation(current: Location) {
        when (current.orientation) {
            Orientation.N -> current.y++
            Orientation.S -> current.y--
            Orientation.E -> current.x++
            Orientation.W -> current.x--
        }
    }

    private fun left(orientation: Orientation): Orientation {
        return when (orientation) {
            Orientation.N -> Orientation.W
            Orientation.S -> Orientation.E
            Orientation.E -> Orientation.N
            Orientation.W -> Orientation.S
        }
    }

    private fun right(orientation: Orientation): Orientation {
        return when (orientation) {
            Orientation.W -> Orientation.N
            Orientation.E -> Orientation.S
            Orientation.N -> Orientation.E
            Orientation.S -> Orientation.W
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovementCommand

        if (start != other.start) return false
        if (movementInputs != other.movementInputs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + movementInputs.hashCode()
        return result
    }

}
