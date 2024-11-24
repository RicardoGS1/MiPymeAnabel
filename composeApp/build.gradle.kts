import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    //KMP
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)

    alias(libs.plugins.gradleBuildConfig)
    id("com.google.gms.google-services")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {

            //COMPOSE
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            //NETWORK
            implementation(libs.ktor.client.okhttp)

            //ID
            implementation(libs.koin.android)

            //FIREBASE
            implementation(project.dependencies.platform(libs.android.firebase.bom))
        }
        commonMain.dependencies {

            //COMPOSE
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.moko.permissions.compose)

            //NAVIGATION
            implementation(libs.androidx.navigation.compose)

            //COROUTINES
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.kotlinx.coroutines.core)

            //COIL
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            //ID
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(project.dependencies.platform(libs.koin.bom))

            //NETWORK
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentnegotiation)

            implementation(libs.ktor.serialization.json)
            //implementation(libs.kotlinx.serialization.json)


            //FIREBASE
            implementation(libs.gitlive.firebase.firestore)


        }

        iosMain.dependencies {
            //NETWORK
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.virtualworld.mipymeanabel"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.virtualworld.mipymeanabel"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.ui.android)
    debugImplementation(compose.uiTooling)
}

