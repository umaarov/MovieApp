package uz.umarov.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import uz.umarov.movieapp.Adapter.RvAdapter
import uz.umarov.movieapp.Model.Movie
import uz.umarov.movieapp.Model.MySharedPreference
import uz.umarov.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RvAdapter
    private lateinit var movieList: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPreference.init(this)
    }

    override fun onStart() {
        super.onStart()

        movieList = MySharedPreference.movieList!!
        adapter = RvAdapter(object : RvAdapter.OnItemClickListener {
            override fun onItemClick(movie: Movie, position: Int) {

            }

            override fun onItemEditClick(movie: Movie, position: Int) {
                val intent = Intent(this@MainActivity, AddMovieActivity::class.java)
                intent.putExtra("moviePosition", position)
                intent.putExtra("key", 2)
                startActivity(intent)
            }

            override fun onItemDeleteClick(movie: Movie, position: Int) {
                movieList.removeAt(position)
                adapter.notifyItemRemoved(position)
                MySharedPreference.movieList = movieList
            }
        }, movieList)
        binding.recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addMovie -> {
                val intent = Intent(this, AddMovieActivity::class.java)
                intent.putExtra("key", 1)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}