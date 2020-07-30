package br.com.codechallenge.data.local

data class ProductModel(
    val id: String,
    val title: String? = null,
    val subTitle: String? = null,
    val description: String? = null,
    val availableUnits: Int? = null,
    var savedUnits: Int? = null,
    val price: String? = null,
    val promoPrice: String? = null,
    val img: String? = null
)