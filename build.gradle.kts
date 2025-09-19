plugins {
    id("java")
    id("com.vanniktech.maven.publish") version "0.34.0"
}

group = "io.github.looming-echo"
version = "1.0.1"

allprojects {
    group = rootProject.group
    version = rootProject.version
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("uberJar") {
    archiveBaseName.set("prism")
    archiveVersion.set(version.toString())

    from(subprojects.map { it.the<SourceSetContainer>()["main"].output })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Implementation-Title"] = "Prism API"
        attributes["Implementation-Version"] = version
        attributes["Automatic-Module-Name"] = "io.github.looming_echo.prism"
    }
}

artifacts {
    add("archives", tasks.named("uberJar"))
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates(group.toString(), "prism", version.toString())

    pom {
        name.set("Prism")
        description.set("I/O Console and formatting library for Java and safely null code")
        inceptionYear.set("2025")
        url.set("https://github.com/rickmvi/Prism")

        licenses {
            license {
                name.set("GNU Lesser General Public License v3.0")
                url.set("https://www.gnu.org/licenses/lgpl-3.0.html")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("rickmvi")
                name.set("Rick M. Viana")
                url.set("https://github.com/rickmvi")
            }
        }

        scm {
            url.set("https://github.com/rickmvi/Prism")
            connection.set("scm:git:git://github.com/rickmvi/Prism.git")
            developerConnection.set("scm:git:ssh://git@github.com/rickmvi/Prism.git")
        }
    }
}
