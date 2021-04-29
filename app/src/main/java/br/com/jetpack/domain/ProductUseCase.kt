package br.com.jetpack.domain

import br.com.jetpack.data.local.ProductMock
import br.com.jetpack.data.local.ProductModel
import br.com.jetpack.data.repository.ProductRepository
import br.com.jetpack.extentions.toProductModel
import br.com.jetpack.extentions.toProductsEntity
import br.com.jetpack.extentions.toProductsModel

class ProductUseCase(
    private val repository: ProductRepository
) {
    suspend fun getProducts(): List<ProductModel> {
        return try {
            val savedProducts = repository.getSavedProducts()

            if (savedProducts.isNullOrEmpty())
                repository.getProducts().toProductModel()
            else
                savedProducts.toProductsModel()
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    suspend fun saveProduct(productModel: ProductModel) {
        try {
            repository.saveProduct(productModel.toProductsEntity())
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    suspend fun initMock() {
        return try {
            repository.saveMockProduct(ProductMock.productMock)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }
}