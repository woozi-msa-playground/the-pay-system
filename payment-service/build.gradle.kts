plugins {
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.axonframework:axon-configuration:4.7.3")
    implementation("org.axonframework:axon-spring-boot-starter:4.7.3")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:4.7.0")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin-test:4.7.0")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
    annotation("org.axonframework.spring.stereotype.Aggregate")
    annotation("org.axonframework.spring.stereotype.Saga")
}

tasks.getByName("bootJar") {
    enabled = true
}