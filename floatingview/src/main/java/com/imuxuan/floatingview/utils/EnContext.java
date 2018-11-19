package com.imuxuan.floatingview.utils;

import android.app.Application;

/**
 * Created by Yunpeng Li on 2018/11/8.
 */
public class EnContext {

    private static final Application INSTANCE;

    static {
        Application app = null;
        try {
            app = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
            if (app == null)
                throw new IllegalStateException("Static initialization of Applications must be on main thread.");
        } catch (final Exception e) {
            e.printStackTrace();
            try {
                app = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            } catch (final Exception ex) {
                e.printStackTrace();
            }
        } finally {
            INSTANCE = app;
        }
    }

    public static Application get() {
        return INSTANCE;
    }
}
