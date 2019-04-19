package com.canker.news.utils

import com.canker.core.extensions.getMethodTag
import org.junit.Assert.assertEquals
import org.junit.Test

class AnyTest {

    @Test
    fun testGetMethodTag() {
        assertEquals("AnyTestgetMethodTag", getMethodTag())
    }
}