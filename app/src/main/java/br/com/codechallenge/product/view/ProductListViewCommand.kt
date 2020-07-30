package br.com.codechallenge.product.view

import br.com.codechallenge.data.local.ProductModel

sealed class ProductListViewCommand {
    object LoadingProducts : ProductListViewCommand()
    object ProductSaved : ProductListViewCommand()
    data class ProductsLoaded(val products: List<ProductModel>) : ProductListViewCommand()
    data class Error(val error: String?) : ProductListViewCommand()
}