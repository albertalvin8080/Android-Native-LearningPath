package org.albert.x16_jetpack_concepts.data

data class Author(
    val id: Int,
    val name: String,
)

class AuthorRepository {
    companion object {
        fun getAll(): List<Author> {
            return listOf(
                Author(1, "Helmuth"),
                Author(2, "Voss"),
                Author(3, "Klaus"),
                Author(4, "Poppe"),
                Author(5, "Franz"),
                Author(6, "Bonaparta"),
                Author(7, "Emil"),
                Author(8, "Sebe"),
                Author(9, "Helmuth"),
                Author(10, "Voss"),
                Author(11, "Klaus"),
                Author(12, "Poppe"),
                Author(13, "Franz"),
                Author(14, "Bonaparta"),
                Author(15, "Emil"),
                Author(16, "Sebe"),
                Author(1, "Helmuth"),
                Author(2, "Voss"),
                Author(3, "Klaus"),
                Author(4, "Poppe"),
                Author(5, "Franz"),
                Author(6, "Bonaparta"),
                Author(7, "Emil"),
                Author(8, "Sebe"),
                Author(9, "Helmuth"),
                Author(10, "Voss"),
                Author(11, "Klaus"),
                Author(12, "Poppe"),
                Author(13, "Franz"),
                Author(14, "Bonaparta"),
                Author(15, "Emil"),
                Author(16, "Sebe"),
            )
        }
    }
}