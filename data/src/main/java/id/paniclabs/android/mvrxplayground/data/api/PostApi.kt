package id.paniclabs.android.mvrxplayground.data.api

import id.paniclabs.android.mvrxplayground.data.models.PostModel
import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    fun getPostList(): Single<List<PostModel>>
}