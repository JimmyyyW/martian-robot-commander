package com.example.martianrobotcommander.command

import com.example.martianrobotcommander.robot.Location
import com.example.martianrobotcommander.robot.Orientation

class MovementCommand(val start: Location, val movementInputs: String) : Command<Location> {

    override fun invoke(): Location {


        return Location(1, 2, Orientation.E)
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
