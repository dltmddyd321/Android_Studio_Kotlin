package com.example.nasadataservice.di

import com.example.nasadataservice.service.MarsRoverManifestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //@Provides는 Room, Retrofit과 같은 외부 라이브러리에서 제공되는 클래스이므로 프로젝트 내에서 소유할 수 없는 경우 또는 Builder 패턴 등을 통해 인스턴스를 생성해야 하는 경우에 사용한다.
    @Provides
    fun provideMarsRoverManifestService(): MarsRoverManifestService = MarsRoverManifestService.create()
}