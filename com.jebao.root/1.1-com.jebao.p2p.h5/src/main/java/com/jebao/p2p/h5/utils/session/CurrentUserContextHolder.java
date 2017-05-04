package com.jebao.p2p.h5.utils.session;

/**
 * Created by wenyq on 2017/2/17.
 */
public class CurrentUserContextHolder {
    private static final ThreadLocal<CurrentUser> WEB_CONTEXT_HOLDER = new ThreadLocal<CurrentUser>();

    public CurrentUserContextHolder() {
    }

    public static CurrentUser get() {
        return WEB_CONTEXT_HOLDER.get();
    }

    public static void set(CurrentUser context) {
        WEB_CONTEXT_HOLDER.set(context);
    }

    public static void remove() {
        WEB_CONTEXT_HOLDER.set(null);
    }
}
