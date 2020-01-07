package com.intellect.test.Assignmentdemo.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverterUtil {
    public String convertDateFormat(String input) throws Exception{
        String OutputDate = null;
        DateFormat srcDf = new SimpleDateFormat("dd-MMM-yyyy");
        // parse the date string into Date object
        Date date = srcDf.parse(input);
        DateFormat destDf = new SimpleDateFormat("yyyy-MM-dd");
        // format the date into another format
        OutputDate = destDf.format(date);
        return OutputDate;
    }

    public static void main(String[] args) throws Exception {
        DateConverterUtil converterUtil = new DateConverterUtil();
        System.out.println(converterUtil.convertDateFormat("23-MAR-1980"));
    }

}