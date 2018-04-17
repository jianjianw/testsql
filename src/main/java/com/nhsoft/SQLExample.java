package com.nhsoft;

import com.google.gson.Gson;
import com.nhsoft.test.*;
import com.sun.istack.internal.localization.NullLocalizable;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SQLExample {

        Connection connection = null;

        //@Before
        public void testBefore()throws Exception{
                //String url = "jdbc:sqlserver://bi.database.chinacloudapi.cn:1433;DatabaseName=AMAZON_4344;";
                String url = "jdbc:jtds:sqlserver://bi.database.chinacloudapi.cn:1433;DatabaseName=AMAZON_4344";
                String username = "nhsoft@bi";
                String pssword = "zVMGdXi90mov44dQ";
                connection = DriverManager.getConnection(url, username, pssword);
        }

        @Test
        public void test1() throws Exception{
                String sql = "select * from sale";
                String url = "jdbc:jtds:sqlserver://bi.database.chinacloudapi.cn:1433;DatabaseName=AMAZON_4344";
                String username = "nhsoft@bi";
                String pssword = "zVMGdXi90mov44dQ";
                Connection connection = DriverManager.getConnection(url, username, pssword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                        int branchNum=resultSet.getInt("branch_num");
                        String branchName=resultSet.getString("branch_name");
                        int pro = resultSet.getInt("pro");

                        System.out.println(branchNum+" : "+branchName+" : "+ pro );
                }

               /* while (resultSet.next()){
                        int anInt = resultSet.getInt(1);
                        System.out.println(anInt);
                        //System.out.println(branchNum+" : "+branchName);
               }
*/
              /* while (resultSet.next()){
                       int pro = resultSet.getInt(1);
                       System.out.println(pro);
               }*/
        }

        @Test
        public void test2() throws Exception{
                //INSERT INTO 表名称 VALUES (值1, 值2,....)
                String sql = "insert into sale values('98','小店','123','123','123','目标',100) ";
                Statement statement = connection.createStatement();
                int i = statement.executeUpdate(sql);
                System.out.println(i);
        }

        @Test
        public void test3() throws Exception{
                String sql = "update sale set PRO = 100 where branch_num = '98'";
                Statement statement = connection.createStatement();
                int i = statement.executeUpdate(sql);
                System.out.println(i);

        }

        @Test
        public void test4()throws Exception{
                String sql  = "ALTER TABLE sale ADD pro int";
                Statement statement = connection.createStatement();
                int i = statement.executeUpdate(sql);
                System.out.println(i);
                /*
                * ALTER TABLE Persons ADD Birthday date
                * */
        }

        @Test
        public void test5() throws Exception{
                String sql = "select sum(case when branch_name = '小店' then (pro*5) else pro end) from sale";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                        int anInt = resultSet.getInt(1);
                        System.out.println(anInt);
                }
        }


        @Test
        public void test6() throws Exception{
                //INSERT INTO 表名称 VALUES (值1, 值2,....)
                String sql1 = "insert into sale values('100','小店0','123','123','123','目标',100) ";
                String sql2 = "insert into sale values('101','小店1','123','123','123','目标',100) ";
                String sql3 = "insert into sale values('102','小店2','123','123','123','目标',100) ";
                String sql4 = "insert into sale values('103','小店3','123','123','123','目标',100) ";
                String sql5 = "insert into sale values('104','小店4','123','123','123','目标',100) ";
                String sql6 = "insert into sale values('105','小店5','123','123','123','目标',100) ";
                Statement statement = connection.createStatement();
                statement.addBatch(sql1);
                statement.addBatch(sql2);
                statement.addBatch(sql3);
                statement.addBatch(sql4);
                statement.addBatch(sql5);
                statement.addBatch(sql6);
                int[] ints = statement.executeBatch();
                System.out.println();


        }

        @Test
        public void test7(){
//                String date  = "2017-10-10";
//                String replace = date.replace("-", "");
//                System.out.println(replace);

                List<Integer> list  = new ArrayList<Integer>(0);
                int size = list.size();
                System.out.println(size);
        }


        @Test
        public void test8(){
                String s1 = "0100";
                String s2 = "1234";
                Integer integer = Integer.valueOf(s1);
                Integer integer1 = Integer.valueOf(s2);
                System.out.println(integer);
                System.out.println(integer1);
               /* if(integer<=){

                }*/
        }

        @Test
        public void test9(){
                Integer integer = 9999;
                String str = "8888";
                StringBuilder sb = new StringBuilder();
                sb.append(integer).append(str);
                System.out.println(sb.toString());
        }

        @Test
        public void test10(){

                BigDecimal[] arr = new BigDecimal[2];
                arr[1] = new BigDecimal(1);
                arr[1] = arr[1].add(new BigDecimal(2));
                String s = Arrays.toString(arr);
                System.out.println(s);
        }

        @Test
        public void test11(){
                deleteHistoryData("branch_daily",new Date(),new Date());
                deleteCurrentData("branch_daily");
        }

        public void deleteHistoryData(String tableName, Date dateFrom, Date dateTo){
                String date = formatDate(dateFrom);
                String sql = "delete from "+ tableName +" shift_table_bizday < '" + date + "' ";
                System.out.println(sql);

        }

        public void deleteCurrentData(String tableName){
                Calendar calendar = Calendar.getInstance();
                Date time = calendar.getTime();
                String date = formatDate(time);
                String sql = "delete from "+ tableName +" shift_table_bizday = '" + date + "' ";
                System.out.println(sql);
        }

        public String formatDate(Date date){
                if(date != null){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String format = sdf.format(date);
                        return format;
                }else{
                        return null;
                }

        }



        @Test
        public void test12(){
                List<String> list = new ArrayList<String>();
                list.add("a");
                list.add("b");
                list.add("c");

                System.out.println(list.get(0));
                System.out.println(list.get(1));
                System.out.println(list.get(2));

        }

        @Test
        public void test(){
                Date time = Calendar.getInstance().getTime();

                StringBuilder sb  =  new StringBuilder();
                sb.append("delete from branch_daily ");
                sb.append("where shift_table_bizday >= '" + formatDate(time) + "' ");
                sb.append("and shift_table_bizday <= '" + formatDate(time) + "' ");
                System.out.println(sb.toString());
        }

        @Test
        public void test13(){
                Calendar calendar = Calendar.getInstance();
                Date currentDay = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH,-1);
                Date yesterday = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH,-1);
                Date beforeYesterday = calendar.getTime();
                System.out.println(currentDay);
                System.out.println(yesterday);
                System.out.println(beforeYesterday);
        }

        @Test
        public void test14(){
                List<Integer> list = new ArrayList<Integer>();

                List<Integer> list1 = new ArrayList<Integer>();
                list1.add(1);
                List<Integer> list2 = new ArrayList<Integer>();
                list2.add(2);
                List<Integer> list3 = new ArrayList<Integer>();
                list3.add(3);

                list.addAll(list1);
                list.addAll(list2);
                list.addAll(list3);

                for (int i = 0; i <list.size() ; i++) {
                        Integer integer = list.get(i);
                        System.out.println(integer);
                }
        }


        @Test
        public void test15(){
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH,-3);
                Date time = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String format = sdf.format(time);
                String substring = format.substring(0, 6);
                StringBuilder sb = new StringBuilder();
                String s = sb.append(substring).append("01").toString();
                System.out.println(s);

        }

        @Test
        public void test16(){
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH,-3);
                Date time = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd");
                String format = sdf.format(time);
                System.out.println(format);
                calendar.add(Calendar.DAY_OF_MONTH,-Integer.valueOf(format)+1);
                Date time1 = calendar.getTime();
                System.out.println(time1.toString());
                System.out.println();

        }

      /*  @Test
        public void test17() throws Exception{
                CloseableHttpClient client = HttpClients.createDefault();
                //http://101.37.17.16
                String s = "http://101.37.17.16/amazonReport/azure/init/cardDaily/4410/";
                Integer var = 20170501;
               for (int i = 0; i < 31; i++) {
                        Thread.sleep(5000);
                        int date = var + i;
                        String url = s + date + "/" + date;
                         HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1 + "_" + result);
                }
        }


        @Test
        public void test17_1() throws Exception{
                CloseableHttpClient client = HttpClients.createDefault();
                //http://101.37.17.16
                String s = "http://101.37.17.16/amazonReport/azure/init/cardDaily/4410/";
                Integer var = 20170201;
                for (int i = 0; i < 28; i++) {
                        Thread.sleep(5000);
                        int date = var + i;
                        String url = s + date + "/" + date;
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1 + "_" + result);
                }
        }

        @Test
        public void test17_2() throws Exception{
                CloseableHttpClient client = HttpClients.createDefault();
                //http://101.37.17.16
                String s = "http://101.37.17.16/amazonReport/azure/init/cardDaily/4410/";
                Integer var = 20170101;
                for (int i = 0; i < 31; i++) {
                        Thread.sleep(5000);
                        int date = var + i;
                        String url = s + date + "/" + date;
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1 + "_" + result);
                }
        }*/

        @Test
        public void testSaleDaily() throws Exception{
                CloseableHttpClient client = HttpClients.createDefault();
                //http://101.37.17.16
                String s = "http://101.37.17.16/amazonReport/azure/init/saleDaily/4410/";
                Integer var = 20170501;
                for (int i = 0; i < 31; i++) {
                        Thread.sleep(5000);
                        int date = var + i;
                        String url = s + date + "/" + date;
                        System.out.println(url);
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1 + "_" + result+"------saleDaily");
                }
        }

        @Test
        public void testSaleDaily_04() throws Exception{
                CloseableHttpClient client = HttpClients.createDefault();
                //http://101.37.17.16
                String s = "http://101.37.17.16/amazonReport/azure/init/saleDaily/4410/";
                Integer var = 20170401;
                for (int i = 0; i < 30; i++) {
                        Thread.sleep(5000);
                        int date = var + i;
                        String url = s + date + "/" + date;
                        System.out.println(url);
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1 + "_" + result+"------saleDaily");
                }
        }

        @Test
        public void testSaleDaily_03() throws Exception{
                CloseableHttpClient client = HttpClients.createDefault();
                //http://101.37.17.16
                String s = "http://101.37.17.16/amazonReport/azure/init/saleDaily/4410/";
                Integer var = 20170301;
                for (int i = 0; i < 31; i++) {
                        Thread.sleep(5000);
                        int date = var + i;
                        String url = s + date + "/" + date;
                        System.out.println(url);
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1 + "_" + result+"------saleDaily");
                }
        }

        @Test
        public void testSaleDaily_02() throws Exception{
                CloseableHttpClient client = HttpClients.createDefault();
                //http://101.37.17.16
                String s = "http://101.37.17.16/amazonReport/azure/init/saleDaily/4410/";
                Integer var = 20170201;
                for (int i = 0; i < 28; i++) {
                        Thread.sleep(5000);
                        int date = var + i;
                        String url = s + date + "/" + date;
                        System.out.println(url);
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1 + "_" + result+"------saleDaily");
                }
        }

        @Test
        public void testSaleDaily_01() throws Exception{
                CloseableHttpClient client = HttpClients.createDefault();
                //http://101.37.17.16
                String s = "http://101.37.17.16/amazonReport/azure/init/saleDaily/4410/";
                Integer var = 20170101;
                for (int i = 0; i < 31; i++) {
                        Thread.sleep(5000);
                        int date = var + i;
                        String url = s + date + "/" + date;
                        System.out.println(url);
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = client.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1 + "_" + result+"------saleDaily");
                }
        }


