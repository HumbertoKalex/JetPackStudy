package br.com.codechallenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.codechallenge.data.local.database.converters.ProductConverter
import br.com.codechallenge.data.local.database.converters.SavedProductConverter
import br.com.codechallenge.data.local.database.dao.ProductDao
import br.com.codechallenge.data.local.database.entity.ProductEntity
import br.com.codechallenge.data.local.database.entity.SavedProductEntity

@Database(entities = [ProductEntity::class,SavedProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(ProductConverter::class,SavedProductConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
