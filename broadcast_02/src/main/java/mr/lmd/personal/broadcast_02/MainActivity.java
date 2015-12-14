package mr.lmd.personal.broadcast_02;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //动态注册
        //动态注册与静态注册的比较就是
        //动态注册是app级别的 ---> 即这个app退出以后，那么这个接收器就不起作用了
        //而通过Manifest文件进行的静态注册是系统级别的，即使这个app被退出了，这个接收器仍然是有效的
        //比如你要做一些短信呀，来电的拦截呀之类的操作，那么这个接收器就不能使用动态注册了
        //IntentFilter intentFilter = new IntentFilter("BC_One");
        //BC2 bc2 = new BC2();
        //registerReceiver(bc2, intentFilter);
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                Intent intent = new Intent();
                intent.putExtra("msg", "这是一条普通广播");
                intent.setAction("BC_One");

                //发送一条普通(无序)的广播
                //由于普通广播是不能被截断的 ---> 所以普通的广播就可以用在开机信号的发送广播
                sendBroadcast(intent);

                break;//接收广播 ---> BC1
            case R.id.send2:
                Intent intent2 = new Intent();
                intent2.putExtra("msg", "这是一条有序广播");
                intent2.setAction("BC_One");

                //发送一条有序的广播
                sendOrderedBroadcast(intent2, null);

                break;//接收广播 ---> BC2
            case R.id.send3:
                //粘性广播的特点是 ---> 粘滞性滞留的
                //普通、有序广播的特点是，广播用完之后就销毁掉了
                //自己区别开来广播和广播接收器 ---> 广播可以分为无序广播、有序广播 和 异步广播(粘性广播)
                //而广播接收器就这一种啦(当然你可以看情况静态注册，也可以动态注册)
                //并且呢，你还可以先注册接收器，后发送广播都可以
                Intent intent3 = new Intent();
                intent3.putExtra("msg", "这是一条粘性广播");
                intent3.setAction("BC_Three");

                //发送一条异步的广播
                sendStickyBroadcast(intent3);

                //动态注册接收器
                IntentFilter intentfilter = new IntentFilter("BC_Three");
                BC3 bc3 = new BC3();
                registerReceiver(bc3, intentfilter);

                //当然你也可以演示 先注册接收器后发送广播

                break;//接收广播 ---> BC3
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //特别特别注意的一点是，如果你都接收器是动态注册的
        //那么你一定要在组件结束的时候释放掉注册
        //unregisterReceiver(receiver);
    }
}
