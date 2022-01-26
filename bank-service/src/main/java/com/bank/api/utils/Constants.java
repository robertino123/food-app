package com.bank.api.utils;

public final class Constants {

    public static final String INCORRECT_INFO_ERROR = "Please provide correct information!";
    public static final String ACCOUNT_CREATED = "Account created successfully ! Account number : ";
    public static final String ACCOUNT_NOT_FOUND = "Please provide existing accounts information !";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds!";
    public static final String SUCCESS_TRANSFER = "Fund transfer successfully !";
    public static final String ACCOUNT_TYPE = " account type : ";

    public static String getFirstDayOfMonth(int month, int year) {
        return "01/" + month + "/" + year;
    }
}
