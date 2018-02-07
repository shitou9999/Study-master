package chatqq.home.com.app2.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.TextPaint;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"NewApi", "DefaultLocale"})
public class Utils {

    public static String getDateStrFormLong(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dt = new Date(time);
        return sdf.format(dt);
    }

    public static Date getDate(String strDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        try {
            return sdf1.parse(sdf1.format(sdf2.parse(strDate)));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static String getNowDate() {
        Date nowDate = new Date();
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();

        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        String dateTime = year + "年" + strMonth + "月" + strDay + "日";
        return dateTime;
    }

    public static String getStrNowDate() {
        Date nowDate = new Date();
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();

        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        String dateTime = year + "-" + strMonth + "-" + strDay;
        return dateTime;
    }

    public static String getNowYearMonthDay() {
        Date nowDate = new Date();
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();

        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        String dateTime = year + strMonth + strDay;
        return dateTime;
    }

    public static String getFrontWeekYearMonthDay() {

        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, -7);
        Date nowDate = currentDate.getTime();

        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();

        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        String dateTime = year + strMonth + strDay;
        return dateTime;
    }

    public static String getCarTail(String weekDay) {
        if (weekDay.equals("星期一")) {
            return " 限行车辆尾号 1和9";
        }
        if (weekDay.equals("星期二")) {
            return " 限行车辆尾号 2和8";
        }
        if (weekDay.equals("星期三")) {
            return " 限行车辆尾号 3和7";
        }
        if (weekDay.equals("星期四")) {
            return " 限行车辆尾号 4和6";
        }
        if (weekDay.equals("星期五")) {
            return " 限行车辆尾号 0和5";
        }
        return "";
    }

    public static String getCarTailNum(String weekDay) {
        if (weekDay.equals("星期一")) {
            return "1|9";
        }
        if (weekDay.equals("星期二")) {
            return "2|8";
        }
        if (weekDay.equals("星期三")) {
            return "3|7";
        }
        if (weekDay.equals("星期四")) {
            return "4|6";
        }
        if (weekDay.equals("星期五")) {
            return "0|5";
        }
        return weekDay;
    }

    public static int getHour() {
        Date nowDate = new Date();
        return nowDate.getHours();
    }

    public static int getMin() {
        Date nowDate = new Date();
        return nowDate.getMinutes();
    }

    public static String getUpdataDate() {
        Date nowDate = new Date();
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();
        int seconds = nowDate.getSeconds();
        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        String strHours = String.valueOf(hours);
        String strMinutes = String.valueOf(minutes);
        String strSeconds = String.valueOf(seconds);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        if (hours < 10)
            strHours = "0" + strHours;
        if (minutes < 10)
            strMinutes = "0" + strMinutes;
        if (seconds < 10)
            strSeconds = "0" + strSeconds;
        String dateTime = year + "-" + strMonth + "-" + strDay + " " + strHours
                + ":" + strMinutes;
        return dateTime;
    }

    public static String getDate(Date nowDate) {
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();
        int seconds = nowDate.getSeconds();
        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        String strHours = String.valueOf(hours);
        String strMinutes = String.valueOf(minutes);
        String strSeconds = String.valueOf(seconds);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        if (hours < 10)
            strHours = "0" + strHours;
        if (minutes < 10)
            strMinutes = "0" + strMinutes;
        if (seconds < 10)
            strSeconds = "0" + strSeconds;
        String dateTime = year + "-" + strMonth + "-" + strDay + " " + strHours
                + ":" + strMinutes;
        return dateTime;
    }

    public static String formatDate(String year, String month, String day) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy MM dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        String datestr = year + " " + month + " " + day;
        try {
            return format2.format(format1.parse(datestr));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间
     */
    public static String getCurTimeString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    public static String getCurTimeDayString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    public static String getCurTimeDateString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    public static String getCurTimeUpImgString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    public static String getNowDateTime() {
        Date nowDate = new Date();
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();
        int seconds = nowDate.getSeconds();
        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        String strHours = String.valueOf(hours);
        String strMinutes = String.valueOf(minutes);
        String strSeconds = String.valueOf(seconds);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        if (hours < 10)
            strHours = "0" + strHours;
        if (minutes < 10)
            strMinutes = "0" + strMinutes;
        if (seconds < 10)
            strSeconds = "0" + strSeconds;
        String dateTime = year + "/" + strMonth + "/" + strDay + " " + strHours
                + ":" + strMinutes + ":" + strSeconds;
        return dateTime;
    }

    public static String getNowDateHourMin() {
        Date nowDate = new Date();
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();
        int seconds = nowDate.getSeconds();
        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        String strHours = String.valueOf(hours);
        String strMinutes = String.valueOf(minutes);
        String strSeconds = String.valueOf(seconds);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        if (hours < 10)
            strHours = "0" + strHours;
        if (minutes < 10)
            strMinutes = "0" + strMinutes;
        if (seconds < 10)
            strSeconds = "0" + strSeconds;
        String dateTime = strHours + ":" + strMinutes;
        return dateTime;
    }

    public static String getNowTime() {
        Date nowDate = new Date();
        // int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();
        int seconds = nowDate.getSeconds();
        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        String strHours = String.valueOf(hours);
        String strMinutes = String.valueOf(minutes);
        String strSeconds = String.valueOf(seconds);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        if (hours < 10)
            strHours = "0" + strHours;
        if (minutes < 10)
            strMinutes = "0" + strMinutes;
        if (seconds < 10)
            strSeconds = "0" + strSeconds;
        String dateTime = strHours + ":" + strMinutes;
        return dateTime;
    }

    public static String getNowTimeSec() {
        Date nowDate = new Date();
        // int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();
        int seconds = nowDate.getSeconds();
        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        String strHours = String.valueOf(hours);
        String strMinutes = String.valueOf(minutes);
        String strSeconds = String.valueOf(seconds);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        if (hours < 10)
            strHours = "0" + strHours;
        if (minutes < 10)
            strMinutes = "0" + strMinutes;
        if (seconds < 10)
            strSeconds = "0" + strSeconds;

        String dateTime = strHours + ":" + strMinutes + ":" + strSeconds;
        return dateTime;
    }

    public static String getDateStr(String str) {

        String ret = "";
        if (str != null) {
            if (str.length() >= 14) {
                ret += str.substring(0, 4);
                ret += "-";
                ret += str.substring(4, 6);
                ret += "-";
                ret += str.substring(6, 8);
                ret += " ";
                ret += str.substring(8, 10);
                ret += ":";
                ret += str.substring(10, 12);
                ret += ":";
                ret += str.substring(12, 14);
            } else if (str.length() >= 12) {
                ret += str.substring(0, 4);
                ret += "-";
                ret += str.substring(4, 6);
                ret += "-";
                ret += str.substring(6, 8);
                ret += " ";
                ret += str.substring(8, 10);
                ret += ":";
                ret += str.substring(10, 12);
            } else if (str.length() >= 8) {
                ret += str.substring(0, 4);
                ret += "-";
                ret += str.substring(4, 6);
                ret += "-";
                ret += str.substring(6, 8);
            } else if (str.length() >= 6) {
                ret += str.substring(0, 2);
                ret += ":";
                ret += str.substring(2, 4);
                ret += ":";
                ret += str.substring(4, 6);
            }
        }
        return ret;
    }

    public static String getYMDHMS(String str) {
        String ret = "";
        if (str != null) {
            if (str.length() >= 14) {
                ret += str.substring(0, 4);
                ret += "/";
                ret += str.substring(4, 6);
                ret += "/";
                ret += str.substring(6, 8);
                ret += "  ";
                ret += str.substring(8, 10);
                ret += ":";
                ret += str.substring(10, 12);
                ret += ":";
                ret += str.substring(12, 14);
            }
        }
        return ret;
    }

    public static String getHMS(String str) {
        String ret = "";
        if (str != null) {
            if (str.length() >= 14) {
                ret += str.substring(8, 10);
                ret += ":";
                ret += str.substring(10, 12);
                ret += ":";
                ret += str.substring(12, 14);
            }
        }
        return ret;
    }

    public static String getHM(String str) {
        String ret = "";
        if (str != null) {
            if (str.length() >= 4) {
                ret += str.substring(0, 2);
                ret += ":";
                ret += str.substring(2, 4);
            }
        }
        return ret;
    }

    public static String getChYearMonthDay(String str) {
        String ret = "";
        if (str != null) {
            if (str.length() >= 8) {
                ret += str.substring(0, 4);
                ret += "年";
                ret += str.substring(4, 6);
                ret += "月";
                ret += str.substring(6, 8);
                ret += "日";
            }
        }
        return ret;
    }

    public static String getYearMonthDay(String str) {
        String ret = "";
        if (str != null) {
            if (str.length() >= 8) {
                ret += str.substring(0, 4);
                ret += "/";
                ret += str.substring(4, 6);
                ret += "/";
                ret += str.substring(6, 8);
            }
        }
        return ret;
    }

    public static String getTimeStr(String str) {
        String ret = "";
        if (str != null) {
            if (str.length() >= 14) {
                ret += str.substring(8, 10);
                ret += ":";
                ret += str.substring(10, 12);
            }
        }
        return ret;
    }

    public static long getNowSec() {
        Date nowDate = new Date();
        return nowDate.getTime() / 1000;
    }

    public static long getNowMinSec() {
        Date nowDate = new Date();
        return nowDate.getTime();
    }

    public static long getSec(String data1, String data2) {
        if (data1 != null && data2 != null) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                return (Math.abs(sdf1.parse(data1).getTime()
                        - sdf2.parse(data2).getTime())) / 1000;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static long getSignedSec(String data1, String data2) {
        if (data1 != null && data2 != null) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                return (sdf1.parse(data1).getTime() - sdf2.parse(data2)
                        .getTime()) / 1000;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static String getNowDateStr() {
        Date nowDate = new Date();
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();
        int seconds = nowDate.getSeconds();
        String strYear = String.valueOf(year);
        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        String strHours = String.valueOf(hours);
        String strMinutes = String.valueOf(minutes);
        String strSeconds = String.valueOf(seconds);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        if (hours < 10)
            strHours = "0" + strHours;
        if (minutes < 10)
            strMinutes = "0" + strMinutes;
        if (seconds < 10)
            strSeconds = "0" + strSeconds;
        String dateTime = strYear + strMonth + strDay + strHours + strMinutes
                + strSeconds;
        return dateTime;
    }

    public static String getMonthDayStr() {
        Date nowDate = new Date();
        int year = nowDate.getYear() + 1900;
        int month = nowDate.getMonth() + 1;
        int day = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();
        int seconds = nowDate.getSeconds();
        String strYear = String.valueOf(year);
        String strMonth = String.valueOf(month);
        String strDay = String.valueOf(day);
        String strHours = String.valueOf(hours);
        String strMinutes = String.valueOf(minutes);
        String strSeconds = String.valueOf(seconds);
        if (month < 10)
            strMonth = "0" + strMonth;
        if (day < 10)
            strDay = "0" + strDay;
        if (hours < 10)
            strHours = "0" + strHours;
        if (minutes < 10)
            strMinutes = "0" + strMinutes;
        if (seconds < 10)
            strSeconds = "0" + strSeconds;
        String dateTime = strMonth + "月" + strDay + "日";
        return dateTime;
    }

    public static String getWeekOfDate() {
        Date nowDate = new Date();
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static int getWeek() {
        Date nowDate = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;// 0-星期日 1-6 对应星期一到星期六

    }

    public static String getChDataString(String srcDate) {
        if (srcDate != null) {
            if (srcDate.equals(getSubDate(getNowYearMonthDay(), 1))) {
                return "昨天";
            } else if (srcDate.equals(getSubDate(getNowYearMonthDay(), 2))) {
                return "前天";
            } else if (srcDate.equals(getSubDate(getNowYearMonthDay(), 0))) {
                return "今天";
            } else {
                return getChYearMonthDay(srcDate);
            }
        } else {
            return "";
        }
    }

    public static String getSubDateMinutes(String srcDate, int min) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMddHHmmss");
        String desDate = srcDate;
        Date calcdate;
        try {
            calcdate = dft.parse(srcDate);
            Calendar date = Calendar.getInstance();
            date.setTime(calcdate);
            date.set(Calendar.MINUTE, date.get(Calendar.MINUTE) - min);
            Date resultDate = dft.parse(dft.format(date.getTime()));

            int year = resultDate.getYear() + 1900;
            int month = resultDate.getMonth() + 1;
            int day = resultDate.getDate();
            int hours = resultDate.getHours();
            int minutes = resultDate.getMinutes();
            int seconds = resultDate.getSeconds();

            String strYear = String.valueOf(year);
            String strMonth = String.valueOf(month);
            String strDay = String.valueOf(day);
            String strHours = String.valueOf(hours);
            String strMinutes = String.valueOf(minutes);
            String strSeconds = String.valueOf(seconds);
            if (month < 10)
                strMonth = "0" + strMonth;
            if (day < 10)
                strDay = "0" + strDay;
            if (hours < 10)
                strHours = "0" + strHours;
            if (minutes < 10)
                strMinutes = "0" + strMinutes;
            if (seconds < 10)
                strSeconds = "0" + strSeconds;
            desDate = strYear + strMonth + strDay + strHours + strMinutes
                    + strSeconds;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return desDate;

    }

    public static String getSubDate(String srcDate, int daynum) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String desDate = srcDate;
        Date calcdate;
        try {
            calcdate = dft.parse(srcDate);
            Calendar date = Calendar.getInstance();
            date.setTime(calcdate);
            date.set(Calendar.DATE, date.get(Calendar.DATE) - daynum);
            Date resultDate = dft.parse(dft.format(date.getTime()));
            int year = resultDate.getYear() + 1900;
            int month = resultDate.getMonth() + 1;
            int day = resultDate.getDate();

            String strMonth = String.valueOf(month);
            String strDay = String.valueOf(day);
            if (month < 10)
                strMonth = "0" + strMonth;
            if (day < 10)
                strDay = "0" + strDay;
            desDate = year + strMonth + strDay;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return desDate;

    }

    public static String getHidePhone(String num) {
        if (num.length() >= 11) {
            return num.substring(0, 3) + "*****" + num.substring(8, 11);
        } else {
            return "";
        }
    }

    public static boolean isPhoneNum(String num) {
        Pattern pattern = Pattern.compile("1[34578][0-9]{9}");
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    public static boolean is6_32Number(String num) {
        Pattern pattern = Pattern.compile("[0-9]{6,32}$");
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    public static boolean is6_32NumOrChar(String items) {
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]{6,32}$");
        Matcher matcher = pattern.matcher(items);
        return matcher.matches();
    }

    public static boolean is4RandomCode(String items) {
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]{4}$");
        Matcher matcher = pattern.matcher(items);
        return matcher.matches();
    }

    public static boolean isNumOrChar(String items) {
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]{1,}$");
        Matcher matcher = pattern.matcher(items);
        return matcher.matches();
    }

    public static boolean isNum(String items) {
        Pattern pattern = Pattern.compile("[0-9]{1,}$");
        Matcher matcher = pattern.matcher(items);
        return matcher.matches();
    }

    public static boolean isNumIndex(String items) {
        Pattern pattern = Pattern.compile("([0-9]|[.]){1,}$");
        Matcher matcher = pattern.matcher(items);
        return matcher.matches();
    }

    public static void show(final Context context, final String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    public static int getFontHeight(TextView tv) {
        Paint paint = new Paint();
        paint.setTextSize(tv.getTextSize());
        FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    public static int getTextWidth(TextView tv, String txt) {
        TextPaint tpaint = tv.getPaint();
        return (int) Layout.getDesiredWidth(txt, 0, txt.length(), tpaint);
    }

    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        return true;
    }

    public static boolean isRecognisePlate(String plate) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5][\\u4e00-\\u9fa5][\\u4e00-\\u9fa5][0-9a-zA-Z]{6}$");
        Matcher matcher = pattern.matcher(plate);
        return matcher.matches();
    }

    public static boolean isPlate(String plate) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]{1}[a-zA-Z]{1}[0-9a-zA-Z]{5}$");
        Matcher matcher = pattern.matcher(plate);
        return matcher.matches();
    }

    public static String getRootFilePath() {
        if (hasSDCard()) {
            java.io.File qxDir = null;
            qxDir = new java.io.File(Environment.getExternalStorageDirectory(),
                    "spks");
            if (!qxDir.exists()) {
                qxDir.mkdirs();

            }
            return Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/spks";// filePath:/sdcard/
        } else {
            java.io.File qxDir = null;
            qxDir = new java.io.File(Environment.getDataDirectory()
                    .getAbsolutePath() + "/data/", "spks");
            if (!qxDir.exists()) {
                qxDir.mkdirs();

            }
            return Environment.getDataDirectory().getAbsolutePath()
                    + "/data/spks"; // filePath:
            // /data/data/
        }
    }

    public static String getResourceFileFullName() {
        return getResourceFilePath() + "/Resource.irf";
    }

    public static String getResourceFilePath() {
        java.io.File qxDir = null;
        qxDir = new java.io.File(getRootFilePath(), "TTS");
        if (!qxDir.exists()) {
            qxDir.mkdirs();
        }
        return getRootFilePath() + "/TTS";
    }

    public static String getImgFilePath() {
        java.io.File qxDir = null;
        qxDir = new java.io.File(getRootFilePath(), "Img");
        if (!qxDir.exists()) {
            qxDir.mkdirs();
        }
        return getRootFilePath() + "/Img";
    }

    public static String getUcPath() {
        java.io.File qxDir = null;
        qxDir = new java.io.File(getRootFilePath(), "UC");
        if (!qxDir.exists()) {
            qxDir.mkdirs();
        }
        return getRootFilePath() + "/UC";
    }

    public static String getDbFilePath() {
        java.io.File qxDir = null;
        qxDir = new java.io.File(getRootFilePath(), "db");
        if (!qxDir.exists()) {
            qxDir.mkdirs();
        }
        return getRootFilePath() + "/db";
    }

    public static int getAccuracyLevel(float fAccuracy) {
        int iac = 0;
        /*
         * 0-50 0 50-80 1 80-140 2 140-230 3 230-350 4 350-500 5 500-680 6
		 * 680-890 7 890-1130 8 1130- 9
		 */
        if (fAccuracy < 50) {
            iac = 0;
        } else if (fAccuracy < 80) {
            iac = 1;
        } else if (fAccuracy < 140) {
            iac = 2;
        } else if (fAccuracy < 230) {
            iac = 3;
        } else if (fAccuracy < 350) {
            iac = 4;
        } else if (fAccuracy < 500) {
            iac = 5;
        } else if (fAccuracy < 680) {
            iac = 6;
        } else if (fAccuracy < 890) {
            iac = 7;
        } else if (fAccuracy < 1130) {
            iac = 8;
        } else {
            iac = 9;
        }
        return iac;
    }


    public static void goNextActivityAndFinish(final Context context,
                                               final @SuppressWarnings("rawtypes") Class class1,
                                               final Bundle dataName) {
        if (null == dataName) {
            Intent intent = new Intent();
            intent.setClass(context, class1);
            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            ((Activity) context).finish();
        } else {
            Intent intent = new Intent();
            intent.setClass(context, class1);
            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtras(dataName);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
    }

    public static void goNextActivityAndFinishExit(final Context context,
                                                   final Class class1, final Bundle dataName) {
        if (null == dataName) {
            Intent intent = new Intent();
            intent.setClass(context, class1);
            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            ((Activity) context).finish();
            System.exit(0);
        } else {
            Intent intent = new Intent();
            intent.setClass(context, class1);
            // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtras(dataName);
            context.startActivity(intent);
            ((Activity) context).finish();
            System.exit(0);
        }
    }

    public static void goNextActivityForResult(final Context context,
                                               @SuppressWarnings("rawtypes") final Class class1,
                                               final int requestCode, final Bundle dataName) {
        if (null == dataName) {
            Intent intent = new Intent(context, class1);
            ((Activity) context).startActivityForResult(intent, requestCode);
        } else {
            Intent intent = new Intent(context, class1);
            intent.putExtras(dataName);
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }

    public static void goBackResult(final Context context,
                                    @SuppressWarnings("rawtypes") final Class class1,
                                    final int resultCode, final Bundle dataName) {
        if (null == dataName) {
            Intent intent = new Intent(context, class1);
            ((Activity) context).setResult(resultCode, intent);
        } else {
            Intent intent = new Intent(context, class1);
            intent.putExtras(dataName);
            ((Activity) context).setResult(resultCode, intent);
        }
    }

    public static void goBackResultAndFinsh(final Context context,
                                            @SuppressWarnings("rawtypes") final Class class1,
                                            final int resultCode, final Bundle dataName) {
        if (null == dataName) {
            Intent intent = new Intent(context, class1);
            ((Activity) context).setResult(resultCode, intent);
            ((Activity) context).finish();
        } else {
            Intent intent = new Intent(context, class1);
            intent.putExtras(dataName);
            ((Activity) context).setResult(resultCode, intent);
            ((Activity) context).finish();
        }
    }

    public static void goBackResultAndFinshExit(final Context context,
                                                @SuppressWarnings("rawtypes") final Class class1,
                                                final int resultCode, final Bundle dataName) {
        if (null == dataName) {
            Intent intent = new Intent(context, class1);
            ((Activity) context).setResult(resultCode, intent);
            ((Activity) context).finish();
            System.exit(0);
        } else {
            Intent intent = new Intent(context, class1);
            intent.putExtras(dataName);
            ((Activity) context).setResult(resultCode, intent);
            ((Activity) context).finish();
            System.exit(0);
        }
    }

    public static void goNextActivity(final Context context,
                                      final @SuppressWarnings("rawtypes") Class class1,
                                      final Bundle dataName) {
        if (null == dataName) {
            Intent intent = new Intent(context, class1);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, class1);
            intent.putExtras(dataName);
            context.startActivity(intent);
        }
    }

    public static void setUnderline(final TextView tv) {
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void setMidline(final TextView tv) {
        tv.getPaint().setFlags(
                Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    public static void showInPutPanle(final EditText et) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) et
                        .getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(et, 0);
            }
        }, 500);
    }

