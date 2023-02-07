package uz.umarov.movieapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import uz.umarov.movieapp.Model.Movie
import uz.umarov.movieapp.databinding.MovieItemBinding

class RvAdapter(
    private val onItemClickListener: OnItemClickListener,
    private val movieList: List<Movie>
) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(movie: Movie, position: Int) {
            binding.movieTitle.text = movie.title
            binding.movieDate.text = movie.date
            binding.movieAuthor.text = movie.author

            binding.root.setOnClickListener{
                onItemClickListener.onItemClick(movie, position)
            }
            binding.movieEdit.setOnClickListener {
                onItemClickListener.onItemEditClick(movie, position)
            }
            binding.movieDelete.setOnClickListener {
                onItemClickListener.onItemDeleteClick(movie, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(movieList[position], position)
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie, position: Int)
        fun onItemEditClick(movie: Movie, position: Int)
        fun onItemDeleteClick(movie: Movie, position: Int)
    }
}