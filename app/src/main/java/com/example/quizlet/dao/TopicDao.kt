package com.example.quizlet.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.flashcardapplication.models.Topic

@Dao
interface TopicDao {
    @Transaction
    @Query("SELECT * FROM topic")
    fun getAllTopics(): List<Topic>

    @Transaction
    @Query("SELECT * FROM topic WHERE id = :id")
    fun getTopicById(id: Int): Topic

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopic(topic: Topic)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTopics(topics: List<Topic>) {
        topics.forEach {
            insertTopic(it)
        }
    }

    @Transaction
    @Update
    fun updateTopic(topic: Topic)

    @Transaction
    @Update
    fun updateAllTopics(topics: List<Topic>) {
        topics.forEach {
            updateTopic(it)
        }
    }

    @Transaction
    @Query("DELETE FROM topic")
    fun deleteAllTopics()

    @Transaction
    @Query("DELETE FROM topic WHERE id = :id")
    fun deleteTopicById(id: Int)
}