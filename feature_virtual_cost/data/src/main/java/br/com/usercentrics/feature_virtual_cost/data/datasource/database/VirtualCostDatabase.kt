package br.com.usercentrics.feature_virtual_cost.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.dao.CostRuleDao
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.dao.DataTypeDao
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.fixture.costRuleFixture
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.fixture.dataTypeFixtures
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.CostRuleEntity
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.DataTypeEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities = [CostRuleEntity::class, DataTypeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MapDataConverter::class)
abstract class VirtualCostDatabase : RoomDatabase() {

    abstract fun costRules(): CostRuleDao

    abstract fun dataTypes(): DataTypeDao

    companion object {

        private var instance: VirtualCostDatabase? = null

        fun getDatabase(context: Context): VirtualCostDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    VirtualCostDatabase::class.java,
                    "virtual_cost_database.db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            dataTypeFixtures.map {
                                db.execSQL(it)
                            }
                            costRuleFixture.map {
                                db.execSQL(it)
                            }
                        }
                    })
                    .build().also { instance = it }
            }
        }
    }

}

class MapDataConverter {
    @TypeConverter
    fun stringToMap(value: String): Map<String, Any> {
        val type = object : TypeToken<Map<String, Any>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun mapToString(map: Map<String, Any>?): String {
        val type = object : TypeToken<Map<String, Any>>() {}.type
        return Gson().toJson(map, type)
    }
}