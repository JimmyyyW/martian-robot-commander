package com.example.martianrobotcommander.command

import com.example.martianrobotcommander.robot.Location
import com.example.martianrobotcommander.robot.Orientation
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class MovementCommandParser : CommandParser<List<MovementCommand>> {

    @Throws(Exception::class)
    override fun invoke(text: String): List<MovementCommand> {

        val newLineSplit = text.split("\n")
        val iter = newLineSplit.iterator()
        val marsGrid = iter.next()

        val movementCommands = mutableListOf<MovementCommand>()

        while (iter.hasNext()) {
            val locationString = iter.next().replace(" ", "")
            val location = Location(
                locationString[0].digitToInt(),
                locationString[1].digitToInt(),
                Orientation.valueOf(locationString[2].toString())
            )
            movementCommands.add(MovementCommand(location, iter.next().replace(" ", "")))
        }

        return movementCommands
    }

}
