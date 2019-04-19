package com.canker.core.extensions

import com.canker.core.utils.Synk
import io.reactivex.Single

fun <T> Single<T>.updateSynkStatus(key: String): Single<T> {
    return this.doOnSuccess { Synk.syncSuccess(key = key) }
        .doOnError { Synk.syncFailure(key = key) }
}
