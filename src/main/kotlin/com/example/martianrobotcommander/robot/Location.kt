package com.example.martianrobotcommander.robot

data class Location(val x: Int, val y: Int, val orientation: Orientation) {
    override fun toString(): String {
        return "$x $y $orientation"
    }
}

enum class Orientation {
    N, S, E, W
}