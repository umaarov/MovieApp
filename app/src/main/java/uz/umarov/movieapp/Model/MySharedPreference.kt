package uz.umarov.movieapp.Model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME: String = "movie"
    private const val MODE: Int =Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit ){
        val editor = edit()
        operation(editor)
        editor.apply()
    }
    private fun arrayToString(arrayList: ArrayList<Movie>): String {
        return Gson().toJson(arrayList)
    }
    private fun stringToArray(string: String): ArrayList<Movie>{
        val type = object : TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(string, type)
    }
    var movieList: ArrayList<Movie>?
    get() = stringToArray(sharedPreferences.getString("movie", "[]")!!)
    set(value) = sharedPreferences.edit {
        if (value != null) {
            it.putString("movie", arrayToString(value))
        }
    }
}