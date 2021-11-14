package com.fahmy.task.domian.di

import com.fahmy.task.data.network.ApiInterface
import com.fahmy.task.data.paging.TagsRepoImpl
import com.fahmy.task.domian.repository.TagsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTagsRepo(
        apiInterface: ApiInterface
    ): TagsRepo = TagsRepoImpl(apiInterface = apiInterface)


}