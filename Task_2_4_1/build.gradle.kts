plugins {
    id("java")
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    implementation("org.apache.groovy:groovy:4.0.2")
    implementation("org.projectlombok:lombok:1.18.22")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.gradle:gradle-tooling-api:7.3-20210825160000+0000")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.10")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("$buildDir/jacoco/jacocoHtml"))
    }
}