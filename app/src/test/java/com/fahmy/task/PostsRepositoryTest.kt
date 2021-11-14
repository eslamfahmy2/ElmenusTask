package com.fahmy.task

import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import com.fahmy.task.domian.mapper.TagDtoMapper
import com.fahmy.task.domian.model.Tag
import com.fahmy.task.domian.useCase.ListPagedTagsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class PostsRepositoryTest {

    private val domainPosts = listOf<Tag>(
        Tag(
            source = "source",
            publishedAt = "publishedAt",
            content = "content",
            author = "author",
            url = "url",
            urlToImage = "urlToImage",
            description = "description",
            title = "title"
        ),
        Tag(
            source = "source",
            publishedAt = "publishedAt",
            content = "content",
            author = "author",
            url = "url",
            urlToImage = "urlToImage",
            description = "description",
            title = "title"
        ),
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val coroutineDispatcher = TestCoroutineDispatcher()

    private val postsApi = mock()

    lateinit var mapper: TagDtoMapper

    private lateinit var postsRepository: ListPagedTagsUseCase

    @Before
    fun createRepository() = coroutineDispatcher.runBlockingTest {

        postsRepository = ListPagedTagsUseCase(postsApi, mapper = mapper)
    }

    @Test
    fun loadPosts_returnsCorrectPosts() = runBlocking {
        launch {

            postsRepository.invoke("teslla").collect { pagingData ->

                val posts = mutableListOf<Tag>()
                pagingData.map {
                    posts.add(it)
                    println(it)
                }

                //THEN: retrieved posts should be the remotePosts

                assertThat(posts, IsEqual(domainPosts))

            }


        }
    }
}