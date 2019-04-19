package com.canker.news.ui.pages.home.model

import com.canker.core.networking.Scheduler
import com.canker.news.ioc.modules.database.AppDatabase

class HomeActivityLocalData(private val appDb: AppDatabase, private val scheduler: Scheduler) :
    HomeActivityContract.Local {

    //todo room database

}
