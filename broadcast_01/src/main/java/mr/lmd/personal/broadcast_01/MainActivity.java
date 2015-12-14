package mr.lmd.personal.broadcast_01;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    /*
    * 除了可以接收安卓系统发出的Broadcast意外还可以自己发出BroadCast
    *
    * 除了在xml文件当中注册BroadcastReceiver意外，还可以使用代码进行注册
    * */

    Button btnSend, btnCancel,btn_send_first_broadcast;

    //此处表示拦截本身
    private static final String BROADCAST_ACTION = "com.broadcast_demo.MainActivity";

    private MyReceiver receiver;

    /***********************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        registerMyReceiver();
    }

    /***********************************************************************************************/

    private void findViews() {

        btn_send_first_broadcast = (Button)findViewById(R.id.send_first_broadcast);
        btn_send_first_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("Action_Test");
                MainActivity.this.sendBroadcast(intent);
            }
        });

        //send broadcast and receive the broadcast
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, "begin to send a broadcast", Toast.LENGTH_SHORT).show();
                //how to send broadcast
                MainActivity.this.sendBroadcast(new Intent(BROADCAST_ACTION));
            }
        });

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, "begin to unregister receiver", Toast.LENGTH_SHORT).show();

                //how to unregisterReceiver
                try {
                    unregisterReceiver(receiver);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "receiver has been unregistered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /***********************************************************************************************/

    //coding the Receiver class
    private class MyReceiver extends BroadcastReceiver {

        //注意到使用代码注册的BroadcastReceiver是常驻内存的
        //而使用XML文件注册的BroadcastReceiver则是每次接收到广播后创建，处理完广播后销毁
        public MyReceiver(){
            System.out.println("MyReceiver ——》 MyReceiver Object is Created !");
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "I have received the broadcast !", Toast.LENGTH_SHORT).show();
        }
    }

    //BroadcastReceiver do these important things:
    //the first——》receive the broadcast
    //the second——》(handle)deal with the broadcast
    //register the MyReceiver using code not the xml file
    private void registerMyReceiver() {

        //Toast.makeText(MainActivity.this, "register the MyReceiver", Toast.LENGTH_SHORT).show();

        /*
        <receiver android:name=".receiver.MySMSReceiver">
            <intent-filter>
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
            </intent-filter>
        </receiver>
        */

        /*
        理解上面配置文件当中的 action ——》Intent到底要如何处理数据是要由两部分来决定的
        1：如何处理——》动作——》action
        2：处理的对象——》数据——》data
         */

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        //intentFilter.addAction(BROADCAST_ACTION);
        receiver = new MyReceiver();
        MainActivity.this.registerReceiver(receiver, intentFilter);

        /*
        注意通过AndroidManifest文件进行注册的BroadcastReceiver在这个程序被关闭以后也会接收广播的
        而通过代码注册的Receiver则是在对应的Activity启动的时候注册，在Activity不可见的时候自动取消注册，在Activity可见的这段时间是常驻内存的
         */

    }

    /***********************************************************************************************/

    @Override
    protected void onDestroy() {

        super.onDestroy();

        try {
            unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "receiver has been unregistered", Toast.LENGTH_SHORT).show();
        }

    }
}
