package com.mobileappscompany.training.dailyphoto.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author mpamplona
 * created on 3/27/2016
 * <p/>
 * Activity Launched when app starts
 */
public class FaceView extends View {

    private int numFaces;
    private static final int MAX_FACES = 2;
    private Paint tmp_paint;
    private PointF tmp_point;
    private String imgPath;
    private FaceDetector.Face faces[];
    private Bitmap bitmap;


    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        tmp_paint = new Paint();
        tmp_point = new PointF();
    }

    public void showFacesDetectedBitmap(String imgPath) {
        this.imgPath = imgPath;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        bitmap = BitmapFactory.decodeFile(imgPath, options);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.5), (int) (bitmap.getHeight() * 0.5), true);

        FaceDetector detector = new FaceDetector(bitmap.getWidth(), bitmap.getHeight(), MAX_FACES);
        faces = new FaceDetector.Face[MAX_FACES];
        numFaces = detector.findFaces(bitmap, faces);

        Canvas canvas = new Canvas();
        draw(canvas);

    }

    @Override
    public void onDraw(Canvas canvas) {
        if (numFaces > 0) {
            canvas.drawBitmap(bitmap, 0, 0, null);
            for (int i = 0; i < numFaces; i++) {
                FaceDetector.Face face = faces[i];
                tmp_paint.setColor(Color.RED);
                tmp_paint.setAlpha(100);
                face.getMidPoint(tmp_point);
                canvas.drawCircle(tmp_point.x, tmp_point.y, face.eyesDistance(), tmp_paint);
            }
        }

    }

}
