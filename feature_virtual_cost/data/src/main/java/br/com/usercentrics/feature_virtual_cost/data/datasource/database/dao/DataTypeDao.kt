package br.com.usercentrics.feature_virtual_cost.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.DataTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DataTypeDao {

    @Query("SELECT * FROM data_types")
    fun getAll(): Flow<List<DataTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(dataTypes: List<DataTypeEntity>)

}