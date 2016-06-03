package com.example.daniel.barra_dinamica;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by nuraa on 02-06-2016.
 */
public class Utils {
    public static int getActionBarSize(Context context) {
        final TypedArray actionBarSizeArray = context.getTheme().obtainStyledAttributes(new int[] {R.attr.actionBarSize});
        int actionbarSize = (int) actionBarSizeArray.getDimension(0, 0);
        actionBarSizeArray.recycle();
        return actionbarSize;
    }
}
