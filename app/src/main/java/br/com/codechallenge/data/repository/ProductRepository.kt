package br.com.codechallenge.data.repository

import br.com.codechallenge.data.local.database.DataBase
import br.com.codechallenge.data.local.database.entity.ProductEntity
import br.com.codechallenge.data.local.database.entity.SavedProductEntity

class ProductRepository(
    private val dataBase: DataBase
) {

    suspend fun getProducts() = dataBase.productDao().getAllProducts()

    suspend fun getSavedProducts() = dataBase.productDao().getAllSaved()

    suspend fun saveProduct(savedProductEntity: SavedProductEntity) =
        dataBase.productDao().insertSavedProduct(savedProductEntity)

    suspend fun saveMockProduct(productEntity: List<ProductEntity>) =
        dataBase.productDao().insertMock(productEntity)
}