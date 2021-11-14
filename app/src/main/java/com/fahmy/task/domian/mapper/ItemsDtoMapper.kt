package com.fahmy.task.domian.mapper

import com.fahmy.task.data.dto.ItemDto
import com.fahmy.task.domian.model.Item

class ItemsDtoMapper : DomainMapper<ItemDto, Item> {

    override fun mapToDomainModel(model: ItemDto): Item {
        return Item(
            id =  model.id ,
            description = model.description ,
            name =  model.name ,
            photoUrl = model.photoUrl
        )
    }


}