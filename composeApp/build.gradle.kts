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

    //Firebase
    alias(libs.plugins.googleServices)
    //Room
    id("androidx.room")
}


//room {
//    schemaDirectory("$projectDir/schemas")
//}

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
           // export("io.github.mirzemehdi:kmpnotifier:1.4.0")
            baseName = "ComposeApp"
            isStatic = true
            linkerOpts.add("-lsqlite3")

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
            implementation("com.google.firebase:firebase-messaging-ktx:23.4.1")
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
            implementation(libs.kotlinx.serialization.json)


            //FIREBASE
            implementation(libs.gitlive.firebase.firestore)
            implementation(libs.gitlive.firebase.auth)
           // dependencies { api("io.github.mirzemehdi:kmpnotifier:1.4.0") }
                //implementation("com.google.firebase:firebase-messaging-ktx:23.4.1")


            implementation("androidx.room:room-runtime:2.7.0-alpha11")
            implementation("androidx.sqlite:sqlite-bundled:2.5.0-alpha01")
            //ksp("androidx.room:room-compiler:2.7.0-alpha11")


            //implementation("com.github.arkivanov:decompose-extensions-compose-jetbrains:0.8.0")


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

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.foundation.android)
    debugImplementation(compose.uiTooling)
    ksp("androidx.room:room-compiler:2.7.0-alpha11")
    annotationProcessor("androidx.room:room-compiler:2.7.0-alpha11")
    add("kspAndroid", "androidx.room:room-compiler:2.7.0-alpha11")
    add("kspIosX64", "androidx.room:room-compiler:2.7.0-alpha11")
}

