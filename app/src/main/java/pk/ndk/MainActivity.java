package pk.ndk;


import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;

import pk.base.debug.DebugActivity;
import pk.base.util.ToastUtil;

public class MainActivity extends DebugActivity {

    private Holder mHolder = new Holder();
    private final long MAX = 1000000000L;

    @Override
    protected Item.Builder getItems(Item.Builder builder) {
        return builder.add(
                new Item("print", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String r = mHolder.print();
                        T(r);
                    }
                }),
                new Item("plus", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int r = mHolder.plus(45, 55);
                        T(r);
                    }
                }),
                new Item("callJava", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHolder.testCallJava();
                    }
                }),
                new Item("Java_compute", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long cur = System.currentTimeMillis();
                        long r = Holder.Java_sub(MAX);
                        T(r + "\n" + String.valueOf(System.currentTimeMillis() - cur) + "毫秒");
                    }
                }),
                new Item("C_compute", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long cur = System.currentTimeMillis();
                        long r = Holder.C_sub(MAX);
                        T(r + "\n" + String.valueOf(System.currentTimeMillis() - cur) + "毫秒");
                    }
                }),
                new Item("C++_compute", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long cur = System.currentTimeMillis();
                        long r = Holder.CC_sub(MAX);
                        T(r + "\n" + String.valueOf(System.currentTimeMillis() - cur) + "毫秒");
                    }
                }),
                null

        );
    }


    private Item create(final String name, final Object... args) {
        return new Item(name, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class cls = MainActivity.class;
                Class[] paramsType = new Class[args.length];
                int i = 0;
                for (Object obj : args) {
                    paramsType[i] = obj.getClass();
                    i++;
                }
                try {
                    Method method = cls.getMethod(name, paramsType);
                    method.setAccessible(true);
                    method.invoke(mContext, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void T(Object s) {
        ToastUtil.show(s);
        Log.i("COMPUTEZZZ", s.toString());
    }

}
