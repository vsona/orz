#roy-base-library
```
//copy to project.gradle
repositories {
jcenter()
maven { url "https://jitpack.io" }
}

def versionMajor = 1
def versionMinor = 0
def versionPatch = 0

ext {
versionCode = versionMajor * 100 + versionMinor * 10 + versionPatch
versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

// Sdk and tools
minSdkVersion = 15
targetSdkVersion = 23
compileSdkVersion = 23
buildToolsVersion = "23.0.3"

// App dependencies
supportLibraryVersion = '23.4.0'
junitVersion = '4.12'
}
```