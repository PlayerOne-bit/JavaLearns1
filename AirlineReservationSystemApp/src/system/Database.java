package system;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database{
	private class Admin{
		private static String url=System.getenv("URL_DB"),
				root=System.getenv("USER_DB"),
				my_password=System.getenv("UPASS_DB");
		
	}
	public static Connection connectSQL() throws Exception{
		return DriverManager.getConnection(
				Admin.url,
				Admin.root,
				Admin.my_password
			);
	}
}