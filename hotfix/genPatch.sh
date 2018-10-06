#!/bin/sh
# [apkpatch](https://github.com/alibaba/AndFix/raw/master/tools/apkpatch-1.0.3.zip)
# from  https://github.com/alibaba/AndFix

./apkpatch.sh -f fix.apk -t origin.apk -o apatch -k public.key -p 123456 -a key0 -e 123456

#output:
#add modified Method:V  onClick(Landroid/view/View;)  in Class:Lcom/caojianfeng/hotfixdemo/MainActivity;


