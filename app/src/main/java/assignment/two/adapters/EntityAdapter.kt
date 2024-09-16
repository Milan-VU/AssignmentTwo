package assignment.two.adapters

import assignment.two.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import assignment.two.models.Entity

// Adapter for displaying a list of entities in a RecyclerView using ListAdapter
class EntityAdapter(private val clickListener: (Entity) -> Unit) :
    ListAdapter<Entity, EntityAdapter.EntityViewHolder>(EntityDiffCallback()) {

    // Creates a new ViewHolder for an entity item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    // Binds the entity data to the ViewHolder
    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = getItem(position)
        holder.bind(entity, clickListener)
    }

    // ViewHolder for an entity item view
    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistNameTextView: TextView = itemView.findViewById(R.id.artistNameTextView)
        private val albumTitleTextView: TextView = itemView.findViewById(R.id.albumTitleTextView)
        private val releaseYearTextView: TextView = itemView.findViewById(R.id.releaseYearTextView)
        private val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)
        private val trackCountTextView: TextView = itemView.findViewById(R.id.trackCountTextView)
        private val popularTrackTextView: TextView = itemView.findViewById(R.id.popularTrackTextView)

        // Binds the entity data to the TextViews and sets up the click listener
        fun bind(entity: Entity, clickListener: (Entity) -> Unit) {
            // Set the text views in the correct order
            artistNameTextView.text = entity.artistName
            albumTitleTextView.text = entity.albumTitle
            releaseYearTextView.text = entity.releaseYear?.toString() ?: "N/A"
            genreTextView.text = entity.genre
            trackCountTextView.text = entity.trackCount?.toString() ?: "N/A"
            popularTrackTextView.text = entity.popularTrack

            // Set the click listener
            itemView.setOnClickListener { clickListener(entity) }
        }
    }

    // A DiffUtil.ItemCallback to optimize item changes
    class EntityDiffCallback : DiffUtil.ItemCallback<Entity>() {
        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem.albumTitle == newItem.albumTitle // Assuming albumTitle is unique
        }

        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem // Compares the entire content of the entity
        }
    }
}