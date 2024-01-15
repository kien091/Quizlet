package com.example.quizlet.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.quizlet.models.Folder
import com.example.quizlet.models.FolderWithTopics

@Dao
interface FolderDao {
    @Query("SELECT * FROM folder")
    fun getAllFolders(): List<Folder>

    @Query("SELECT * FROM folder WHERE id = :id")
    fun getFolderById(id: Int): Folder

    @Query("SELECT * FROM folder WHERE owner = :owner")
    fun getFolderByOwner(owner: String): Folder

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFolder(folder: Folder)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFolders(folders: List<Folder>) {
        folders.forEach {
            insertFolder(it)
        }
    }

    @Transaction
    @Update
    fun updateFolder(folder: Folder)

    @Transaction
    @Update
    fun updateAllFolders(folders: List<Folder>) {
        folders.forEach {
            updateFolder(it)
        }
    }

    @Transaction
    @Query("DELETE FROM folder")
    fun deleteAllFolders()

    @Transaction
    @Query("DELETE FROM folder WHERE id = :id")
    fun deleteFolderById(id: Int)

    @Query("SELECT * FROM folder")
    fun getFoldersWithTopics(): List<FolderWithTopics>
}