plugins {
    kotlin("jvm") version "1.9.24"
    application
}

group = "com.hotel"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin padrão
    implementation(kotlin("stdlib"))

    // JSON (muito útil pra APIs)
    implementation("com.google.code.gson:gson:2.10.1")

    // Logs (opcional mas profissional)
    implementation("org.slf4j:slf4j-simple:2.0.9")

    // Testes
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}