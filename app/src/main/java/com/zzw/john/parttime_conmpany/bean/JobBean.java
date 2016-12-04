package com.zzw.john.parttime_conmpany.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by john(Zhewei) on 2016/11/29.
 */

public class JobBean {

    private List<JobListBean> jobList;

    public List<JobListBean> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobListBean> jobList) {
        this.jobList = jobList;
    }


    public static class JobListBean implements Serializable {
        private String address;
        private String employerID;
        private int id;
        private String name;
        private int num;
        private String remark;
        private String salary;
        private String sex;
        private String type;

        @Override
        public String toString() {
            return "JobListBean{" +
                    "address='" + address + '\'' +
                    ", employerID='" + employerID + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", num=" + num +
                    ", remark='" + remark + '\'' +
                    ", salary='" + salary + '\'' +
                    ", sex='" + sex + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmployerID() {
            return employerID;
        }

        public void setEmployerID(String employerID) {
            this.employerID = employerID;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
