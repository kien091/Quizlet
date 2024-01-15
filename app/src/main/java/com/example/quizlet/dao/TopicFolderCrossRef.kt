package com.example.quizlet.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TopicFolderCrossRef {
    @Query("SELECT * FROM topicfoldercrossref")
    fun getAllTopicFolderCrossRef(): List<TopicFolderCrossRef>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopicFolderCrossRef(crossRef: TopicFolderCrossRef)

    @Transaction
    @Delete
    fun deleteTopicFolderCrossRef(crossRef: TopicFolderCrossRef)

    @Transaction
    @Query("DELETE FROM topicfoldercrossref WHERE topicId = :topicId")
    fun deleteTopicFolderCrossRefByTopicId(topicId: Int)

    @Transaction
    @Query("DELETE FROM topicfoldercrossref WHERE folderId = :folderId")
    fun deleteTopicFolderCrossRefByFolderId(folderId: Int)

    @Transaction
    @Query("DELETE FROM topicfoldercrossref")
    fun deleteAllTopicFolderCrossRef()
}