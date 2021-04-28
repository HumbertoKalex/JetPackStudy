package br.com.codechallenge.domain

import br.com.codechallenge.data.local.ProductMock
import br.com.codechallenge.data.local.ProductModel
import br.com.codechallenge.data.repository.ProductRepository
import br.com.codechallenge.extentions.toProductModel
import br.com.codechallenge.extentions.toProductsEntity
import br.com.codechallenge.extentions.toProductsModel

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