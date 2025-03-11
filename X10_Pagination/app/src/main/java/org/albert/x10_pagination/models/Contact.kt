package org.albert.x10_pagination.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact (
    @PrimaryKey val id: Int,
    val name: String
)