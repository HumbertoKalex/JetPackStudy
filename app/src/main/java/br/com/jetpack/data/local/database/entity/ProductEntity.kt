package br.com.jetpack.data.local.database.entity

import androidx.annotation.DrawableRes
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
    val title: String,
    @SerializedName("SubTitle")
    val subTitle: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("AvailableUnits")
    val availableUnits: Int,
    @SerializedName("Price")
    val price: String,
    @SerializedName("PromoPrice")
    val promoPrice: String,
    @SerializedName("Img")
    @DrawableRes val img: Int
):Serializable
