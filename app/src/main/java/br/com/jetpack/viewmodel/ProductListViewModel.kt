package br.com.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jetpack.data.SafeResponse
import br.com.jetpack.data.local.ProductModel
import br.com.jetpack.data.safeRequest
import br.com.jetpack.domain.ProductUseCase
import br.com.jetpack.domain.ProductUseCaseImpl
import br.com.jetpack.view.ProductListViewCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 *Created by humbertokalex
 */

/*
    Aqui eu uso apenas uma view model pela simplicidade das telas.
    Em um meio mais real, cada fragmento teria sua view model, ou cada fluxo teria sua view model.
    Tudo depende da complexidade do seu objetivo e do princípio de reposabilidade única.
 */

class ProductListViewModel(
    private val useCase: ProductUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO + SupervisorJob()
) : ViewModel() {

    private val mutableCommand = MutableLiveData<ProductListViewCommand>()
    val command: LiveData<ProductListViewCommand> = mutableCommand

    var viewLiveData: ViewLiveData = ViewLiveData()

    /*
        Esta classe é um "encapsulador" de live data que fiz para o xml ter visibilidade
    das variaveis que eu quero que ele acompanhe direto da viewModel.
    */
    inner class ViewLiveData {
        val productDetailModel: MutableLiveData<ProductModel> = MutableLiveData()
    }

    fun initMock() = viewModelScope.launch(dispatcher) {
            when (val response = safeRequest { useCase.initMock() }) {
                is SafeResponse.Success -> { }
                is SafeResponse.GenericError -> ProductListViewCommand.Error(
                    response.error?.message()).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(null).run()
            }
        }

    fun loadProducts() = viewModelScope.launch(dispatcher) {
            ProductListViewCommand.LoadingProducts.run()
            when (val response = safeRequest { useCase.getProducts() }) {
                is SafeResponse.Success -> ProductListViewCommand.ProductsLoaded(
                    response.value).run()
                is SafeResponse.GenericError -> ProductListViewCommand.Error(
                    response.error?.message()).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(null).run()
            }
        }

    fun loadSavedProducts() = viewModelScope.launch(dispatcher) {
        ProductListViewCommand.LoadingProducts.run()
        when (val response = safeRequest { useCase.getSavedProducts() }) {
            is SafeResponse.Success -> ProductListViewCommand.ProductsLoaded(
                response.value).run()
            is SafeResponse.GenericError -> ProductListViewCommand.Error(
                response.error?.message()).run()
            is SafeResponse.NetworkError -> ProductListViewCommand.Error(null).run()
        }
    }

    fun saveProduct(productModel: ProductModel) = viewModelScope.launch(dispatcher) {
            when (val response = safeRequest { useCase.saveProduct(productModel) }) {
                is SafeResponse.Success -> ProductListViewCommand.ProductSaved.run()
                is SafeResponse.GenericError -> ProductListViewCommand.Error(
                    response.error?.message()).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(null).run()
            }
        }

    fun removeProduct(productModel: ProductModel) = viewModelScope.launch(dispatcher) {
            when (val response = safeRequest { useCase.removeProduct(productModel) }) {
                is SafeResponse.Success -> ProductListViewCommand.ProductRemoved.run()
                is SafeResponse.GenericError -> ProductListViewCommand.Error(
                    response.error?.message()).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(null).run()
            }
        }

    fun goToDetail(productModel: ProductModel) = run {
        viewLiveData.productDetailModel.value = productModel
        ProductListViewCommand.GoToDetail.run()
    }

    fun goToSavedProducts() = ProductListViewCommand.GoToSavedProduct.run()

    fun onBack() = ProductListViewCommand.OnBack.run()

    private fun ProductListViewCommand.run() = mutableCommand.postValue(this)

}