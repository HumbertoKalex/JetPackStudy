package br.com.codechallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.codechallenge.BaseFragment
import br.com.codechallenge.R
import br.com.codechallenge.databinding.FragmentDetailBinding
import br.com.codechallenge.viewmodel.ProductListViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.sharedViewModel

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
        Glide.with(requireActivity()).load(viewModel.viewLiveData.url.value)
            .into(binding.imageProductDetail)
    }

    private fun setUpObservables() {
        viewModel.command.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ProductListViewCommand.OnBack -> findNavController().popBackStack()
            }
        })
    }
}