plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.example.a3m1l"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.a3m1l"
        minSdk = 28
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}
val room_version = "2.6.1"
val nav_version = "2.8.4"
dependencies {

//    Lottie
    implementation("com.airbnb.android:lottie:6.0.0")

    //    Navigation component
    implementation ("androidx.navigation:navigation-fragment:$nav_version")
    implementation ("androidx.navigation:navigation-ui:$nav_version")

    //indicator tochek
    implementation ("com.tbuonomo:dotsindicator:4.3")

    //ROOM
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
//    ksp("androidx.room:room-compiler:$room_version")
//    implementation("androidx.room:room-ktx:$room_version")

//Google Auth
    implementation(platform("com.google.firebase:firebase-bom:33.8.0"))
    implementation(libs.firebase.auth)
    implementation("com.google.android.gms:play-services-auth:21.3.0")

//    Android studio
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}