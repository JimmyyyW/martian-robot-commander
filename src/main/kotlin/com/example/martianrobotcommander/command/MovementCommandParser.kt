package com.example.martianrobotcommander.command

import com.example.martianrobotcommander.robot.Location
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
            iter.next()
            movementCommands.add(MovementCommand(Location(), iter.next()))
        }

        return movementCommands
    }

}
