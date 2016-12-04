package com.zzw.john.parttime_conmpany.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zzw.john.parttime_conmpany.utils.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by ZheWei on 2016/9/14.
 */
public abstract class BaseActivity<T> extends AppCompatActivity {

    protected T mPresenter;
    protected Activity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(setLayout());

        ButterKnife.bind(this);
        mContext = this;
        mPresenter = setPresenter();
        init();
    }

    protected void setCommonBackToolBack(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showToast("点击了Navigation");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int setLayout();

    protected abstract T setPresenter();

    protected abstract void init();

}
