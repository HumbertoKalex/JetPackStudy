package br.com.jetpack.view

import br.com.jetpack.data.local.ProductModel

sealed class ProductListViewCommand {
    object LoadingProducts : ProductListViewCommand()
    object ProductSaved : ProductListViewCommand()
    object OnBack : ProductListViewCommand()
    data class GoToDetail(val productModel: ProductModel) : ProductListViewCommand()
    data class ProductsLoaded(val products: List<ProductModel>) : ProductListViewCommand()
    data class Error(val error: String?) : ProductListViewCommand()
}