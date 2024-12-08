package fr.insa.soa.ConfigClientMS.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientResource {
	
	
	@Value("${server.port}")
	private String serverPort;
	@Value("${db.connection}")
	private String typeConnectionDB;
	@Value("${db.host}")
	private String dbHost;
	@Value("${db.port}")
	private String dbPort;
	
	@GetMapping("/serverPort")
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	@GetMapping("/typeConnectionDB")
	public String getTypeConnectionDB() {
		return typeConnectionDB;
	}
	public void setTypeConnectionDB(String typeConnectionDB) {
		this.typeConnectionDB = typeConnectionDB;
	}
	@GetMapping("/dbHost")
	public String getDbHost() {
		return dbHost;
	}
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	@GetMapping("/dbPort")
	public String getDbPort() {
		return dbPort;
	}
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
}

