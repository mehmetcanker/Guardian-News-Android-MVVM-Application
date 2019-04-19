package com.canker.data.enums

enum class ServiceType(s: String) {

    SEARCH("search"),
    TAG("tag");

    fun getServiceType(type: ServiceType): String {
        return type.name
    }
}