package net.vsona.orz.utils;


import timber.log.Timber;

/**
 * Created by roy on 16/7/31.
 */
public class AppLogUtils {

    public static void log(String message, Object... args) {
        Timber.d(message, args);
    }

    public static void init() {
//        Settings settings = Logger.init(AppConstants.LOG_TAG);// default PRETTYLOGGER or use just init()
//        settings.methodCount(AppConstants.LOG_METHOD);                 // default 2
//        settings.hideThreadInfo();               // default shown

//        LogLevel logLevel = AppConstants.sDebug ? LogLevel.FULL : LogLevel.NONE;
//        settings.logLevel(logLevel);

        if (AppConstants.sDebug) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    public static void w(String message, Object... args) {
        Timber.w(message, args);
    }

    public static void i(String message, Object... args) {
        Timber.i(message, args);
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            /**
             *
             *
             System.out.println("priority = " + priority);
             System.out.println("tag = " + tag);
             System.out.println("message = " + message);
             System.out.println("t = " + t);

             if (priority == Log.VERBOSE || priority == Log.DEBUG) {
             return;
             }

             FakeCrashLibrary.log(priority, tag, message);

             if (t != null) {
             if (priority == Log.ERROR) {
             FakeCrashLibrary.logError(t);
             } else if (priority == Log.WARN) {
             FakeCrashLibrary.logWarning(t);
             }
             }
             */
        }
    }
}
