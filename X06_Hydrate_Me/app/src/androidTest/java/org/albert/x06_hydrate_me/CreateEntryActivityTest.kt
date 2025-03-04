package org.albert.x06_hydrate_me

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.core.app.launchActivityForResult
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

// THIS IS AN INSTRUMENTATION TEST
@RunWith(AndroidJUnit4::class)
class CreateEntryActivityTest {
    @Test
    fun test_result() {
//        val scenario = ActivityScenario.launch(CreateEntryActivity::class.java) // Does this work?
//        val scenario = launchActivity<CreateEntryActivity>() // IllegalStateException
        val scenario = launchActivityForResult<CreateEntryActivity>()

        onView(withId(R.id.input)).perform(typeText("1050"))
        onView(withId(R.id.addBtn)).perform(click())

        val rData = scenario.result.resultData
        val intake = rData.getIntExtra("intake", 0)

        Assert.assertEquals(1050, intake)
    }
}