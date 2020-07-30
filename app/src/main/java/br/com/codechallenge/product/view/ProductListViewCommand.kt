package br.com.codechallenge.product.view

import br.com.codechallenge.data.local.ProductModel
import br.com.codechallenge.data.local.database.entity.ProductEntity
import br.com.codechallenge.data.local.database.entity.SavedProductEntity

sealed class ProductListViewCommand {
    object LoadingProducts: ProductListViewCommand()
    object ProductSaved: ProductListViewCommand()
    data class ProductsLoaded(val products: List<ProductModel>): ProductListViewCommand()
    data class Error(val error: String?): ProductListViewCommand()
}