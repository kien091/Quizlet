package com.example.quizlet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashcardapplication.models.Topic
import com.example.quizlet.dao.FolderDao
import com.example.quizlet.dao.TopicDao
import com.example.quizlet.dao.VocabularyDao
import com.example.quizlet.models.Folder
import com.example.quizlet.models.TopicFolderCrossRef
import com.example.quizlet.models.Vocabulary

@Database(
    entities = [Vocabulary::class,
        Topic::class,
        Folder::class,
        TopicFolderCrossRef::class],
    version = 1,
    exportSchema = false)
abstract class RoomDb : RoomDatabase() {
    abstract fun TopicDao(): TopicDao
    abstract fun FolderDao(): FolderDao
    abstract fun VocabularyDao(): VocabularyDao
    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null
        private const val DATABASE_NAME = "quizlet_database"
        fun getDatabase(context: Context): RoomDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}