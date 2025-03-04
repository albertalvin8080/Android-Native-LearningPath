plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.albert.x06_hydrate_me"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.albert.x06_hydrate_me"
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

    // Old way (groovy?)
//    flavorDimensions "monetization"
//    productFlavors {
//        free {
//            dimension "monetization"
//            applicationIdSuffix ".free"
//            versionNameSuffix "-free"
//        }
//        free {
//            dimension "monetization"
//            applicationIdSuffix ".pro"
//            versionNameSuffix "-pro"
//        }
//    }

//    flavorDimensions.add("monetization")
    flavorDimensions += "monetization"
    productFlavors {
        create("free") {
            dimension = "monetization"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
        }
        create("pro") {
            dimension = "monetization"
            applicationIdSuffix = ".pro"
            versionNameSuffix = "-pro"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}