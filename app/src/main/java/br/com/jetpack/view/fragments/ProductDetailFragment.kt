package br.com.jetpack.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.jetpack.BaseFragment
import br.com.jetpack.R
import br.com.jetpack.databinding.FragmentDetailBinding
import br.com.jetpack.view.ProductListViewCommand
import br.com.jetpack.viewmodel.ProductListViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 *Created by humbertokalex
 */

class ProductDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by sharedViewModel<ProductListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getDataBinding(inflater, container, R.layout.fragment_detail)
        binding.viewModel = viewModel
        setUpObservables()
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        viewModel.viewLiveData.productDetailModel.value?.img?.let {
            binding.imageProductDetail.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), it)
            )
        }
    }

    private fun setUpObservables() {
        viewModel.command.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ProductListViewCommand.OnBack -> findNavController().popBackStack()
            }
        })
    }
}