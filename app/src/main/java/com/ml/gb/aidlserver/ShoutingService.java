package com.ml.gb.aidlserver;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Random;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class ShoutingService extends Service {
    private Handler handler;
    // aidl defined in src/main/aidl/pack/to/aidl
    IShoutingService.Stub binder = new IShoutingService.Stub() {
        @Override
        public int getPid() throws RemoteException {
            return android.os.Process.myPid();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) throws RemoteException {
        }

        @Override
        public String shout(int shoutType) throws RemoteException {
            switch (shoutType) {
                case IShoutingService.SHOUT_0:
                    return "Owwwwwwww!";
                case IShoutingService.SHOUT_1:
                    return "Walahlahlahlah!";
                case IShoutingService.SHOUT_2:
                    return "Bleeeeh!";
            }
            return "unsupported shouting type";
        }
    };

    @Override
    public void onCreate() {
        HandlerThread thread =
                new HandlerThread("ServiceArguments", THREAD_PRIORITY_BACKGROUND);
        thread.start();
        handler = new Handler(thread.getLooper());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void shoutWihtoutString() {
        handler.post(() -> {
            // off of main
            Toast t = Toast.makeText(getApplicationContext(), "Go To the Fucking Bed!!! " +
                    "Otherwise tomorrow will suck!", Toast.LENGTH_SHORT);
            Display display = ((WindowManager) getApplicationContext().getSystemService(
                    Context.WINDOW_SERVICE)).getDefaultDisplay();
            Random r = new Random();
            int yOffset = (int) (r.nextFloat() * display.getHeight());
            t.setGravity(Gravity.TOP | Gravity.CENTER, 0, yOffset);
            t.show();
        });
    }

    public void shoutWithString(String string) {
        handler.post(() -> {
            // off of main
            Toast t = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
            Display display = ((WindowManager) getApplicationContext().getSystemService(
                    Context.WINDOW_SERVICE)).getDefaultDisplay();
            Random r = new Random();
            int yOffset = (int) (r.nextFloat() * display.getHeight());
            t.setGravity(Gravity.TOP | Gravity.CENTER, 0, yOffset);
            t.show();
        });
    }

}
