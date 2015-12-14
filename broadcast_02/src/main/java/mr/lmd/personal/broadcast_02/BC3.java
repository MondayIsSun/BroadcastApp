package mr.lmd.personal.broadcast_02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BC3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("收到了异步广播!");
    }
}
