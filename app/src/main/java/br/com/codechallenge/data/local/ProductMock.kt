package br.com.codechallenge.data.local

import br.com.codechallenge.data.local.database.entity.ProductEntity

object ProductMock {
    val productMock = listOf<ProductEntity>(
        ProductEntity(
            "12345",
            "Bebida",
            "1x24Unidad",
            "120ML CAN",
            10,
            "R$10.20",
            "R$1.20",
            "https://qa-m1-dr.abi-sandbox.net/media/catalog/product//-/R/-R002151.png"
        ),
        ProductEntity(
            "12313445",
            "Bebida",
            "1x24Unidad",
            "120ML CAN",
            10,
            "R$10.20",
            null,
            "https://qa-m1-dr.abi-sandbox.net/media/catalog/product//-/R/-R002151.png"
        ),
        ProductEntity(
            "1237547545",
            "Bebida",
            "1x24Unidad",
            "120ML CAN",
            10,
            "R$15.60",
            "R$5.60",
            "https://qa-m1-dr.abi-sandbox.net/media/catalog/product//-/R/-R002151.png"
        ),
        ProductEntity(
            "12341341255",
            "Bebida",
            "1x24Unidad",
            "120ML CAN",
            10,
            "R$10.20",
            null,
            "https://qa-m1-dr.abi-sandbox.net/media/catalog/product//-/R/-R002151.png"
        )
    )
}