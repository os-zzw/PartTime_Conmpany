package com.zzw.john.parttime_conmpany.model.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.bean.BaseBean;
import com.zzw.john.parttime_conmpany.componments.ApiClient;
import com.zzw.john.parttime_conmpany.service.Api;
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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    BootstrapEditText mEtUsername;
    @BindView(R.id.et_password)
    BootstrapEditText mEtPassword;
    @BindView(R.id.btn_register)
    BootstrapButton mBtnRegister;
    @BindView(R.id.btn_back)
    BootstrapButton mBtnBack;

    Api api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        api = ApiClient.getApi();
    }

    @OnClick({R.id.btn_register, R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String nickname = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(nickname) && TextUtils.isEmpty(password)) {
                    UIUtils.showToast("不能为空,请重新输入");
                    break;
                } else {
                    Observable<BaseBean> register = api.register(nickname, password);
                    register.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<BaseBean>() {
                                @Override
                                public void call(BaseBean baseBean) {
                                    UIUtils.showToast(baseBean.getFlag());
                                }
                            });
                }
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
