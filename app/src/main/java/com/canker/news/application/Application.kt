package com.canker.news.application

import com.evernote.android.state.StateSaver
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

open class Application : DaggerApplication() {

    @Inject
    protected lateinit var timberTree: Timber.Tree

    private val applicationInjector =
        DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(timberTree)

//        if (LeakCanary.isInAnalyzerProcess(this))
//            return
//
//        LeakCanary.install(this)

        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true)
    }

    override fun applicationInjector() = applicationInjector

}

