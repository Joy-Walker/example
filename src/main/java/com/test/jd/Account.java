package com.test.jd;

public final class Account {
    private final StringBuffer user;

    public Account(String user) {
        this.user =
                new StringBuffer(user);
    }

    public StringBuffer getUser() {
        return this.user;
    }

    public String toString() {
        return "user" + user;
    }

    public static void main(String[] args) {
        Account account = new Account("panligang");
        StringBuffer user = account.getUser();
        user.append("111");
        System.out.println(account);
    }
}