package br.com.jetpack.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.jetpack.data.local.database.entity.ProductEntity
import br.com.jetpack.data.local.database.entity.SavedProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM savedProduct")
    suspend fun getAllSaved(): List<SavedProductEntity>

    @Query("SELECT * FROM product")
    suspend fun getAllProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedProduct(product: SavedProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMock(product: List<ProductEntity>)

    @Query("DELETE FROM product WHERE id = :id")
    suspend fun removeProduct(id: String)

    @Query("DELETE FROM savedProduct WHERE id = :id")
    suspend fun removeSavedProduct(id: String)

}