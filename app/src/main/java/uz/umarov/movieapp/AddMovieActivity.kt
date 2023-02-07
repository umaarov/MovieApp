package uz.umarov.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.umarov.movieapp.Model.Movie
import uz.umarov.movieapp.Model.MySharedPreference
import uz.umarov.movieapp.databinding.ActivityAddMovieBinding

class AddMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding
    private lateinit var movieList: ArrayList<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val key = intent.getIntExtra("key", 0)
        val moviePosition = intent.getIntExtra("moviePosition", 0)

        MySharedPreference.init(this)
        movieList = MySharedPreference.movieList!!

        when (key) {
            1 -> {
                binding.save.setOnClickListener {
                    val title: String = binding.movieTitle.text.toString()
                    val date: String = binding.movieDate.text.toString()
                    val author: String = binding.movieAuthor.text.toString()
                    val desc: String = binding.movieDesc.text.toString()

                    val movie = Movie(title, date, author, desc)
                    movieList.add(movie)
                    MySharedPreference.movieList = movieList
                    Toast.makeText(this, "Movie Saved Successfully", Toast.LENGTH_SHORT).show()
                    this.finish()
                }
            }
            2 -> {
                binding.movieTitle.setText(MySharedPreference.movieList!![moviePosition].title)
                binding.movieDate.setText(MySharedPreference.movieList!![moviePosition].date)
                binding.movieAuthor.setText(MySharedPreference.movieList!![moviePosition].author)
                binding.movieDesc.setText(MySharedPreference.movieList!![moviePosition].desc)

                binding.save.setOnClickListener {
                    val title: String = binding.movieTitle.text.toString()
                    val date: String = binding.movieDate.text.toString()
                    val author: String = binding.movieAuthor.text.toString()
                    val desc: String = binding.movieDesc.text.toString()

                    movieList[moviePosition] = Movie(title, date, author, desc)
                    MySharedPreference.movieList = movieList

                    Toast.makeText(this, "Movie Edited Successfully", Toast.LENGTH_SHORT).show()
                    this.finish()
                }
            }
        }
    }
}