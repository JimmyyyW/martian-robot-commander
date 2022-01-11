package com.example.martianrobotcommander.integration

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
class IntegrationTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    @DisplayName("it responds with test output when supplied test input")
    fun itRespondsWithCorrectOutput() {
        val response: ResponseEntity<String> = testRestTemplate.postForEntity("/movement", """53
            1 1 E
            RFRFRFRF
            3 2 N
            FRRFLLFFRRFLL
            0 3 W
            LLFFFLFLFL
        """.trimIndent(), String::class)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).contains("1 1 E")
        Assertions.assertThat(response.body).contains("3 3 N LOST")
        Assertions.assertThat(response.body).contains("2 3 S")
    }

}