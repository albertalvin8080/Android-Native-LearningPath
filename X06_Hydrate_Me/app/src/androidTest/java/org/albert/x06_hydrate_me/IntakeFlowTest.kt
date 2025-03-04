package org.albert.x06_hydrate_me

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// THIS IS A UI TEST
@RunWith(AndroidJUnit4::class)
class IntakeFlowTest {
    @get:Rule
    var activityRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun test_intake_flow() {
        // 1. MainActivity
        onView(withId(R.id.fab)).perform(click())

        // 2. This occurs in the CreateEntryActivity
        onView(withId(R.id.input)).perform(typeText("1050"))
        onView(withId(R.id.addBtn)).perform(click())

        // 3. This checks the TextView in the MainActivity
        onView(withId(R.id.totalIntake)).check(matches(withText("1050")))
    }
}