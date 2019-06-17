package sion.my.myapplication.Until;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;


import com.google.gson.Gson;

import java.util.Map;

public class OftenUntil {
    public static Handler handler=new Handler();
    public static void CancelActionBar(Activity activity){
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public static void JumpActivity(Activity activity, Class activityclass){
        Intent intent = new Intent(activity, activityclass);
        activity.startActivity(intent);

    }
    public static void JumpActivity(Activity activity, Class activityclass, String key, String value){
        Intent intent = new Intent(activity, activityclass);
        intent.putExtra(key,value);
        activity.startActivity(intent);

    }
    public static void ChangestatusBar(Activity activity, String colors){
        Window window = activity.getWindow();
        window.setStatusBarColor(Color.parseColor(colors));
    }
    /*pop*/
    public static PopupWindow searchRelated(Activity activity, View view, String bgcolor, View location){
        PopupWindow popupWindow = new PopupWindow(activity);
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);
        ColorDrawable cd = new ColorDrawable(Color.parseColor(bgcolor));
        popupWindow.setBackgroundDrawable(cd);
        popupWindow.showAsDropDown(location);
        return popupWindow;
    }
    public static void Dispop(PopupWindow popupWindow){
        popupWindow.dismiss();
    }
    public static void StorageSharep(Activity activity, String name, String key, String value){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,value);
        edit.commit();
    }
    public static String SelectSharep(Activity activity, String name, String key){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static <T>T SelectSharep(Activity activity, String name, String key, Class<T> myclass){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(name, Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, null);
        return string==null?null:new Gson().fromJson(string,myclass);
    }
    public static Intent initintent(Activity activity, Class myclass){
        return new Intent(activity, myclass);
    }
    public static void  StopServerr(Activity activity, Class myclass){
        activity.stopService(initintent(activity,myclass));

    }
    public static void StartServer(Activity activity, Class myclass, Map<String, String> map){
       handler.post(new Runnable() {
           @Override
           public void run() {
               Intent intent = initintent(activity, myclass);
               for (Map.Entry<String, String> maps:map.entrySet()){
                   intent.putExtra(maps.getKey(),maps.getValue());
               }
               activity.startService(intent);
           }
       });
    }

}
