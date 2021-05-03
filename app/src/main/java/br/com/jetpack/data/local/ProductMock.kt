package br.com.jetpack.data.local

import br.com.jetpack.R
import br.com.jetpack.data.local.database.entity.ProductEntity

/**
 *Created by humbertokalex
 */

object ProductMock {
    val productMock = listOf(
        ProductEntity(
            "12345",
            "Bebida",
            "1x24Unidade",
            "120ML CAN",
            3,
            "R$10.20",
            "R$1.20",
            R.drawable.img_beer1
        ),
        ProductEntity(
            "12313445",
            "Alcool",
            "1x24Unidade",
            "120ML CAN",
            13,
            "R$10.20",
            "",
            R.drawable.img_beer2
        ),
        ProductEntity(
            "1237547545",
            "Soda",
            "1x24Unidad",
            "120ML CAN",
            5,
            "R$15.60",
            "R$5.60",
            R.drawable.img_soda1
        ),
        ProductEntity(
            "12341341255",
            "Refrigerante",
            "1x24Unidad",
            "120ML CAN",
            7,
            "R$10.20",
            "",
            R.drawable.img_soda2
        )
    )
}