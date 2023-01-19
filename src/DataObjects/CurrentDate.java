package DataObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface CurrentDate {

    default String getCurrentDate() {
        //getting and formatting the current account creation date
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");//format patter
        Date date = new Date(System.currentTimeMillis());//System.currentTimeMillis() get the current date
        return formatter.format(date);
    }

    default String formatDate(Date date){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");//format patter
        return formatter.format(date);
    }
}
