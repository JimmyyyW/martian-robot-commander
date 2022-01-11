package com.example.martianrobotcommander

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MartianRobotCommanderApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<MartianRobotCommanderApplication>(*args)
        }

    }
}

