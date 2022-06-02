package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler


fun DependencyHandler.materialDesign() {
    implementation("com.google.android.material:material:1.6.0")
}