package br.com.usercentrics.feature_virtual_cost.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.usercentrics.feature_virtual_cost.data.datasource.database.model.CostRuleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CostRuleDao {

    @Query("SELECT * FROM cost_rules where enabled = 1")
    fun getAllActive(): Flow<List<CostRuleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(dataTypes: List<CostRuleEntity>)
}