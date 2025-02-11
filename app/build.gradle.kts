@file:Suppress("UnstableApiUsage", "SpellCheckingInspection")

import com.android.build.gradle.internal.api.ApplicationVariantImpl
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.CRC32

val applicationId = "org.autojs.autojs6"
val sign = Sign("$rootDir/sign.properties")
val versions = Versions("$rootDir/version.properties")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

dependencies /* Unclassified */ {
    // LeakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")

    // Android supports
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.android.material:material:1.7.0")

    // SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Common Markdown
    implementation("com.github.atlassian:commonmark-java:commonmark-parent-0.9.0")

    // Licenses Dialog
    implementation("de.psdev.licensesdialog:licensesdialog:2.2.0")

    // Commons Lang
    implementation("org.apache.commons:commons-lang3:3.12.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.14.2")
    kapt("com.github.bumptech.glide:compiler:4.14.2")

    // Joda Time
    implementation("joda-time:joda-time:2.12.2")

    // Flurry
    implementation("com.flurry.android:analytics:14.0.0")

    // Bugly
    implementation(project(":libs:com.tencent.bugly.crashreport-4.0.4"))

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.9")

    // Webkit
    implementation("androidx.webkit:webkit:1.5.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Zip4j
    implementation("net.lingala.zip4j:zip4j:2.11.2")

    // Log4j
    implementation("de.mindpipe.android:android-logging-log4j:1.0.3")
    implementation("log4j:log4j:1.2.17")

    // Preference
    implementation("androidx.preference:preference-ktx:1.2.0")

    // RootShell
    implementation("com.github.Stericson:RootShell:1.6")

    // JDeferred
    implementation("org.jdeferred:jdeferred-android-aar:1.2.6")

    // Rx
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1@aar")

    // Device Names
    implementation("com.jaredrummler:android-device-names:2.1.1")

    // Version Compare
    implementation("io.github.g00fy2:versioncompare:1.5.0")

    // Terminal emulator
    implementation(project(":libs:jackpal.androidterm.libtermexec-1.0"))
    implementation(project(":libs:jackpal.androidterm.emulatorview-1.0.42"))
    implementation(project(":libs:jackpal.androidterm-1.0.70"))

    // Dex
    implementation(files("$rootDir/libs/com.android.dx-1.7.0.jar"))

    // LocaleHelper
    implementation(project(":libs:com.zeugmasolutions.localehelper-1.5.1"))

    // OpenCV
    implementation(project(":libs:org.opencv-4.5.5"))

    // Android Job
    implementation(project(":libs:android-job-simplified-1.4.3"))

    // Rhino
    implementation(files("$rootDir/libs/org.mozilla.rhino-1.7.15-snapshot.jar"))

    // Tasker Plugin
    implementation(files("$rootDir/libs/android-plugin-client-sdk-for-locale-9.0.0.jar"))
}

dependencies /* Test */ {
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

dependencies /* Annotations */ {
    // Android Annotations
    kapt("org.androidannotations:androidannotations:4.8.0")
    implementation("org.androidannotations:androidannotations-api:4.8.0")
    implementation("androidx.annotation:annotation:1.5.0")

    // JCIP Annotations
    implementation("net.jcip:jcip-annotations:1.0")

    // ButterKnife
    kapt("com.jakewharton:butterknife-compiler:10.2.3")
    implementation("com.jakewharton:butterknife:10.2.3")

    // EventBus
    implementation("org.greenrobot:eventbus:3.3.1")
}

dependencies /* AppCompat */ {
    implementation("androidx.appcompat:appcompat") {
        version {
            strictly("1.4.2")
            because("version 1.5.0 duplicates some classes")
        }
    }

    // AppCompat for legacy views (such as JsTextViewLegacy)
    implementation(project(":libs:androidx.appcompat-1.0.2")) {
        setVersion("1.0.2")
    }
}

dependencies /* Material Dialogs */ {
    // Material Dialogs
    // TODO by SuperMonster003 on Feb 5, 2022.
    //  ! Upgrade to 3.3.0 (more difficult than expected)
    val configuration: (ExternalModuleDependency).() -> Unit = {
        version {
            prefer("0.9.6.0")
            because("not ready to update to version 3.3.0 yet")
        }
    }
    configuration.let {
        implementation("com.afollestad.material-dialogs:core", it)
        implementation("com.afollestad.material-dialogs:commons", it)
    }
}

dependencies /* Layout */ {
    // Expandable Layout
    implementation("com.github.aakira:expandable-layout:1.6.0")

    // Expandable RecyclerView
    implementation("com.bignerdranch.android:expandablerecyclerview:3.0.0-RC1")

    // Flexible Divider
    implementation("com.yqritc:recyclerview-flexibledivider:1.4.0")
}

dependencies /* View */ {
    // RoundedImageView
    implementation("com.makeramen:roundedimageview:2.3.0")

    // CircleImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Animated SVG
    implementation("com.jaredrummler:animated-svg-view:1.0.6")
}

dependencies /* GitHub API */ {
    implementation(files("$rootDir/libs/github-api-1.306.jar"))

    implementation("commons-io:commons-io") {
        version {
            strictly("2.8.0")
            because("Exception on newer versions: 'NoClassDefFoundError: org.apache.commons.io.IOUtils'")
        }
    }

    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1") {
        because("compatibility for Android API Level < 26 (Android 8.0) [O]")
    }

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.0") {
        because("compatibility of java.time.* for Android API Level < 26 (Android 8.0) [O]")
    }
}

