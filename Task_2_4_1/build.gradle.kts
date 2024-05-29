plugins {
    id("java")
    jacoco
    checkstyle
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
    implementation("org.freemarker:freemarker:2.3.30")
    implementation("com.puppycrawl.tools:checkstyle:10.15.0")
    implementation("com.github.stefanbirkner:system-lambda:1.2.1")
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