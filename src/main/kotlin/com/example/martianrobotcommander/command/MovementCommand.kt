package com.example.martianrobotcommander.command

import com.example.martianrobotcommander.robot.Location

class MovementCommand(val start: Location, val movementInputs: String) : Command<Location> {
    override fun invoke(): Location {
        TODO("Not yet implemented")
    }
}