dependencies /* Auto.js Extensions */ {
    // Settings Compat
    implementation("com.github.hyb1996:settingscompat:1.1.5")

    // Enhanced Floaty
    implementation("com.github.hyb1996:EnhancedFloaty:0.31")

    // MultiLevelListView
    implementation("com.github.hyb1996:android-multi-level-listview:1.1")

    // Extracted from com.github.hyb1996:MutableTheme:1.0.0
    // @Legacy com.jrummyapps:colorpicker:2.1.7
    implementation("com.jaredrummler:colorpicker:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.github.ozodrukh:CircularReveal:2.0.1")

    // Auto.js APK Builder
    implementation("com.github.hyb1996:Auto.js-ApkBuilder:1.0.3")
}

dependencies /* Archived */ {
    // Kotlin
    // @Comment by SuperMonster003 on May 19, 2022.
    //  ! It is no longer necessary to declare a dependency on the stdlib library in any Kotlin Gradle project.
    //  ! The dependency is added by default.
    //  ! See https://kotlinlang.org/docs/gradle.html#dependency-on-the-standard-library
    // implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.21")

    // Google Guava
    // @Comment by SuperMonster003 on Jun 1, 2022.
    //  ! Not necessary for current project but worth keeping the trace.
    // implementation("com.google.guava:guava:31.1-jre")
}

dependencies /* Reserved for auto append by IDE */ {

}

