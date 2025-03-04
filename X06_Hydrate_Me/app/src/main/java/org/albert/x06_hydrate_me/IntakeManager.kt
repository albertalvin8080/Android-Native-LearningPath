package org.albert.x06_hydrate_me

object IntakeManager {
    private val intakes = mutableListOf<Intake>()

    public fun addIntake(intake: Int) {
        intakes += Intake(intake)
    }

    public fun getAboveMinimum(): Boolean {
        return (intakes.map { i -> i.value }.reduceOrNull { a, b -> a + b } ?: 0) >= 3000
    }

    public fun getTotalIntake(): Int {
        return (intakes.map { i -> i.value }.reduceOrNull { a, b -> a + b } ?: 0)
    }
}

class Intake(val value: Int)