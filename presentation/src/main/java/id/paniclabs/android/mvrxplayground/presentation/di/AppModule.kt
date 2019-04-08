package id.paniclabs.android.mvrxplayground.presentation.di

import id.paniclabs.android.mvrxplayground.data.api.PostApi
import id.paniclabs.android.mvrxplayground.data.repository.PostRepository
import id.paniclabs.android.mvrxplayground.data.repository.PostRepositoryImpl
import id.paniclabs.android.mvrxplayground.domain.usecase.PostUseCase
import id.paniclabs.android.mvrxplayground.presentation.util.AsyncTransformer
import id.paniclabs.android.mvrxplayground.presentation.BuildConfig.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    val retrofit = module {

        single<Retrofit> {

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        single<PostApi> {

            val retrofit = get<Retrofit>()
            retrofit.create(PostApi::class.java)
        }

    }

    val repositories = module {

        single<PostRepository> {
            PostRepositoryImpl(get())
        }
    }


    val useCases = module {

        single {
            PostUseCase(AsyncTransformer(), get())
        }
    }

}