package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class nextWorkingDay {
    public static String getNextWorkingDay (String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekString;
        switch (dayOfWeek) {
            case 1:
                calendar.add(Calendar.DATE, 1);
                date1 = calendar.getTime();
                String changedBookingDate = sdf.format(date1);
                date = changedBookingDate;
                dayOfWeekString = "Sunday";
                break;
            case 2:
                dayOfWeekString = "Monday";
                break;
            case 3:
                dayOfWeekString = "Tuesday";
                break;
            case 4:
                dayOfWeekString = "Wednesday";
                break;
            case 5:
                dayOfWeekString = "Thursday";
                break;
            case 6:
                dayOfWeekString = "Friday";
                break;
            case 7:
                dayOfWeekString = "Saturday";
                break;
            default:
                dayOfWeekString = "error";
        }

        return date;
    }
}
