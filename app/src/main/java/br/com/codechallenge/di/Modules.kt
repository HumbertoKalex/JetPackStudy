package br.com.codechallenge.di

import androidx.room.Room
import br.com.codechallenge.data.local.database.DataBase
import br.com.codechallenge.data.repository.ProductRepository
import br.com.codechallenge.product.domain.ProductUseCase
import br.com.codechallenge.product.view.ProductListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object Modules {
    private val dataModule = module {
        single {
            Room.databaseBuilder(get(), DataBase::class.java, "product-db")
                .fallbackToDestructiveMigration()
                .build()
        }
        factory { ProductRepository(get()) }
    }

    private val domainModule = module {
        factory { ProductUseCase(get()) }
    }

    private val viewModule = module {
        viewModel {
            ProductListViewModel(
                get()
            )
        }
    }

    fun loadModules() {
        loadKoinModules(listOf(dataModule, domainModule, viewModule))
    }
}