plugins {
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

    group = "zhi.yest"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<Pmd> {
        ruleSetConfig = rootProject.resources.text.fromFile("config/pmd/pmd.xml")
    }

    configure<com.google.cloud.tools.jib.gradle.JibExtension> {
        to.image = "gcr.io/alexa-jumoresques/${project.name}:${project.version}"
    }
}