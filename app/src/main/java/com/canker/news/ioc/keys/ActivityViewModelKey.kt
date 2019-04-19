package com.canker.news.ioc.keys

import com.canker.news.ui.base.viewmodel.BaseActivityViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@MustBeDocumented
annotation class ActivityViewModelKey(val value: KClass<out BaseActivityViewModel>)