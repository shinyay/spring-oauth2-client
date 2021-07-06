package com.google.shinyay.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringOauth2ClientApplication

fun main(args: Array<String>) {
	runApplication<SpringOauth2ClientApplication>(*args)
}
