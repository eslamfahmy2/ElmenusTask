package com.fahmy.task.domian.di

import com.fahmy.task.domian.mapper.ItemsDtoMapper
import com.fahmy.task.domian.mapper.TagDtoMapper
import com.fahmy.task.domian.repository.TagsRepo
import com.fahmy.task.domian.useCase.ListItemsByTagNameUseCase
import com.fahmy.task.domian.useCase.ListPagedTagsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun provideListPagedTagsUseCase(
        repo: TagsRepo,
        tagDtoMapper: TagDtoMapper,
        mapperModule: ItemsDtoMapper,
    ) = ListPagedTagsUseCase(repo, tagDtoMapper, itemsDtoMapper = mapperModule)


    @Singleton
    @Provides
    fun provideListItemsByTagNameUseCase(
        repo: TagsRepo,
        mapperModule: ItemsDtoMapper,
    ) = ListItemsByTagNameUseCase(repo, mapperModule)

}