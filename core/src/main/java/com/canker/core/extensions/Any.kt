package com.canker.core.extensions

fun Any.getClassTag(): String = this.javaClass.simpleName

fun Any.getMethodTag(): String =
    getClassTag() + object : Any() {}.javaClass.enclosingMethod?.name
