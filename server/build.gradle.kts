plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.pax.messenger"
version = "1.0.0"

application {
    mainClass.set("org.pax.messenger.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    compilerOptions {
        optIn.add("kotlin.uuid.ExperimentalUuidApi")
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serialization.jackson)
    implementation(libs.ktor.serverNetty)
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-server-content-negotiation")

    implementation("org.flywaydb:flyway-core:9.20.0")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.jetbrains.exposed:exposed-core:0.56.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.56.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.56.0")
    implementation("org.jetbrains.exposed:exposed-json:0.56.0")
    implementation("org.jetbrains.exposed:exposed-java-time:0.56.0")

    implementation("io.insert-koin:koin-ktor3:4.1.0-Beta8")
    implementation("io.insert-koin:koin-logger-slf4j:4.1.0-Beta8")

    implementation("at.favre.lib:bcrypt:0.10.2")

    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}