package xin.lz1998.autosms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Tel {
    public String phoneNum;
    public String msg;
    public void SendMsg() {






        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> list = smsManager.divideMessage(msg);

        for (String text : list) {
            smsManager.sendTextMessage(phoneNum, null, text, null, null);
        }
    }

}