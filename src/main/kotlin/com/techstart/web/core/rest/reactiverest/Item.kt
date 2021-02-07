package com.techstart.web.core.rest.reactiverest

import org.springframework.data.annotation.Id


data class Item(@Id val id: String?, val value: String)