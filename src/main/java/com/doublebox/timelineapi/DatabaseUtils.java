package com.doublebox.timelineapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseUtils {
	@Value("${spring.datasource.url}")
    private String databaseUrl;
	
	@Value("${spring.datasource.username}")
    private String databaseUsername;
	
	@Value("${spring.datasource.password}")
    private String databasePassword;
	
    public static String DATABASE_URL;
    public static String DATABASE_USERNAME;
    public static String DATABASE_PASSWORD;
    
    @Value("${spring.datasource.url}")
    public void setDatabaseUrlStatic(String databaseUrl){
    	DatabaseUtils.DATABASE_URL = databaseUrl;
    }
    
    @Value("${spring.datasource.username}")
    public void setDatabaseUsernameStatic(String databaseUsername){
    	DatabaseUtils.DATABASE_USERNAME = databaseUsername;
    }
    
    @Value("${spring.datasource.password}")
    public void setDatabasePasswordStatic(String databasePassword){
    	DatabaseUtils.DATABASE_PASSWORD = databasePassword;
    }
}
