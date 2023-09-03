package sample.aniwave.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import sample.aniwave.BuildConfig
import sample.aniwave.data.source.network.AnimeApi
import sample.aniwave.data.source.network.AnimeSearchApi
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = when (BuildConfig.BUILD_TYPE) {
            "debug" -> HttpLoggingInterceptor.Level.BASIC
            else -> HttpLoggingInterceptor.Level.NONE
        }

        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    @Named(TOP_ANIME)
    fun provideAnimeRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.jikan.moe/v4/").build()
    }

    @Singleton
    @Provides
    @Named(SEARCH_ANIME)
    fun provideSearchAnimeRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("https://api.trace.moe/").build()
    }

    @Singleton
    @Provides
    fun provideSearchApi(@Named(SEARCH_ANIME) retrofit: Retrofit): AnimeSearchApi {
        return retrofit.create()
    }

    @Singleton
    @Provides
    fun provideTopAnimeApi(@Named(TOP_ANIME) retrofit: Retrofit): AnimeApi {
        return retrofit.create()
    }


    private const val TOP_ANIME = "TOP_ANIME"
    private const val SEARCH_ANIME = "SEARCH_ANIME"
}
