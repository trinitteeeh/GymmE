package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Transaction;

public class TransactionDAO extends AbstractGenericDAO<Transaction>{

	public TransactionDAO() {
		super(Transaction.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "TransactionHeader";
	}

	@Override
	protected Transaction mapResultSetToObject(ResultSet resultSet) throws SQLException {
		String transactionID = resultSet.getString("TransactionID");
		String userID = resultSet.getString("UserID");
		LocalDate transactionDate = resultSet.getDate("TransactionDate").toLocalDate();
		return new Transaction(transactionID, userID, transactionDate);
	}

	@Override
	protected String getIdFromEntity(Transaction entity) {
		// TODO Auto-generated method stub
		return entity.getTransactionID();
	}

	@Override
	protected String getIdName() {
		// TODO Auto-generated method stub
		return "TransactionID";
	}
	
	public List<Transaction> findByUserID(String id) {
		List<Transaction> entities = new ArrayList<>();
		String query = "SELECT * FROM " + getTableName() + " WHERE UserID = '" +id+"'";
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

}
