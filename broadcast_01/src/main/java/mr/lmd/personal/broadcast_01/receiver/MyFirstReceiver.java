package mr.lmd.personal.broadcast_01.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Mr.Lin on 2015/2/6.
 */
public class MyFirstReceiver extends BroadcastReceiver {

    public MyFirstReceiver(){
        System.out.println("MyFirstReceiver ——》 MyFirstReceiver Object is Created !");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"I have received my firstly send Broadcast Message !",Toast.LENGTH_SHORT).show();
    }
}
