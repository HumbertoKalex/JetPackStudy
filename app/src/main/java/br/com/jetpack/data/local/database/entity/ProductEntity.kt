package br.com.jetpack.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    @SerializedName("Id")
    val id: String,
    @SerializedName("Title")
    val title: String? = null,
    @SerializedName("SubTitle")
    val subTitle: String? = null,
    @SerializedName("Description")
    val description: String? = null,
    @SerializedName("AvailableUnits")
    val availableUnits: Int? = null,
    @SerializedName("Price")
    val price: String? = null,
    @SerializedName("PromoPrice")
    val promoPrice: String? = null,
    @SerializedName("Img")
    val img: String? = null
):Serializable
