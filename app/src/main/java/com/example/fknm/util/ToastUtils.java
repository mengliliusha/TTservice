package com.example.fknm.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;



public class ToastUtils {
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static final Object synObj = new Object();


    public static void show(final Context act, final String msg) {
    showMessage(act, msg, Toast.LENGTH_SHORT);
    }


    public static void show(final Context act, final int msg) {
    showMessage(act, msg, Toast.LENGTH_SHORT);
    }


    private static void showMessage(final Context act, final String msg, final int len)
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        synchronized (synObj)
                        {
                            if (toast != null)
                            {
                                toast.cancel();
                            }
                            toast = Toast.makeText(act, msg, len);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }


private static void showMessage(final Context act, final int msg,
final int len) {
new Thread(new Runnable() {
public void run() {
handler.post(new Runnable() {
@Override
public void run() {

toast = Toast.makeText(act, msg, len);
toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

toast.show();

}
});
}
}).start();
}


}