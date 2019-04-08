package id.paniclabs.android.mvrxplayground.domain.util

import io.reactivex.SingleTransformer

abstract class Transformer<T> : SingleTransformer<T, T>