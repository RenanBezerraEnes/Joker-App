package co.renanbezerra.jokerappdev.presentation

import co.renanbezerra.jokerappdev.view.JokeDayFragment
import co.renanbezerra.jokerappdev.data.JokeCallBack
import co.renanbezerra.jokerappdev.data.JokeDayRemoteDataSource
import co.renanbezerra.jokerappdev.model.Joke

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
): JokeCallBack {

    fun findRandom() {
        view.showProgress()
        dataSource.findJokeOfTheDay(this)
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