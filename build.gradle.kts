plugins {
    id("java")
    id("com.vanniktech.maven.publish") version "0.34.0"
}

group = "org.looming.echo"
version = "1.0.0"

allprojects {
    group = rootProject.group
    version = rootProject.version
    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
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

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates(group.toString(), "looming", version.toString())

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