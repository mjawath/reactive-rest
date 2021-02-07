package com.techstart.web.core.rest.reactiverest


import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

class ItemHandler(private val itemRepository: ItemRepository) {

    fun getAllItems(request: ServerRequest) = ServerResponse.ok()
        .contentType(APPLICATION_JSON)
        .body(itemRepository.findAll(), Item::class.java)

    fun getItem(request: ServerRequest) = ServerResponse.ok()
        .contentType(APPLICATION_JSON)
        .body(itemRepository.findById(request.pathVariable("id")), Item::class.java)
        .switchIfEmpty(ServerResponse.notFound().build())

    fun updateItem(request: ServerRequest) = request.bodyToMono(Item::class.java)
        .zipWith(this.itemRepository.findById(request.pathVariable("id")), { item, existingItem ->
            Item(existingItem.id, item.value)
        }).flatMap(::saveAndRespond).switchIfEmpty(ServerResponse.notFound().build())

    fun addItem(request: ServerRequest) = request.bodyToMono(Item::class.java).flatMap(::saveAndRespond)

    private fun saveAndRespond(item: Item) = ServerResponse.ok()
        .contentType(APPLICATION_JSON)
        .body(itemRepository.save(item), Item::class.java)
}