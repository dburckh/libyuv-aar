# libyuv-aar
This a prefab AAR for libyuv.  It allows you to pull in a prebuilt version of libyuv into your app.  This is intend for C/C++ NDK developers.

For Java/Kotlin use, this is an excellent library: 
https://github.com/crow-misia/libyuv-android  

## Why use a Prefab?
Prefab AARs greatly simplify integrating C/C++ libraries into NDK projects.  Since the library is pre-built, your build should also be faster.

## Checkout
This project uses a git submobule linked to https://chromium.googlesource.com/libyuv/libyuv.  As such, you will need to do a special version of clone that also retrieves the submodule

`git clone --recurse-submodules https://github.com/dburckh/libyuv-aar.git`

## Using a Prefab AAR
You'll need to get/build the AAR.  
1. You can do it through the Gradle tab or command line, like below.

    `gradlew yuv::assemble`
2. You can download also it from here.  https://github.com/dburckh/libyuv-aar/packages

Next you'll need to add the AAR as a dependency.
1.  Since the AAR has no dependencies you can add it directly to your project in Studio.  Under Project Structure->Your Module->+ (add dependancy) -> JAR/ARR dependancy.  
2. You can use it out of the Gitbub repo.  (See Using Github's repo)

Finally, you need to add the following to your CMakeLists.txt.  See the sample app CMakeLists.txt or the [AGP Prefab Documentation](https://developer.android.com/build/dependencies?agpversion=4.1#using-native-dependencies) more info.  
```CMake
#Add this line
find_package(yuv REQUIRED CONFIG)
#Update this entry
target_link_libraries(yourLibrary 
    ...
    yuv::yuv)
```
### Using Github's repo
Unfortunately, githubs repo requires authentication, so you'll need to set GPR_USER and GPR_API_KEY in your env.  GPR_API_KEY is your token, NOT your password.  You'll also need to add this to your settings.gradle
```Groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven {
            url = 'https://maven.pkg.github.com/dburckh/libyuv-aar'
            credentials {
                username = System.getenv("GPR_USER")
                //This password expires, so it will need to updated in environment
                password = System.getenv("GPR_API_KEY")
            }
        }
        mavenCentral()
    }
}
```
After that, you can just add the dependancy as normal
```Groovy
dependencies {
    implementation "com.homesoft.android:libyuv-aar:$version"
```
