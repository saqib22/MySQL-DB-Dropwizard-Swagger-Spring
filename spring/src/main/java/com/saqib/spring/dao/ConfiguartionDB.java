package com.saqib.spring.dao;

public class ConfiguartionDB {
	private String DBDriver;
	private String dbname;
	private String dbuser;
	private String dbpasword;
	public String getDBDriver() {
		return DBDriver;
	}
	public void setDBDriver(String dBDriver) {
		DBDriver = dBDriver;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	public String getDbpasword() {
		return dbpasword;
	}
	public void setDbpasword(String dbpasword) {
		this.dbpasword = dbpasword;
	}
	
	public String toString()
	{
		System.out.println(DBDriver+"\t"+dbname+"\t"+dbuser+"\t"+dbpasword);
		return DBDriver+"\t"+dbname+"\t"+dbuser+"\t"+dbpasword;
	}

}
