package co.renanbezerra.jokerappdev.presentation

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
        val categories = response.map {
            Category(it, 0xFFFF0000)
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