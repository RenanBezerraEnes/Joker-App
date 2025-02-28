package co.renanbezerra.jokerappdev.presentation

import android.os.Handler
import android.os.Looper
import co.renanbezerra.jokerappdev.model.Category
import co.renanbezerra.jokerappdev.view.HomeFragment

class HomePresenter(private val view: HomeFragment) {

    fun findAllCategories() {
        view.showProgress()
        fakeRequest()
    }

    fun onSuccess(response: List<String>) {
        val categories = response.map {
            Category(it, 0xFFFF0000)
        }

        view.showCategories(categories)
    }

    fun onError(message: String) {
        view.showFailure(message)
    }

    private fun onComplete() {
        view.hideProgress()
    }

    private fun fakeRequest() {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Category 1",
                "Category 2",
                "Category 3"
            )

            onSuccess(response)
            onComplete()
        }, 2000)
    }
}