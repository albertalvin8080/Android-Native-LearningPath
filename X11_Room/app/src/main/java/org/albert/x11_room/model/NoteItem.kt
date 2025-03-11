package org.albert.x11_room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteItem(
    @PrimaryKey val id: Int,
    val value: String,
    val noteListId: Int
)