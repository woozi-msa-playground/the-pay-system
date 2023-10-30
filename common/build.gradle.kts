dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.axonframework:axon-configuration:4.7.3")
    implementation("org.axonframework:axon-spring-boot-starter:4.7.3")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:4.7.0")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin-test:4.7.0")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
