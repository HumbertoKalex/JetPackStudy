package br.com.codechallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.codechallenge.R
import br.com.codechallenge.data.local.ProductModel
import br.com.codechallenge.databinding.FragmentProductListBinding
import br.com.codechallenge.extentions.isVisible
import br.com.codechallenge.BaseFragment
import br.com.codechallenge.view.component.ItemsAdapter
import br.com.codechallenge.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_product_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : BaseFragment() {

    private val viewModel by sharedViewModel<ProductListViewModel>()
    private lateinit var binding: FragmentProductListBinding
    private lateinit var adapter: ItemsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getDataBinding(inflater, container, R.layout.fragment_product_list)
        setupObservers()
        viewModel.initMock()
        viewModel.loadProducts()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.command.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ProductListViewCommand.LoadingProducts -> showLoad()

                is ProductListViewCommand.ProductSaved -> { }

                is ProductListViewCommand.ProductsLoaded -> {
                    hideLoad()
                    setUpAdapter(it.products)
                }

                is ProductListViewCommand.Error -> {
                    hideLoad()
                    showError(it.error)
                }

                is ProductListViewCommand.GoToDetail -> findNavController().navigate(R.id.action_listFragment_to_detailFragment)
            }
        })
    }

    private fun setUpAdapter(products: List<ProductModel>) {
        adapter =
            ItemsAdapter(viewModel,products.sortedBy { it.title })
        recyclerProducts.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.onEditClickListener = { _, _ ->

        }
        adapter.onStockLevelMaxListener = {

        }
    }

    private fun showError(error: String?) {
        Toast.makeText(requireContext(), error ?: "Error", Toast.LENGTH_SHORT).show()
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

