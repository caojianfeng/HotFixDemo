# 使用AndFix进行热修复的例程

本示例展示了如何使用阿里的andfix，对软件进行热修复。

## 简单的说，分五步：

1. 集成代码
2. 保留未经混淆的apk
3. 生成修复bug的apk
4. 生成patch文件
5. 将patch文件更新到指定目录

## 集成代码
### 添加引用
向app module的 build.gradle文件中加入如下的依赖

```gradle
dependencies {
  ...
  implementation 'com.alipay.euler:andfix:0.5.0@aar'
  ...
}
```

### 初始化PatchManager

在Application实例中初始化PatchManager对象
```java
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


```
