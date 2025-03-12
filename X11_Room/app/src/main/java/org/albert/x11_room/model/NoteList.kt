package org.albert.x11_room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class NoteList(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="title") val title: String,
)