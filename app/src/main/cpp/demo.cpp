#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include "libyuv.h"

extern "C" JNIEXPORT int JNICALL
Java_com_homesoft_libyuv_Demo_bitmapToYv12(
        JNIEnv* env,
        jclass,
        jobject bitmap,
        jobject yPlane,
        jint yStride,
        jobject uPlane,
        jint uStride,
        jobject vPlane,
        jint vStride) {


    AndroidBitmapInfo androidBitmapInfo;
    if (AndroidBitmap_getInfo(env, bitmap, &androidBitmapInfo) == EXIT_SUCCESS) {
        const int width = (int)androidBitmapInfo.width;
        const int height = (int)androidBitmapInfo.height;
        const int bStride = (int)androidBitmapInfo.stride;
        void *abgrPtr;
        if (AndroidBitmap_lockPixels(env, bitmap, &abgrPtr) == EXIT_SUCCESS) {
            auto argbPtr = reinterpret_cast<uint8_t*>( malloc(height * bStride));
            libyuv::ABGRToARGB(reinterpret_cast<uint8_t*>(abgrPtr), bStride,
                               argbPtr, width * 4,
                               width, height);
            AndroidBitmap_unlockPixels(env, bitmap);
            auto yPtr = reinterpret_cast<uint8_t*>(env->GetDirectBufferAddress(yPlane));
            auto uPtr = reinterpret_cast<uint8_t*>(env->GetDirectBufferAddress(uPlane));
            auto vPtr = reinterpret_cast<uint8_t*>(env->GetDirectBufferAddress(vPlane));
            auto rc = libyuv::ARGBToI420(argbPtr, width * 4, yPtr, yStride, uPtr, uStride, vPtr, vStride, width, height);
            free(argbPtr);
            return rc;
        }
    }
    return -1;
}