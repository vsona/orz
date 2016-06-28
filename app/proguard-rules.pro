# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/roy/devhome/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keep class net.vsona.orz.domain.** { *; }
-keep class net.vsona.orz.api.** { *; }
-keep class net.vsona.orz.event.** { *; }

-keepattributes Exceptions, Signature, InnerClasses

# keep annotated by NotProguard
-keep @net.vsona.baselibrary.annotation.NotProguard class * {*;}
-keep class * {
    @net.vsona.baselibrary.annotation.NotProguard <fields>;
}
-keepclassmembers class * {
    @net.vsona.baselibrary.annotation.NotProguard <methods>;
}

#----------------  glide ---------------------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class com.bumptech.glide.integration.okhttp3.OkHttpGlideModule
-keep class com.bumptech.glide.integration.okhttp.OkHttpGlideModule
#----------------  butterknife ---------------------
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#----------------  RxJava pro ---------------------
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#----------------  retrofit ---------------------
#-keep public class xxx.** { *; }
#-dontwarn xxx.**
#-keep class java.** { *; }
#-keep class org.codehaus.** { *; }
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
#----------------  okhttp ---------------------
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn okio.**
-dontwarn retrofit2.**

#----------------  gson ---------------------
-keepattributes Signature
-keep class com.google.gson.** { *; }

-keepattributes *Annotation*

###################################
###Ignore Retrofit relative depedencies.
-keep class retrofit.** { *; }
-keep class retrofit.http.** { *; }
-keep class retrofit.client.** { *; }
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.inject.* { *; }

-dontwarn retrofit.RxSupport.*
-dontwarn retrofit.appengine.*
-dontwarn rx.*
-dontwarn rx.subscriptions.*
#----------------  fresco ---------------------
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
-keep class com.facebook.imagepipeline.gif.** { *; }
-keep class com.facebook.imagepipeline.webp.** { *; }

#----------------  EventBus ---------------------
#-keepclassmembers,includedescriptorclasses class ** { public void onEvent*(**); }
#-keepclassmembers class ** {
#    public void onEvent*(**);
#}
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep class de.greenrobot.event.** {*;}
-keep class org.greenrobot.event.** {*;}
-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}
-keepclassmembers class ** {
    public void onEvent*(**);
}

# Only required if you use AsyncExecutor
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
#---------
-dontwarn org.greenrobot.eventbus.**
-dontwarn de.greenrobot.eventbus.**
-keep class org.greenrobot.eventbus.** { *; }

-keepattributes Annotation
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#(java.lang.Throwable);
#}
#----------------  youmeng ---------------------
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class com.shuidihuzhu.rock.R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#----------------  trinea ---------------------
-keep class cn.trinea.android.** { *; }
-keepclassmembers class cn.trinea.android.** { *; }
-dontwarn cn.trinea.android.**
#----------------  testin ---------------------
#-libraryjars ../qAMasterSDK/libs/testinagent_crash_release.jar
#-dontwarn com.qamaster.android.**
#-keep class com.qamaster.android.** {*;}
#-dontwarn com.testin.agent.**
#-keep  class  com.testin.agent.** {*;}
#-keepattributes SourceFile, LineNumberTable
#----------------  bugly ---------------------
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#----------------  umeng ---------------------
 -dontshrink
 -dontoptimize
 -dontwarn com.google.android.maps.**
 -dontwarn android.webkit.WebView
 -dontwarn com.umeng.**
 -dontwarn com.tencent.weibo.sdk.**
 -dontwarn com.facebook.**
 -keep public class javax.**
 -keep public class android.webkit.**
 -dontwarn android.support.v4.**
 -keep enum com.facebook.**
 -keepattributes Exceptions,InnerClasses,Signature
 -keepattributes *Annotation*
 -keepattributes SourceFile,LineNumberTable

 -keep public interface com.facebook.**
 -keep public interface com.tencent.**
 -keep public interface com.umeng.socialize.**
 -keep public interface com.umeng.socialize.sensor.**
 -keep public interface com.umeng.scrshot.**

 -keep public class com.umeng.socialize.* {*;}


 -keep class com.facebook.**
 -keep class com.facebook.** { *; }
 -keep class com.umeng.scrshot.**
 -keep public class com.tencent.** {*;}
 -keep class com.umeng.socialize.sensor.**
 -keep class com.umeng.socialize.handler.**
 -keep class com.umeng.socialize.handler.*
 -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
 -keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

 -keep class im.yixin.sdk.api.YXMessage {*;}
 -keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

 -dontwarn twitter4j.**
 -keep class twitter4j.** { *; }

 -keep class com.tencent.** {*;}
 -dontwarn com.tencent.**
 -keep public class com.umeng.soexample.R$*{
     public static final int *;
 }
 -keep public class com.umeng.soexample.R$*{
     public static final int *;
 }
 -keep class com.tencent.open.TDialog$*
 -keep class com.tencent.open.TDialog$* {*;}
 -keep class com.tencent.open.PKDialog
 -keep class com.tencent.open.PKDialog {*;}
 -keep class com.tencent.open.PKDialog$*
 -keep class com.tencent.open.PKDialog$* {*;}

 -keep class com.sina.** {*;}
 -dontwarn com.sina.**
 -keep class  com.alipay.share.sdk.** {
    *;
 }
 -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
 }

 -keep class com.linkedin.** { *; }
 -keepattributes Signature

#----------------  welcome ---------------------
