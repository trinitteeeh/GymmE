package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cart;
import models.TransactionDetail;

public class TransactionDetailDAO extends AbstractGenericDAO<TransactionDetail>{

	public TransactionDetailDAO() {
		super(TransactionDetail.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "TransactionDetail";
	}

	@Override
	protected TransactionDetail mapResultSetToObject(ResultSet resultSet) throws SQLException {
		String transactionID = resultSet.getString("TransactionID");
		String supplementID = resultSet.getString("SupplementID");
		int quantity= resultSet.getInt("Quantity");
		return new TransactionDetail(transactionID, supplementID, quantity);
	}

	@Override
	protected String getIdFromEntity(TransactionDetail entity) {
		return entity.getTransactionID();
	}
	
	public List<TransactionDetail> findAll(String transactionID) {
		List<TransactionDetail> entities = new ArrayList<>();
		String query = "SELECT * FROM " + getTableName() +" WHERE TransactionID = '" + transactionID+ "'";
		try { 
			PreparedStatement statement = connect.prepare(query);
			ResultSet resultSet = statement.executeQuery(query); 
			while (resultSet.next()) {
				entities.add(mapResultSetToObject(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entities;
	}
	
	public TransactionDetail findById(String transactionID, String supplementID) {
		String query = "SELECT * FROM " + getTableName() + " WHERE TransactionID = ? AND SupplementID = ?";
		try {
			PreparedStatement statement = connect.prepare(query);
			statement.setString(1, transactionID);
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
		// TODO Auto-generated method stub
		return "TransactionID";
	}
	
	

}
