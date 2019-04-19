package com.canker.news.validations


import com.canker.core.extensions.PasswordValidator
import com.canker.core.networking.Scheduler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@Config(manifest = Config.NONE)
class ValidationUnitTest {

    @Before
    fun setUp() {

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun passwordValidator_CorrentPassowrd_ReturnsTrue() {
        assertTrue(PasswordValidator().validate("12345678"))
    }
}
