package tw.org.iii.appps.brad07_therad;
//設計執行序20秒發現,如果只是上衣頁的話,執行序還是會跑,除非關掉
//設定自動排成,其實java還是有內建關閉機制,雖然你沒關閉
//
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer; //宣告計時器
    private  int k=0; //宣告週期任務的變數為痊癒變數
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer =  new Timer();
        timer.schedule(new MyTask(),0,1000); //排上形成,(執行序物件,延遲時間,多久做一次)

    }



   //設計執行序20秒
    public void test1(View view) {
        new Thread(){
            @Override
                 public void run() {
                    for(int i =0; i<20; i++){
                        Log.v("brad","i =" + i);
                        try {
                            Thread.sleep(1000);
                        }catch (Exception e){

                     }
                  }
            }
        }.start();
    }

    //週期任務內部類別
    private class MyTask extends TimerTask{
        @Override
        public void run() {
          Log.v("brad","k = " + k++);
        }
    }
}
