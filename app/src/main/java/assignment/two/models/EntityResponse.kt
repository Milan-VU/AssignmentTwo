package assignment.two.models

// Data class representing the response from the API containing a list of entities and the total count
data class EntityResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)