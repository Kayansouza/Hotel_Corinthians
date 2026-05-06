import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    standardInput = System.`in`
}

plugins {
    id("org.springframework.boot") version "3.2.5" // Use a versão 3.x estável
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23" // Essencial para Kotlin + Spring
    kotlin("plugin.jpa") version "1.9.23"   // Essencial para o banco de dados
}

group = "com.kayan"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // Web e API (Para as rotas do hotel)
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // Banco de Dados (Para substituir o HotelState)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("com.h2database:h2") // Útil para testes rápidos
    // API de Dinheiro (Interfaces)
    implementation("javax.money:money-api:1.1")
    // Implementação Moneta (Lógica real)
    implementation("org.javamoney:moneta:1.4.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    // Validação de dados (E-mails, nomes, etc)
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Suporte para JSON com Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Coroutines (Para a alta concorrência exigida no PDF)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Driver do MySQL (ou PostgreSQL se preferir)
    runtimeOnly("com.mysql:mysql-connector-j")

    // Bean Validation para garantir dados corretos (Requisito 2.2)[cite: 1]
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21"
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}