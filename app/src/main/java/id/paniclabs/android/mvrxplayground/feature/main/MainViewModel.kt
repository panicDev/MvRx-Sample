package id.paniclabs.android.mvrxplayground.feature.main

import id.paniclabs.android.mvrxplayground.domain.usecase.PostUseCase
import id.paniclabs.android.mvrxplayground.presentation.states.PostListState
import id.paniclabs.android.mvrxplayground.presentation.view.BaseViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(initialState: PostListState) : BaseViewModel<PostListState>(initialState), KoinComponent {

    private val mPostListUseCase: PostUseCase by inject()

    fun getPostList() {
        this.mPostListUseCase.create(null).execute { postList ->
            copy(postList = postList)
        }
    }

}