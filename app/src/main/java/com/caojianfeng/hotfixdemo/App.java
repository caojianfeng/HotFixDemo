package com.caojianfeng.hotfixdemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

public class App extends Application {
    private static final String TAG = "-- app --";
    private PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        initHotfix();
        loadPatchFromSdcard();
    }

    private void initHotfix() {
        Log.i(TAG, "initHotfix");
        App context = this;
        patchManager = new PatchManager(context);
        patchManager.init(BuildConfig.VERSION_NAME);//current version
        patchManager.loadPatch();
    }

    private void loadPatchFromSdcard(){
        File patchDir = new File(Environment.getExternalStorageDirectory(), "/com.caojianfeng/patchs");
        File[] subFile = patchDir.listFiles();

        if (subFile == null) {
            Log.e(TAG, "null subfiles on:" + patchDir.getAbsolutePath());
            return;
        }
        try {
            for (File file : subFile) {
                String path = file.getAbsolutePath();
                if (path.endsWith(".apatch")) {
                    Log.i(TAG, "adding patch:" + path);
                    patchManager.addPatch(path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
