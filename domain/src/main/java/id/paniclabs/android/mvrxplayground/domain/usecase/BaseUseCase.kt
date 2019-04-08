package id.paniclabs.android.mvrxplayground.domain.usecase

import id.paniclabs.android.mvrxplayground.domain.util.Transformer
import io.reactivex.Single

abstract class BaseUseCase<RESPONSE, REQUEST>(private val transformer: Transformer<RESPONSE>) {


    abstract fun createSingle(request: REQUEST?): Single<RESPONSE>

    fun create(request: REQUEST?): Single<RESPONSE> {
        return createSingle(request).compose(transformer)
    }
}