package com.kuby.kbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class KbotApplication

fun main(args: Array<String>) {
	runApplication<KbotApplication>(*args)
}
