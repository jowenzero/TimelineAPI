package com.doublebox.timelineapi;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.doublebox.timelineapi.models.SqlResult;

public class Database {	
	private static Class<Database> classObject = Database.class;
	private static Logger log = LogManager.getLogger(classObject);
	
	// Create connection object
	public static DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		
		/*
		dataSource.setUrl("jdbc:postgresql://ec2-54-205-232-84.compute-1.amazonaws.com:5432/db63vf3jfeths5");
		dataSource.setUsername("lqcaqpncnrxnvx");
		dataSource.setPassword("74a980d3022140ce349e0d8b99b5e50c3976f70fb74200e5a4c385913265ca2c");
		*/
		
		dataSource.setUrl(DatabaseUtils.DATABASE_URL);
		dataSource.setUsername(DatabaseUtils.DATABASE_USERNAME);
		dataSource.setPassword(DatabaseUtils.DATABASE_PASSWORD);
		
		dataSource.setConnectionProperties("useUnicode=yes;characterEncoding=utf8;");

		return dataSource;
	}

	// Execute DML command that returns a value
	public static SqlResult ExecuteScalar(String query, MapSqlParameterSource parameterSource) {
		DataSource dataSource = null;
		NamedParameterJdbcTemplate jdbcTemplate = null;

		String scalarResult = "";
		Boolean isSuccess = false;
		String errorMessage = "";

		try {
			dataSource = getDataSource();
			jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(query, parameterSource, keyHolder);

			isSuccess = true;
			errorMessage = "Success";

			scalarResult = keyHolder.getKey().toString();
		} 
		catch (Exception ex) {
			isSuccess = false;
			errorMessage = ex.getLocalizedMessage();

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String error = sw.toString();
			log.error(error);
		}

		return new SqlResult(isSuccess, errorMessage, scalarResult);
	}

	// Execute DML command that does not return any value
	public static SqlResult Execute(String query, MapSqlParameterSource parameterSource) {	
		DataSource dataSource = null;
		NamedParameterJdbcTemplate jdbcTemplate = null;

		SqlRowSet rowSet = null;
		Boolean isSuccess = false;
		String errorMessage = "";

		try {	
			dataSource = getDataSource();
			jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			jdbcTemplate.update(query, parameterSource);

			isSuccess = true;
			errorMessage = "Success";
		} 
		catch (Exception ex) {
			isSuccess = false;
			errorMessage = ex.getLocalizedMessage();

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String error = sw.toString();
			log.error(error);
		}

		return new SqlResult(isSuccess, errorMessage, rowSet);
	}

	// Retrieve data via SELECT statement
	public static SqlResult Retrieve(String query, MapSqlParameterSource parameterSource) {
		DataSource dataSource = null;
		NamedParameterJdbcTemplate jdbcTemplate = null;

		SqlRowSet rowSet = null;
		Boolean isSuccess = false;
		String errorMessage = "";

		try {
			dataSource = getDataSource();
			jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			rowSet = jdbcTemplate.queryForRowSet(query, parameterSource);

			isSuccess = true;
			errorMessage = "Success";
		} 
		catch (Exception ex) {
			isSuccess = false;
			errorMessage = ex.getLocalizedMessage();

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String error = sw.toString();
			log.error(error);
		}

		return new SqlResult(isSuccess, errorMessage, rowSet);
	}
}
