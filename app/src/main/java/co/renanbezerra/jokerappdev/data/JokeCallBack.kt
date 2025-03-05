package co.renanbezerra.jokerappdev.data

import co.renanbezerra.jokerappdev.model.Joke

interface JokeCallBack {

    fun onSuccess(response: Joke)

    fun onError(response: String)

    fun onComplete()
}