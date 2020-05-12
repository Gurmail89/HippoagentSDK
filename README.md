# Welcome to Hippo Agent SDK

Install Hippo agent SDK to see and talk to users of your Android app. Hippo agent SDK for Android supports API 21 and above.


# Pre Requirements
Android Studio 3.+
min API 21
AndroidX support
Java 8 support

## Step 1: Add Hippo SDK to your app

Add the following dependency to your app module’s build.gradle file project/app/build.gradle:

```
apply plugin: 'kotlin-android'  
apply plugin: 'kotlin-android-extensions'  
apply plugin: 'kotlin-kapt'

android {
 ...
 compileOptions {
		sourceCompatibility JavaVersion.VERSION_1_8  
		targetCompatibility JavaVersion.VERSION_1_8  
		kotlinOptions.jvmTarget = "1.8"
 }
 packagingOptions {
	 pickFirst('META-INF/LICENSE')
	 pickFirst('META-INF/LICENSE.txt')
 }
 dataBinding {  
    enabled = true  
}
}

dependencies {
	implementation 'com.hippochat:hippoagentsdk:0.0.7'
}

```

Add Kotlin support in your project(If not supported) Add the following code into your project build.gradle file

```
buildscript {
 ext.kotlin_version = '1.3.41'
 ...
 dependencies {
	 ...
	 classpath "org.jetbrains.kotlin:kotlin-gradle plugin:$kotlin_version"
 }
}

allprojects {  
    repositories {  
       ... 
        maven { url "https://github.com/jitsi/jitsi-maven-repository/raw/master/releases" }  
        ... 
    }  
}

```


## Step 2: Add provider in AndroidManifest.xml

```
<provider  
  android:name="androidx.core.content.FileProvider"  
  android:authorities="{applicationId}.provider"  
  android:exported="false"  
  android:grantUriPermissions="true">  
    <meta-data  
  android:name="android.support.FILE_PROVIDER_PATHS"  
  android:resource="@xml/provider_paths" />  
</provider>

```

Create a folder named xml in app/src/main/res Create a file named provider_paths.xml in xml folder and add following code:

```
<paths xmlns:android="http://schemas.android.com/apk/res/android">
	<external-path name="external_files" path="."/>
</paths>

```

## Step 3: Initializing Hippo Agent SDK

**First you need to extend the Application class with HippoApplication class**

Now create a builder object for the hippo
```
val attributes = HippoConfigAttributes.Builder()  
  .setAppType(<app_type>)  
  .setAppSecretKey(<app_secret_key>)  
  .setAuthToken(<hippo_access_token>)  
  .setDeviceToken(<device_token>)    
  .setProvider(<file_provider_path>) //e.g "com.test.provider"
  .setIsWhitelabel(<true/false>)  
  .setAppName(<app_name>)  
  .setShowLog(<true/false>)  
  .build()  
                
```
  
  After creating the builder and set the values, Its time to init the Hippo agent SDK as follow:
  ```
 HippoConfig.initHippoConfig(this@MainActivity, attributes, object : LoginCallback{  
    override fun onError() {
    } 
    override fun hasData() { 
    }  
    override fun success() {  
    }  
    override fun failed() {  
    }  
})
```

## UI changes:

Change the following color file using in Hippo Agent SDK:

```
<color name="colorPrimary">#6DD400</color>  
<color name="chat_bottom_disabled_color">#6DD400</color>  
<color name="picker_primary_color">#6DD400</color>  
<color name="colorPrimaryDark">#62be00</color>  
<color name="colorAccent">#80000000</color>  
<color name="fugu_theme_color_primary_pressed">#996DD400</color>  
  
  
<color name="start_color">#6DD400</color>  
<color name="center_color">#62be00</color>  
<color name="end_color">#62be00</color>  
<color name="special_bg">#E0F9FF</color>  
<color name="color_self">#f1f1f4</color>
```


##### Setting android.enableDexingArtifactTransform.desugaring=false on gradle.properties solves the problem.

