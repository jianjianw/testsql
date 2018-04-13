package com.nhsoft;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestData {
    public String getDateStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    Connection connection = null;
    @Before
    public void testBefore()throws Exception{
        String url = "jdbc:jtds:sqlserver://118.31.165.59:3433;sendStringParametersAsUnicode=false;DatabaseName=bi_demo";
        String username = "nh_alpha";
        String pssword = "uEvHlqyoMOTw1de5Y5Eu";
        connection = DriverManager.getConnection(url, username, pssword);
    }

    @Test
    public void test2() throws Exception{

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String shiftTableBizday = getDateStr(date);
        Random random = new Random();
        String systemBookCode = "12345";
        Integer branchNum;      //1-100

        double dailyMoney;          //营业额   5000-20000
        Integer dailyQty;         //客单量     100-500
        double dailyPrice;      //客单价      50-100
        double targetMoney;     //营业额目标  10000-15000
        Integer dailyCount;     //客单购买数   200-800

        branchNum = random.nextInt(100)+1;
        dailyMoney = random.nextDouble();
        dailyQty = random.nextInt(500)+100;
        dailyPrice = random.nextInt(100)+50;
        targetMoney = random.nextDouble();
        dailyCount  = random.nextInt(800)+200;

        String sql = "insert into branch_daily values('12345',branchNum,shiftTableBizday,date,dailyMoney,dailyQty,dailyPrice,targetMoney,dailyCount) ";
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(sql);
        System.out.println(i);
    }

    @Test
    public void test3(){
        Integer value2 = 8;
        Integer value3 = 9;
        System.out.println(9/8);
    }
    @Test
    public void test4(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("aaa","111");
        map.put("aaa","222");
        String aaa = map.get("aaa");
        System.out.println(aaa);
    }

    @Test
    public void test5(){
        String str = ",";
        String[] split = str.split(",");
        for (int i = 0; i <split.length ; i++) {
            String s = split[i];
            System.out.println(s);
        }

    }



    @Test
    public void test6(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   //设置日期格式
        Calendar calendar = Calendar.getInstance();
        Date day = calendar.getTime();
        calendar.setTime(day);

        Integer thisYear = calendar.get(Calendar.YEAR);             //本年
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        Integer thisWeek = calendar.get(Calendar.WEEK_OF_YEAR);     //本周

        Integer thisMonth = Integer.valueOf(sdf.format(calendar.getTime()).substring(5, 7)); //为了匹配微软的周 运算结果
        if(thisWeek == 1 && thisMonth > 11) {
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            thisWeek = calendar.get(Calendar.WEEK_OF_YEAR) + 1;
            calendar.add(Calendar.DAY_OF_MONTH, 7);
        }

        List<Bizday> list = new ArrayList<Bizday>();
        for(int i = 0; i < 735; i++) {
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            String date = sdf.format(calendar.getTime());                  //bizday_date
            String year = sdf.format(calendar.getTime()).substring(0, 4);  //bizday_year
            Integer quarter = (Integer.valueOf(sdf.format(calendar.getTime()).substring(5, 7))+2)/3;//bizday_quater
            String yearAndMonth = sdf.format(calendar.getTime()).substring(0, 4)+sdf.format(calendar.getTime()).substring(5, 7);//bizday_year_month
            Integer month = Integer.valueOf(sdf.format(calendar.getTime()).substring(5, 7));        //bizday_month
            Integer dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);    //bizday_dayofyear
            Integer weeknumOfYear = calendar.get(Calendar.WEEK_OF_YEAR); //bizday_week_of_year
            if(weeknumOfYear == 1 && month > 11) {      //为了匹配微软的周 运算结果
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                weeknumOfYear = calendar.get(Calendar.WEEK_OF_YEAR) + 1;
                calendar.add(Calendar.DAY_OF_MONTH, 7);
            }
            String yearAndWeek = null; //bizday_year_week
            if(weeknumOfYear < 10) {
                yearAndWeek = year + "0" + weeknumOfYear;
            } else {
                yearAndWeek = year + weeknumOfYear;
            }
            Integer weekDay = null;
            if(calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                weekDay = 7;
            } else {
                weekDay = calendar.get(Calendar.DAY_OF_WEEK)-1;
            }
            String yearName = "Y" + year;       //bizday_year_name
            String quarterName = "Q" + quarter; //bizday_quarter_name
            String monthName = null;            //bizday_month_name
            switch(month) {
                case 1: monthName = "一月";
                    break;
                case 2: monthName = "二月";
                    break;
                case 3: monthName = "三月";
                    break;
                case 4: monthName = "四月";
                    break;
                case 5: monthName = "五月";
                    break;
                case 6: monthName = "六月";
                    break;
                case 7: monthName = "七月";
                    break;
                case 8: monthName = "八月";
                    break;
                case 9: monthName = "九月";
                    break;
                case 10: monthName = "十月";
                    break;
                case 11: monthName = "十一月";
                    break;
                case 12: monthName = "十二月";
                    break;
            }
            String weekOfYearName = "W" + weeknumOfYear; //bizday_weekOfYear_name
            String dayOfMonthName = "D" + dayOfMonth;    //bizday_day_name
            String dayOfWeekName = null;                 //bizday_dayofweek_name
            switch(weekDay) {
                case 1: dayOfWeekName = "周一";
                    break;
                case 2: dayOfWeekName = "周二";
                    break;
                case 3: dayOfWeekName = "周三";
                    break;
                case 4: dayOfWeekName = "周四";
                    break;
                case 5: dayOfWeekName = "周五";
                    break;
                case 6: dayOfWeekName = "周六";
                    break;
                case 7: dayOfWeekName = "周日";
                    break;
            }
            String yearmonth = year+"年"+month+"月";              //bizday_yearandmonth_name
            String yearweek = year+"年"+" 第"+weeknumOfYear+"周"; //bizday_yearandweek_name
            Integer isThisWeek = null;                          //bizday_isthisweek
            if(Integer.valueOf(year).equals(thisYear) && weeknumOfYear == thisWeek) {
                isThisWeek = 1;
            } else {
                isThisWeek = 0;
            }
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Bizday bizday = new Bizday();
            bizday.setBizdayDate(date);
            bizday.setBizdayYear(year);
            bizday.setBizdayQuarter(quarter);

            bizday.setBizdayYearMonth(yearAndMonth);
            bizday.setBizdayMonth(month);
            bizday.setBizdayDayofyear(dayOfMonth);

            bizday.setBizdayYearWeek(yearAndWeek);
            bizday.setBizdayWeekofyear(weeknumOfYear);
            bizday.setBizdayDayofweek(weekDay);

            bizday.setBizdayYearName(yearName);
            bizday.setBizdayQuarterName(quarterName);
            bizday.setBizdayMonthName(monthName);

            bizday.setBizdayWeekofyearName(weekOfYearName);
            bizday.setBizdayDayName(dayOfMonthName);
            bizday.setBizdayDayofweekName(dayOfWeekName);

            bizday.setBizdayYearandmonthName(yearmonth);
            bizday.setBizdayYearandweekName(yearweek);
            bizday.setBizdayIsthisweek(isThisWeek);
            list.add(bizday);
        }
        System.out.println();
    }
}
