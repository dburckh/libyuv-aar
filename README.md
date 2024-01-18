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
See the Jitpack.io website for detailed instructions on how to add the AAR to your project: https://jitpack.io/#dburckh/libyuv-aar

Next, you will need to add the following to your CMakeLists.txt.  See the sample app CMakeLists.txt or the [AGP Prefab Documentation](https://developer.android.com/build/dependencies?agpversion=4.1#using-native-dependencies) more info.  
```CMake
#Add this line
find_package(yuv REQUIRED CONFIG)
#Update this entry
target_link_libraries(yourLibrary 
    ...
    yuv::yuv)
```
