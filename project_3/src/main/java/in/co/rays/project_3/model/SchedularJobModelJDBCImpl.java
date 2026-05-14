package in.co.rays.project_3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.RoleDTO;
import in.co.rays.project_3.dto.SchedulerJobDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.JDBCDataSource;

public class SchedularJobModelJDBCImpl implements SchedularJobModelInt{
	
	private static Logger log = Logger.getLogger(SchedularJobModelJDBCImpl.class);

	/**
	 * create id 
	 * @return pk
	 * @throws DatabaseException
	 */
	public long nextPK() throws DatabaseException {
		Connection con = null;
		long pk = 0;
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select max(id) from st_schedularJob");
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				pk = (int) r.getLong(1);
			}
		} catch (Exception e) {
			log.error("Database Exception", e);
			throw new DatabaseException("Exception getting in pk");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		return pk + 1;
	}

	/**
	 * add new role 
	 * @param rdto
	 * @return pk
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(SchedulerJobDTO rdto) throws  ApplicationException, DuplicateRecordException {
		Connection con = null;
		long pk = 0;
		SchedulerJobDTO duplicateSchedularJob = findByJobName(rdto.getJobName());
		if (duplicateSchedularJob != null) {
			throw new DuplicateRecordException("SchedularJob already exists");
		}
		try {
			pk=nextPK();
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("insert into st_schedularJob values(?,?,?,?,?");
			ps.setLong(1, pk);
			ps.setString(2, rdto.getJobName());
			ps.setString(3, rdto.getJobCode());
			ps.setString(4, rdto.getCronExpression());
			ps.setString(5, rdto.getStatus());
			
			int a = ps.executeUpdate();
			System.out.println("insert data" + a);
			ps.close();
			con.commit();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				con.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add SchedularJob");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model add End");
		return 0;
	}

	/**
	 * delete role
	 * @param rdto
	 * @throws ApplicationException
	 */
	public void delete(SchedulerJobDTO rdto) throws ApplicationException {
		Connection con = null;
		try {

			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("delete from st_schedularJob where id=?");
			ps.setLong(1, rdto.getJobId());
			System.out.println("Delete data successfully");
			ps.executeUpdate();
			ps.close();
			con.commit();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				con.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete SchedulerJob");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model delete Started");

	}

	/**
	 * update role 
	 * @param rdto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public void update(SchedulerJobDTO rdto) throws ApplicationException, DuplicateRecordException {
		Connection con = null;
		SchedulerJobDTO duplicataSchedularJob = findByJobName(rdto.getJobName());
		if (duplicataSchedularJob != null && duplicataSchedularJob.getJobId() != rdto.getJobId()) {
			throw new DuplicateRecordException("SchedularJob already exists");
		}
		try {

			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(
					"update st_schedularJob set JobName=?,jobCode=?,cronExpression=?,status=?,created_datetime=? where id=?");
			ps.setString(1, rdto.getJobName());
			ps.setString(2, rdto.getJobCode());
			ps.setString(3, rdto.getCronExpression());
			ps.setString(4, rdto.getStatus());
			ps.setLong(5, rdto.getJobId());

			System.out.println("update data successfully");
			ps.executeUpdate();
			ps.close();
			con.commit();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				con.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating role ");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
	}

	public List list() throws ApplicationException {

		return list(0, 0);
	}

	
	/**
	 *list of role
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_schedularJob");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		SchedulerJobDTO dto=null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				 dto = new SchedulerJobDTO();
				 dto.setJobId(rs.getLong(1));
					dto.setJobName(rs.getString(2));
					dto.setJobCode(rs.getString(3));
					dto.setCronExpression(rs.getString(4));
					dto.setStatus(rs.getString(5));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of SchedularJob");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

	/**
	 * find by role with the help of role
	 * @param pk
	 * @return dto
	 * @throws ApplicationException
	 */
	public SchedulerJobDTO findByPK(long pk) throws ApplicationException {
		Connection con = null;
		SchedulerJobDTO rdto = null;
		try {

			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from st_schedularJob where id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rdto = new SchedulerJobDTO();
				rdto.setJobId(rs.getLong(1));
				rdto.setJobName(rs.getString(2));
				rdto.setJobCode(rs.getString(3));
				rdto.setCronExpression(rs.getString(4));
				rdto.setStatus(rs.getString(5));

			}
			ps.close();
			con.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Schedular Job by pk");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("model findBy pk end");

		return rdto;

	}

	/**
	 * find role with the help of name
	 * @param name
	 * @return dto
	 * @throws ApplicationException
	 */
	public SchedulerJobDTO findByJobName(String jobName) throws ApplicationException {
		Connection con = null;
		SchedulerJobDTO rdto = null;
		try {

			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from st_schedularJob where name=?");
			ps.setString(1, jobName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rdto = new SchedulerJobDTO();
				rdto.setJobId(rs.getLong(1));
				rdto.setJobName(rs.getString(2));
				rdto.setJobCode(rs.getString(3));
				rdto.setCronExpression(rs.getString(4));
				rdto.setStatus(rs.getString(5));

			}
			ps.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model findBy EmailId End");

		return rdto;

	}

	public List search(SchedulerJobDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * search role
	 * @param rdto1
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 * @throws ApplicationException
	 */
	public ArrayList<SchedulerJobDTO> search(SchedulerJobDTO dto, int pageNo, int pageSize) throws ApplicationException{
	    //log.debug("Model search Started");
	    StringBuffer sql = new StringBuffer("select * from st_schedularJob where 1=1");

	    if (dto != null) {
	        if (dto.getJobId() > 0) {
	            sql.append(" AND JOBID = " + dto.getJobId());
	        }
	        if (dto.getJobName() != null && dto.getJobName().length() > 0) {
	            sql.append(" AND JOBNAME like '" + dto.getJobName() + "%'");
	        }
	        if (dto.getJobCode() != null && dto.getJobCode().length() > 0) {
	            sql.append(" AND JOBCODE like '" + dto.getJobCode() + "%'");
	        }
	        if (dto.getCronExpression() != null && dto.getCronExpression().length() > 0) {
	            sql.append(" AND CRONEXPRESSION like '" + dto.getCronExpression() + "%'");
	        }
	        if (dto.getStatus() != null && dto.getStatus().length() > 0) {
	            sql.append(" AND STATUS like '" + dto.getStatus() + "%'");
	        }
	       
	    }

	    // if page size is greater than zero then apply pagination
	    if (pageSize > 0) {
	        // Calculate start record index
	        pageNo = (pageNo - 1) * pageSize;

	        sql.append(" Limit " + pageNo + ", " + pageSize);
	        // sql.append(" limit " + pageNo + "," + pageSize);
	    }

	    System.out.println(sql);
	    ArrayList<SchedulerJobDTO> list = new ArrayList<SchedulerJobDTO>();
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) 
	        {
	        	
	            dto = new SchedulerJobDTO();
	            dto.setJobId(rs.getLong(1));
	            dto.setJobName(rs.getString(2));
	            dto.setJobCode(rs.getString(3));
	            dto.setCronExpression(rs.getString(4));
	            dto.setStatus(rs.getString(5));
         

	            list.add(dto);
	        }
	        rs.close();
	    } catch (Exception e) {
	    	throw new ApplicationException("exception in SchedularJob model  search"+e.getMessage());
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }

	    //log.debug("Model search End");
	    return list;
	}

	

}
