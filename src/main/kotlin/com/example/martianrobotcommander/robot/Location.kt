package com.example.martianrobotcommander.robot

data class Location(val x: Int, val y: Int, val orientation: Orientation)

enum class Orientation {
    N, S, E, W
}