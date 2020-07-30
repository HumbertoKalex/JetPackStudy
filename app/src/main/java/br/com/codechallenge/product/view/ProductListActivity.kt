package br.com.codechallenge.product.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.codechallenge.R
import br.com.codechallenge.data.local.ProductModel
import br.com.codechallenge.extentions.isVisible
import br.com.codechallenge.product.view.component.ItemsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModel<ProductListViewModel>()
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.pedido_facil)
        setupObservers()
        viewModel.initMock()
        viewModel.loadProducts()
    }

    private fun setupObservers() {
        viewModel.command.observe(this, Observer {
            when (it) {
                is ProductListViewCommand.LoadingProducts -> showLoad()

                is ProductListViewCommand.ProductSaved -> {
                }

                is ProductListViewCommand.ProductsLoaded -> {
                    hideLoad()
                    setUpAdapter(it.products)
                }

                is ProductListViewCommand.Error -> {
                    hideLoad()
                    showError(it.error)
                }
            }
        })
    }

    private fun setUpAdapter(products: List<ProductModel>) {
        adapter = ItemsAdapter(products.sortedBy { it.title })
        recyclerProducts.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.onAddClickListener = {
            viewModel.saveProduct(it)
        }
        adapter.onEditClickListener = { _, _ ->

        }
        adapter.onStockLevelMaxListener = {

        }
    }

    private fun showError(error: String?) {
        Toast.makeText(applicationContext, error ?: "Error", Toast.LENGTH_SHORT).show()
    }

    private fun showLoad() {
        recyclerProducts.isVisible(false)
        progressBar.isVisible(true)
    }

    private fun hideLoad() {
        recyclerProducts.isVisible(true)
        progressBar.isVisible(false)
    }
}

