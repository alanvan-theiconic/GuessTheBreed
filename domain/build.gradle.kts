plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.rxjava)

    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
}
