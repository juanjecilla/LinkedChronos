package com.scallop.linkedchronos.di.component

import com.scallop.linkedchronos.AppClass
import com.scallop.linkedchronos.di.modules.ActivitiesModule
import com.scallop.linkedchronos.di.modules.AppModule
import com.scallop.linkedchronos.di.modules.FragmentsModule
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