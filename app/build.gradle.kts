plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services) // Đã đúng
}

android {
    namespace = "com.example.tlucontact"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tlucontact"
        minSdk = 24
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)                // Firebase Authentication
    implementation(libs.credentials)                 // Credentials API
    implementation(libs.credentials.play.services.auth) // Play Services Auth
    implementation(libs.googleid)                    // Google ID
    implementation(libs.firebase.firestore)          // Firebase Firestore
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}