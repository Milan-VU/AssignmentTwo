package assignment.two

import android.os.Bundle
import android.content.Intent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import assignment.two.DetailsActivity
import assignment.two.adapters.EntityAdapter
import assignment.two.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var entityAdapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize RecyclerView with a LinearLayoutManager
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter
        entityAdapter = EntityAdapter { entity ->
            // Handle item click: Navigate to Details screen
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("entity", entity)
            startActivity(intent)
        }

        // Set the adapter to the RecyclerView
        recyclerView.adapter = entityAdapter

        // Get the keypass from the intent
        val keypass = intent.getStringExtra("keypass")

        // Use the keypass to load entities
        keypass?.let {
            dashboardViewModel.loadEntities(it)
        }

        // Observe the entities data and update the RecyclerView adapter
        dashboardViewModel.entities.observe(this) { entities ->
            entityAdapter.submitList(entities)
        }
    }
}