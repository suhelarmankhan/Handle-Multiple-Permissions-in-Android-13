### It will support e in all sdk. Especially from 21 -34 sdk

<br/>

## AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">



    <uses-feature
        android:name="android.hardware.camera"
        android:required="false" />

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES"/>
    <uses-permission android:name="android.permission.READ_MEDIA_AUDIO"/>
    <uses-permission android:name="android.permission.READ_MEDIA_VIDEO"/>
    <uses-permission android:name="android.permission.CAMERA"/>


    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.MyApplicationHandleMultiplePermissionsInAndroid13"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
<br/>

## MainActivity.java
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.userPermission).setOnClickListener(v -> {

            myPermissions();

        });

    }//onCreate end here -----------------------------

        private final ActivityResultLauncher<String[]> requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), permission ->{
                    boolean allGranted = true;

                    for (Boolean isGranted : permission.values()){
                        if (!isGranted){
                            allGranted = false;
                            break;
                        }
                    }

                    if (allGranted){
                        // All is granted
                    } else {
                        // All is not granted
                    }

                });

        private void myPermissions(){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

                String[] permissions = new String[]{
                        android.Manifest.permission.READ_MEDIA_IMAGES,
                        android.Manifest.permission.READ_MEDIA_AUDIO,
                        android.Manifest.permission.READ_MEDIA_VIDEO,
                        android.Manifest.permission.CAMERA,
                };


                List<String> permissionsTORequest = new ArrayList<>();
                for (String permission : permissions){
                    if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                        permissionsTORequest.add(permission);
                    }
                }

                if (permissionsTORequest.isEmpty()){
                    // All permissions are already granted
                    Toast.makeText(this, "All permissions are already granted", Toast.LENGTH_SHORT).show();


                } else {
                    String[] permissionsArray = permissionsTORequest.toArray(new String[0]);
                    boolean shouldShowRationale = false;

                    for (String permission : permissionsArray){
                        if (shouldShowRequestPermissionRationale(permission)){
                            shouldShowRationale = true;
                            break;
                        }
                    }

                    if (shouldShowRationale){
                        new AlertDialog.Builder(this)
                                .setMessage("Please allow all permissions")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        requestPermissionLauncher.launch(permissionsArray);
                                    }
                                })

                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .show();

                    } else {
                        requestPermissionLauncher.launch(permissionsArray);
                    }


                }


            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String[] permissions = new String[]{
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                };


                List<String> permissionsTORequest = new ArrayList<>();
                for (String permission : permissions){
                    if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                        permissionsTORequest.add(permission);
                    }
                }

                if (permissionsTORequest.isEmpty()){
                    // All permissions are already granted
                    Toast.makeText(this, "All permissions are already granted", Toast.LENGTH_SHORT).show();


                } else {
                    String[] permissionsArray = permissionsTORequest.toArray(new String[0]);
                    boolean shouldShowRationale = false;

                    for (String permission : permissionsArray){
                        if (shouldShowRequestPermissionRationale(permission)){
                            shouldShowRationale = true;
                            break;
                        }
                    }

                    if (shouldShowRationale){
                        new AlertDialog.Builder(this)
                                .setMessage("Please allow all permissions")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        requestPermissionLauncher.launch(permissionsArray);
                                    }
                                })

                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .show();

                    } else {
                        requestPermissionLauncher.launch(permissionsArray);
                    }


                }


            }


        } // myPermissions end here ================


    } // public class end here =========================

```
<br/>

## build.gradle (:app)
```java


plugins {
    id 'com.android.application'
}

android {
    namespace 'com.tecoditlimited.myapplicationhandlemultiplepermissionsinandroid13'
    compileSdk 34

    defaultConfig {
        applicationId "com.tecoditlimited.myapplicationhandlemultiplepermissionsinandroid13"
        minSdk 21
        targetSdk 34
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}
```



