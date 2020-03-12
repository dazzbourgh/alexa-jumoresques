import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import io.spring.gradle.dependencymanagement.dsl.ImportsHandler
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.5.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
    kotlin("jvm") version "1.3.61" apply false
    kotlin("plugin.spring") version "1.3.61" apply false
    id("com.google.cloud.tools.jib") version "2.1.0" apply false
}

allprojects {
    repositories {
        jcenter()
    }
}

subprojects {
    apply(plugin = "checkstyle")
    apply(plugin = "pmd")
    apply(plugin = "com.google.cloud.tools.jib")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    group = "zhi.yest"
    version = "0.0.1-SNAPSHOT"

    extra["springCloudVersion"] = "Hoxton.SR1"

    tasks.withType<Pmd> {
        ruleSetConfig = rootProject.resources.text.fromFile("config/pmd/pmd.xml")
    }

    configure<com.google.cloud.tools.jib.gradle.JibExtension> {
        to.image = "gcr.io/alexa-jumoresques/${project.name}:${project.version}"
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
    }

    repositories {
        mavenCentral()
    }

    val implementation by configurations
    val testImplementation by configurations

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testImplementation("io.projectreactor:reactor-test")
    }

    configure<DependencyManagementExtension> {
        imports(delegateClosureOf<ImportsHandler> {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        })
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}