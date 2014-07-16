package de.ur.mi.bischofshof.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.FrameLayout;

/**
 * Created by Jonas on 19.06.2014.
 */
public class MaskOverlay extends FrameLayout {
    public MaskOverlay(Context context) {

        super(context);



    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        testit();
        super.dispatchDraw(canvas);
    }

    private void testit() {






    }
}
