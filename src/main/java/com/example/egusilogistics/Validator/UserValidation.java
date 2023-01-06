package com.example.egusilogistics.Validator;

public class UserValidation {

    public static boolean isPhoneNumber(String phoneNumber) {
      return   phoneNumber.length() == 11;
    }

    public static boolean isEmailAddress(String emailAddress) {
        return emailAddress.contains("@");
    }

    public static boolean isPassword(String password) {
        return password.matches("[a-zA-Z0-9(!@#$*_)]{8,20}");

    }
}
