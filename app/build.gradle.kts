import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.linchausen.netssdkproguardbug"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.linchausen.netssdkproguardbug"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val properties = Properties().apply {
        load(rootProject.file("local.properties").reader())
    }
    val merchantId = properties["NETS_MERCHANT"]
    val transactionId = properties["TRANSACTION_ID"]
    buildTypes {
        debug {
            buildConfigField("String", "NETAXEPT_MERCHANT_ID", "\"${merchantId.toString()}\"")
            buildConfigField("String", "TRANSACTION_ID", "\"${transactionId.toString()}\"")
        }
        release {
            buildConfigField("String", "NETAXEPT_MERCHANT_ID", "\"${merchantId.toString()}\"")
            buildConfigField("String", "TRANSACTION_ID", "\"${transactionId.toString()}\"")

            signingConfig = signingConfigs.getByName("debug")

            isMinifyEnabled = true
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
    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("eu.nets.pia:pia-sdk:2.7.2") {
        isTransitive = true
        isChanging = true
    }

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}