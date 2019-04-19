package com.canker.core.ioc.modules

import android.content.Context
import com.canker.core.BuildConfig
import com.canker.core.ioc.qualifiers.ApplicationContext
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import dagger.Module
import dagger.Provides
import io.fabric.sdk.android.Fabric
import javax.inject.Singleton


@Module
object CrashlyticsModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideCrashlyticsCore(): CrashlyticsCore =
        CrashlyticsCore.Builder()
            .disabled(BuildConfig.DEBUG)
            .build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideCrashlytics(crashlyticsCore: CrashlyticsCore): Crashlytics =
        Crashlytics.Builder().core(crashlyticsCore).build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideFabric(@ApplicationContext context: Context, crashlytics: Crashlytics): Fabric =
        Fabric.with(context, crashlytics)
}