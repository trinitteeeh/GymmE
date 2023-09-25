package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.SupplementType;

public class SupplementTypeDAO extends AbstractGenericDAO<SupplementType>{

	public SupplementTypeDAO() {
		super(SupplementType.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "MsSupplementType";
	}

	@Override
	protected SupplementType mapResultSetToObject(ResultSet resultSet) throws SQLException {
		String supplementTypeID = resultSet.getString("SupplementTypeID");
		String supplementTypeName = resultSet.getString("SupplementTypeName");
		return new SupplementType(supplementTypeID, supplementTypeName);
	}

	@Override
	protected String getIdFromEntity(SupplementType entity) {
		// TODO Auto-generated method stub
		return entity.getSupplementTypeID();
	}

	@Override
	protected String getIdName() {
		// TODO Auto-generated method stub
		return "SupplemetTypeID";
	}

}
