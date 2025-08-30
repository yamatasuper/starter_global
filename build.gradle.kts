plugins {
	java
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("io.freefair.lombok") version "8.6"  // Добавляем lombok для удобства
	id("maven-publish")
}


group = "com.example"
version = "1.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

// ОТКЛЮЧАЕМ BOOTJAR ↓
tasks.bootJar {
	enabled = false
}

// НАСТРАИВАЕМ ОБЫЧНЫЙ JAR ↓
tasks.jar {
	enabled = true
	archiveClassifier.set("")
	archiveBaseName.set("exception-handler-starter")
}

// КОНФИГУРАЦИЯ ПУБЛИКАЦИИ ↓
publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			artifact(tasks.jar)
			groupId = "com.example"
			artifactId = "exception-handler-starter"
			version = "1.0.0"
		}
	}
	repositories {
		mavenLocal()
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
