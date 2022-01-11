package com.example.martianrobotcommander.robot

data class Location(var x: Int, var y: Int, var orientation: Orientation) {

    var locationUnknown: Boolean? = false

    override fun toString(): String {
        return if (locationUnknown == true) {
            "$x $y $orientation LOST"
        } else "$x $y $orientation"
    }
}

enum class Orientation {
    N, S, E, W
}