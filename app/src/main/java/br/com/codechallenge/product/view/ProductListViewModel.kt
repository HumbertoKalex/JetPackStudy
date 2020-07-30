package br.com.codechallenge.product.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.codechallenge.data.SafeResponse
import br.com.codechallenge.data.local.ProductModel
import br.com.codechallenge.data.safeRequest
import br.com.codechallenge.product.domain.ProductUseCase
import br.com.codechallenge.product.view.ProductListViewCommand
import br.com.codechallenge.product.view.ProductListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProductListViewModel(
    private val useCase: ProductUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO + SupervisorJob()
): ViewModel() {

    private val mutableViewState = MutableLiveData<ProductListViewState>()
    val viewState: LiveData<ProductListViewState> = mutableViewState

    private val mutableCommand = MutableLiveData<ProductListViewCommand>()
    val command: LiveData<ProductListViewCommand> = mutableCommand

    private fun ProductListViewCommand.run() {
        mutableCommand.postValue(this)
    }

    fun initMock(){
        viewModelScope.launch(dispatcher) {
            when (val response = safeRequest { useCase.initMock() }) {
                is SafeResponse.Success -> {}
                is SafeResponse.GenericError -> ProductListViewCommand.Error(response.error?.message()).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(null).run()
            }
        }
    }

    fun loadProducts() {
        viewModelScope.launch(dispatcher) {
            ProductListViewCommand.LoadingProducts.run()
            when (val response = safeRequest { useCase.getProducts() }) {
                is SafeResponse.Success -> ProductListViewCommand.ProductsLoaded(response.value).run()
                is SafeResponse.GenericError -> ProductListViewCommand.Error(response.error?.message()).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(null).run()
            }
        }
    }

    fun saveProduct(productModel: ProductModel) {
        viewModelScope.launch(dispatcher) {
            when (val response = safeRequest { useCase.saveProduct(productModel) }) {
                is SafeResponse.Success -> ProductListViewCommand.ProductSaved.run()
                is SafeResponse.GenericError -> ProductListViewCommand.Error(response.error?.message()).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(null).run()
            }
        }
    }

}