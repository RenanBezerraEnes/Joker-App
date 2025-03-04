package co.renanbezerra.jokerappdev.presentation

import android.graphics.Color
import co.renanbezerra.jokerappdev.data.CategoryRemoteDataSource
import co.renanbezerra.jokerappdev.data.ListCategoryCallBack
import co.renanbezerra.jokerappdev.model.Category
import co.renanbezerra.jokerappdev.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
): ListCategoryCallBack {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val start = 40
        val end = 190
        val diff = (end - start) / response.size

        val categories = response.mapIndexed { index, stringValue ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f
            )

            Category(stringValue, Color.HSVToColor(hsv).toLong())
        }

        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}