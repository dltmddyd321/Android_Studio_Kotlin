package com.example.mvpbasic

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    //@Provides 어노테이션으로 컴포넌트에 제공할 메소드를 정의할 수 있으며, 활동범위를 지정해주는데,
    //@Singleton 어노테이션으로 싱글톤 패턴으로 생성하여 사용할 때마다 인스턴스가 생성되는 것을 막을 수 있다.
    @Singleton
    @Provides
    fun provideTestString() = "Inject Test Sentence"
}

//@Module
//@InstallIn(ActivityComponent::class)
//object AppModule {
//
//    @ActivityScoped
//    @Provides
//    fun provideTestString() = "Inject Test Sentence"
//
//}