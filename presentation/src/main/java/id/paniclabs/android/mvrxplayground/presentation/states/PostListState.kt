package id.paniclabs.android.mvrxplayground.presentation.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Uninitialized
import id.paniclabs.android.mvrxplayground.data.models.PostModel

data class PostListState(
    val postList: Async<List<PostModel>> = Uninitialized,
    val loading: Async<Boolean> = Uninitialized
) : BaseState(loading)