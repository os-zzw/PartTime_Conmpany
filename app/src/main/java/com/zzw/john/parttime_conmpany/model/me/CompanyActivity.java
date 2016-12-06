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
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.base.MyApplication;
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

    private TextView userNameTV;
    private EditText intentET,commentET;
    private LinearLayout intentLLO,intentEditLLO,commentLLO,commentEditLLO;
    private ListView meInfoLV;
    private ProgressDialog progressDialog;

    private EmployerBeanAll.EmployerBean employer= MyApplication.employerBean;

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
    }

    private void initView() {
        userNameTV=(TextView)findViewById(R.id.userNameTV);
        intentET=(EditText)findViewById(R.id.intentET);
        commentET=(EditText)findViewById(R.id.commentET);
        intentLLO=(LinearLayout)findViewById(R.id.intentLLO);
        intentEditLLO=(LinearLayout)findViewById(R.id.intentEditLLO);
        commentLLO=(LinearLayout)findViewById(R.id.commentLLO);
        commentEditLLO=(LinearLayout)findViewById(R.id.commentEditLLO);
        meInfoLV=(ListView)findViewById(R.id.meInfoLV);
        meInfoLV.setAdapter(new ResumeListAdapter(employer));

        meInfoLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                final EditText editText=new EditText(UIUtils.getContext());
                final ViewGroup viewGroup=(ViewGroup) meInfoLV.getChildAt(position);
                final TextView itemInfoTV=(TextView)viewGroup.getChildAt(1);
                editText.setTextColor(Color.rgb(0,0,0));

                switch (position){
                    case 0:
//                        new AlertDialog.Builder(ResumeActivity.this).setTitle("请输入真实姓名").setView(editText).
//                                setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        progressDialog.setMessage("请稍等");
//                                        progressDialog.show();
//                                        Observable<BaseBean> register = api.updateName(employer.getId(),editText.getText().toString());
//                                        register.subscribeOn(Schedulers.io())
//                                                .observeOn(AndroidSchedulers.mainThread())
//                                                .subscribe(new Subscriber<BaseBean>() {
//                                                    @Override
//                                                    public void onCompleted() {}
//
//                                                    @Override
//                                                    public void onError(Throwable e) {
//                                                        Logger.d(e);
//                                                        progressDialog.dismiss();
//                                                        UIUtils.showToast("超时,请重试!");
//                                                    }
//
//                                                    @Override
//                                                    public void onNext(BaseBean baseBean) {
//                                                        progressDialog.dismiss();
//                                                        if (baseBean.getFlag().equals("true")) {
//                                                            UIUtils.showToast("更改成功");
//                                                            employer.setName(editText.getText().toString());
//                                                            itemInfoTV.setText(editText.getText());
//                                                        } else {
//                                                            UIUtils.showToast("更改失败,请重试");
//                                                        }
//                                                    }
//                                                });
//                                    }
//                                }).setNegativeButton("取消",null).show();
                        break;
                    case 1:
//                        Calendar calendar= Calendar.getInstance();
//                        final int yearCurrent, monthCurrent, dayCurrent;
//
//                        yearCurrent = calendar.get(Calendar.YEAR);
//                        monthCurrent = calendar.get(Calendar.MONTH);
//                        dayCurrent = calendar.get(Calendar.DAY_OF_MONTH);
//
//                        new DatePickerDialog(ResumeActivity.this, new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                progressDialog.setMessage("请稍等");
//                                progressDialog.show();
//                                final Integer age=new Integer(yearCurrent-year);
//                                Observable<BaseBean> register = api.updateAge(employer.getId(),age);
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
//                                                    employer.setAge(age);
//                                                    itemInfoTV.setText(Integer.toString(age));
//                                                } else {
//                                                    UIUtils.showToast("更改失败,请重试");
//                                                }
//                                            }
//                                        });
//                            }
//                        }, yearCurrent, monthCurrent, dayCurrent).show();
                        break;
                    case 2:
//                        new AlertDialog.Builder(ResumeActivity.this).setTitle("选择性别").
//                                setSingleChoiceItems(new String[]{"男","女"},-1,new DialogInterface.OnClickListener(){
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        final String sex;
//                                        String gender=null;
//                                        if (which==0)
//                                            gender="男";
//                                        else
//                                            gender="女";
//                                        sex=gender;
//                                        progressDialog.setMessage("请稍等");
//                                        progressDialog.show();
//                                        Observable<BaseBean> register = api.updateSex(employer.getId(),sex);
//                                        register.subscribeOn(Schedulers.io())
//                                                .observeOn(AndroidSchedulers.mainThread())
//                                                .subscribe(new Subscriber<BaseBean>() {
//                                                    @Override
//                                                    public void onCompleted() {}
//
//                                                    @Override
//                                                    public void onError(Throwable e) {
//                                                        Logger.d(e);
//                                                        progressDialog.dismiss();
//                                                        UIUtils.showToast("超时,请重试!");
//                                                    }
//
//                                                    @Override
//                                                    public void onNext(BaseBean baseBean) {
//                                                        progressDialog.dismiss();
//                                                        if (baseBean.getFlag().equals("true")) {
//                                                            UIUtils.showToast("更改成功");
//                                                            employer.setSex(sex);
//                                                            itemInfoTV.setText(sex);
//                                                        } else {
//                                                            UIUtils.showToast("更改失败,请重试");
//                                                        }
//                                                    }
//                                                });
//                                        dialog.dismiss();
//                                    }
//
//                                }).show();
                        break;
                    case 3:
//                        final Dialog statureDialog = new Dialog(ResumeActivity.this);
//                        statureDialog.setContentView(R.layout.meinfopicker_dialog);
//
//                        String initValue=itemInfoTV.getText().toString();
//                        final NumberPicker statureNP = (NumberPicker) statureDialog.findViewById(R.id.statureNP);
//                        final Button statureCfmBtn=(Button)statureDialog.findViewById(R.id.confirmBtn);
//
//                        statureNP.setMaxValue(250);
//                        statureNP.setMinValue(70);
//                        if (initValue.equals(""))
//                            statureNP.setValue(160);
//                        else
//                            statureNP.setValue(Integer.parseInt(initValue.split("cm")[0]));
//                        statureNP.setFormatter(new NumberPicker.Formatter() {
//                            @Override
//                            public String format(int value) {
//                                return Integer.toString(value)+"cm";
//                            }
//                        });
//
//                        statureNP.setWrapSelectorWheel(false);
//                        statureNP.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//
//                        statureCfmBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                progressDialog.setMessage("请稍等");
//                                progressDialog.show();
//                                Observable<BaseBean> register = api.updateHeight(employer.getId(),statureNP.getValue());
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
//                                                    employer.setHeight(statureNP.getValue());
//                                                    itemInfoTV.setText(Integer.toString(statureNP.getValue())+"cm");
//                                                } else {
//                                                    UIUtils.showToast("更改失败,请重试");
//                                                }
//                                            }
//                                        });
//                                statureDialog.dismiss();
//                            }
//                        });
//
//                        statureDialog.show();

                        break;
                    case 4:
//                        final Dialog schoolDialog=new Dialog(ResumeActivity.this);
//                        schoolDialog.setContentView(R.layout.meinfopicker_dialog);
//
//                        final NumberPicker schoolNP = (NumberPicker) schoolDialog.findViewById(R.id.statureNP);
//                        final Button schoolCfmBtn=(Button)schoolDialog.findViewById(R.id.confirmBtn);
//                        final String[] schools={"东北大学","鲁迅美术学院","辽宁大学","沈阳航空航天大学","中国医科大学"};
//
//                        schoolNP.setDisplayedValues(schools);
//                        schoolNP.setMaxValue(schools.length-1);
//                        schoolNP.setMinValue(0);
//                        schoolNP.setValue(0);
//
//                        schoolNP.setWrapSelectorWheel(false);
//                        schoolNP.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//
//                        schoolCfmBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                progressDialog.setMessage("请稍等");
//                                progressDialog.show();
//                                Observable<BaseBean> register = api.updateSchoolName(employer.getId(),schools[schoolNP.getValue()]);
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
//                                                    employer.setSchoolName(schools[schoolNP.getValue()]);
//                                                    itemInfoTV.setText(schools[schoolNP.getValue()]);
//                                                } else {
//                                                    UIUtils.showToast("更改失败,请重试");
//                                                }
//                                            }
//                                        });
//                                schoolDialog.dismiss();
//                            }
//                        });
//
//                        schoolDialog.show();

                        break;
                    case 5:
//                        final EditText phoneET=new EditText(UIUtils.getContext());
//                        phoneET.setTextColor(Color.BLACK);
//                        phoneET.setInputType(EditorInfo.TYPE_CLASS_PHONE);
//                        new AlertDialog.Builder(ResumeActivity.this).setTitle("请输入电话号码").setView(phoneET).
//                                setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        progressDialog.setMessage("请稍等");
//                                        progressDialog.show();
//                                        Observable<BaseBean> register = api.updatePhone(employer.getId(),phoneET.getText().toString());
//                                        register.subscribeOn(Schedulers.io())
//                                                .observeOn(AndroidSchedulers.mainThread())
//                                                .subscribe(new Subscriber<BaseBean>() {
//                                                    @Override
//                                                    public void onCompleted() {}
//
//                                                    @Override
//                                                    public void onError(Throwable e) {
//                                                        Logger.d(e);
//                                                        progressDialog.dismiss();
//                                                        UIUtils.showToast("超时,请重试!");
//                                                    }
//
//                                                    @Override
//                                                    public void onNext(BaseBean baseBean) {
//                                                        progressDialog.dismiss();
//                                                        if (baseBean.getFlag().equals("true")) {
//                                                            UIUtils.showToast("更改成功");
//                                                            employer.setPhone(phoneET.getText().toString());
//                                                            itemInfoTV.setText(phoneET.getText().toString());
//                                                        } else {
//                                                            UIUtils.showToast("更改失败,请重试");
//                                                        }
//                                                    }
//                                                });
//                                    }
//                                }).setNegativeButton("取消",null).show();

                        break;
                    default:
                        break;
                }
            }
        });

        intentEditLLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentEditLLO.setVisibility(View.INVISIBLE);
                intentET.setInputType(InputType.TYPE_CLASS_TEXT);
                addOptionBtn(intentLLO,1);
            }
        });

        commentEditLLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentEditLLO.setVisibility(View.INVISIBLE);
                commentET.setInputType(InputType.TYPE_CLASS_TEXT);
                addOptionBtn(commentLLO,2);
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
            initContent=intentET.getText().toString();
        else
            initContent=commentET.getText().toString();

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
//                    editContent=intentET.getText().toString();
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
//                                        intentEditLLO.setVisibility(View.VISIBLE);
//                                        intentET.setInputType(InputType.TYPE_NULL);
//                                        superLayout.removeView(newLayout);
//                                    } else {
//                                        UIUtils.showToast("更改失败,请重试");
//                                    }
//                                }
//                            });

                }else {
//                    editContent=commentET.getText().toString();
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
//                                        commentEditLLO.setVisibility(View.VISIBLE);
//                                        commentET.setInputType(InputType.TYPE_NULL);
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
                    intentET.setText(initContent);
                    intentEditLLO.setVisibility(View.VISIBLE);
                    intentET.setInputType(InputType.TYPE_NULL);
                }else {
                    commentET.setText(initContent);
                    commentEditLLO.setVisibility(View.VISIBLE);
                    commentET.setInputType(InputType.TYPE_NULL);
                }
                superLayout.removeView(newLayout);
            }
        });
    }

    //适配器
    private class ResumeListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private EmployerBeanAll.EmployerBean employer;

        public ResumeListAdapter(EmployerBeanAll.EmployerBean employer){
            this.layoutInflater=LayoutInflater.from(UIUtils.getContext());
            this.employer=employer;
        }

        @Override
        public int getCount() { return 6;}

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView itemNameTV,itemInfoTV;
            if (convertView==null){
                convertView=layoutInflater.inflate(R.layout.list_mecompany_item,null);
            }
            itemNameTV=(TextView)convertView.findViewById(R.id.itemNameTV);
            itemInfoTV=(TextView)convertView.findViewById(R.id.itemInfoTV);
            switch (position){
                case 0:
                    itemNameTV.setText("真实姓名");
//                    if (employer.getName()!=null)
//                        itemInfoTV.setText(employer.getName());
                    break;
                case 1:
                    itemNameTV.setText("年龄");
//                    if (employer.getAge()!=0)
//                        itemInfoTV.setText(Integer.toString(employer.getAge()));
                    break;
                case 2:
                    itemNameTV.setText("性别");
//                    if (employer.getSex()!=null)
//                        itemInfoTV.setText(employer.getSex());
                    break;
                case 3:
                    itemNameTV.setText("身高");
//                    if (employer.getHeight()!=0)
//                        itemInfoTV.setText(Integer.toString(employer.getHeight())+"cm");
                    break;
                case 4:
                    itemNameTV.setText("所在学校");
//                    if (employer.getSchoolName()!=null)
//                        itemInfoTV.setText(employer.getSchoolName());
                    break;
                case 5:
                    itemNameTV.setText("电话号码");
//                    if (employer.getPhone()!=null)
//                        itemInfoTV.setText(employer.getPhone());
                    break;
                default:
                    break;
            }
            return convertView;
        }
    }
}
