package assignment.two.models

import java.io.Serializable

// Data class representing an entity with all the properties
data class Entity(
    val artistName: String?,
    val albumTitle: String?,
    val releaseYear: Int?,
    val genre: String?,
    val trackCount: Int?,
    val description: String?,
    val popularTrack: String?
) : Serializable