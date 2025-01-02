package com.test.jd.enums;


public enum Gender {
    MALE("Male") {
        public boolean isMale() {
            return true;
        }
    },
    FEMALE("Female") {
        public boolean isMale() {
            return false;
        }
    };

    private final String genderText;

    Gender(String genderText) {
        this.genderText = genderText;
    }

    public String getGenderText() {
        return genderText;
    }

//    public abstract boolean isMale();
}