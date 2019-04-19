package com.canker.data.enums

enum class FieldType(name: kotlin.String) {

    ALL("ALL"),
    BODY("BODY"),
    THUMBNAIL("thumbnail");

    fun getName(fieldType: FieldType): String {
        return fieldType.name
    }
}