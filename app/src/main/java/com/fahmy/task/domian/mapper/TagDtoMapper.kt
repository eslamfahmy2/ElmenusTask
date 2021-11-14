package com.fahmy.task.domian.mapper

import com.fahmy.task.data.dto.TagDto
import com.fahmy.task.domian.model.Tag

class TagDtoMapper : DomainMapper<TagDto, Tag> {

    override fun mapToDomainModel(model: TagDto): Tag {
        return Tag(
            tagName = model.tagName,
            photoURL = model.photoURL
        )
    }


}