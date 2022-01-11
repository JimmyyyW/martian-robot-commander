package com.example.martianrobotcommander.command

typealias Command<T> = () -> T

typealias CommandParser<T> = (text: String) -> T

