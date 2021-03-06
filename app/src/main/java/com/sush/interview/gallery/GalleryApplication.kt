package com.sush.interview.gallery

import android.app.Activity
import android.app.Application
import com.sush.interview.gallery.dagger.AppComponent
import com.sush.interview.gallery.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Gallery application.
 *
 * @author Jakub Kalina (kalina.kuba@gmail.com)
 */
class GalleryApplication : Application(), HasActivityInjector {

    companion object {
        lateinit var instance: GalleryApplication
    }

    init {
        instance = this
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerAppComponent.builder().application(this).build()
        applicationComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    fun getApplicationComponent(): AppComponent {
        return applicationComponent
    }
}