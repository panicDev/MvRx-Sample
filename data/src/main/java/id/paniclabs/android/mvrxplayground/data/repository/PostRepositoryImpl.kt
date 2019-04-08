package id.paniclabs.android.mvrxplayground.data.repository

import id.paniclabs.android.mvrxplayground.data.api.PostApi
import id.paniclabs.android.mvrxplayground.data.models.PostModel
import io.reactivex.Single

class PostRepositoryImpl(val postApi: PostApi) : PostRepository {

    override fun getPostList(): Single<List<PostModel>> = postApi.getPostList()

}