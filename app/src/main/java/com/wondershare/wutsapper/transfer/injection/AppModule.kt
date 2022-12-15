package com.wondershare.wutsapper.transfer.injection

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.basic.common.util.theme.Colors
import com.basic.common.util.theme.FontProvider
import com.basic.common.util.theme.TextViewStyler
import com.basic.data.Preferences
import com.wondershare.wutsapper.transfer.data.manager.PermissionManagerImpl
import com.wondershare.wutsapper.transfer.domain.manager.PermissionManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.wondershare.wutsapper.transfer.data.manager.NotificationManagerImpl
import com.wondershare.wutsapper.transfer.domain.manager.NotificationManager
import com.wondershare.wutsapper.transfer.common.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(): Context = App.app

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences = RxSharedPreferences.create(sharedPreferences)

    @Provides
    @Singleton
    fun providePreferences(rxSharedPreferences: RxSharedPreferences): Preferences = Preferences(rxSharedPreferences)

    // Manager

    @Provides
    @Singleton
    fun providePermissionManagerImpl(manager: PermissionManagerImpl): PermissionManager = manager

    @Provides
    @Singleton
    fun provideNotificationManagerImpl(manager: NotificationManagerImpl): NotificationManager = manager

}