package id.paniclabs.android.mvrxplayground.presentation.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState

abstract class BaseState(val isLoading: Async<Boolean>) : MvRxState