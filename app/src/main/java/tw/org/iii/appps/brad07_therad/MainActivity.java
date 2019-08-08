package tw.org.iii.appps.brad07_therad;
//設計執行序20秒發現,如果只是上衣頁的話,執行序還是會跑,除非關掉
//設定自動排成,其實java還是有內建關閉機制,雖然你沒關閉
//所有的執行序都是完背景無法玩前景,所以要靠程序之間溝通得機制ipc
//2.創造Message物件實體,等著放入sendmessage
//Message message = new Message(); //創造一個message掛訊息
// Bundle data = new Bundle(); //產生bundel來掛上去
//  data.putInt("i",i); //bundel,put上去資訊
// message.setData(data); //在訊息上掛上data資料包(Bundle),交給handlemessage顯示
// //1.準備uihandel.去輸出訊息,但裡面需要Message
//uIhandler.sendMessage(message); //發送訊息(包裹著執行序的資訊)
////1.acyivity 2.model 3.handle 話面紙給一個呈現,演算拉去旁邊,有需要時才叫handle去處理
////在程序跟程序之間溝通的東西,作業系統等級的叫OS
//Bundle data = msg.getData(); //把拿到的資訊解包出來,存放在Bundle裡
//int i = data.getInt("i"); //把資訊裡的int質拿出來


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer; //宣告計時器
    private  int k=0; //宣告週期任務的變數為痊癒變數
    private TextView mesg;
    private  UIhandler uIhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uIhandler = new UIhandler();
        timer =  new Timer();
        timer.schedule(new MyTask(),0,1000); //排上形成,(執行序物件,延遲時間,多久做一次)
        mesg = findViewById(R.id.mesg);

    }



   //設計執行序20秒,要按鈕才啟用
    public void test1(View view) {
        new Thread(){
            @Override
                 public void run() {
                    for(int i =0; i<20; i++){
//                        Log.v("brad","i =" + i);
//                        mesg.setText("i=" + i); 沒辦法顯示,因為一個畫面只能由一個activity處理,所以叫出Uihandle來幫忙

                        //2.創造Message物件實體,等著放入sendmessage
                        Message message = new Message(); //創造一個message掛訊息
                        Bundle data = new Bundle(); //產生bundel來掛上去
                        data.putInt("i",i); //bundel,put上去資訊
                        message.setData(data); //在訊息上掛上data資料包(Bundle),交給handlemessage顯示
                        //1.準備uihandel.去輸出訊息,但裡面需要Message
                        uIhandler.sendMessage(message); //發送訊息(包裹著執行序的資訊)
                        try {
                            Thread.sleep(1000);
                        }catch (Exception e){

                     }
                  }
            }
        }.start();


    }

    //執行序間彼此的溝東靠habdle來處理,他是os等級的處理器,這邊顯示訊息
    private  class  UIhandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) { //時做訊息

            //3.拿到訊息解包出來,並且呈現在螢幕
            super.handleMessage(msg);
            Bundle data = msg.getData(); //把拿到的資訊解包出來,存放在Bundle裡
            int i = data.getInt("i"); //把資訊裡的int質拿出來
            mesg.setText("i" + i); //顯示在螢幕上
            Log.v("brad","handle");
        }
    }

    //週期任務內部類別
    private class MyTask extends TimerTask{
        @Override
        public void run() {
          Log.v("brad","k = " + k++);
        }
    }
}
