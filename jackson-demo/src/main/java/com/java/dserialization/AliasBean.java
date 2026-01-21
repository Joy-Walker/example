package com.java.dserialization;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AliasBean {

    @JsonProperty("first_name") // 发出去是 first_name，回来也认 first_name
    @JsonAlias({ "fName", "f_name" })
    private String firstName;   
    private String lastName;

    public AliasBean(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}