package xin.lz1998.autosms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText msgFormat;
    EditText msgVar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgFormat=findViewById(R.id.msg_format);
        msgVar=findViewById(R.id.msg_var);

    }

    public void sendMsg(View view) {
        //Toast.makeText(this,msgVar.getText().toString(),Toast.LENGTH_SHORT).show();;
        MyClass.setInfo(msgFormat.getText().toString(),msgVar.getText().toString());
        Tel[] t=MyClass.getTel();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS}, 1);
            else
                for(int i=0;i<t.length;i++)
                    try {
                        t[i].SendMsg();
                    }
                    catch (Exception e){

                    }

        }

    }

    public void doSendSMSTo(String phoneNumber,String message){
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            intent.putExtra("sms_body", message);
            startActivity(intent);
        }
    }
}
