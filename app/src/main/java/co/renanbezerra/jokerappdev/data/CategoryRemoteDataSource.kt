package co.renanbezerra.jokerappdev.data

import android.os.Handler
import android.os.Looper

class CategoryRemoteDataSource {

    fun findAllCategories(callBack: ListCategoryCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Category 1",
                "Category 2",
                "Category 3"
            )

            callBack.onSuccess(response)
            callBack.onComplete()
        }, 2000)
    }
}