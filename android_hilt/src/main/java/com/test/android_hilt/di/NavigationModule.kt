package com.test.android_hilt.di

import com.test.android_hilt.navigator.AppNavigator
import com.test.android_hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 2:47 오후
 * @desc
 */
@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}