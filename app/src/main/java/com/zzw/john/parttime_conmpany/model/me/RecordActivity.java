package com.zzw.john.parttime_conmpany.model.me;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.base.MyApplication;
import com.zzw.john.parttime_conmpany.bean.JobBean;
import com.zzw.john.parttime_conmpany.componments.ApiClient;
import com.zzw.john.parttime_conmpany.model.message.JobDetailActivity;
import com.zzw.john.parttime_conmpany.service.Api;
import com.zzw.john.parttime_conmpany.utils.UIUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecordActivity extends AppCompatActivity {

    private ListView recordLV;
    private ProgressDialog progressDialog;

    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_record);

        api= ApiClient.getApi();

        initView();
        initData();

    }

    private void initView() {
        recordLV=(ListView)findViewById(R.id.recordLV);

        progressDialog=new ProgressDialog(RecordActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("请稍等");
        progressDialog.show();
    }

    private void initData() {
        Observable<JobBean> jobBeanObservable = api.queryAllMyJob(MyApplication.employerBean.getId());
        jobBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JobBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e);
                        progressDialog.dismiss();
                        UIUtils.showToast("超时,请重试!");
                        RecordActivity.this.finish();
                    }

                    @Override
                    public void onNext(JobBean jobBean) {
                        recordLV.setAdapter(new EnrollListAdapter(jobBean.getJobList()));
                        progressDialog.dismiss();
                    }
                });
    }

    private class EnrollListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private List<JobBean.JobListBean> jobList;

        public EnrollListAdapter(List<JobBean.JobListBean> jobList){
            this.layoutInflater=LayoutInflater.from(UIUtils.getContext());
            this.jobList=jobList;
        }

        @Override
        public int getCount() {
            return jobList.size();
        }

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

            TextView jobNameTV,jobTypeTV,jobSexTV,jobSalaryTV,jobNumTV,jobStateTV;
            Button detailBtn;

            final JobBean.JobListBean jobListBean;

            if (convertView==null){
                convertView=layoutInflater.inflate(R.layout.list_merecord_item,null);
            }

            jobNameTV=(TextView)convertView.findViewById(R.id.jobNameTV);
            jobTypeTV=(TextView)convertView.findViewById(R.id.jobTypeTV);
            jobSexTV=(TextView)convertView.findViewById(R.id.jobSexTV);
            jobSalaryTV=(TextView)convertView.findViewById(R.id.jobSalaryTV);
            jobNumTV=(TextView)convertView.findViewById(R.id.jobNumTV);
            jobStateTV=(TextView)convertView.findViewById(R.id.jobStateTV);
            detailBtn=(Button)convertView.findViewById(R.id.detailBtn);

            jobListBean=jobList.get(position);

            jobNameTV.setText(jobListBean.getName());
            jobTypeTV.setText(jobListBean.getType());
            jobSexTV.setText(jobListBean.getSex());
            jobSalaryTV.setText(jobListBean.getSalary());
            jobNumTV.setText(Integer.toString(jobListBean.getNum()));
            if (jobListBean.getFull()==0) {
                jobStateTV.setText("未完成");
                jobStateTV.setBackgroundColor(Color.GRAY);
            }else{
                jobStateTV.setText("已完成");
                jobStateTV.setBackgroundColor(Color.GREEN);
            }


            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent jobDetail = new Intent(RecordActivity.this, JobDetailActivity.class);
                    jobDetail.putExtra("bean",jobListBean);
                    jobDetail.putExtra("from","RecordActivity");
                    startActivity(jobDetail);
                }
            });

            return convertView;
        }
    }
}
