package com.zzw.john.parttime_conmpany.model.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.base.MyApplication;
import com.zzw.john.parttime_conmpany.bean.JobBean;
import com.zzw.john.parttime_conmpany.componments.ApiClient;
import com.zzw.john.parttime_conmpany.service.Api;
import com.zzw.john.parttime_conmpany.utils.UIUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ReleaseActivity extends AppCompatActivity {
    Api api;
    private Spinner sp;
    private EditText Jobname;
    private EditText Salary ;
    private EditText Place ;
    private EditText Remarks ;
    private EditText Num;
    private Button msg_btn;
    private Button msg_btn2;

    private String remarks;
    private String place;
    private String jobname;
    private String salary;
    private String result;
    private String sex;
    private int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_release);
        api = ApiClient.getApi();


        sp=(Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter1;
        ArrayAdapter<String> adapter2;

        adapter1=ArrayAdapter.createFromResource(this, R.array.ctype, android.R.layout.simple_dropdown_item_1line);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String[] ctype={ "全部兼职", "优质兼职", "附近兼职", "周末兼职" };

        adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ctype);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter2);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long id)
            {
                result=parent.getItemAtPosition(pos).toString();
                Toast.makeText(ReleaseActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }

        });

        RadioGroup group=(RadioGroup) findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton rButton=(RadioButton) findViewById(radioButtonId);
                sex=(String) rButton.getText();
            }
        });

        Jobname=(EditText)findViewById(R.id.editText);
        Salary=(EditText)findViewById(R.id.editText2);
        Place=(EditText)findViewById(R.id.editText3);
        Remarks=(EditText)findViewById(R.id.editText4);
        Num=(EditText)findViewById(R.id.editText5);


        msg_btn =(Button)findViewById(R.id.button);
        msg_btn.setOnClickListener(submit);
        msg_btn2=(Button)findViewById(R.id.button2);
        msg_btn2.setOnClickListener(fetch);
    }

    private Button.OnClickListener submit=new View.OnClickListener(){
        public void onClick(View view){
            jobname=Jobname.getText().toString();
            salary=Salary.getText().toString();
            place=Place.getText().toString();
            remarks=Remarks.getText().toString();
            num =Integer.valueOf(Num.getText().toString()).intValue();
            Observable<JobBean> ADD = api.add(Integer.toString(MyApplication.employerBean.getId()),result,jobname,sex,salary,place,remarks,num);
            ADD.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<JobBean>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            Logger.d(e);
                            Toast.makeText(ReleaseActivity.this, "出现错误,请重试!", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNext(JobBean jobBean){
                            ReleaseActivity.this.finish();
                        }
                        }

                    );

        }
    };

    private Button.OnClickListener fetch=new View.OnClickListener(){
        public void onClick(View view){
            ReleaseActivity.this.finish();
        }
    };
}
