package DataObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate extends Date{

    public static Date getCurrentDate() {
        //getting and formatting the current account creation date
        Date date = new Date(System.currentTimeMillis());//System.currentTimeMillis() get the current date
        return date;
    }

    public static String showDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");//format patter
        return formatter.format(date);
    }
}
