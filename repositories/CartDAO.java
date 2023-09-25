package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cart;

public class CartDAO extends AbstractGenericDAO<Cart>{

	public CartDAO() {
		super(Cart.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "MsCart";
	}

	@Override
	protected Cart mapResultSetToObject(ResultSet resultSet) throws SQLException {
		String userID = resultSet.getString("UserID");
		String supplementID = resultSet.getString("SupplementID");
		int quantity= resultSet.getInt("Quantity");
		return new Cart(userID, supplementID, quantity);
	}

	@Override
	protected String getIdFromEntity(Cart entity) {
		// TODO Auto-generated method stub
		return entity.getUserID();
	}
	
	public List<Cart> findAll(String userID) {
		List<Cart> entities = new ArrayList<>();
		String query = "SELECT * FROM " + getTableName() + " WHERE UserID = ?";
		try {
			PreparedStatement statement = connect.prepare(query);
			statement.setString(1, userID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				entities.add(mapResultSetToObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entities;
	}

	
	public Cart findById(String userID, String supplementID) {
		String query = "SELECT * FROM " + getTableName() + " WHERE UserID = ? AND SupplementID = ?";
		try {
			PreparedStatement statement = connect.prepare(query);
			statement.setString(1, userID);
			statement.setString(2, supplementID);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return mapResultSetToObject(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getIdName() {
		return "CartID";
	}
	
	public void delete(Cart cart) {
		String query = "DELETE FROM " + getTableName() + " WHERE UserID = ? AND SupplementID = ?";
		try{
			PreparedStatement statement = connect.prepare(query);
			statement.setString(1, cart.getUserID());
			statement.setString(2, cart.getSupplementID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String userID) {
		String query = "DELETE FROM " + getTableName() + " WHERE UserID = ?";
		try{
			PreparedStatement statement = connect.prepare(query);
			statement.setString(1, userID);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Cart cart) {
		String query = "UPDATE " + getTableName() + " SET Quantity = ? WHERE UserID = ? AND SupplementID = ?";
		try{
			PreparedStatement statement = connect.prepare(query);
			statement.setInt(1, cart.getQuantity());
			statement.setString(2, cart.getUserID());
			statement.setString(3, cart.getSupplementID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
