package com.techstart.web.core.rest.reactiverest

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.APPLICATION_XML
import org.springframework.web.reactive.function.server.router

fun router(itemHandler: ItemHandler) = router {
    accept(APPLICATION_JSON,APPLICATION_XML).nest {
        "/api".nest {
            "/items".nest {
                GET("/", itemHandler::getAllItems)
            }
            "/items".nest {
                PUT("/{id}", itemHandler::updateItem)
                GET("/{id}", itemHandler::getItem)
                POST("/add", itemHandler::addItem)
            }
        }
    }
}

//https://medium.com/@oscdj113/spring-boot-2-with-webflux-kotlin-bb1a71167591