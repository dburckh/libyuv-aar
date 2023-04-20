# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

# Declares and names the project.

project("yuv")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        yuv

        # Sets the library as a shared library.
        STATIC

        # Provides a relative path to your source file(s).
        libyuv/source/compare.cc
        libyuv/source/compare_common.cc
        libyuv/source/compare_gcc.cc
        libyuv/source/compare_msa.cc
        libyuv/source/compare_neon.cc
        libyuv/source/compare_neon64.cc
        libyuv/source/compare_win.cc
        libyuv/source/convert.cc
        libyuv/source/convert_argb.cc
        libyuv/source/convert_from.cc
        libyuv/source/convert_from_argb.cc
        libyuv/source/convert_to_argb.cc
        libyuv/source/convert_to_i420.cc
        libyuv/source/cpu_id.cc
        libyuv/source/planar_functions.cc
        libyuv/source/rotate.cc
        libyuv/source/rotate_any.cc
        libyuv/source/rotate_argb.cc
        libyuv/source/rotate_common.cc
        libyuv/source/rotate_gcc.cc
        libyuv/source/rotate_msa.cc
        libyuv/source/rotate_neon.cc
        libyuv/source/rotate_neon64.cc
        libyuv/source/rotate_win.cc
        libyuv/source/row_any.cc
        libyuv/source/row_common.cc
        libyuv/source/row_gcc.cc
        libyuv/source/row_msa.cc
        libyuv/source/row_neon.cc
        libyuv/source/row_neon64.cc
        libyuv/source/row_win.cc
        libyuv/source/scale.cc
        libyuv/source/scale_any.cc
        libyuv/source/scale_argb.cc
        libyuv/source/scale_common.cc
        libyuv/source/scale_gcc.cc
        libyuv/source/scale_msa.cc
        libyuv/source/scale_neon.cc
        libyuv/source/scale_neon64.cc
        libyuv/source/scale_rgb.cc
        libyuv/source/scale_uv.cc
        libyuv/source/scale_win.cc
        libyuv/source/video_common.cc)

target_include_directories(yuv
        PUBLIC
        libyuv/include/
        )
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        yuv

        # Links the target library to the log library
        # included in the NDK.
        )