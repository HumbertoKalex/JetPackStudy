package br.com.jetpack.data.repository

import br.com.jetpack.data.local.database.DataBase
import br.com.jetpack.data.local.database.entity.ProductEntity
import br.com.jetpack.data.local.database.entity.SavedProductEntity

/**
 *Created by humbertokalex
 */

class ProductRepositoryImpl(private val dataBase: DataBase) : ProductRepository {

    override suspend fun getProducts() = dataBase.productDao().getAllProducts()

    override suspend fun getSavedProducts() = dataBase.productDao().getAllSaved()

    override suspend fun saveProduct(savedProductEntity: SavedProductEntity) =
        dataBase.productDao().insertSavedProduct(savedProductEntity)

    override suspend fun removeProduct(savedProductEntity: SavedProductEntity) =
        dataBase.productDao().removeSavedProduct(savedProductEntity.id)

    override suspend fun saveMockProduct(productEntity: List<ProductEntity>) =
        dataBase.productDao().insertMock(productEntity)
}