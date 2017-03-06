package cn.ucai.live.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.easeui.domain.User;

import cn.ucai.live.R;
import cn.ucai.live.ui.activity.ChangeActivity;
import cn.ucai.live.ui.activity.LoginActivity;
import cn.ucai.live.ui.activity.MainActivity;
import cn.ucai.live.ui.activity.RegisterActivity;


/**
 * Created by Administrator on 2017/1/10 0010.
 */
public class MFGT {

    public static void finish(Activity context, Class<?> clz){
        context.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        context.finish();
    }
    public static void finish(Activity context){
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        context.finish();
    }
    public static void startActivity(Activity context, Class<?> clz){
        context.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        context.startActivity(new Intent(context,clz));
    }
    public static void startActivity(Context context,Intent intent){
        context.startActivity(intent);
    }

    public static void gotoLoginActivity(Activity activity) {
        startActivity(activity, LoginActivity.class);
    }
    public static void gotoLoginCleanTask(Activity activity) {
        startActivity(activity, new Intent(activity,LoginActivity.class)
        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));

    }

    public static void gotoRegisterActivity(Activity activity) {
        startActivity(activity, RegisterActivity.class);
    }


    public static void gotoMainActivity(Activity activity) {
        startActivity(activity,new Intent(activity,MainActivity.class).putExtra("right",true));
    }

    public static void gotoChangeActivity(Activity activity) {
        startActivity(activity,ChangeActivity.class);

    }
}
