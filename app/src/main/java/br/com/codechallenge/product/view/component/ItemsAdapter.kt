package br.com.codechallenge.product.view.component

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import br.com.codechallenge.R
import br.com.codechallenge.data.local.ProductModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_item.view.*
import kotlinx.android.synthetic.main.view_item_amount_button.view.*


class ItemsAdapter(
    private var items: List<ProductModel>
) : RecyclerView.Adapter<ItemsAdapter.ViewModel>() {

    var onAddClickListener: (ProductModel) -> Unit = {}
    var onEditClickListener: (ProductModel, Int) -> Unit = { _, _ -> }
    var onStockLevelMaxListener: (Int?) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
        return ViewModel(
            view
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        val item = items[position]
        var itemUnits = 0

        with(holder.itemView) {
            title.text = item.title
            subTitle.text = item.subTitle
            description.text = item.description
            Glide.with(context).load(item.img).into(imageProduct)

            if (item.promoPrice != null && item.price != null)
                setSpannableText(price, item.price, item.promoPrice)
            else
                price.text = item.price

            if (item.savedUnits != null)
                amountButton.amountTextView.text = item.savedUnits.toString()

            amountButton.setStockLevel(item.availableUnits ?: 0)
            amountButton.onAmountChangeListener = {
                itemUnits = it
                onEditClickListener(item, it)
            }
            amountButton.onStockLevelMaxListener = {
                Toast.makeText(context, "Max Units", Toast.LENGTH_SHORT).show()
                onStockLevelMaxListener(item.availableUnits)
            }

            addButton.setOnClickListener {
                item.savedUnits = itemUnits
                onAddClickListener(item)
                disableButton(addButton)
                amountButton.amountTextView.doAfterTextChanged { enableButton(addButton) }

            }

        }
    }

    class ViewModel(view: View) : RecyclerView.ViewHolder(view)

    private fun disableButton(addButton: AppCompatButton) {
        with(addButton) {
            isEnabled = false
            setCompoundDrawablesRelativeWithIntrinsicBounds(
                addButton.context.getDrawable(R.drawable.ic_check),
                null,
                null,
                null
            )
            text = ""
        }
    }

    private fun enableButton(addButton: AppCompatButton) {
        with(addButton) {
            isEnabled = true
            setCompoundDrawables(
                null,
                null,
                null,
                null
            )
            text = "Adicionar"
        }
    }

    private fun setSpannableText(textView: AppCompatTextView, price: String, promoPrice: String) {
        val text = SpannableString("$promoPrice $price")
        text.setSpan(
            ForegroundColorSpan(Color.GREEN), text.indexOf(promoPrice),
            text.indexOf(promoPrice) + promoPrice.length, 0
        )
        text.setSpan(
            StrikethroughSpan(),
            text.indexOf(price),
            text.indexOf(price) + price.length,
            0
        )
        textView.text = text
    }
}
