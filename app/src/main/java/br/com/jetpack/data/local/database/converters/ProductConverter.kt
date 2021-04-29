package br.com.jetpack.data.local.database.converters

import androidx.room.TypeConverter
import br.com.jetpack.data.local.database.entity.ProductEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ProductConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<ProductEntity> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<ProductEntity>>() {

        }.type

        return gson.fromJson<List<ProductEntity>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<ProductEntity>): String {
        return gson.toJson(someObjects)
    }
}