package com.sush.interview.gallery.dagger

import android.app.Application
import com.sush.interview.gallery.GalleryApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, ActivityModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        // provide Application instance into DI
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    // this is needed because LuaApp has @Inject
    fun inject(galleryApp: GalleryApplication)
}