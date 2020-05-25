package com.user.restapi.user

import com.user.restapi.user.repository.UserRepository
import com.user.restapi.userfilestorage.FileStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class UserApplication{
	@Autowired
	lateinit var fileStorage: FileStorage;

	@Bean
	fun run() = CommandLineRunner {
		fileStorage.deleteAll()
		fileStorage.init()
	}
}
fun main(args: Array<String>) {
	runApplication<UserApplication>(*args)
}
