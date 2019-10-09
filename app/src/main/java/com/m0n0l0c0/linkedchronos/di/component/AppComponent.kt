package com.m0n0l0c0.linkedchronos.di.component

import com.m0n0l0c0.linkedchronos.AppClass
import com.m0n0l0c0.linkedchronos.di.modules.ActivitiesModule
import com.m0n0l0c0.linkedchronos.di.modules.AppModule
import com.m0n0l0c0.linkedchronos.di.modules.FragmentsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivitiesModule::class,
    FragmentsModule::class])
interface AppComponent {

    fun inject(appClass: AppClass)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(appClass: AppClass): Builder

        fun build(): AppComponent
    }
}