package co.renanbezerra.jokerappdev.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.renanbezerra.jokerappdev.R
import co.renanbezerra.jokerappdev.model.Joke
import co.renanbezerra.jokerappdev.presentation.JokePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class JokeFragment : Fragment() {

    companion object {
        const val CATEGORY_KEY = "category"
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    private lateinit var presenter: JokePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = arguments?.getString(CATEGORY_KEY)

        if (categoryName != null) {
            activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName

            progressBar = view.findViewById(R.id.progress_bar)
            textView = view.findViewById(R.id.txt_joke)
            imageView = view.findViewById(R.id.img_joke)

            view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
                presenter.findRandom(categoryName)
            }

            presenter.findRandom(categoryName)
        }
    }

    fun showJoke(joke: Joke) {
        textView.text = joke.text
        Picasso.get().load(joke.iconUrl).into(imageView)
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}