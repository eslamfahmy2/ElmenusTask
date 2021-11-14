package com.fahmy.task.domian.di

import com.fahmy.task.domian.mapper.ItemsDtoMapper
import com.fahmy.task.domian.mapper.TagDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun provideTagMapper() = TagDtoMapper()


    @Singleton
    @Provides
    fun provideItemMapper() = ItemsDtoMapper()


}