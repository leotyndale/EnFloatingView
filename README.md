![Logo](https://raw.githubusercontent.com/leotyndale/EnFloatingView/master/preview/logo.png)

EnFloatingView
==========================
[![Jcenter](https://img.shields.io/badge/Jcenter-v1.5-brightgreen.svg?style=flat)](https://bintray.com/leotyndale/Muxuan/EnFloatingView)
[![Muxuan](https://img.shields.io/badge/PoweredBy-Muxuan-green.svg?style=flat)](http://www.imuxuan.com/)
[![Website](https://img.shields.io/website-up-down-green-red/https/shields.io.svg?label=Blog)](http://blog.imuxuan.com)

![《移动开发架构设计实战》](https://upload-images.jianshu.io/upload_images/14802001-4864444c478c88ee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

[点击购买](https://item.jd.com/12730926.html)

==========================
 
[English](/README_EN.md)

无需一切权限，不受各种国产ROM限制，默认可以显示的应用内悬浮窗。

### 传统方案

对于传统悬浮窗和一些古老的“黑科技”悬浮窗的实现，想必已经有很多成熟的案例了，实现策略基本为以下两种：

1. TYPE_SYSTEM_ALERT类型

```java
mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams()
layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
```

​        需要权限：

```java
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" ></uses>
```

2. TYPE_TOAST / TYPE_PHONE 类型

​        7.1.1以下不需要权限声明，在魅族、华为、小米等机型上默认隐藏，需要引导用户打开悬浮窗。

### 传统方案的问题

第一种方案因为存在各种限制，不能被众多开发采纳，故而比较流行的悬浮窗实现方式是第二种。

但是，我们有自己的原则：

- 不能接受7.1.1以上机型，使用第二种方式实现悬浮窗仍需要用户主动授予权限的操作？
- 不能接受在魅族、华为、小米等机型上默认隐藏，需要引导用户打开悬浮窗，就像这样

![权限管理](https://github.com/leotyndale/EnFloatingView/blob/master/preview/1.gif)

### 功能

- 应用内显示，无需申请任何权限
- 应用内显示，所有机型都可以默认显示悬浮窗，无需引导用户做更多设置
- 支持拖拽
- 超出屏幕限制移动
- 可自动吸附到屏幕边缘

### 基本使用规则

1.在gralde的dependencies中加入
   ```java
       compile 'com.imuxuan:floatingview:1.5'
   ```
   
   
2.在基类Activity（注意必须是基类Activity）中的onStart和onStop（或者安卓原生ActivityLifeCycle监听）中添加如下代码

   ```java
       @Override
       protected void onStart() {
           super.onStart();
           FloatingView.get().attach(this);
       }

       @Override
       protected void onStop() {
           super.onStop();
           FloatingView.get().detach(this);
       }
   ```


3.展示悬浮窗

   ```java
       FloatingView.get().add();
   ```

### 扩展用法

1.销毁悬浮窗

   ```java
       FloatingView.get().remove();
   ```
   
2.添加点击事件

   ```java
       FloatingView.get().listener(new MagnetViewListener() {
            @Override
            public void onRemove(FloatingMagnetView magnetView) {
                Toast.makeText(TestActivity.this, "我没了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(FloatingMagnetView magnetView) {
                Toast.makeText(TestActivity.this, "点到我了", Toast.LENGTH_SHORT).show();
            }
        });
   ```
   
   3.获得悬浮窗View

   ```java
       FloatingView.get().getView();
   ```

4.设置悬浮窗icon

   ```java
       FloatingView.get().icon(R.drawable.XXXXX);
   ```

5.设置悬浮窗View

   ```java
       FloatingView.get().customView(new View());
       or
       FloatingView.get().customView(R.layout.XXXXX);
   ```

6.设置悬浮窗位置等布局参数

   ```java
       FloatingView.get().layoutParams(new ViewGroup.LayoutParams());
   ```

### 效果图
![预览图](https://github.com/leotyndale/EnFloatingView/blob/master/preview/2.gif)

### 更新记录

1.3

增加自定义layout等API

1.4

适配折叠屏

1.5

修复内存泄露问题
