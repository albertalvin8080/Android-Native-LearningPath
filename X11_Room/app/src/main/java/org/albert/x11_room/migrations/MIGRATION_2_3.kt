package org.albert.x11_room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
                ALTER TABLE NoteItem
                ADD COLUMN itemOrder INTEGER
            """.trimIndent()
        )
    }
}