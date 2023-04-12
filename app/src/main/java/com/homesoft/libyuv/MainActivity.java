package com.homesoft.libyuv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.media.Image;
import android.media.ImageWriter;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private Bitmap bitmap;
    private ImageWriter imageWriter;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();

        new Thread() {
            @Override
            public void run() {
                final BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
                opts.inScaled=false;
                // Credit: https://all-free-download.com/free-photos/download/creeping_plant_nature_concrete_lantern_226860.html#google_vignette
                bitmap = BitmapFactory.decodeResource(getResources(), R.raw.plant, opts);
                if (bitmap != null) {
                    runOnUiThread(()->{
                        surfaceHolder.addCallback(MainActivity.this);
                        surfaceHolder.setFixedSize(bitmap.getWidth(), bitmap.getHeight());
                        surfaceHolder.setFormat(ImageFormat.YV12);
                    });
                }
            }
        }.start();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        if (format == ImageFormat.YV12) {
            imageWriter = ImageWriter.newInstance(surfaceHolder.getSurface(), 1);
            // Super complicated way to display a Bitmap, done for demonstration purposes only
            final Image image = imageWriter.dequeueInputImage();
            final Image.Plane[] planes = image.getPlanes();
            final Image.Plane yPlane = planes[0];
            final Image.Plane uPlane = planes[1];
            final Image.Plane vPlane = planes[2];
            // See the C code for some basic libyuv code
            Demo.bitmapToYv12(bitmap, yPlane.getBuffer(), yPlane.getRowStride(),
                    uPlane.getBuffer(), uPlane.getRowStride(),
                    vPlane.getBuffer(), vPlane.getRowStride());
            imageWriter.queueInputImage(image);
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}