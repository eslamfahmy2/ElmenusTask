package com.fahmy.task.presentation.ui.menu

import com.fahmy.task.domian.model.Tag

sealed class MenuIntent {
    object LoadTags : MenuIntent()
    data class LoadItemsByTagName(val tag: Tag) : MenuIntent()
}
