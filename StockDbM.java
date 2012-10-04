
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class StockDbM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

    	String[] strarr=new String[1000];
    	int intnum=0;
    	strarr = readfile("zzz.txt");
    	System.out.println(intnum);
    	
    	
    	int i=0;
    	
    	while(strarr[i] != null){
    	
    //	String url = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=9501.T";
    //	String url = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=6954.T";
    	String url = "http://stocks.finance.yahoo.co.jp/stocks/detail/?code=" + strarr[i] + ".T";

    	
    	String arg0=strarr[i];
    	String arg1="";
    	String arg2="";
    	String arg3= checkTime();

    	
    	try{
    	
        Document doc = Jsoup.connect(url).get();
        Elements stoksPrices=doc.select("td[class=stoksPrice]");
        Elements reals=doc.select("dd[class=yjSb real]");


        for (Element stoksPrice : stoksPrices){
            System.out.println(stoksPrice.text());
            arg2 =stoksPrice.text();
            System.out.println(arg2.replace(",",""));
            arg2=(arg2.replace(",",""));
  
        }   
        
        
        
        for (Element real : reals){
           //	if (stoksPrice.getClass()=="stoksPrices")
            System.out.println(real.child(0).text());
            arg1 = real.child(0).text();
           // System.out.println(real.text());
           
        }

    	}catch(IOException e){
            System.out.println(e);
          }

        
//        append(arg0,arg1,arg2,arg3 );


    	System.out.println("arg0 =" + arg0);
		StockDbC stockdbc = new StockDbC(arg0,arg1,arg2,arg3);
//		stockdbc = null;

        
        i=i+1;
    	}
        System.out.println("終了しました。");
		
		
		
				
	}

	
    private static String  checkTime(){
        
    String modori;
    Calendar cal1 = Calendar.getInstance();  //(1)オブジェクトの生成

    int year = cal1.get(Calendar.YEAR);        //(2)現在の年を取得
    int month = cal1.get(Calendar.MONTH) + 1;  //(3)現在の月を取得
    int day = cal1.get(Calendar.DATE);         //(4)現在の日を取得
    int hour = cal1.get(Calendar.HOUR_OF_DAY); //(5)現在の時を取得
    int minute = cal1.get(Calendar.MINUTE);    //(6)現在の分を取得
    int second = cal1.get(Calendar.SECOND);    //(7)現在の秒を取得

    StringBuffer dow = new StringBuffer();
    switch (cal1.get(Calendar.DAY_OF_WEEK)) {  //(8)現在の曜日を取得
      case Calendar.SUNDAY: dow.append("日曜日"); break;
      case Calendar.MONDAY: dow.append("月曜日"); break;
      case Calendar.TUESDAY: dow.append("火曜日"); break;
      case Calendar.WEDNESDAY: dow.append("水曜日"); break;
      case Calendar.THURSDAY: dow.append("木曜日"); break;
      case Calendar.FRIDAY: dow.append("金曜日"); break;
      case Calendar.SATURDAY: dow.append("土曜日"); break;
    }

    //(9)現在の年、月、日、曜日、時、分、秒を表示
    System.out.println(year + "/" + month + "/" + day + " " + dow
                       + " " + hour + ":" + minute + ":" + second);
    
 //   modori = year + "/" + month + "/" + day + " " + dow + " " + hour + ":" + minute + ":" + second;
    
//    modori =String.format("%1$04d/%2$02d/%3$02d%4$s%5$02d:%6$02d:%7$02d"
//    		,year,month,day,dow,hour,minute,second);
    modori =String.format("%1$04d/%2$02d/%3$02d"
    		,year,month,day);
    System.out.println(modori);
    return modori;
    
    
    }


    public static String[] readfile(String inputfilename ){
        
    	String strarray[] =new String[1000];
    	int i = 0;
    	
    	/*
        while(i< 1000){
        	strarray[i] =null;
        	i = i+1;
        	
        } 
    	*/
    	
    	
    	try{
          File file = new File(inputfilename);

          if (checkBeforeReadfile(file)){
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str= null;
            String strtmp[];
            i=0;
            while((str = br.readLine()) != null){
              System.out.println(str);
              strtmp=str.split(",",0);
              strarray[i]=strtmp[0];
              i = i +1;
            
            }

            br.close();
          }else{
            System.out.println(inputfilename + " (ファイルが見つからないか開けません)");
          }
        }catch(FileNotFoundException e){
          System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
          }

    
    return strarray;
    
    }



    private static boolean checkBeforeReadfile(File file){
        if (file.exists()){
          if (file.isFile() && file.canRead()){
            return true;
          }
        }

        return false;
      }
    
    
    
}
