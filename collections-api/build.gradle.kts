plugins {
    `java-library`
}

dependencies {
    implementation(project(":control-api"))
    implementation(project(":console-api"))
    implementation(project(":utils-api"))
    implementation(project(":text-api"))
    implementation(libs.annotations)
    compileOnly(libs.lombok)
}