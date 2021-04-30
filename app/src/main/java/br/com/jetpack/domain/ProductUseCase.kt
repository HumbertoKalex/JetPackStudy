package br.com.jetpack.domain

import br.com.jetpack.data.local.ProductMock
import br.com.jetpack.data.local.ProductModel
import br.com.jetpack.data.repository.ProductRepository
import br.com.jetpack.extentions.toProductModel
import br.com.jetpack.extentions.toProductsEntity
import br.com.jetpack.extentions.savedToProductModel

/**
 *Created by humbertokalex
 */

class ProductUseCase(private val repository: ProductRepository) {

    suspend fun getProducts(): List<ProductModel> = try {
        repository.getProducts().toProductModel()
    } catch (throwable: Throwable) {
        throw throwable
    }

    suspend fun getSavedProducts(): List<ProductModel> = try {
        repository.getSavedProducts().savedToProductModel()
    } catch (throwable: Throwable) {
        throw throwable
    }

    suspend fun saveProduct(productModel: ProductModel) = try {
        repository.saveProduct(productModel.toProductsEntity())
    } catch (throwable: Throwable) {
        throw throwable
    }

    suspend fun removeProduct(productModel: ProductModel) = try {
        repository.removeProduct(productModel.toProductsEntity())
    } catch (throwable: Throwable) {
        throw throwable
    }

    suspend fun initMock() = try {
        repository.saveMockProduct(ProductMock.productMock)
    } catch (throwable: Throwable) {
        throw throwable
    }

}