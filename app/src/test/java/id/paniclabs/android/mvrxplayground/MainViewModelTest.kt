package id.paniclabs.android.mvrxplayground

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import id.paniclabs.android.mvrxplayground.data.models.PostModel
import id.paniclabs.android.mvrxplayground.data.repository.PostRepository
import id.paniclabs.android.mvrxplayground.feature.main.MainViewModel
import id.paniclabs.android.mvrxplayground.presentation.states.PostListState
import io.reactivex.subjects.SingleSubject
import junit.framework.Assert.assertTrue
import org.junit.ClassRule
import org.junit.Test

class MainViewModelTest {

    companion object {
        @JvmField
        @ClassRule
        val mvrxTestRule = MvRxTestRule()

        private val mockResponse: List<PostModel> = listOf(
            PostModel(
                userId = 1,
                id = 1,
                title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                body = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"
            )
        )

    }

    private var repository: PostRepository = mock()
    private lateinit var viewModel: MainViewModel

    @Test
    fun fetchPosts_success() {
        // use subject to be able to verify the loading state before any emissions
        val postsSubject = SingleSubject.create<List<PostModel>>()
        whenever(repository.getPostList()).thenReturn(postsSubject)

        // given the viewmodel with default state
        viewModel = MainViewModel(PostListState())
        viewModel.getPostList()

        // verify that tasks were requested from the repository
        verify(repository).getPostList()

        withState(viewModel) { assertTrue(it.postList is Uninitialized) }

        // new emission from the data source happened
        postsSubject.onSuccess(mockResponse)

        // verify that tasks request was successful and the photo list is present
        withState(viewModel) {
            assertTrue(it.postList is Success)
            assertTrue(it.postList()?.size == 1)
        }

    }
}