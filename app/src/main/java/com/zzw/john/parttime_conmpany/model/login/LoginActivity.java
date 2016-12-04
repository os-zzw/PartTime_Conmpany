package com.zzw.john.parttime_conmpany.model.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.zzw.john.parttime_conmpany.MainActivity;
import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.bean.EmployerBeanAll;
import com.zzw.john.parttime_conmpany.componments.ApiClient;
import com.zzw.john.parttime_conmpany.model.register.RegisterActivity;
import com.zzw.john.parttime_conmpany.service.Api;
import com.zzw.john.parttime_conmpany.utils.ShareP;
import com.zzw.john.parttime_conmpany.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by john on 2016/11/1.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_username)
    BootstrapEditText mEtUsername;
    @BindView(R.id.et_password)
    BootstrapEditText mEtPassword;
    @BindView(R.id.btn_login)
    BootstrapButton mBtnLogin;
    @BindView(R.id.btn_register)
    BootstrapButton mBtnRegister;

    Api api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        api = ApiClient.getApi();
        initView();
    }

    private void initView() {
        mEtUsername.setText(ShareP.getString("nickname"));
        mEtPassword.setText(ShareP.getString("password"));
    }


    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String nickname = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(nickname) && TextUtils.isEmpty(password)) {
                    UIUtils.showToast("不能为空,请重新输入");
                    break;
                } else {
                    ShareP.setString("nickname",nickname);
                    ShareP.setString("password",password);
                    Observable<EmployerBeanAll> register = api.login(nickname, password);
                    register.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<EmployerBeanAll>() {
                                @Override
                                public void call(EmployerBeanAll employerBeanAll) {
                                    String flag = employerBeanAll.getFlag();
                                    if (flag.equals("true")) {
                                        //跳转主页
                                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(main);
                                    } else {
                                        UIUtils.showToast("登录失败,请重试");
                                    }
                                }
                            });
                }

                break;
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
