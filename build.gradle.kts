import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
import org.jetbrains.intellij.tasks.RunIdeTask

plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.intellij") version "0.5.0"
    id("org.jetbrains.grammarkit") version "2020.2.1"
}

group = "nl.jord1e.hledger.idea"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

intellij {
    version = "2020.2.3"
    setPlugins("PsiViewer:202-SNAPSHOT.3")
}

sourceSets {
    main {
        java.srcDirs("src/main/gen", "src/main/kotlin")
    }
}

tasks {
//    withType<JavaCompile> {
//        sourceCompatibility = "1.8"
//        targetCompatibility = "1.8"
//    }
//
//    withType<KotlinCompile> {
//        sourceCompatibility = "1.8"
//        targetCompatibility = "1.8"
//        kotlinOptions.jvmTarget = "1.8"
//    }

    withType<RunIdeTask> {
        dependsOn(":generateRulesLexer")
        systemProperty("idea.is.internal", true)
    }

    task<GenerateLexer>("generateRulesLexer") {
        dependsOn(":generateRulesParser")
        source = "src/main/kotlin/nl/jord1e/hledger/idea/rules/Rules.flex"
        targetDir = "src/main/gen/nl/jord1e/hledger/idea/rules/"
        targetClass = "RulesLexer"
        purgeOldFiles = true
        outputs.upToDateWhen { false }
    }

    task<GenerateParser>("generateRulesParser") {
        source = "src/main/kotlin/nl/jord1e/hledger/idea/rules/Rules.bnf"
        targetRoot = "src/main/gen/"
        pathToParser = "/nl/jord1e/hledger/idea/rules/parser/RulesParser.java"
        pathToPsiRoot = "/nl/jord1e/hledger/idea/rules/psi"
        purgeOldFiles = true
        outputs.upToDateWhen { false }
    }
}
