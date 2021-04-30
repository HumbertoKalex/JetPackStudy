package br.com.jetpack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.jetpack.BaseFragment
import br.com.jetpack.R
import br.com.jetpack.databinding.FragmentDetailBinding
import br.com.jetpack.viewmodel.ProductListViewModel
import com.bumptech.glide.Glide
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
        Glide.with(requireActivity()).load(viewModel.viewLiveData.productDetailModel.value?.img)
            .into(binding.imageProductDetail)
    }

    private fun setUpObservables() {
        viewModel.command.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ProductListViewCommand.OnBack -> findNavController().popBackStack()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.command.removeObservers(viewLifecycleOwner)
    }
}