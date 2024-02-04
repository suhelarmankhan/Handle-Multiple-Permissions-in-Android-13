package com.tecoditlimited.myapplicationhandlemultiplepermissionsinandroid13;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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