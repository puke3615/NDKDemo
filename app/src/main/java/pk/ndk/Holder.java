package pk.ndk;

import android.graphics.Bitmap;

import pk.base.util.ToastUtil;

/**
 * @author zijiao
 * @version 2016/1/20
 * @Mark
 */
public class Holder {

    static {
        System.loadLibrary("PK");
    }

    //===============================分割线==================================

    public native int testCallJava();

    public void addNumber(int add) {
        number += add;
    }

    public static void addStaticNumber(int add, Holder holder) {
        number += add;
    }

    public void show() {
        ToastUtil.show(String.format("number = %d", number));
    }

    //===============================分割线==================================

    public static int number = 0;

    public native String print();

    public native int plus(int a, int b);

}
