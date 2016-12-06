package com.zzw.john.parttime_conmpany.model.message;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.bean.JobBean;
import com.zzw.john.parttime_conmpany.componments.ApiClient;
import com.zzw.john.parttime_conmpany.service.Api;
import com.zzw.john.parttime_conmpany.utils.UIUtils;

import java.util.List;


/**
 * Created by john on 2016/11/1.
 */

public class MessageFragment extends Fragment {

    private ListView messageLV;
    private SwipeRefreshLayout messageSRLO;
    private ProgressDialog progressDialog;

    private Api api;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);

        api= ApiClient.getApi();

        initView(view);
        initData(0);

        return view;
    }

    private void initView(View view) {
        messageLV=(ListView)view.findViewById(R.id.messageLV);
        messageSRLO=(SwipeRefreshLayout)view.findViewById(R.id.messageSRLO);
        messageSRLO.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                initData(1);
            }
        });

//        progressDialog=new ProgressDialog(getActivity());
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("请稍等");
//        progressDialog.show();
    }

    private void initData(final int type) {
        messageLV.setAdapter(new MessageListAdapter(null,null));
    }

    //适配器
    private class MessageListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private List<JobBean.JobListBean> jobList;
        private List<String> nameList;

        public MessageListAdapter(List<JobBean.JobListBean> jobList, List<String> nameList){
            this.layoutInflater=LayoutInflater.from(UIUtils.getContext());
            this.jobList=jobList;
            this.nameList=nameList;
        }

        @Override
        public int getCount() {
            return 5;
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

            TextView jobNameTV,jobTypeTV,employeeTV;
            Button detailBtn,acceptBtn,refuseBtn;

            final JobBean.JobListBean jobListBean;

            if (convertView==null){
                convertView=layoutInflater.inflate(R.layout.list_message_item,null);
            }

            jobNameTV=(TextView)convertView.findViewById(R.id.jobNameTV);
            jobTypeTV=(TextView)convertView.findViewById(R.id.jobTypeTV);
            employeeTV=(TextView)convertView.findViewById(R.id.employeeTV);
            detailBtn=(Button)convertView.findViewById(R.id.detailBtn);
            acceptBtn=(Button)convertView.findViewById(R.id.acceptBtn);
            refuseBtn=(Button)convertView.findViewById(R.id.refuseBtn);

//            jobListBean=jobList.get(position);

//            jobNameTV.setText(jobListBean.getName());
//            jobTypeTV.setText(jobListBean.getType());
//            employeeTV.setText(nameList.get(position));

            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent jobDetail = new Intent(getActivity(), JobDetailActivity.class);
//                    jobDetail.putExtra("bean",jobListBean);
//                    jobDetail.putExtra("from","MessageFragment");
//                    startActivity(jobDetail);
                }
            });

            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messageSRLO.setRefreshing(true);
                }
            });

            refuseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messageSRLO.setRefreshing(false);
                }
            });

            return convertView;
        }
    }

}
