package zhi.yest.jumoresquesrefresherservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class JumoresquesRefresherServiceApplication

fun main(args: Array<String>) {
    runApplication<JumoresquesRefresherServiceApplication>(*args)
}
