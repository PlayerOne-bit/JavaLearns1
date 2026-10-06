package system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.mindrot.jbcrypt.BCrypt;

public class Authentication {
	private final static Set<String> 
			USERNAME= new HashSet<>(),
			EMAIL_ADDRESS=new HashSet<>();
	public static boolean hasUsername(String username) {
		return USERNAME.contains(username);
	}
	public static boolean hasEmailAddress(String email_address) {
		return EMAIL_ADDRESS.contains(email_address);
	}
	
	public static void authenticate(){
		try(Connection connect=Database.connectSQL()){
			PreparedStatement ps = connect.prepareStatement(
					"SELECT username,email_address,passwords FROM accounts");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				USERNAME.add(rs.getString("username"));
				EMAIL_ADDRESS.add(rs.getString("email_address"));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static boolean logIn(String email_address,String password) {
		try(Connection connect =Database.connectSQL()){
			PreparedStatement ps = connect.prepareStatement(
					"SELECT passwords FROM accounts WHERE email_address=? or username=?;");
			ps.setString(1, email_address);
			ps.setString(2, email_address);
			ResultSet rs = ps.executeQuery();
			if(rs.next()&& BCrypt.checkpw(password, rs.getString("passwords"))) {
				User.setUser(email_address);
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	public static void createAccount(String[] info){
		try(Connection connect=Database.connectSQL()){
			PreparedStatement ps =connect.prepareStatement(
					"INSERT INTO accounts("
					+"id,"
					+ "username,"
					+ "email_address,"
					+ "passwords) values(?,?,?,?)");
			for(int i=1; i<=info.length; i++) {
				ps.setString(i,info[i-1]);
			}
			ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void changePassword(String password){
		try(Connection connect = Database.connectSQL()){
			PreparedStatement ps = connect.prepareStatement("UPDATE accounts SET passwords = ? WHERE id=?");
			ps.setString(1, BCrypt.hashpw(password,BCrypt.gensalt()));
			ps.setString(2, User.getId());
			ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static boolean deleteAccount(){
		try(Connection connect = Database.connectSQL()){
			PreparedStatement ps = connect.prepareStatement(
					"DELETE FROM accounts WHERE id=?;"
					+ "DELETE FROM personal_info WHERE id=?;"
					+ "DELETE FROM f");
			ps.setString(1,User.getId());
			ps.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
