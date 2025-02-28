package co.renanbezerra.jokerappdev.data

interface ListCategoryCallBack {

    fun onSuccess(response: List<String>)

    fun onError(response: String)

    fun onComplete()
}