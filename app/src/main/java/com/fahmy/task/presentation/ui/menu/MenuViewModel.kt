package com.fahmy.task.presentation.ui.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.fahmy.task.domian.model.Item
import com.fahmy.task.domian.model.Tag
import com.fahmy.task.domian.useCase.ListItemsByTagNameUseCase
import com.fahmy.task.domian.useCase.ListPagedTagsUseCase
import com.fahmy.task.domian.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MenuViewModel"

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val listPagedTagsUseCase: ListPagedTagsUseCase,
    private val listItemsByTagNameUseCase: ListItemsByTagNameUseCase
) : ViewModel() {

    val userIntent = Channel<MenuIntent>(Channel.UNLIMITED)

    private val _items: MutableStateFlow<DataState<List<Item>>> =
        MutableStateFlow(DataState.Loading())
    val items get() = _items.asStateFlow()

    private val _tags: MutableStateFlow<Flow<PagingData<Tag>>> = MutableStateFlow(flowOf())
    val tags get() = _tags


    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is MenuIntent.LoadItemsByTagName -> {
                        loadItemsByTagName(name = intent.tag.tagName)
                    }
                    MenuIntent.LoadTags -> {
                        loadTags()
                    }
                }
            }
        }

    }

    private fun loadItemsByTagName(name: String) = viewModelScope.launch(Dispatchers.IO) {
        listItemsByTagNameUseCase(name).collect { dataState ->
            Log.d(TAG, "loadItemsByTagName: $dataState")
            when (dataState) {
                is DataState.Error -> {
                    _items.value = DataState.Error(dataState.message)
                }
                is DataState.Loading -> {
                    _items.value = DataState.Loading()
                }
                is DataState.Success -> {
                    dataState.data?.let {
                        _items.value = DataState.Success(it)
                    }
                }
            }
        }
    }

    private fun loadTags() = viewModelScope.launch(Dispatchers.IO) {
        _tags.value = listPagedTagsUseCase()
        loadItemsByTagName(name = "1 - Deserts -  French Fries")
    }


}