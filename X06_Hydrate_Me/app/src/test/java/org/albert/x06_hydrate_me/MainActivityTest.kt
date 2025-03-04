package org.albert.x06_hydrate_me

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

// We tell the test to run with AndroidJUnit and not normal JUnit.
@RunWith(AndroidJUnit4::class)
@Config(maxSdk = 29)
class MainActivityTest {
    // This here is provided by Roboletric dependency.
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun verify_done_string() {
        Assert.assertEquals(
            "You're done for today.",
            context.getString(R.string.done)
        )
    }
}