android {
    namespace = applicationId
    compileSdk = versions.sdkVersionCompile

    defaultConfig {
        applicationId = applicationId
        minSdk = versions.sdkVersionMin
        targetSdk = versions.sdkVersionTarget
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = versions.javaVersion
        targetCompatibility = versions.javaVersion
    }

    packagingOptions {
        arrayOf(
            "META-INF/DEPENDENCIES",
            "META-INF/LICENSE",
            "META-INF/LICENSE.*",
            "META-INF/LICENSE-notice.*",
            "META-INF/license.*",
            "META-INF/NOTICE",
            "META-INF/NOTICE.*",
            "META-INF/notice.*",
            "META-INF/ASL2.0",
            "META-INF/*.kotlin_module",
        ).let { resources.excludes.addAll(it) }
    }

    kotlinOptions {
        jvmTarget = versions.javaVersion.toString()
        // freeCompilerArgs = listOf("-Xjvm-default=all-compatibility")
    }

    lint {
        abortOnError = false
    }

    signingConfigs {
        takeIf { sign.isValid }?.let {
            create("release") {
                storeFile = sign.properties["storeFile"]?.let { file(it as String) }
                keyPassword = sign.properties["keyPassword"] as String
                keyAlias = sign.properties["keyAlias"] as String
                storePassword = sign.properties["storePassword"] as String
            }
        }
    }

    buildTypes {
        arrayOf(
            getDefaultProguardFile("proguard-android.txt"),
            "proguard-rules.pro",
        ).let { proguardFiles ->
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(*proguardFiles)
                signingConfig = takeIf { sign.isValid }?.let { signingConfigs.getByName("release") }
            }
            getByName("debug") {
                isMinifyEnabled = getByName("release").isMinifyEnabled
                proguardFiles(*proguardFiles)
                signingConfig = getByName("release").signingConfig
            }
        }
    }

    defaultConfig {
        versionCode = versions.appVersionCode
        versionName = versions.appVersionName
        multiDexEnabled = true
        javaCompileOptions {
            annotationProcessorOptions {
                mapOf(
                    "resourcePackageName" to (this@defaultConfig.applicationId ?: this@Build_gradle.applicationId),
                    "androidManifestFile" to ("$projectDir/src/main/AndroidManifest.xml")
                ).let { arguments(it) }
            }
        }
        buildConfigField("String", "VERSION_DATE", "\"${Utils.getDateString("MMM dd, yyyy", "GMT+08:00")}\"")
    }

    applicationVariants.all {
        mergeAssetsProvider
            .configure {
                doLast {
                    mapOf(
                        "dir" to outputDir,
                        "includes" to listOf("declarations/**", "sample/declarations/**"),
                    ).let { delete(fileTree(it)) }
                }
            }
        outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { it.outputFileName = Utils.getOutputFileName(this@all as ApplicationVariantImpl, it) }
    }

    splits {
        // Configures multiple APKs based on ABI.
        abi {
            // Enables building multiple APKs per ABI.
            isEnable = true
            // By default, all ABIs are included, so use reset() and include to specify that we only
            // want APKs for x86 and x86_64.
            // Resets the list of ABIs that Gradle should create APKs for to none.
            reset()
            // Specifies a list of ABIs that Gradle should create APKs for.
            include("x86", "armeabi-v7a", "arm64-v8a", "x86_64")
            // Specifies that we do not want to also generate a universal APK that includes all ABIs.
            isUniversalApk = true
        }
    }
}

tasks {
    withType(JavaCompile::class.java) {
        options.encoding = "UTF-8"

        // @Hint by SuperMonster003 on May 18, 2022.
        //  ! Comment or remove this option if you are tired of plenty of warnings. :)
        // options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:unchecked"))
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = versions.javaVersion.toString()
        }
    }

    register<Copy>("appendDigestToReleasedFiles") {
        val src = "release"
        val dst = "${src}s"
        val ext = Utils.FILE_EXTENSION_APK

        from(src); into(dst); include("*.$ext")

        rename { name ->
            Utils.digestCRC32(file("${src}/$name")).let { digest ->
                name.replace("^(.+?)(\\.$ext)\$".toRegex(), "\$1-$digest\$2")
            }
        }

        doLast { println("Destination: ${file(dst)}") }
    }
}

extra {
    versions.handleIfNeeded(project)
}

class Sign(filePath: String) {

    var isValid = false
        private set

    val properties = Properties().also { props ->
        File(filePath).takeIf { it.exists() }?.let {
            props.load(FileInputStream(it))
            isValid = props.isNotEmpty()
        }
    }

}

class Versions(filePath: String) {

    private val properties = Properties()
    private val file = File(filePath).apply {
        if (!canRead()) {
            throw FileNotFoundException("Can't read file '$filePath'")
        }
        properties.load(FileInputStream(this))
    }

