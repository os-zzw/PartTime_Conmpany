package com.zzw.john.parttime_conmpany.model.me;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.base.MyApplication;
import com.zzw.john.parttime_conmpany.bean.BaseBean;
import com.zzw.john.parttime_conmpany.bean.EmployerBeanAll;
import com.zzw.john.parttime_conmpany.componments.ApiClient;
import com.zzw.john.parttime_conmpany.service.Api;
import com.zzw.john.parttime_conmpany.utils.UIUtils;

import java.util.Calendar;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompanyActivity extends AppCompatActivity {

    private TextView userNameTV,companyNameTV;
    private EditText addressET,remarkET;
    private LinearLayout addressLLO,addressEditLLO,remarkLLO,remarkEditLLO;
    private RelativeLayout companyNameRLO;
    private ProgressDialog progressDialog;

    private EmployerBeanAll.EmployerBean employer;

    Api api;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_company);
        
        api= ApiClient.getApi();
        
        initData();
        initView();
    }

    private void initData() {
        employer= MyApplication.employerBean;
    }

    private void initView() {
        userNameTV=(TextView)findViewById(R.id.userNameTV);
        companyNameTV=(TextView)findViewById(R.id.companyNameTV);
        addressET=(EditText)findViewById(R.id.addressET);
        remarkET=(EditText)findViewById(R.id.remarkET);
        addressLLO=(LinearLayout)findViewById(R.id.addressLLO);
        addressEditLLO=(LinearLayout)findViewById(R.id.addressEditLLO);
        remarkLLO=(LinearLayout)findViewById(R.id.remarkLLO);
        remarkEditLLO=(LinearLayout)findViewById(R.id.remarkEditLLO);
        companyNameRLO=(RelativeLayout)findViewById(R.id.companyNameRLO);

        progressDialog=new ProgressDialog(CompanyActivity.this);
        progressDialog.setCancelable(false);

        companyNameTV.setText(employer.getCompanyName());

        companyNameRLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText=new EditText(UIUtils.getContext());
                editText.setTextColor(Color.rgb(0,0,0));
                new AlertDialog.Builder(CompanyActivity.this).setTitle("请输入公司名称").setView(editText).
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                progressDialog.setMessage("请稍等");
                                progressDialog.show();
//                                Observable<BaseBean> register = api.updateName(employer.getId(),editText.getText().toString());
//                                register.subscribeOn(Schedulers.io())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe(new Subscriber<BaseBean>() {
//                                            @Override
//                                            public void onCompleted() {}
//
//                                            @Override
//                                            public void onError(Throwable e) {
//                                                Logger.d(e);
//                                                progressDialog.dismiss();
//                                                UIUtils.showToast("超时,请重试!");
//                                            }
//
//                                            @Override
//                                            public void onNext(BaseBean baseBean) {
//                                                progressDialog.dismiss();
//                                                if (baseBean.getFlag().equals("true")) {
//                                                    UIUtils.showToast("更改成功");
//                                                    employer.setCompanyName(editText.getText().toString());
//                                                    companyNameTV.setText(editText.getText());
//                                                } else {
//                                                    UIUtils.showToast("更改失败,请重试");
//                                                }
//                                            }
//                                        });
                            }
                        }).setNegativeButton("取消",null).show();
            }
        });

        addressEditLLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressEditLLO.setVisibility(View.INVISIBLE);
                addressET.setInputType(InputType.TYPE_CLASS_TEXT);
                addOptionBtn(addressLLO,1);
            }
        });

        remarkEditLLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remarkEditLLO.setVisibility(View.INVISIBLE);
                remarkET.setInputType(InputType.TYPE_CLASS_TEXT);
                addOptionBtn(remarkLLO,2);
            }
        });
    }

    private void addOptionBtn(final LinearLayout superLayout, final int source){
        //source 1:兼职意向 2:个人评价
        final LinearLayout newLayout=new LinearLayout(this);
        Button confirmBtn=new Button(this);
        Button cancelBtn=new Button(this);
        LinearLayout.LayoutParams layoutParams;
        int layoutWidth=superLayout.getWidth();
        final String initContent;

        if (source==1)
            initContent=addressET.getText().toString();
        else
            initContent=remarkET.getText().toString();

        newLayout.setOrientation(LinearLayout.HORIZONTAL);
        confirmBtn.setText("确认");
        cancelBtn.setText("取消");
        layoutParams=new LinearLayout.LayoutParams(layoutWidth/4, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(layoutWidth/6,0,0,0);
        confirmBtn.setLayoutParams(layoutParams);
        cancelBtn.setLayoutParams(layoutParams);
        newLayout.addView(confirmBtn);
        newLayout.addView(cancelBtn);
        superLayout.addView(newLayout);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String editContent;

                progressDialog.setMessage("请稍等");
                progressDialog.show();

                if (source==1){
//                    editContent=addressET.getText().toString();
//                    Observable<BaseBean> register = api.updateIntent(employer.getId(),editContent);
//                    register.subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Subscriber<BaseBean>() {
//                                @Override
//                                public void onCompleted() {}
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Logger.d(e);
//                                    progressDialog.dismiss();
//                                    UIUtils.showToast("超时,请重试!");
//                                }
//
//                                @Override
//                                public void onNext(BaseBean baseBean) {
//                                    progressDialog.dismiss();
//                                    if (baseBean.getFlag().equals("true")) {
//                                        UIUtils.showToast("更改成功");
//                                        employer.setIntent(editContent);
//                                        addressEditLLO.setVisibility(View.VISIBLE);
//                                        addressET.setInputType(InputType.TYPE_NULL);
//                                        superLayout.removeView(newLayout);
//                                    } else {
//                                        UIUtils.showToast("更改失败,请重试");
//                                    }
//                                }
//                            });

                }else {
//                    editContent=remarkET.getText().toString();
//                    Observable<BaseBean> register = api.updateAdvantage(employer.getId(),editContent);
//                    register.subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Subscriber<BaseBean>() {
//                                @Override
//                                public void onCompleted() {}
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Logger.d(e);
//                                    progressDialog.dismiss();
//                                    UIUtils.showToast("超时,请重试!");
//                                }
//
//                                @Override
//                                public void onNext(BaseBean baseBean) {
//                                    progressDialog.dismiss();
//                                    if (baseBean.getFlag().equals("true")) {
//                                        UIUtils.showToast("更改成功");
//                                        employer.setAdvantage(editContent);
//                                        remarkEditLLO.setVisibility(View.VISIBLE);
//                                        remarkET.setInputType(InputType.TYPE_NULL);
//                                        superLayout.removeView(newLayout);
//                                    } else {
//                                        UIUtils.showToast("更改失败,请重试");
//                                    }
//                                }
//                            });

                }

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (source==1){
                    addressET.setText(initContent);
                    addressEditLLO.setVisibility(View.VISIBLE);
                    addressET.setInputType(InputType.TYPE_NULL);
                }else {
                    remarkET.setText(initContent);
                    remarkEditLLO.setVisibility(View.VISIBLE);
                    remarkET.setInputType(InputType.TYPE_NULL);
                }
                superLayout.removeView(newLayout);
            }
        });
    }

}
