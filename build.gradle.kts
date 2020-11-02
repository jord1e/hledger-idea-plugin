import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
import org.jetbrains.intellij.tasks.RunIdeTask

plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.intellij") version "0.5.0"
    id("org.jetbrains.grammarkit") version "2020.2.1"
}

group = "nl.jord1e.pta.idea"
version = "0.1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://www.jetbrains.com/intellij-repository/releases")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
//    implementation("com.jetbrains.intellij.platform:core:202.7660.26")
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
        dependsOn("generateLexers")
        systemProperty("idea.is.internal", true)
    }

    task("generateLexers") {
        dependsOn(":generateRulesLexer")
        dependsOn(":generateJournalLexer")
    }

    task<GenerateLexer>("generateRulesLexer") {
        dependsOn(":generateRulesParser")
        source = "src/main/grammars/Rules.flex"
        targetDir = "src/main/gen/nl/jord1e/idea/pta/rules/lang/lexer"
        targetClass = "RulesLexer"
        purgeOldFiles = true
        outputs.upToDateWhen { false }
    }

    task<GenerateParser>("generateRulesParser") {
        source = "src/main/grammars/Rules.bnf"
        targetRoot = "src/main/gen/"
        pathToParser = "/nl/jord1e/idea/pta/rules/lang/parser/RulesParser.java"
        pathToPsiRoot = "/nl/jord1e/idea/pta/rules/lang/psi"
        purgeOldFiles = true
        outputs.upToDateWhen { false }
    }

    task<GenerateLexer>("generateJournalLexer") {
        dependsOn(":generateJournalParser")
        source = "src/main/grammars/Journal.flex"
        targetDir = "src/main/gen/nl/jord1e/idea/pta/journal/lang/lexer"
        targetClass = "JournalLexer"
        purgeOldFiles = true
        outputs.upToDateWhen { false }
    }

    task<GenerateParser>("generateJournalParser") {
        source = "src/main/grammars/Journal.bnf"
        targetRoot = "src/main/gen/"
        pathToParser = "/nl/jord1e/idea/pta/journal/lang/parser/JournalParser.java"
        pathToPsiRoot = "/nl/jord1e/idea/pta/journal/lang/psi"
        purgeOldFiles = true
        outputs.upToDateWhen { false }
    }

}
