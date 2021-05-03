package br.com.jetpack.data.repository

import br.com.jetpack.data.local.database.entity.ProductEntity
import br.com.jetpack.data.local.database.entity.SavedProductEntity

interface ProductRepository {

    suspend fun getProducts(): List<ProductEntity>

    suspend fun getSavedProducts(): List<SavedProductEntity>

    suspend fun saveProduct(savedProductEntity: SavedProductEntity)

    suspend fun removeProduct(savedProductEntity: SavedProductEntity)

    suspend fun saveMockProduct(productEntity: List<ProductEntity>)

}