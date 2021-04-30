package br.com.jetpack.view.component

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.jetpack.data.local.ProductModel
import br.com.jetpack.databinding.ViewItemBinding
import br.com.jetpack.extentions.setPromoSpannableText
import br.com.jetpack.viewmodel.ProductListViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_item_amount_button.view.*

/**
 *Created by humbertokalex
 */

class ItemsAdapter(
    private val viewModelProduct: ProductListViewModel,
    private var items: List<ProductModel>
) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    var onEditClickListener: (ProductModel, Int) -> Unit = { _, _ -> }
    var onStockLevelMaxListener: (Int?) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productModel = items[position]
        holder.bind(productModel)
    }

    inner class ViewHolder(private val binding: ViewItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productModel: ProductModel) {
            with(binding) {
                viewModel = viewModelProduct
                binding.productModel = productModel

                Glide.with(context).load(productModel.img).into(imageProduct)

                if (productModel.promoPrice != null && productModel.price != null)
                    price.setPromoSpannableText(productModel.price, productModel.promoPrice)
                else
                    price.text = productModel.price

                amountButton.setStockLevel(productModel.availableUnits ?: 0)

                amountButton.onAmountChangeListener = {
                    productModel.savedUnits = it
                    binding.productModel = productModel
                    onEditClickListener(productModel, it)
                }

                amountButton.onStockLevelMaxListener = {
                    Toast.makeText(
                        context,
                        "Max Units",
                        Toast.LENGTH_SHORT
                    ).show()
                    onStockLevelMaxListener(productModel.availableUnits)
                }
            }
        }
    }

}
