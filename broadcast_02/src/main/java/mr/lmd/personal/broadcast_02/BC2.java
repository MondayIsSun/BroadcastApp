package mr.lmd.personal.broadcast_02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BC2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        System.out.println("receiver2收到消息了 ---> " + s);

        //截断广播
        //abortBroadcast();

        //得到接收器处理后的结果
        Bundle bundle = getResultExtras(true);
        String s2 = bundle.getString("test");
        System.out.println("得到的处理结果是：" + s2);
    }

}
