package com.canker.core.networking

import io.reactivex.Scheduler


interface Scheduler {
    fun mainThread():Scheduler
    fun io():Scheduler
}
