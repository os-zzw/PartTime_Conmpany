package com.zzw.john.parttime_conmpany.model.me;

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

import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.componments.ApiClient;
import com.zzw.john.parttime_conmpany.service.Api;


public class ReleaseActivity extends AppCompatActivity {
    Api api;
    private Spinner sp;
    private EditText Jobname;
    private EditText Salary ;
    private EditText Place ;
    private EditText Remarks ;
    private Button msg_btn;
    private Button msg_btn2;

    private String remarks;
    private String place;
    private String jobname;
    private String salary;
    private String result;
    private String sex;
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
        jobname=Jobname.getText().toString();
        Salary=(EditText)findViewById(R.id.editText2);
        salary=Salary.getText().toString();
        Place=(EditText)findViewById(R.id.editText3);
        place=Place.getText().toString();
        Remarks=(EditText)findViewById(R.id.editText4);
        remarks=Remarks.getText().toString();

        msg_btn =(Button)findViewById(R.id.button);
        msg_btn.setOnClickListener(submit);
        msg_btn2=(Button)findViewById(R.id.button2);
        msg_btn2.setOnClickListener(fetch);
    }

    private Button.OnClickListener submit=new View.OnClickListener(){
        public void onClick(View view){

        }
    };

    private Button.OnClickListener fetch=new View.OnClickListener(){
        public void onClick(View view){

        }
    };
}
