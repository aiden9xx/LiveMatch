object Testing {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.1"
    const val room = "androidx.room:room-testing:2.2.6"
    const val jUnit = "junit:junit:4.13"
    const val extJUnitKtx = "androidx.test.ext:junit-ktx:1.1.5"
    const val mockito = "org.mockito:mockito-inline:2.21.0"
    const val extJUnit = "androidx.test.ext:junit:1.1.5"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val testRunner = "androidx.test:runner:1.5.2"
    const val testRule = "androidx.test:rules:1.5.0"
    const val okHttp = "com.squareup.okhttp3:mockwebserver:4.9.0"
    const val core = "androidx.arch.core:core-testing:2.1.0"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:1.4.10"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0"
    const val gradle = "com.android.tools.build:gradle:4.1.1"
    const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:2.31-alpha"
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:9.2.1"
    const val materialDesign = "com.google.android.material:material:1.6.0"
    const val coil = "io.coil-kt:coil:1.0.0"
    const val gson = "com.google.code.gson:gson:2.8.7"
}

object Lifecycle {
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
}

object Hilt {
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:2.31-alpha"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0-alpha02"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02"
    const val hiltAndroid = "com.google.dagger:hilt-android:2.31-alpha"
}

object Moshi {
    const val moshi = "com.squareup.moshi:moshi-kotlin:1.11.0"
    const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:1.11.0"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val moshiRetrofitConverter = "com.squareup.retrofit2:converter-moshi:2.9.0"
    const val retrofit_response_adapter = "com.github.haroldadmin:NetworkResponseAdapter:4.2.1"
    const val retrofit_logging = "com.squareup.okhttp3:logging-interceptor:3.6.0"
    const val retrofit_okhttp3 = "com.squareup.okhttp3:okhttp"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val activityKtx = "androidx.activity:activity-ktx:1.1.0"
    const val coreKtx = "androidx.core:core-ktx:1.3.2"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:2.3.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.5.2"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.6"
    const val exoplayer = "com.google.android.exoplayer:exoplayer:2.18.5"
}

object Room {
    const val compiler = "androidx.room:room-compiler:2.4.0"
    const val ktx = "androidx.room:room-ktx:2.4.0"
    const val runtime = "androidx.room:room-runtime:2.4.0"
}