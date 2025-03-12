package org.albert.x11_room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Version 1 to 2
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE IF NOT EXISTS NoteItem (
                    id INTEGER NOT NULL,
                    value TEXT NOT NULL,
                    noteListId INTEGER NOT NULL,
                    PRIMARY KEY(id)
                );
            """.trimIndent()
        )
    }
}