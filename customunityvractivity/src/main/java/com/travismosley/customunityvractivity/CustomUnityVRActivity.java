package com.travismosley.customunityvractivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.unity.GoogleUnityActivity;
import com.unity3d.player.UnityPlayer;

/**
 * An Activity for customizing a Unity Cardboard app
 * Created by Travis Mosley on 7/25/2015
 * **/
public class CustomUnityVRActivity extends GoogleUnityActivity{

    static final String TAG = GoogleUnityActivity.class.getSimpleName();
    static final int FILE_REQUEST_CODE = 66;

    private Intent mRequestFileIntent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
        mRequestFileIntent.setType("file/*");
    }

    public void requestFile() {
        startActivityForResult(mRequestFileIntent, FILE_REQUEST_CODE);
    }

    public static void myCustomMethod () {
        Log.v(TAG, "Look at me, doing custom stuff!");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case FILE_REQUEST_CODE:
                    handlePickedFile(data);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void handlePickedFile(Intent data){
        String _filePath = data.getDataString();
        Log.i(TAG, _filePath);
        UnityPlayer.UnitySendMessage("Display", "Java_updateText", _filePath);
    }

}
