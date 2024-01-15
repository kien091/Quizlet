package com.example.quizlet.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.quizlet.models.Vocabulary

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary")
    fun getAllVocabularies(): List<Vocabulary>

    @Query("SELECT * FROM vocabulary WHERE id = :id")
    fun getVocabularyById(id: Int): Vocabulary

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVocabulary(vocabulary: Vocabulary)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllVocabularies(vocabularies: List<Vocabulary>) {
        vocabularies.forEach {
            insertVocabulary(it)
        }
    }

    @Transaction
    @Update
    fun updateVocabulary(vocabulary: Vocabulary)

    @Transaction
    @Update
    fun updateAllVocabularies(vocabularies: List<Vocabulary>) {
        vocabularies.forEach {
            updateVocabulary(it)
        }
    }

    @Transaction
    @Query("DELETE FROM vocabulary")
    fun deleteAllVocabularies()

    @Transaction
    @Query("DELETE FROM vocabulary WHERE id = :id")
    fun deleteVocabularyById(id: Int)
}