package cn.ucai.live.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.ucai.live.I;
import cn.ucai.live.LiveHelper;
import cn.ucai.live.R;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.live.data.NetDao;
import cn.ucai.live.data.model.Result;
import cn.ucai.live.utils.CommonUtils;
import cn.ucai.live.utils.L;
import cn.ucai.live.utils.MD5;
import cn.ucai.live.utils.MFGT;
import cn.ucai.live.utils.OkHttpUtils;
import cn.ucai.live.utils.OnCompleteListener;
import cn.ucai.live.utils.ResultUtils;

public class RegisterActivity extends BaseActivity {
    private final static String TAG = BaseActivity.class.getSimpleName();

    @BindView(R.id.email)
    EditText etUsername;
    @BindView(R.id.usernick)
    EditText etUserNick;
    @BindView(R.id.password_confirm)
    EditText etPasswordConfirm;
    @BindView(R.id.password)
    EditText etPassword;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ProgressDialog pd;

    String username, usernick, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                usernick = etUserNick.getText().toString().trim();
                confirmPassword = etPasswordConfirm.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    CommonUtils.showShortToast(R.string.User_name_cannot_be_empty);
                    etUsername.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(usernick)) {
                    CommonUtils.showShortToast(R.string.User_nick_cannot_be_empty);
                    etUserNick.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    CommonUtils.showShortToast(R.string.Password_cannot_be_empty);
                    etPassword.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(confirmPassword)) {
                    CommonUtils.showShortToast(R.string.Confirm_password_cannot_be_empty);
                    etPasswordConfirm.requestFocus();
                    return;
                } else if (!password.equals(confirmPassword)) {
                    CommonUtils.showShortToast(R.string.Two_input_password);
                    return;
                }
/*
                if(TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(password.getText())){
                    showToast("用户名和密码不能为空");
                    return;
                }*/
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    pd = new ProgressDialog(RegisterActivity.this);
                    pd.setMessage(getResources().getString(R.string.Is_the_registered));
                    pd.show();

                }
                registerAppSever();
            }

            private void registerEMservice() {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            // call method in SDK
                            EMClient.getInstance().createAccount(username, MD5.getMessageDigest(password));
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    if (!RegisterActivity.this.isFinishing())
                                        pd.dismiss();
                                    // save current user
                                    LiveHelper.getInstance().setCurrentUserName(username);
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
                                    finish();
                                    MFGT.gotoLoginActivity(RegisterActivity.this);
                                }
                            });
                        } catch (final HyphenateException e) {
                            //取消注册
                            unRegister();
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    if (!RegisterActivity.this.isFinishing())
                                        pd.dismiss();
                                    int errorCode = e.getErrorCode();
                                    if (errorCode == EMError.NETWORK_ERROR) {
                                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
                                    } else if (errorCode == EMError.USER_ALREADY_EXIST) {
                                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
                                    } else if (errorCode == EMError.USER_AUTHENTICATION_FAILED) {
                                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
                                    } else if (errorCode == EMError.USER_ILLEGAL_ARGUMENT) {
                                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }).start();

            }


            private void registerAppSever() {
                //注册自己的服务器的账号
                NetDao.register(RegisterActivity.this, username, usernick, password, new OnCompleteListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        if (s != null) {
                            Result result = ResultUtils.getResultFromJson(s, null);
                            L.e(TAG, "result==" + result);
                            if (result != null) {
                                L.e(TAG, "result.RetMsg==" + result.isRetMsg());
                                if (result.isRetMsg()) {
                                    L.e(TAG, "成功" );
                                    //注册成功后调用环信的注册
                                    registerEMservice();
                                } else {
                                    L.e(TAG, "result.RetMsg==" + result.isRetMsg());
                                    pd.dismiss();
                                    if (result.getRetCode() == I.MSG_REGISTER_USERNAME_EXISTS) {
                                        CommonUtils.showShortToast(R.string.User_already_exists);
                                    } else {
                                        CommonUtils.showShortToast(R.string.Registration_failed);
                                    }
                                }
                            } else {
                                pd.dismiss();
                                CommonUtils.showShortToast(R.string.Registration_failed);
                            }
                        } else {
                            pd.dismiss();
                            CommonUtils.showShortToast(R.string.Registration_failed);
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }

            public void unRegister() {
                NetDao.unRegister(RegisterActivity.this, username, new OkHttpUtils.OnCompleteListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        L.e(TAG, "result=" + result);
                    }

                    @Override
                    public void onError(String error) {
                        L.e(TAG, "error=" + error);
                    }
                });
            }
        });
    }
}