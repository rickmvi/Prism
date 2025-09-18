plugins {
    `java-library`
}

dependencies {
    implementation(project(":collections-api"))
    implementation(project(":control-api"))
    implementation(project(":utils-api"))
    implementation(project(":debug-api"))
    implementation(project(":text-api"))
    implementation(libs.annotations)
    compileOnly(libs.lombok)
}