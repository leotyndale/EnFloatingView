![Logo](https://raw.githubusercontent.com/leotyndale/EnFloatingView/master/preview/logo.png)

EnFloatingView
==========================
[![Jcenter](https://img.shields.io/badge/Jcenter-v1.0-brightgreen.svg?style=flat)](https://bintray.com/leotyndale/Muxuan/EnFloatingView)
[![Muxuan](https://img.shields.io/badge/PoweredBy-Muxuan-green.svg?style=flat)](http://www.imuxuan.com/)
[![Website](https://img.shields.io/website-up-down-green-red/https/shields.io.svg?label=Blog)](http://blog.imuxuan.com)


Do not need all permissions, not subject to a variety of domestic ROM restrictions, the default can be displayed within the application suspension window.

### Traditional way

For the implementation of traditional suspension Windows and some ancient "black technology" suspension Windows, there must have been many mature cases, and the implementation strategies are basically the following two：

1. TYPE_SYSTEM_ALERT Type

```java
mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams()
layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
```

need permission：

```java
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" ></uses>
```

2. TYPE_TOAST / TYPE_PHONE Type

No permission statement is required below 7.1.1. It is hidden by default on models like meizu, huawei and xiaomi, and users need to be guided to open the suspension window.

### Traditional solutions

The first scheme can not be adopted by many development because of various restrictions, so the more popular way to achieve the suspension window is the second one.

But we have our own rules:

- the above models cannot be accepted. The second way to achieve the suspension window still requires users to actively grant permission?
- it is not acceptable to hide by default on models such as meizu, huawei and xiaomi. Users need to be guided to open the suspension window, like this

![权限管理](https://github.com/leotyndale/EnFloatingView/blob/master/preview/1.gif)

### function


- the application is displayed without any permission
- in-app display, all models can display the suspension window by default, without guiding users to do more Settings
- support drag
- move beyond screen limits
- can be automatically attached to the edge of the screen

### Instructions

1.gralde dependencies

   ```java
       compile 'com.imuxuan:floatingview:1.0'
   ```
   

2.Add the following code to onStart and onStop in the base class Activity (note that this must be the base class Activity)

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


3.Display

   ```java
       FloatingView.get().add(getApplicationContext());
   ```

4.Dismiss

   ```java
       FloatingView.get().remove();
   ```

### Rendering
![预览图](https://github.com/leotyndale/EnFloatingView/blob/master/preview/2.gif)
