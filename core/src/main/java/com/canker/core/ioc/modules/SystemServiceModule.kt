package com.canker.core.ioc.modules

import android.accounts.AccountManager
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.canker.core.ioc.qualifiers.ApplicationContext
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
object SystemServiceModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    @JvmStatic
    fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences =
        RxSharedPreferences.create(sharedPreferences)

    @Provides
    @Singleton
    @JvmStatic
    fun provideAccountManager(@ApplicationContext context: Context): AccountManager =
        AccountManager.get(context)

    @Provides
    @Singleton
    @JvmStatic
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

}