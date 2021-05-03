package br.com.jetpack.view

import br.com.jetpack.data.local.ProductModel

/**
 *Created by humbertokalex
 */

/*
    Aqui é uma classe de action, que serve para o fragmento/activity observar os comandos da view model.
    Em um meio mais complexo costuma-se usar também a mesma estrutura de classe com o nome de ViewState;
    É usada em casos onde a view sofre muitas mudanças programaticamente,
    desse modo o view state fica reponsável pelas mudanças da view(Ex: ShowLoad, ChangeButtonColor),
    e o command/action fica responsável pelos comandos ativos(Ex: GoToDetail, ProductSaved).
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