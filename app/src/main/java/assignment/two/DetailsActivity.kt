package assignment.two

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import assignment.two.models.Entity

// Activity to display the details of a selected entity.
class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Retrieve the selected entity passed from the DashboardActivity
        val entity = intent.getSerializableExtra("entity") as? Entity

        // Find the TextViews in the layout
        val artistNameTextView = findViewById<TextView>(R.id.artistNameTextView)
        val albumTitleTextView = findViewById<TextView>(R.id.albumTitleTextView)
        val releaseYearTextView = findViewById<TextView>(R.id.releaseYearTextView)
        val genreTextView = findViewById<TextView>(R.id.genreTextView)
        val trackCountTextView = findViewById<TextView>(R.id.trackCountTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val popularTrackTextView = findViewById<TextView>(R.id.popularTrackTextView)

        // Populate the TextViews with the entity's details
        entity?.let {
            artistNameTextView.text = it.artistName
            albumTitleTextView.text = it.albumTitle
            releaseYearTextView.text = it.releaseYear.toString()
            genreTextView.text = it.genre
            trackCountTextView.text = it.trackCount.toString()
            descriptionTextView.text = it.description
            popularTrackTextView.text = it.popularTrack
        }
    }
}