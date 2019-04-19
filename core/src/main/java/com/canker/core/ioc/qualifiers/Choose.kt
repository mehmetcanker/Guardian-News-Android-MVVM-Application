package com.canker.core.ioc.qualifiers

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Choose(val value: String = "")