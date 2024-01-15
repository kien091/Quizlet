package com.example.flashcardapplication.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.quizlet.models.Folder
import com.example.quizlet.models.TopicFolderCrossRef
import com.example.quizlet.models.Vocabulary

@Entity
data class Topic(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val timestamp: String,
    val status: String,
    val owner: String,
){
    constructor(): this(0, "", "", "", "", "")
}

data class TopicWithFolders(
    @Embedded val topic: Topic,
    @Relation(
        parentColumn = "id",
        entity = Folder::class,
        entityColumn = "id",
        associateBy = Junction(
            value = TopicFolderCrossRef::class,
            parentColumn = "topicId",
            entityColumn = "folderId")
    )
    val folders: List<Folder>
)

data class TopicWithVocabularies(
    @Embedded val topic: Topic,
    @Relation(
        parentColumn = "id",
        entity = Vocabulary::class,
        entityColumn = "topicId"
    )
    val vocabularies: List<Vocabulary>
)