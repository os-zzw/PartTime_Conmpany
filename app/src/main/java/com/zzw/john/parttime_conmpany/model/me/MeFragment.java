package com.zzw.john.parttime_conmpany.model.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zzw.john.parttime_conmpany.R;
import com.zzw.john.parttime_conmpany.utils.UIUtils;


/**
 * Created by john on 2016/11/1.
 */

public class MeFragment extends Fragment {

    private ListView choiceLV;
    private TextView userNameTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);

        userNameTV=(TextView)view.findViewById(R.id.userNameTV);
        //userNameTV.setText(MyApplication.employeeBean.getNickname());

        choiceLV=(ListView)view.findViewById(R.id.choiceLV);
        choiceLV.setAdapter(new MeListAdapter());
        choiceLV.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=null;
                if (position==0){
                    intent=new Intent(getActivity(),CompanyActivity.class);
                }else if (position==1){
                    intent=new Intent(getActivity(),RecordActivity.class);
                }else {
                    intent=new Intent(getActivity(),ReleaseActivity.class);
                }
                startActivity(intent);
            }
        });

        return view;
    }

    //适配器
    private class MeListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public MeListAdapter(){
            this.layoutInflater=LayoutInflater.from(UIUtils.getContext());
        }

        @Override
        public int getCount() {
            return 3;
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
            TextView listNameTV;
            if (convertView==null){
                convertView=layoutInflater.inflate(R.layout.list_me_item,null);
            }

            listNameTV=(TextView)convertView.findViewById(R.id.listNameTV);
            if (position==0) {
                listNameTV.setText("公司信息");
            }else if (position==1){
                listNameTV.setText("我的发布");
            }else{
                listNameTV.setText("发布兼职");
            }
            return convertView;
        }
    }
}
