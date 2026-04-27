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
    implementation(kotlin("stdlib"))

    // JSON (melhor opção moderna)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")

    // Logs
    implementation("ch.qos.logback:logback-classic:1.5.6")

    // Testes
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}

// --- ADICIONADO AQUI: Permite que o terminal leia o que você digita no teclado ---
// Permite teclado e arruma a leitura de acentos/emojis no terminal
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
    systemProperty("file.encoding", "UTF-8")
}