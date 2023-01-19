package DataObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {

    public static String getCurrentDate() {
        //getting and formatting the current account creation date
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");//format patter
        Date date = new Date(System.currentTimeMillis());//System.currentTimeMillis() get the current date
        return formatter.format(date);
    }

    public static String formatDate(Date date){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");//format patter
        return formatter.format(date);
    }
}
