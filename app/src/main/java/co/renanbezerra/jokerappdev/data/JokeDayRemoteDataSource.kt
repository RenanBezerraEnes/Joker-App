package co.renanbezerra.jokerappdev.data

import co.renanbezerra.jokerappdev.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeDayRemoteDataSource {
    fun findJokeOfTheDay(callBack: JokeCallBack) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findRandom()
            .enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful) {
                        val joke = response.body()
                        callBack.onSuccess(joke ?: throw RuntimeException("Joke not found"))
                    } else {
                        val error = response.errorBody()?.string()
                        callBack.onError(error ?: "Error not recognize")
                    }
                    callBack.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callBack.onError(t.message ?: "Intern Error")
                    callBack.onComplete()
                }
            })
    }
}