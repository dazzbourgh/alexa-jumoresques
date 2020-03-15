package zhi.yest.jumoresquesrefresherservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.scheduling.annotation.EnableScheduling
import zhi.yest.jumoresquesrefresherservice.config.GcpConfig

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableConfigurationProperties(GcpConfig::class)
class JumoresquesRefresherServiceApplication

fun main(args: Array<String>) {
    runApplication<JumoresquesRefresherServiceApplication>(*args)
}
