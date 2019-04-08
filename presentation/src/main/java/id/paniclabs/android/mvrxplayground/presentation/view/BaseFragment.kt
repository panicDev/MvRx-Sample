package id.paniclabs.android.mvrxplayground.presentation.view

import android.view.View
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.BaseMvRxViewModel
import id.paniclabs.android.mvrxplayground.presentation.states.BaseState


abstract class BaseFragment<S : BaseState, V : BaseMvRxViewModel<S>> : BaseMvRxFragment() {

    protected lateinit var mView: View

}