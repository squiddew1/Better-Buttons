object Properties {
	const val MINECRAFT_VERSION = "26.3"
	const val LOADER_VERSION = "0.19.5"
	const val VERSION = "0.1.0"
}

plugins {
	id("net.fabricmc.fabric-loom") version "1.18-SNAPSHOT"
}

repositories {
	mavenCentral()
	maven {
		name = "Terraformers"
		url = uri("https://maven.terraformersmc.com/releases/")
		content {
			includeGroupAndSubgroups("com.terraformersmc")
			includeGroup("dev.emi")
		}
	}
}

base {
	archivesName = "better-buttons-fabric"

	version = getFullVersion()
	group = "me.squiddew.betterbuttons"
}

dependencies {
	minecraft("com.mojang:minecraft:${Properties.MINECRAFT_VERSION}")
	implementation("net.fabricmc:fabric-loader:${Properties.LOADER_VERSION}")
	implementation("com.terraformersmc:modmenu:21.0.0")
}

tasks.processResources {
	inputs.property("version", version)

	filesMatching("fabric.mod.json") {
		expand("version" to version)
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.release = 25
}

tasks.jar {
	from("LICENSE.md") {
		rename { "${it}_${project.name}" }
	}
}

fun getFullVersion(): String {
	val version = Properties.VERSION
	val builder = StringBuilder()
	val minecraftVersion = Properties.MINECRAFT_VERSION

	builder.append("$version+mc$minecraftVersion")

	return builder.toString()
}