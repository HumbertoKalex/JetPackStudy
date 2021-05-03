package br.com.jetpack.view.component

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.jetpack.R
import kotlinx.android.synthetic.main.view_item_amount_button.view.*

/**
 *Created by humbertokalex
 */

class ItemAmountButton : ConstraintLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var onAmountChangeListener: (Int) -> Unit = {}
    var onStockLevelMaxListener: () -> Unit = {}

    private var stockLevel: Int = 0

    init {
        inflate(context, R.layout.view_item_amount_button, this)
        setupClicks()
    }

    private fun setupClicks() {
        decreaseButton.setOnClickListener {
            val amount = amountTextView.text.toString().toIntOrNull()
            amount?.takeIf { it > MIN_VALUE }?.let {
                val amountDecrease = it - INCREASE_AMOUNT
                amountTextView.text = amountDecrease.toString()
                onAmountChangeListener(amountDecrease)
            }
        }

        increaseButton.setOnClickListener {
            val amount = amountTextView.text.toString().toIntOrNull()
            amount?.takeIf { it < stockLevel }?.let {
                val amountIncrease = it + INCREASE_AMOUNT
                amountTextView.text = amountIncrease.toString()
                onAmountChangeListener(amountIncrease)
            }
            amount?.takeIf { it >= stockLevel }?.let {
                onStockLevelMaxListener()
            }
        }
    }

    fun setStockLevel(stockLevel: Int = 0) {
        this.stockLevel = stockLevel
    }

    private companion object {
        private const val MIN_VALUE = 1
        private const val INCREASE_AMOUNT = 1
    }
}
