package br.com.jetpack.domain

import br.com.jetpack.data.local.ProductModel

interface ProductUseCase {

    suspend fun getProducts(): List<ProductModel>

    suspend fun getSavedProducts(): List<ProductModel>

    suspend fun saveProduct(productModel: ProductModel)

    suspend fun removeProduct(productModel: ProductModel)

    suspend fun initMock()

}
