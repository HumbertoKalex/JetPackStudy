package br.com.jetpack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.jetpack.R
import br.com.jetpack.data.local.ProductModel
import br.com.jetpack.databinding.FragmentProductListBinding
import br.com.jetpack.extentions.isVisible
import br.com.jetpack.BaseFragment
import br.com.jetpack.view.component.SavedItemsAdapter
import br.com.jetpack.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_product_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 *Created by humbertokalex
 */

class ProductSavedListFragment : BaseFragment() {

    private val viewModel by sharedViewModel<ProductListViewModel>()
    private lateinit var binding: FragmentProductListBinding
    private lateinit var adapter: SavedItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getDataBinding(inflater, container, R.layout.fragment_product_list)
        setupObservers()
        viewModel.loadSavedProducts()
        return binding.root
    }

    private fun setupObservers() {
        binding.btnSavedProducts.isVisible(false)
        viewModel.command.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ProductListViewCommand.LoadingProducts -> showLoad()

                is ProductListViewCommand.ProductRemoved -> {
                    showLoad()
                    viewModel.loadSavedProducts()
                }

                is ProductListViewCommand.ProductsLoaded -> {
                    hideLoad()
                    setUpAdapter(it.products)
                }

                is ProductListViewCommand.Error -> {
                    hideLoad()
                    showToast(it.error)
                }

                is ProductListViewCommand.GoToDetail -> findNavController().navigate(R.id.action_savedListFragment_to_detailFragment)
            }
        })
    }

    private fun setUpAdapter(products: List<ProductModel>) {
        adapter = SavedItemsAdapter(viewModel,products.sortedBy { it.title })
        recyclerProducts.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg ?: "Saved Products not found!", Toast.LENGTH_SHORT).show()
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

