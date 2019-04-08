package id.paniclabs.android.mvrxplayground.data.repository

import id.paniclabs.android.mvrxplayground.data.models.PostModel
import io.reactivex.Single

interface PostRepository {

    fun getPostList(): Single<List<PostModel>>
}