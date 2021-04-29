package br.com.jetpack.data.local

import br.com.jetpack.data.local.database.entity.ProductEntity

object ProductMock {
    val productMock = listOf<ProductEntity>(
        ProductEntity(
            "12345",
            "Bebida",
            "1x24Unidade",
            "120ML CAN",
            10,
            "R$10.20",
            "R$1.20",
            "https://cdn3.iconfinder.com/data/icons/drink-30/64/alcohol-beer-drink-bottle-512.png"
        ),
        ProductEntity(
            "12313445",
            "Alcool",
            "1x24Unidade",
            "120ML CAN",
            10,
            "R$10.20",
            null,
            "https://icon-library.com/images/beer-icon/beer-icon-13.jpg"
        ),
        ProductEntity(
            "1237547545",
            "PepsiCola",
            "1x24Unidad",
            "120ML CAN",
            10,
            "R$15.60",
            "R$5.60",
            "https://assets.webiconspng.com/uploads/2017/09/Pepsi-PNG-Image-44437-768x768.png"
        ),
        ProductEntity(
            "12341341255",
            "CocaCola",
            "1x24Unidad",
            "120ML CAN",
            10,
            "R$10.20",
            null,
            "https://icons.iconarchive.com/icons/michael/coke-pepsi/256/Coca-Cola-Can-icon.png"
        )
    )
}