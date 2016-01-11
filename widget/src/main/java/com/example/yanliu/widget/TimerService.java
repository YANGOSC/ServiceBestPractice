package com.example.yanliu.widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    //定义一个定时器
    private Timer timer;
    //定义一个格式化，格式化日期
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        //延迟0，更新周期1000ms
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //在这里进行更新操作
                updateViews();
            }
        }, 0, 1000);
    }

    private  void updateViews(){
        //时间
        String time = sdf.format(new Date());
        //将时间传给RemoteViews
        RemoteViews rv = new RemoteViews(getPackageName(),R.layout.widget);
        //显示在TextView上
        rv.setTextViewText(R.id.appwidget_text,time);
        //创建APPWidgetManager
        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        //想通知的是widgetProvider执行onUpdate方法
        ComponentName cn = new ComponentName(getApplicationContext(),widget.class);
        manager.updateAppWidget(cn,rv);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //当Service被销毁的时候倒计时也应该被停止
        timer = null;
    }
}
