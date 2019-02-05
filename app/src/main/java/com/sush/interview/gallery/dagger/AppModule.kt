package com.sush.interview.gallery.dagger

import android.content.Context
import com.sush.interview.gallery.model.GalleryRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides [Retrofit] instance, [GalleryRepository] instance and application [Context].
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
@Module
internal class AppModule {

    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com"
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGalleryRepository(): GalleryRepository {
        return GalleryRepository(provideRetrofitInterface())
    }

    @Provides
    internal fun provideApplicationContext(context: Context): Context {
        return context
    }
}