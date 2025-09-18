dependencies {
    implementation(project(":collections-api"))
    implementation(project(":utils-api"))
    implementation(project(":text-api"))
    implementation(libs.annotations)
    compileOnly(libs.lombok)
}