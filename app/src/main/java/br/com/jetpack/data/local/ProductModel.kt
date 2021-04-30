package br.com.jetpack.data.local

/**
 *Created by humbertokalex
 */

data class ProductModel(
    val id: String,
    val title: String,
    val subTitle: String,
    val description: String,
    val availableUnits: Int,
    var savedUnits: Int,
    val price: String,
    val promoPrice: String,
    val img: String
)