
apply { plugin("kotlin") }

jvmTarget = "1.8"

dependencies {
    compile(project(":compiler:util"))
    compile(project(":compiler:cli-common"))
    compile(project(":compiler:frontend"))
    compile(project(":compiler:frontend.java"))
    compile(project(":compiler:frontend.script"))
    compile(project(":compiler:backend-common"))
    compile(project(":compiler:backend"))
    compile(project(":compiler:light-classes"))
    compile(project(":compiler:serialization"))
    compile(project(":compiler:plugin-api"))
    compile(project(":js:js.translator"))
    compile(project(":js:js.serializer"))
    compile(project(":js:js.dce"))
    compile(ideaSdkCoreDeps(*(rootProject.extra["ideaCoreSdkJars"] as Array<String>)))
    compile(commonDep("org.fusesource.jansi", "jansi"))
    compile(commonDep("org.jline", "jline"))
    compile(files("${System.getProperty("java.home")}/../lib/tools.jar"))

    // Used in REPL code insight
    compileOnly(project(":idea:idea-core"))

    testCompile(project(":compiler:backend"))
    testCompile(project(":compiler:cli"))
    testCompile(project(":compiler:tests-common"))
    testCompile(projectTests(":compiler:tests-common"))
    testCompile(commonDep("junit:junit"))
}

sourceSets {
    "main" {
        projectDefault()
        java.srcDirs("../../plugins/annotation-collector/src",
                     "../builtins-serializer/src",
                     "../javac-wrapper/src")
    }
    "test" {
        java.srcDirs("../../plugins/annotation-collector/test")
    }
}

testsJar {}

projectTest {
    workingDir = rootDir
}