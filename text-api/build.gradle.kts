plugins {
    `java-library`
}

dependencies {
    implementation(project(":collections-api"))
    implementation(project(":console-api"))
    implementation(project(":control-api"))
    implementation(project(":debug-api"))
    implementation(project(":utils-api"))
    implementation(libs.annotations)
    compileOnly(libs.lombok)
}