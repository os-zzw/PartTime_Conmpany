package com.zzw.john.parttime_conmpany.bean;

/**
 * Created by john(Zhewei) on 2016/11/29.
 */

public class EmployerBeanAll extends BaseBean {


    private EmployerBean employer;

    public EmployerBean getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerBean employer) {
        this.employer = employer;
    }

    public static class EmployerBean {
        private int id;
        private String companyName;
        private String location;
        private String nickname;
        private String password;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
