package mr.lmd.personal.broadcast_02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BC1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播
        //注意在这里面一定不要去做一些耗时的操作
        String s = intent.getStringExtra("msg");
        System.out.println("receiver1收到消息了 ---> " + s);

        //截断广播
        //abortBroadcast();

        //有序广播 ---> 可以处理广播，也可以截断广播
        //当然如果是同级别的接收器，那么应该是没有必要处理广播的吧 ---> 当然，同级别你也可以处理广播
        //如果是不同级别的接收器，那么处理广播就很正常了，因为有接收到先后顺序，可能逻辑上确实需要先收到的接收器先处理一下广播或截断广播等一些处理
        Bundle bundle = new Bundle();
        bundle.putString("test", "接收器1处理广播的数据");
        setResultExtras(bundle);
    }

}
