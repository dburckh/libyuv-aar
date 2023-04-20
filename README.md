# libyuv-aar
This a prefab AAR for libyuv.  It allows you to pull in a prebuilt version of libyuv into your app.  This is intend for C/C++ NDK developers.

For Java/Kotlin use, this is an excellent library: 
https://github.com/crow-misia/libyuv-android  

## Why use a Prefab?
Prefab AARs greatly simplify integrating C/C++ libraries into NDK projects.

## Checkout
This project uses a git submobule linked to https://chromium.googlesource.com/libyuv/libyuv.  As such, you will need to do a special version of clone that also retrieves the submodle

`git clone --recurse-submodules https://github.com/dburckh/libyuv-aar.git`

## Using a Prefab AAR
1. You'll need to get/build the AAR.  At some I'll host the AAR, but for now you can generate it by building the yuv project.  You can do it through the Gradle tab or command line, like below.

    `gradlew yuv::assemble`

2. Add the AAR as a project dependency.  The AAR has no dependencies, so you can add it directly.  Or  
3. Add the following lines to your CMakeLists.txt.  See sample app CMakeLists.txt or the official documentation link below. 
```CMake
#Add this line
find_package(yuv REQUIRED CONFIG)
#Update this entry
target_link_libraries(yourLibrary 
    ...
    yuv::yuv)
```

### Official Gradle Prefab documentation
https://developer.android.com/build/dependencies?agpversion=4.1#using-native-dependencies