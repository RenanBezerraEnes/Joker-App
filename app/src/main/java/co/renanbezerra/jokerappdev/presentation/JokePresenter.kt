package co.renanbezerra.jokerappdev.presentation

import co.renanbezerra.jokerappdev.data.JokeCallBack
import co.renanbezerra.jokerappdev.data.JokeRemoteDataSource
import co.renanbezerra.jokerappdev.model.Joke
import co.renanbezerra.jokerappdev.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
): JokeCallBack {

    fun findBy(categoryName: String) {
        view.showProgress()
        dataSource.findBy(categoryName, this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}