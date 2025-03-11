package org.albert.x11_room.model

import androidx.room.Embedded
import androidx.room.Relation

// WARNING: The embedded relationship doesn't work for modifying the database.
// This is not JPA after all.
data class NoteListWithNoteItems (
    @Embedded val noteList: NoteList,
    @Relation(
        parentColumn = "id",
        entityColumn = "noteListId"
    ) val noteItems: List<NoteItem>
)