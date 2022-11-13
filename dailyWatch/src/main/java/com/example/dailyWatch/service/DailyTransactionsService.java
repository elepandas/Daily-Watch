package com.example.dailyWatch.service;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.dailyWatch.model.DailyTransaction;
import com.example.dailyWatch.repository.DailyTransactionsDTO;
import com.opencsv.CSVReader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Service
public class DailyTransactionsService {

	@Autowired
	public DailyTransactionsDTO dailyTranDTO;

	@Scheduled(cron = "0 0 22 * * *" , zone="Asia/Calcutta")
	public void saveDailyTransactionstest()  {
		LocalDateTime now = LocalDateTime.now(); 
		Date dateOfTransaction =  java.sql.Date.valueOf(now.toLocalDate());
		System.out.println("CRON SCHEDULED JOB RUNNING " + "RAMU BRO !!!!" + dateOfTransaction );
	}
	
	@SuppressWarnings("resource")
	@Scheduled(cron = "0 0 6 * * *" , zone="Asia/Calcutta")
	public void saveDailyTransactions() throws IOException {

					
		   try {
			   
			    //Desktop desk = Desktop.getDesktop();
		        
		        // now we enter our URL that we want to open in our
		        // default browser
		       // desk.browse(new URI("https://api.bseindia.com/BseIndiaAPI/api/Mkt_DownloadCSV/w?ordcol=TT&strType=AllMkt&strfilter=All"));
			  
			  String urlfile = "https://api.bseindia.com/BseIndiaAPI/api/Mkt_DownloadCSV/w?ordcol=TT&strType=AllMkt&strfilter=All";
		      
		      String url = "https://codedestine.com/text.html";
		      String file = "E:/Project/dailywatch/MarketWatchtoday.csv";
		 
		      BufferedInputStream bufferedIS = null;
		      FileOutputStream fileOS = null;
		        URL urlObj = new URL(urlfile);
		        bufferedIS = new BufferedInputStream(urlObj.openStream());
		        fileOS = new FileOutputStream(file);
		 
		        int data = bufferedIS.read();
		        while(data != -1){
		          fileOS.write(data);
		          data = bufferedIS.read();
		        }
			    System.out.println("AFTER RUNTIME: BEFORE FILE READ");
			    // Create an object of filereader
		        // class with CSV file as a parameter.
		        FileReader filereader = new FileReader(file);
		 
		        // create csvReader object passing
		        // file reader as a parameter
		        CSVReader csvReader = new CSVReader(filereader);
		        String[] nextRecord;
		 
		        int flag = 0 ;
		        // we are going to read data line by line
		        while ((nextRecord = csvReader.readNext()) != null) {
		        	if(flag>0) {
			        	createDailyTransaction(nextRecord);
			        				            
		        	}
		            flag++;
		        }
		        System.out.println("Total count :" + flag);
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	
	  private void createDailyTransaction(String[] metadata) {
	  
	  String companyName = metadata[1]; 
	  int dailyClose =  (int) Float.parseFloat(metadata[6]); 
	  int dailyLow = (int) Float.parseFloat(metadata[5]);
	  int dailyHigh = (int) Float.parseFloat(metadata[4]); 
	  String companyType = metadata[2];
	  
	  DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	  LocalDateTime now = LocalDateTime.now(); 
	  Date dateOfTransaction =  java.sql.Date.valueOf(now.toLocalDate());
	  
	  // create and return book of this metadata return new
	  DailyTransaction dailyTransaction = new DailyTransaction(companyName, dailyClose, dailyLow, dailyHigh, companyType, dateOfTransaction); 
	  dailyTranDTO.save(dailyTransaction);
	  
	  final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);	   
	    
	 // Date yesterday =   (Date) cal.getTime();
	  
	 
	  List<DailyTransaction> dailyTransactionsList = dailyTranDTO.showTransactionHistory(companyName,dateOfTransaction);
	 
	 System.out.println("Before :" + dateOfTransaction);
	 for (int i = 0; i < dailyTransactionsList.size(); i++) {
            System.out.println(dailyTransactionsList.get(i).getDailyClose()+": VALUE"+companyName+"");
        }
	  System.out.println("after");
	  
	  }


	

}
