package com.canker.news.ui.pages.home.model

import com.canker.core.networking.DataFetchResult
import io.reactivex.subjects.PublishSubject


interface HomeActivityContract {

    interface Repository {
        fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Local {
        //todo for localdb - room
    }

}
