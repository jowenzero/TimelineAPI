package com.doublebox.timelineapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class TimelineApiApplication {
	private static Class<TimelineApiApplication> classObject = TimelineApiApplication.class;
	private static Logger log = LogManager.getLogger(classObject);
	
	public static void main(String[] args) {
		SpringApplication.run(TimelineApiApplication.class, args);
		log.info("--------------------------------");
        log.info("Program Up and Running");
        log.info("--------------------------------");
	}
	
	@GetMapping("/timelineapi")
	public String hello() {
		String text = "Welcome to Timeline API server! ";
		
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        
        DateFormat df = new SimpleDateFormat(pattern);
        df.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();	        
        String todayAsString = df.format(today);
        
        text += "Today is " + todayAsString;

        log.info(text);
        
        log.info("Testing Database Row Delete");
        
        MapSqlParameterSource parameterSource = null;
  
		String query = "DELETE FROM t_email;";
		
		parameterSource = new MapSqlParameterSource();
		
  		Database.Execute(query, parameterSource);
  		
  		log.info("Database Delete Row Works");
		
		return text;
	}
}


