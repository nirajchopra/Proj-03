
package in.co.rays.project_3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.SettingDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.JDBCDataSource;

/**
 * JDBC implements of Setting model
 */
public class SettingModelJDBCImpl implements SettingModelInt {

	private static Logger log = Logger.getLogger(SettingModelJDBCImpl.class);

	public long nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");

		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_setting");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			log.error(e);
			throw new DatabaseException("Database Exception " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model nextPK End");
		return pk + 1;
	}

	public long add(SettingDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");

		SettingDTO duplicate = findBySettingKey(dto.getSettingKey());

		if (duplicate != null) {
			throw new DuplicateRecordException("Setting Key already exists");
		}

		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_setting values(?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, dto.getSettingId());
			pstmt.setString(3, dto.getSettingName());
			pstmt.setString(4, dto.getSettingKey());
			pstmt.setString(5, dto.getSettingValue());
			pstmt.setString(6, dto.getSettingType());
			pstmt.setString(7, dto.getDescription());
			pstmt.setString(8, dto.getStatus());
			

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Setting");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");
		return pk;
	}

	public void delete(SettingDTO dto) throws ApplicationException {
		log.debug("Model delete Started");

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_setting where id=?");
			pstmt.setString(1, dto.getSettingId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Setting");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model delete End");
	}

	public void update(SettingDTO dto) throws ApplicationException, DuplicateRecordException {
  		log.debug("Model update Started");

  		SettingDTO duplicate = findBySettingKey(dto.getSettingKey());

  		if (duplicate != null && duplicate.getSettingId() != dto.getSettingId()) {
  			throw new DuplicateRecordException("Setting Key already exists");
  		}

  		Connection conn = null;

  		try {
  			conn = JDBCDataSource.getConnection();
  			conn.setAutoCommit(false);

  			PreparedStatement pstmt = conn.prepareStatement(
  					"update st_setting set setting_id=?, setting_name=?, setting_key=?, setting_value=?, setting_type=?, description=?, status=? where id=?");

  			pstmt.setString(1, dto.getSettingId());
  			pstmt.setString(2, dto.getSettingName());
  			pstmt.setString(3, dto.getSettingKey());
  			pstmt.setString(4, dto.getSettingValue());
  			pstmt.setString(5, dto.getSettingType());
  			pstmt.setString(6, dto.getDescription());
  			pstmt.setString(7, dto.getStatus());
  			pstmt.setString(8, dto.getSettingId());

  			pstmt.executeUpdate();
  			conn.commit();
  			pstmt.close();

  		} catch (Exception e) {
  			log.error("Database Exception..", e);
  			try {
  				conn.rollback();
  			} catch (Exception ex) {
  				throw new ApplicationException("Exception : Update rollback exception " + ex.getMessage());
  			}
  			throw new ApplicationException("Exception in updating Setting");
  		} finally {
  			JDBCDataSource.closeConnection(conn);
  		}

  		log.debug("Model update End");
  	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_setting");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SettingDTO dto = new SettingDTO();

				dto.setSettingId(rs.getString(1));
				dto.setSettingId(rs.getString(2));
				dto.setSettingName(rs.getString(3));
				dto.setSettingKey(rs.getString(4));
				dto.setSettingValue(rs.getString(5));
				dto.setSettingType(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setStatus(rs.getString(8));
				

				list.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Setting");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;
	}

	public List search(SettingDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	public List search(SettingDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");

		StringBuffer sql = new StringBuffer("select * from st_setting where 1=1");

		if (dto != null) {
			if (dto.getSettingId() != null && dto.getSettingId().length() > 0) {
				sql.append(" AND id = " + dto.getSettingId());
			}
			if (dto.getSettingId() != null && dto.getSettingId().length() > 0) {
				sql.append(" AND setting_id like '" + dto.getSettingId() + "%'");
			}
			if (dto.getSettingName() != null && dto.getSettingName().length() > 0) {
				sql.append(" AND setting_name like '" + dto.getSettingName() + "%'");
			}
			if (dto.getSettingKey() != null && dto.getSettingKey().length() > 0) {
				sql.append(" AND setting_key like '" + dto.getSettingKey() + "%'");
			}
			if (dto.getSettingValue() != null && dto.getSettingValue().length() > 0) {
				sql.append(" AND setting_value like '" + dto.getSettingValue() + "%'");
			}
			if (dto.getSettingType() != null && dto.getSettingType().length() > 0) {
				sql.append(" AND setting_type like '" + dto.getSettingType() + "%'");
			}
			if (dto.getStatus() != null && dto.getStatus().length() > 0) {
				sql.append(" AND status like '" + dto.getStatus() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new SettingDTO();

				dto.setSettingId(rs.getString(1));
				dto.setSettingId(rs.getString(2));
				dto.setSettingName(rs.getString(3));
				dto.setSettingKey(rs.getString(4));
				dto.setSettingValue(rs.getString(5));
				dto.setSettingType(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setStatus(rs.getString(8));
				
				list.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Setting");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	public SettingDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");

		StringBuffer sql = new StringBuffer("select * from st_setting where id=?");
		SettingDTO dto = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new SettingDTO();

				dto.setSettingId(rs.getString(1));
				dto.setSettingId(rs.getString(2));
				dto.setSettingName(rs.getString(3));
				dto.setSettingKey(rs.getString(4));
				dto.setSettingValue(rs.getString(5));
				dto.setSettingType(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setStatus(rs.getString(8));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Setting by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model findByPK End");
		return dto;
	}

	public SettingDTO findBySettingKey(String settingKey) throws ApplicationException {
		log.debug("Model findBySettingKey Started");

		StringBuffer sql = new StringBuffer("select * from st_setting where setting_key=?");
		SettingDTO dto = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, settingKey);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new SettingDTO();

				dto.setSettingId(rs.getString(1));
				dto.setSettingId(rs.getString(2));
				dto.setSettingName(rs.getString(3));
				dto.setSettingKey(rs.getString(4));
				dto.setSettingValue(rs.getString(5));
				dto.setSettingType(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setStatus(rs.getString(8));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Setting by Setting Key");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model findBySettingKey End");
		return dto;
	}
}
