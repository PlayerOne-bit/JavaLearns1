package system;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
public class User {
	private static String id, username, password, email_address;
	List<Flight> list = new ArrayList<>();
	public static void setUser(String email_address){
		try(Connection connect = Database.connectSQL()){
			PreparedStatement ps = connect.prepareStatement(
					"SELECT * FROM accounts WHERE email_address=? or username=?");
			ps.setString(1,email_address);
			ps.setString(2, email_address);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id=rs.getString("id");
				username=rs.getString("username");
				password=rs.getString("passwords");
				User.email_address=rs.getString("email_address");
				SecureRandom random = new SecureRandom();
				byte[] bytes = new byte[32];
				random.nextBytes(bytes);
				String token=Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
				PreparedStatement update=connect.prepareStatement(
						"UPDATE accounts SET token=? WHERE email_address=?");
				update.setString(1, token);
				update.setString(2, email_address);
				update.executeUpdate();
			}
			
			
		}catch(Exception e) {
			System.out.println("Error");
		}
	}
	public static String getId() {
		return id;
	}
	public static String getUsername() {
		return username;
	}
	public static String getEmailAddress() {
		return email_address;
	}
	public static String getPassword() {
		return password;
	}
	
}
