package com.bingjunior.fastestcars

import java.io.Serializable

data class CarModel(val imageResId: Int,
                    val name: String,
                    val description: String,
                    val url: String,
                    var text: String = "") : Serializable
