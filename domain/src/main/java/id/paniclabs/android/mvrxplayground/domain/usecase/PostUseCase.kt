package id.paniclabs.android.mvrxplayground.domain.usecase

import id.paniclabs.android.mvrxplayground.data.models.PostModel
import id.paniclabs.android.mvrxplayground.data.repository.PostRepository
import id.paniclabs.android.mvrxplayground.domain.util.Transformer
import io.reactivex.Single

class PostUseCase(transformer: Transformer<List<PostModel>>, val repository: PostRepository) :
    BaseUseCase<List<PostModel>, Any?>(transformer) {

    override fun createSingle(request: Any?): Single<List<PostModel>> {
        return repository.getPostList()
    }

}