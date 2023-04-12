package com.homesoft.libyuv;

import android.graphics.Bitmap;
import android.media.Image;

import java.nio.ByteBuffer;

public class Demo {

    // Used to load the 'libyuv' library on application startup.
    static {
        System.loadLibrary("demo");
    }

    /**
     * A native method that is implemented by the 'demo' native library,
     * which is packaged with this application.
     */
    public static native int bitmapToYv12(Bitmap bitmap,
                                           ByteBuffer yPlane,
                                           int yStride,
                                           ByteBuffer uPlane,
                                           int uStride,
                                           ByteBuffer vPlane,
                                           int vStride);
}