    public static Boolean getGpsStatus(Context context) {
        LocationManager lm = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        boolean GPS_status = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return GPS_status;
    }


    public static double distance(double lat1, double lng1, double lat2, double lng2) {
        double radlat1 = lat1 * 3.14 / 180;
        double radlat2 = lat2 * 3.14 / 180;
        double a = radlat1 - radlat2;
        double b = lng1 * 3.14 / 180 - lng2 * 3.14 / 180;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radlat1) * Math.cos(radlat2)
                * Math.pow(Math.sin(b / 2), 2)));
        double earth_radius = 6378.137;
        s = s * earth_radius;
        if (s < 0) {
            return -s * 1000;
        } else
            return s * 1000;
    }

    public static String getVersion(Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int[] convertYUV420_NV21toARGB8888(byte[] data, int width,
                                                     int height) {
        int size = width * height;
        int offset = size;
        int[] pixels = new int[size];
        int u, v, y1, y2, y3, y4;

        // i along Y and the final pixels
        // k along pixels U and V
        for (int i = 0, k = 0; i < size; i += 2, k += 2) {
            y1 = data[i] & 0xff;
            y2 = data[i + 1] & 0xff;
            y3 = data[width + i] & 0xff;
            y4 = data[width + i + 1] & 0xff;

            u = data[offset + k] & 0xff;
            v = data[offset + k + 1] & 0xff;
            u = u - 128;
            v = v - 128;

            pixels[i] = convertYUVtoARGB(y1, u, v);
            pixels[i + 1] = convertYUVtoARGB(y2, u, v);
            pixels[width + i] = convertYUVtoARGB(y3, u, v);
            pixels[width + i + 1] = convertYUVtoARGB(y4, u, v);

            if (i != 0 && (i + 2) % width == 0)
                i += width;
        }

        return pixels;
    }

    private static int convertYUVtoARGB(int y, int u, int v) {
        int r, g, b;

        r = y + (int) 1.402f * u;
        g = y - (int) (0.344f * v + 0.714f * u);
        b = y + (int) 1.772f * v;
        r = r > 255 ? 255 : r < 0 ? 0 : r;
        g = g > 255 ? 255 : g < 0 ? 0 : g;
        b = b > 255 ? 255 : b < 0 ? 0 : b;
        return 0xff000000 | (r << 16) | (g << 8) | b;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toLowerCase().trim();
    }

    public static String bytesToChars(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            stringBuilder.append((char) src[i]);
        }
        return stringBuilder.toString().toLowerCase().trim();
    }

    public static String bytesToString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = String.valueOf(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toLowerCase().trim();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static Bitmap getBMPFile(String filename) {
        String filePath = getImgFilePath() + "/" + filename;
        File f = new File(filePath);
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 100;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;

    }

    public static void setCursorDrawableColor(EditText editText, int color) {
        //设置Edittext光标颜色 from https://stackoverflow.com/questions/11554078/set-textcursordrawable-programmatically
        //API Rev 19(4.4.2)
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            Drawable[] drawables = new Drawable[2];
            drawables[0] = editText.getContext().getResources().getDrawable(mCursorDrawableRes);
            drawables[1] = editText.getContext().getResources().getDrawable(mCursorDrawableRes);
            drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (Throwable ignored) {
        }
    }


    /**
     * 将毫秒时间转换成时分秒
     * 有点问题
     *
     * @param totalTime
     * @return
     */
    public static String formatTimeDuring(long totalTime) {
        long hour = 0;
        long minute = 0;
        long second = 0;
        second = totalTime / 1000;//s
//        second = totalTime * 1000;//s
        if (totalTime <= 1000 && totalTime > 0) {
            second = 1;
        }
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        // 转换时分秒 00:00:00
        String duration = (hour >= 10 ? hour : "0" + hour) + "小时:" + (minute >= 10 ? minute : "0" + minute) + "分钟";
//		String duration = (hour >= 10 ? hour : "0" + hour) + "小时:" + (minute >= 10 ? minute : "0" + minute) + "分:" + (second >= 10 ? second : "0" + second);
        return duration;
    }

    /**
     * 整数(秒数)转换为时分秒格式(xx:xx:xx)
     *
     * @param times
     * @return
     */
    public static String secToTime(int times) {
        String timeStr = null;
        int time = times * 60;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00";
        } else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
//                if (hour > 99)
//                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }


    /**
     * 将秒数转换为日时分秒，
     *
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;            //转换天数
        second = second % 86400;            //剩余秒数
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second / 60;            //转换分钟
        second = second % 60;                //剩余秒数
        if (days > 0) {
            return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
        } else {
            return hours + "小时" + minutes + "分" + second + "秒";
        }
    }

    /*把毫秒转成时间hh:mi:ss.xxx*/

    public static String secToTime2(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int millisecond = 0;
        if (time <= 0)
            return "00:00:00.000";
        else {
            second = time / 1000;
            minute = second / 60;
            millisecond = time % 1000;
            if (second < 60) {
                timeStr = "00:00:" + unitFormat01(second) + "." + unitFormat2(millisecond);
            } else if (minute < 60) {
                second = second % 60;
                timeStr = "00:" + unitFormat01(minute) + ":" + unitFormat01(second) + "." + unitFormat2(millisecond);
            } else {//数字>=3600 000的时候
                hour = minute / 60;
                minute = minute % 60;
                second = second - hour * 3600 - minute * 60;
                timeStr = unitFormat01(hour) + ":" + unitFormat01(minute) + ":" + unitFormat01(second) + "." + unitFormat2(millisecond);
            }
        }
        return timeStr;
    }

    public static String unitFormat01(int i) {//时分秒的格式转换
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static String unitFormat2(int i) {//毫秒的格式转换
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "00" + Integer.toString(i);
        else if (i >= 10 && i < 100) {
            retStr = "0" + Integer.toString(i);
        } else
            retStr = "" + i;
        return retStr;
    }

}