//        @Test
//        public void test18(){
//                Integer var = 20170801;
//                String s = "http://47.96.250.40/amazonReport/azure/init/cardDaily/4410/";
//                for (int i = 0; i < 31; i++) {
//                        int date = var + i;
//                        String url = s + date + "/" + date;
//                        System.out.println(url);
//                }
//        }

        @Test
        public void test19(){
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH,-3);
                Date time = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd");
                String format = sdf.format(time);
                calendar.add(Calendar.DAY_OF_MONTH,-Integer.valueOf(format)+1);
                Date date = calendar.getTime();
                System.out.println(date.toString());
                //azureService.batchDeleteCardDailies(systemBook,date,date);
        }

        @Test
        public void test20(){
                String[] split = UUID.randomUUID().toString().split("-");
                String s = split[0];
                System.out.println(s);
        }


        @Test
        public void testFor1(){

                List<Integer> list  = new ArrayList<Integer>();
                for (int i = 0; i <1000000 ; i++) {
                        list.add(i);
                }
                long begin = System.nanoTime();
                for (int i = 0; i <list.size() ; i++) {
                        
                }
                long end = System.nanoTime();

                System.out.println(end-begin);//2317391

        }

        @Test
        public void testFor2(){

                List<Integer> list  = new ArrayList<Integer>();
                for (int i = 0; i <1000000 ; i++) {
                        list.add(i);
                }
                long begin = System.nanoTime();
                int size = list.size();
                for (int i = 0; i <size ; i++) {

                }
                long end = System.nanoTime();
                System.out.println(end-begin);  //297644
        }
        @Test
        public void test22() {
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
                                case 1: dayOfWeekName = "星期一";
                                        break;
                                case 2: dayOfWeekName = "星期二";
                                        break;
                                case 3: dayOfWeekName = "星期三";
                                        break;
                                case 4: dayOfWeekName = "星期四";
                                        break;
                                case 5: dayOfWeekName = "星期五";
                                        break;
                                case 6: dayOfWeekName = "星期六";
                                        break;
                                case 7: dayOfWeekName = "星期日";
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


        @Test
        public void test23() throws Exception{
                CloseableHttpClient httpClient = HttpClients.createDefault();
                String str = "http://localhost:55116/importData/itemSaleDaily/12345/";
                Integer var = 20171201;
                for (int i = 0; i <31 ; i++) {
                        int date = var + i;
                        String url  = str + date + "/" + date;
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = httpClient.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(i+1+"_____"+result);
                }

        }

        @Test
        public void test24() {

                String value2 = "qqq";
                String value3 = "aaa";
                long begin = System.nanoTime();
                StringBuilder stringBuilder;
                for (int i = 0; i < 10000000; i++) {
                        stringBuilder = new StringBuilder();
                        String string = stringBuilder.append(value2).append("ww").append(value3).toString();
                }
                long end = System.nanoTime();
                System.out.println(end - begin);//770774492
        }

        @Test
        public void test25() {
                String value2 = "qqq";
                String value3 = "aaa";
                long begin = System.nanoTime();
                for (int i = 0; i < 10000000; i++) {
                        String str = value2 + "ww" + value3;
                }
                long end = System.nanoTime();
                System.out.println(end - begin);//1352540531
        }

        @Test
        public void test26(){
                List<Integer> list = new ArrayList<Integer>();

                for (int i = 0,len = list.size(); i < len ; i++) {

                }

                for (int i = 0,len = list.size(); i < len ; i++) {

                }
        }

        @Test
        public void test27(){
                Double dou = 132.12;
                int value2 = 12;
                Object[] objects = {dou,value2};
                for (int i = 0; i <objects.length ; i++) {
                        if(objects[i] instanceof Double){
                                System.out.println("double ...");
                        }
                        Object object = objects[i];
                        System.out.println(object);

                }
        }

        @Test
        public void test28(){
                List<Integer> list =  new ArrayList<Integer>();
                list.add(1);
                list.add(2);
                for (int i = 0,len = list.size();i <len ; i++) {
                        System.out.println(list.get(i));
                }


        }

        @Test
        public void test29(){
                Collection<String> result = new TreeSet();
                result.addAll(null);
        }

        @Test
        public void test30(){
                String str = "strr";
                int i = str.hashCode();
                System.out.println(i);

        }
        @Test
        public void test31(){
                Class<Bizday> bizdayClass = Bizday.class;
                String name1 = bizdayClass.getSimpleName();
                System.out.println(name1);
                Field[] declaredFields = bizdayClass.getDeclaredFields();
                for (Field s : declaredFields) {
                        //s.setAccessible(true);
                        ReportKey annotation = s.getAnnotation(ReportKey.class);
                        if(annotation != null){
                                System.out.println(annotation);
                        }
                        String name = s.getName();
                        System.out.println(name);
                }


                /**
                 * String simpleName = cls.getSimpleName();
                 if(simpleName.equals("OrderDetailCompare")){
                 for(Field field : fields) {
                 field.setAccessible(true);
                 String name = field.getName();
                 if(name.equals("branchNum")){
                 keyFields.add(field);
                 continue;
                 }
                 if(name.equals("itemNum")){
                 keyFields.add(field);
                 continue;
                 }
                 if(name.equals("itemMatrixNum")){
                 keyFields.add(field);
                 continue;
                 }
                 dataFields.add(field);
                 }
                 }
                 *
                 * */

        }
        @Test
        public void test32(){
                int i = 0;
                i++;
                int value = i + 1;
                System.out.println(value);

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH,3);
                Date time = calendar.getTime();
                System.out.println(time);
        }


        @Test
        public void test33(){

                String value2 = "10";
                String value3 = "01";
                int i = value3.compareTo(value2);
                System.out.println(i);

        }

        @Test
        public void test34() throws Exception{
                CloseableHttpClient httpClient = HttpClients.createDefault();
                String str = "http://101.37.17.16/biChaoQi/azure/init/cardDaily/6586/";
                Integer value2 = 20180101;
                for (int i = 0; i < 31 ; i++) {
                        int temp = value2 + i;
                        StringBuilder sb = new StringBuilder();
                        String url = sb.append(str).append(temp).append("/").append(temp).toString();
                        System.out.println(url);
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = httpClient.execute(httpGet);
                        String result = EntityUtils.toString(response.getEntity());
                        System.out.println(result);
                }

        }

        @Test
        public void test35(){
                Branch branch = new Branch();
                branch.setBranchName("中心店");
                Gson gson = new Gson();
                String s = gson.toJson(branch);

                System.out.println(s);
        }


        @Test
        public void test36() throws Exception{

                /*Stream<Path> list = Files.list(Paths.get("c:"));
                 list.map(String::valueOf).filter(path -> path.startsWith("a")).collect(Collectors.toList()).forEach(System.out::println);


                try (Stream<String> stream = Files.lines(Paths.get("res/nashorn1.js"))) {
                        stream
                                .filter(line -> line.contains("print"))
                                .map(String::trim)
                                .forEach(System.out::println);
                }*/


                List<String> collect = Files.lines(Paths.get("c:/mylog.log")).collect(Collectors.toList());
                for (int i = 0; i <collect.size() ; i++) {
                        String s = collect.get(i);
                        System.out.println(s);
                }

        }

        @Test
        public void test37(){
               String qqq = "123";
                String substring1 = qqq.substring(0, 1);
                String substring = qqq.substring(1);
                System.out.println(substring1);

        }

        @Test
        public void test38(){
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR,-1);

                //1800000
                Date time = calendar.getTime();
                System.out.println(time);
                long l = System.currentTimeMillis();
                long ennd = l - 1800000;
                calendar.setTimeInMillis(ennd);
                Date time1 = calendar.getTime();
                System.out.println(time1);

        }

        @Test
        public void test39(){

               /* List<Integer> ranges = new ArrayList<>();
                Integer range = 0;
                for (int i = 0; i <10 ; i++) {
                        ranges.add(range);
                        range+=100;
                }
                System.out.println();*/

               /**
                (case when p.payment_money >= 0 AND p.payment_money < 100 THEN '0'
                when p.payment_money >= 100 AND p.payment_money < 200 THEN '100'
                when p.payment_money >= 200 AND p.payment_money < 300 THEN '200'
                when p.payment_money >= 300 AND p.payment_money < 400 THEN '300'
                when p.payment_money >= 400 AND p.payment_money < 500 THEN '400'
                when p.payment_money >= 500 AND p.payment_money < 600 THEN '500'
                when p.payment_money >= 600 AND p.payment_money < 700 THEN '600'
                when p.payment_money >= 700 AND p.payment_money < 800 THEN '700'
                when p.payment_money >= 800 AND p.payment_money < 900 THEN '800'
                when p.payment_money >= 900 AND p.payment_money <= 1000 THEN '900'

                else '1000' END) as range ");
                *
                * */
                int range = 100;
                int size = 1000/range+1;


                int money = 0;
                StringBuilder sb = new StringBuilder();
                sb.append("(case when ");
                for (int i = 0; i < size; i++) {
                        if (i == 0) {
                                money = 0;
                        } else {
                                money += range;
                        }

                        if(i == size-1){
                                sb.append("else '1000' END) as range ");
                        }else{
                                sb.append("p.payment_money > ").append(money).append(" AND p.payment_money <= ").append(money + range).append(" then '").append(money).append("' ");
                        }

                }

                System.out.println(sb.toString());

        }

        @Test
        public void test40() {

                BigDecimal range = BigDecimal.valueOf(100);
                int size = BigDecimal.valueOf(1000).divide(range, 0, BigDecimal.ROUND_HALF_UP).intValue() + 1;
                BigDecimal money = BigDecimal.ZERO;
                StringBuilder sb = new StringBuilder();
                sb.append("(case ");
                for (int i = 0; i < size; i++) {
                        if (i == 0) {
                                money = BigDecimal.ZERO;
                        } else {
                                money = money.add(range);
                        }

                        if (i == size - 1) {
                                sb.append("else '1000' END) as range ");
                        } else {
                                sb.append("when p.payment_money > ").append(money).append(" AND p.payment_money <= ").append(money.add(range)).append(" then '").append(money).append("' ");
                        }

                }
                System.out.println(sb.toString());


        }


        @Test
        public void test41(){

                Bizday biz = getBiz("111");
                Bizday biz1 = getBiz("111");
                boolean b = biz == biz1;
                System.out.println(b);

                System.out.println(biz);
                System.out.println(biz1);


        }


        public Bizday getBiz(String string){
                Bizday bizday = new Bizday();
                bizday.setBizdayDate(string);
                return bizday;
        }

        @Test
        public void test42() throws Exception{


                SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
                Date dateFrom = sdf.parse("20180301");
                Date dateTo = sdf.parse("20180331");
                Calendar calendar = Calendar.getInstance();

                int diffDay = diffDay(dateFrom, dateTo);
                System.out.println(diffDay);
                for (int i = 0; i < diffDay; i++) {
                        calendar.setTime(dateFrom);
                        calendar.add(Calendar.DAY_OF_MONTH,i);
                        Date time = calendar.getTime();
                        String dateShortStr = getDateShortStr(time);
                        System.out.println(dateShortStr);

                }
        }

        public static String getDateShortStr(Date argument) {
                if(argument == null){
                        return null;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                return sdf.format(argument);
        }

        public static int diffDay(final Date startTime, final Date endTime) {
                float startTimeVal = startTime.getTime();
                float endTimeVal = endTime.getTime();

                float days = (endTimeVal - startTimeVal) / (1000 * 60 * 60 * 24);
                int intValue = new Float(days).intValue();
                //超过一天按一天计算
                if(days > intValue){
                        return intValue + 1;
                }
                return intValue;
        }

        @Test
        public void test43(){
                BranchProfitData data01 = new BranchProfitData();
                data01.setBranchNum(1);
                data01.setCostMoney(BigDecimal.valueOf(2));
                data01.setLossMoney(BigDecimal.valueOf(3));
                data01.setPosItemCode("0001");

                BranchProfitData data02 = new BranchProfitData();
                data02.setBranchNum(2);
                data02.setCostMoney(BigDecimal.valueOf(4));
                data02.setLossMoney(BigDecimal.valueOf(5));
                data02.setPosItemCode("0003");

                BranchProfitData data03 = new BranchProfitData();
                data03.setBranchNum(3);
                data03.setCostMoney(BigDecimal.valueOf(6));
                data03.setLossMoney(BigDecimal.valueOf(7));
                data03.setPosItemCode("0003");

                BranchProfitData data04 = new BranchProfitData();
                data04.setBranchNum(3);
                data04.setCostMoney(BigDecimal.valueOf(8));
                data04.setLossMoney(BigDecimal.valueOf(9));
                data04.setPosItemCode("0004");

                List<BranchProfitData> list = new ArrayList<>();
                list.add(data01);
                list.add(data02);
                list.add(data03);
                list.add(data04);

                MyComparator<BranchProfitData> comparator = new MyComparator<>("posItemCode","desc");
                Collections.sort(list,comparator);


                for (BranchProfitData data : list) {
                        System.out.println(data.getBranchNum());
                        System.out.println(data.getCostMoney());
                        System.out.println(data.getLossMoney());
                        System.out.println(data.getPosItemCode());
                        System.out.println("---------------");
                }



        }

        @Test
        public void test44() throws Exception{
                /*String branchNum = toUpperCaseFirstOne("branchNum");
                System.out.println(branchNum);*/


                Class<BranchProfitData> branchProfitDataClass = BranchProfitData.class;
                Method getItemNum = branchProfitDataClass.getMethod("getBranch");
                Type type = getItemNum.getGenericReturnType();
                String end = type.toString();
                String substring = end.substring(type.toString().lastIndexOf(".") + 1, end.length());
                System.out.println(substring);

        }

        //首字母转大写
        public static String toUpperCaseFirstOne(String str){
        if(Character.isUpperCase(str.charAt(0))){
                return str;
        }else{
                return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
        }

}
