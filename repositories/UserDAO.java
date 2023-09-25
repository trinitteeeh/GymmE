package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import connection.Connect;
import models.User;

public class UserDAO extends AbstractGenericDAO<User>{

	public UserDAO() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "MsUser";
	}

	@Override
	protected User mapResultSetToObject(ResultSet resultSet) throws SQLException {
		String userID = resultSet.getString("UserID");
		String userName = resultSet.getString("UserName");
		String userEmail = resultSet.getString("UserEmail");
		String userPassword = resultSet.getString("UserPassword");
		String userGender = resultSet.getString("UserGender");
		LocalDate userDOB = resultSet.getDate("UserDOB").toLocalDate();
		String userRole = resultSet.getString("UserRole");
		return new User(userID, userName, userEmail, userPassword, userGender, userDOB, userRole);
	}

	@Override
	protected String getIdFromEntity(User entity) {
		// TODO Auto-generated method stub
		return entity.getUserID();
	}
	
	public User select(String email, String password) {
		String query = "SELECT * FROM MsUser WHERE UserEmail = '"+email+"' AND UserPassword = '"+password+"'";

		Connect connect = Connect.getConnection();
		PreparedStatement ps = connect.prepare(query);
		ResultSet rs = connect.executeStatementQuery(ps);

		try {
			while(rs.next()) {
				return mapResultSetToObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User select(String email) {
		String query = "SELECT * FROM MsUser WHERE UserEmail = '"+email+"'";

		Connect connect = Connect.getConnection();
		PreparedStatement ps = connect.prepare(query);
		ResultSet rs = connect.executeStatementQuery(ps);

		try {
			while(rs.next()) {
				return mapResultSetToObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getIdName() {
		// TODO Auto-generated method stub
		return "UserID";
	}
}
