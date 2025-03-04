package co.renanbezerra.jokerappdev.data

import android.os.Handler
import android.os.Looper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {

    fun findAllCategories(callBack: ListCategoryCallBack) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if(response.isSuccessful) {
                        val categories = response.body()
                        callBack.onSuccess(categories?: emptyList())
                    } else {
                        val error = response.errorBody()?.string()
                        callBack.onError(error?: "Error not recognize")
                    }
                    callBack.onComplete()
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callBack.onError(t.message ?: "Intern Error")
                    callBack.onComplete()
                }

            })
    }
}