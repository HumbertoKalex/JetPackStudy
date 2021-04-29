package br.com.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jetpack.data.SafeResponse
import br.com.jetpack.data.local.ProductModel
import br.com.jetpack.data.safeRequest
import br.com.jetpack.domain.ProductUseCase
import br.com.jetpack.view.ProductListViewCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProductListViewModel(
    private val useCase: ProductUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO + SupervisorJob()
) : ViewModel() {

    private val mutableCommand = MutableLiveData<ProductListViewCommand>()
    val command: LiveData<ProductListViewCommand> = mutableCommand

    var viewLiveData: ViewLiveData = ViewLiveData()

    inner class ViewLiveData {
        val name: MutableLiveData<String> = MutableLiveData("")
        val url: MutableLiveData<String> = MutableLiveData("")
    }

    fun initMock() {
        viewModelScope.launch(dispatcher) {
            when (val response = safeRequest { useCase.initMock() }) {
                is SafeResponse.Success -> {
                }
                is SafeResponse.GenericError -> ProductListViewCommand.Error(
                    response.error?.message()
                )
                    .run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(
                    null
                ).run()
            }
        }
    }

    fun loadProducts() {
        viewModelScope.launch(dispatcher) {
            ProductListViewCommand.LoadingProducts.run()
            when (val response = safeRequest { useCase.getProducts() }) {
                is SafeResponse.Success -> ProductListViewCommand.ProductsLoaded(
                    response.value
                ).run()
                is SafeResponse.GenericError -> ProductListViewCommand.Error(
                    response.error?.message()
                ).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(
                    null
                ).run()
            }
        }
    }

    fun saveProduct(productModel: ProductModel) {
        viewModelScope.launch(dispatcher) {
            when (val response = safeRequest { useCase.saveProduct(productModel) }) {
                is SafeResponse.Success -> ProductListViewCommand.ProductSaved.run()
                is SafeResponse.GenericError -> ProductListViewCommand.Error(
                    response.error?.message()
                ).run()
                is SafeResponse.NetworkError -> ProductListViewCommand.Error(
                    null
                ).run()
            }
        }
    }

    fun goToDetail(productModel: ProductModel) = run {
        viewLiveData.name.value = productModel.title
        viewLiveData.url.value = productModel.img
        ProductListViewCommand.GoToDetail(productModel).run()
    }

    fun onBack() = ProductListViewCommand.OnBack.run()

    private fun ProductListViewCommand.run() {
        mutableCommand.postValue(this)
    }

}