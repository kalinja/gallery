package com.sush.interview.gallery.dagger

import android.app.Application
import com.sush.interview.gallery.GalleryApplication
import com.sush.interview.gallery.fragment.AlbumFragment
import com.sush.interview.gallery.fragment.MainFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Component responsible for providing application scope instances.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        // provide Application instance into DI
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(galleryApp: GalleryApplication)
    fun inject(mainFragment: MainFragment)
    fun inject(albumFragment: AlbumFragment)
}