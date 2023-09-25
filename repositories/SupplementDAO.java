package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import models.Supplement;

public class SupplementDAO extends AbstractGenericDAO<Supplement> {
	
    public SupplementDAO() {
		super(Supplement.class);
	}

	@Override
    protected String getTableName() {
        return "MsSupplement";
    }

    @Override
    protected Supplement mapResultSetToObject(ResultSet resultSet) throws SQLException {
        String supplementID = resultSet.getString("SupplementID");
        String supplementName = resultSet.getString("SupplementName");
        LocalDate supplementExpiryDate = resultSet.getDate("SupplementExpiryDate").toLocalDate();
        int supplementPrice = resultSet.getInt("supplementPrice");
        int supplementStock = resultSet.getInt("SupplementStock");
        String supplementTypeID = resultSet.getString("SupplementTypeID");
        // Additional mapping for other properties
        return new Supplement(supplementID, supplementName, supplementExpiryDate, supplementPrice, supplementStock, supplementTypeID);
    }

    @Override
    protected String getIdFromEntity(Supplement entity) {
        return entity.getSupplementID();
    }

	@Override
	protected String getIdName() {
		// TODO Auto-generated method stub
		return "SupplementID";
	}
}
