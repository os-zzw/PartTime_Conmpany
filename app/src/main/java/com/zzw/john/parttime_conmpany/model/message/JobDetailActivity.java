package com.zzw.john.parttime_conmpany.model.message;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.orhanobut.logger.Logger;
import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.base.MyApplication;
import com.zzw.john.parttime_conmpany.bean.JobBean;
import com.zzw.john.parttime_conmpany.componments.ApiClient;
import com.zzw.john.parttime_conmpany.service.Api;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JobDetailActivity extends AppCompatActivity {

    JobBean.JobListBean mJobListBean;
    @BindView(R.id.detail_title)
    TextView mDetailTitle;
    @BindView(R.id.btn_signup)
    BootstrapButton mBtnSignup;
    @BindView(R.id.btn_back)
    BootstrapButton mBtnBack;
    @BindView(R.id.activity_job_detail)
    LinearLayout mActivityJobDetail;
    @BindView(R.id.detail_money)
    TextView mDetailMoney;
    @BindView(R.id.detail_sex)
    TextView mDetailSex;
    @BindView(R.id.detail_type)
    TextView mDetailType;
    @BindView(R.id.detail_address)
    TextView mDetailAddress;
    @BindView(R.id.detail_request)
    TextView mDetailRequest;

    Api mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        ButterKnife.bind(this);

        mApi = ApiClient.getApi();

        initData();
        initView();

    }

    private void initView() {
        mDetailTitle.setText(mJobListBean.getName());
        mDetailMoney.setText(mJobListBean.getSalary());
        mDetailSex.setText(mJobListBean.getSex());
        mDetailType.setText(mJobListBean.getType());
        mDetailAddress.setText(mJobListBean.getAddress());
        mDetailRequest.setText(mJobListBean.getRemark());

        if (!this.getIntent().getSerializableExtra("from").equals("AllJobActivity")){
            mBtnSignup.setVisibility(View.INVISIBLE);
            mBtnBack.setVisibility(View.INVISIBLE);
        }

    }

    private void initData() {
        mJobListBean = (JobBean.JobListBean) this.getIntent().getSerializableExtra("bean");
    }
}
