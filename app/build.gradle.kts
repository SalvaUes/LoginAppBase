plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "sv.edu.ues.vl23003.loginappbase"
    compileSdk = 34

    defaultConfig {
        applicationId = "sv.edu.ues.vl23003.loginappbase"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

// FORZAR DEPENDENCIAS PARA EVITAR CONFLICTOS EN EQUIPO
configurations.all {
    resolutionStrategy {
        force("com.google.android.material:material:1.12.0")
        force("androidx.appcompat:appcompat:1.7.0")
        force("androidx.activity:activity:1.9.0")
        force("androidx.constraintlayout:constraintlayout:2.1.4")
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}