    val sdkVersionMin = properties["MIN_SDK_VERSION"].let { it as String }.toInt()
    val sdkVersionTarget = properties["TARGET_SDK_VERSION"].let { it as String }.toInt()
    val sdkVersionCompile = properties["COMPILE_SDK_VERSION"].let { it as String }.toInt()
    val appVersionName = properties["VERSION_NAME"] as String
    val appVersionCode = properties["VERSION_BUILD"].let { it as String }.toInt()
    val javaVersion = JavaVersion.toVersion(properties["JAVA_VERSION"] as String)

    private var isBuildNumberAutoIncremented = false
    private val minBuildTimeGap = Utils.hours2Millis(0.5)
    private val assembleTargets = mapOf("app" to listOf("debug", "release"))

    private val isBuildGapEnough
        get() = properties["BUILD_TIME"]?.let {
            Date().time - (it as String).toLong() > minBuildTimeGap
        } ?: false

    private fun appendToTask(project: Project, buildType: String) {
        project.tasks.getByName(Utils.getAssembleTaskName(buildType)).doLast {
            updateProperties()
            println()
            showInfo()
        }
    }

    private fun updateProperties() {
        if (isBuildGapEnough) {
            properties["VERSION_BUILD"] = "${appVersionCode + 1}"
            isBuildNumberAutoIncremented = true
        }
        properties["BUILD_TIME"] = "${Date().time}"
        properties.store(file.writer(), null)
    }

    fun showInfo() = arrayOf(
        "Version name: $appVersionName",
        "Version code: $appVersionCode${" [auto-incremented]".takeIf { isBuildNumberAutoIncremented } ?: ""}",
        "SDK versions: min [$sdkVersionMin] / target [$sdkVersionTarget] / compile [$sdkVersionCompile]",
        "Java version: $javaVersion",
    ).forEach { println(it) }

    fun handleIfNeeded(project: Project) = assembleTargets.forEach {
        val targetName = it.key
        val targetBuildType = it.value
        if (targetName == project.name) {
            project.gradle.taskGraph.whenReady(object : Action<TaskExecutionGraph> {
                override fun execute(taskGraph: TaskExecutionGraph) {
                    for (buildType in targetBuildType) {
                        if (taskGraph.hasTask(Utils.getAssembleFullTaskName(targetName, buildType))) {
                            return appendToTask(project, buildType)
                        }
                    }
                    return showInfo()
                }
            })
        }
    }

}

object Utils {

    const val FILE_EXTENSION_APK = "apk"

    fun hours2Millis(hour: Double) = hour * 3.6e6

    fun getDateString(format: String, timeZone: String): String {
        // e.g. May 23, 2011
        return SimpleDateFormat(format).also { it.timeZone = TimeZone.getTimeZone(timeZone) }.format(Date())
    }

    fun getOutputFileName(variant: ApplicationVariantImpl, output: BaseVariantOutputImpl): String {
        val autojs = variant.applicationId.replace("^.+\\.(.+)$".toRegex(), "$1") // e.g. autojs6
        val version = variant.versionName.replace("\\s".toRegex(), "-") // e.g. 6.1.0
        val architecture = output.getFilter("ABI") ?: "universal"
        val extension = FILE_EXTENSION_APK

        return "$autojs-v$version-$architecture.$extension".toLowerCase()
    }

    fun getAssembleTaskName(buildType: String) = "assemble${capitalize(buildType)}"

    fun getAssembleFullTaskName(name: String, buildType: String) = ":$name:${getAssembleTaskName(buildType)}"

    fun digestCRC32(file: File): String {
        val instance = CRC32()

        val fis = FileInputStream(file)
        val buffer = ByteArray(4096)
        var read: Int

        while (fis.read(buffer).also { read = it } > 0) {
            instance.update(buffer, 0, read)
        }

        return String.format("%08x", instance.value)
    }

    private fun capitalize(s: String) = "${s[0].toUpperCase()}${s.substring(1)}"

}
