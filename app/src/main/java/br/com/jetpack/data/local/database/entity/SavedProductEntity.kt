package br.com.jetpack.data.local.database.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "savedProduct")
data class SavedProductEntity(
    @PrimaryKey
    @SerializedName("Id")
    val id: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("SubTitle")
    val subTitle: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("UnitsSaved")
    val unitsSaved: Int,
    @SerializedName("Price")
    val price: String,
    @SerializedName("Img")
    @DrawableRes val img: Int
):Serializable
