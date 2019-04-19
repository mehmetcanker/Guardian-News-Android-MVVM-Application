package com.canker.data.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [(Index("Id"))])
data class Color(
	@SerializedName("Id") @PrimaryKey(autoGenerate = true) val Id: Int,
	@SerializedName("Text") val text: String?,
	@SerializedName("Code") val code: String?,
	@SerializedName("Value") val value: String?,
	var isSelected: Boolean
)
