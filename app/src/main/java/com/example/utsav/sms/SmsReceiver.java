package com.example.utsav.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import static android.telephony.SmsMessage.createFromPdu;

/**
 * Created by utsav on 29/3/17.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        String sendernumber=null;
        if(bundle!=null)
        {
            Object[] pdus=(Object[])bundle.get("pdus"); //incoming message has portable description units

            for(int i=0; i< pdus.length ;i++)
            {
                SmsMessage sms= createFromPdu((byte[])pdus[i],null);
                sendernumber = sms.getDisplayOriginatingAddress();
                String message=sms.getDisplayMessageBody();

                Toast.makeText(context, "From: "+sendernumber+ "Message: "+message,Toast.LENGTH_LONG).show();
            }
            SmsManager smsmanager=SmsManager.getDefault();
            smsmanager.sendTextMessage(sendernumber,null,"Sorry i was busy, will call you back later or never",null,null);

        }
    }
}
