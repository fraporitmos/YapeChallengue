# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.fraporitmos.yapechallengue.core.data.entities.** { *; }
-keep class com.fraporitmos.yapechallengue.data.remote.di.RemoteRetrofitModule
-keep class com.fraporitmos.yapechallengue.data.remote.repository.ApplicationRemoteRepository
-keep class com.fraporitmos.yapechallengue.data.persist.repository.ApplicationPersistentDataSource
-keep class com.fraporitmos.yapechallengue.data.remote.services.RecipeApiService
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

