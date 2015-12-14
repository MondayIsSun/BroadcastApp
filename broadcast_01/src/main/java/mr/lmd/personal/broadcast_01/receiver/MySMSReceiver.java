package mr.lmd.personal.broadcast_01.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by mr.lin on 2014/10/16.
 */
public class MySMSReceiver extends BroadcastReceiver {

    public MySMSReceiver() {
        //每次接收到广播都会创建，处理完后都会销毁
        System.out.println("MySMSReceiver ————》 MySMSReceiver Object is Created !");
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //System.out.print("yes");
        Toast.makeText(context, "I have Received The SMS !", Toast.LENGTH_SHORT).show();

        Bundle bundle = intent.getExtras();
        Object[] myObjPdus = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[myObjPdus.length];
        System.out.println(messages.length);
        for (int i = 0; i < myObjPdus.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) myObjPdus[i]);
            System.out.println(messages[i].getDisplayMessageBody());
        }
    }
}