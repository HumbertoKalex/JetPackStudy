package br.com.jetpack.view

import br.com.jetpack.data.local.ProductModel

/**
 *Created by humbertokalex
 */

sealed class ProductListViewCommand {
    object LoadingProducts : ProductListViewCommand()
    object ProductSaved : ProductListViewCommand()
    object ProductRemoved : ProductListViewCommand()
    object OnBack : ProductListViewCommand()
    object GoToDetail : ProductListViewCommand()
    object GoToSavedProduct : ProductListViewCommand()
    data class ProductsLoaded(val products: List<ProductModel>) : ProductListViewCommand()
    data class Error(val error: String?) : ProductListViewCommand()
}