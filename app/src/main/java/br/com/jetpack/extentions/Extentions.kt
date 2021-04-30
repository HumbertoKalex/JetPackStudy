package br.com.jetpack.extentions

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import br.com.jetpack.data.local.ProductModel
import br.com.jetpack.data.local.database.entity.ProductEntity
import br.com.jetpack.data.local.database.entity.SavedProductEntity

/**
 *Created by humbertokalex
 */

fun View.isVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun Int.toString():String = this.toString()

fun AppCompatTextView.setPromoSpannableText(price: String, promoPrice: String) {
    val text = SpannableString("$promoPrice $price")
    text.setSpan(
        ForegroundColorSpan(Color.GREEN), text.indexOf(promoPrice),
        text.indexOf(promoPrice) + promoPrice.length, 0
    )
    text.setSpan(
        StrikethroughSpan(),
        text.indexOf(price),
        text.indexOf(price) + price.length,
        0
    )
    this.text = text
}

fun List<ProductEntity>.toProductModel(): List<ProductModel> {
    return this.map { productEntity ->
        ProductModel(
            productEntity.id,
            productEntity.title,
            productEntity.subTitle,
            productEntity.description,
            productEntity.availableUnits,
            0,
            productEntity.price,
            productEntity.promoPrice,
            productEntity.img
        )
    }
}

fun List<SavedProductEntity>.savedToProductModel(): List<ProductModel> {
    return this.map { productEntity ->
        ProductModel(
            productEntity.id,
            productEntity.title,
            productEntity.subTitle,
            productEntity.description,
            10,
            productEntity.unitsSaved,
            productEntity.price,
            "",
            productEntity.img
        )
    }
}

fun ProductModel.toProductsEntity(): SavedProductEntity {
    return SavedProductEntity(
        this.id,
        this.title,
        this.subTitle,
        this.description,
        this.savedUnits,
        this.price,
        this.img
    )
}