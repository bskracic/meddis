package hr.bskracic.meddis.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.bskracic.meddis.data.model.Therapy
import hr.bskracic.meddis.data.model.TherapyAndMedication
import kotlinx.coroutines.flow.Flow

@Dao
interface TherapyDao {
    @Query("SELECT * FROM therapies")
    fun getAll(): LiveData<List<Therapy>>

    @Transaction
    @Query("SELECT * FROM therapies INNER JOIN medications ON medications.id = medication_id")
    fun getAllWithMedication(): LiveData<List<TherapyAndMedication>>

    @Query("SELECT * FROM therapies WHERE id IN (:therapyIds)")
    fun getByIds(therapyIds: IntArray): LiveData<List<Therapy>>

    @Query("SELECT * FROM therapies WHERE id=:therapyId")
    fun getById(therapyId: Int): LiveData<Therapy>

    @Transaction
    @Query("SELECT * FROM therapies INNER JOIN medications ON medications.id = medication_id WHERE therapies.id=:therapyId")
    fun getByIdWithMedication(therapyId: Int): LiveData<TherapyAndMedication>

    @Update
    fun update(vararg therapies: Therapy)

    @Insert
    fun insert(vararg therapies: Therapy)

    @Delete
    fun delete(therapy: Therapy)

}