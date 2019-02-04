package com.sush.interview.gallery.dagger

import com.sush.interview.gallery.activity.AlbumActivity
import com.sush.interview.gallery.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeAlbumActivity(): AlbumActivity
}