package com.test.jd.copy;

/**
 * @author :panligang
 * @description :
 * @create :2023-04-24 17:08:00
 */
public class User implements Cloneable {

    private String userName;

    private int age;

    public User() {
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 浅copy
    @Override
    public User clone() {
        try {
            User clone = (User) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        User user = new User("tom",10);
        User clone = user.clone();
        System.out.println(user.userName == clone.userName);
        clone.setUserName("jack");
        System.out.println(user.getUserName());
    }
}
