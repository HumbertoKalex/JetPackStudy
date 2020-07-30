package br.com.codechallenge.extentions

import android.view.View
import br.com.codechallenge.data.local.ProductModel
import br.com.codechallenge.data.local.database.entity.ProductEntity
import br.com.codechallenge.data.local.database.entity.SavedProductEntity

fun View.isVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun List<ProductEntity>.toProductModel(): List<ProductModel> {
    return this.map { productEntity ->
        ProductModel(
            productEntity.id,
            productEntity.title,
            productEntity.subTitle,
            productEntity.description,
            productEntity.availableUnits,
            null,
            productEntity.price,
            productEntity.promoPrice,
            productEntity.img
        )
    }
}

fun List<SavedProductEntity>.toProductsModel(): List<ProductModel> {
    return this.map { productEntity ->
        ProductModel(
            productEntity.id,
            productEntity.title,
            productEntity.subTitle,
            productEntity.description,
            10,
            productEntity.unitsSaved,
            productEntity.price,
            null,
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