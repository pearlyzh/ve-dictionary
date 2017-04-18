/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.bussiness;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author NghiaTruongNgoc
 */
public class DateHandler {
    public static Date stringToDate(String strDate)
    {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = df.parse(strDate);
            //String newDateString = df.format(startDate);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static String dateToString(Date date)
    {
       DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
       return df.format(date);
    }
    
    public static void formatDatePicker(JXDatePicker jXDatePicker)
    {
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        jXDatePicker.setFormats(formater);
    }

    public static Date getCurrentDate() {
        return new Date();
    }
}
