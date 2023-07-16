package ingsis.roles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.metrics.export.datadog.EnableDatadogMetrics

@SpringBootApplication
@EnableDatadogMetrics
class RolesApplication

fun main(args: Array<String>) {
	runApplication<RolesApplication>(*args)
}
