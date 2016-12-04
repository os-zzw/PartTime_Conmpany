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
        private String advantage;
        private int age;
        private int height;
        private int id;
        private String intent;
        private String name;
        private String nickname;
        private String password;
        private String schoolName;
        private String sex;

        public String getAdvantage() {
            return advantage;
        }

        public void setAdvantage(String advantage) {
            this.advantage = advantage;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
