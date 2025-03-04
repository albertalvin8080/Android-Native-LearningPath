package org.albert.x06_hydrate_me

import com.google.common.truth.Truth
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class IntakeManagerTest {
    private val manager = IntakeManager

    @Before
    fun setUp() {
        manager.addIntake(300)
        manager.addIntake(500)
        manager.addIntake(900)
        manager.addIntake(1300)
    }

    @Test
    fun getTotalIntake_ReturnsTotalIntake_WhenSuccessful() {
        Assert.assertEquals(3000, manager.getTotalIntake())
    }

    @Test
    fun getAboveMinimum_ReturnsTrue_WhenSuccessful() {
        Assert.assertTrue(manager.getAboveMinimum())
    }
}