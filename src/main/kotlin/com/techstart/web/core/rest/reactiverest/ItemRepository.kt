package com.techstart.web.core.rest.reactiverest

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ItemRepository : ReactiveMongoRepository<Item, String>