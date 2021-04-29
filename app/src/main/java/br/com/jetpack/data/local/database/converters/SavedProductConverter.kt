package br.com.jetpack.data.local.database.converters

import androidx.room.TypeConverter
import br.com.jetpack.data.local.database.entity.ProductEntity
import br.com.jetpack.data.local.database.entity.SavedProductEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class SavedProductConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<SavedProductEntity> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<ProductEntity>>() {

        }.type

        return gson.fromJson<List<SavedProductEntity>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<SavedProductEntity>): String {
        return gson.toJson(someObjects)
    }
}