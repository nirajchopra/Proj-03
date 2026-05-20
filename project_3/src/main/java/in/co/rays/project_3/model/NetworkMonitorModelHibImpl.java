package in.co.rays.project_3.model;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.NetworkMonitorDTO;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;


public class NetworkMonitorModelHibImpl implements NetworkMonitorModelInt{
	
	  private static Logger log = Logger.getLogger(NetworkMonitorModelHibImpl.class.getName());

	    @Override
	    public long add(NetworkMonitorDTO dto) throws DatabaseException, DuplicateRecordException {

	        log.info("NetworkMonitorModelHibImpl add started");

	        NetworkMonitorDTO existDto = findByIpAddress(dto.getIpAddress());
	        if (existDto != null) {
	            throw new DuplicateRecordException("IpAddress ID already exists");
	        }

	        Session session = HibDataSource.getSession();
	        Transaction tx = null;

	        try {
	            tx = session.beginTransaction();
	            session.save(dto);
	            tx.commit();

	        } catch (HibernateException e) {
	            if (tx != null) {
	                tx.rollback();
	            }
	            throw new DatabaseException("Exception in IpAddress add " + e.getMessage());
	        } finally {
	            session.close();
	        }

	        return dto.getId();
	    }

	    @Override
	    public void delete(NetworkMonitorDTO dto) throws DatabaseException {

	        log.info("HospitalModelHibImp delete started");

	        NetworkMonitorDTO hDto = findByPK(dto.getId());
	        
	        Session session = null;
	        Transaction tx = null;

	        try {
	            session = HibDataSource.getSession();
	            tx = session.beginTransaction();
	            session.delete(hDto);
	            
	            tx.commit();

	        } catch (HibernateException e) {
	            if (tx != null) {
	                tx.rollback();
	            }
	            throw new DatabaseException("Exception in NetworkMonitor delete " + e.getMessage());
	        } finally {
	            session.close();
	        }
	    }

	    @Override
	    public void update(NetworkMonitorDTO dto) throws DatabaseException, DuplicateRecordException {

	        log.info("HospitalModelHibImp update started");

	        NetworkMonitorDTO existDto = findByIpAddress(dto.getIpAddress());
	        if (existDto != null && existDto.getId() != dto.getId()) {
	            throw new DuplicateRecordException("IpAddress ID already exists");
	        }

	        Session session = null;
	        Transaction tx = null;

	        try {
	            session = HibDataSource.getSession();
	            tx = session.beginTransaction();
	            session.saveOrUpdate(dto);
	            tx.commit();

	        } catch (HibernateException e) {
	            if (tx != null) {
	                tx.rollback();
	            }
	            throw new DatabaseException("Exception in NetworkMonitor update " + e.getMessage());
	        } finally {
	            session.close();
	        }
	    }

	    @Override
	    public NetworkMonitorDTO findByPK(long pk) throws DatabaseException {

	        Session session = null;
	        NetworkMonitorDTO dto = null;

	        try {
	            session = HibDataSource.getSession();
	            dto = (NetworkMonitorDTO) session.get(NetworkMonitorDTO.class, pk);

	        } catch (HibernateException e) {
	            throw new DatabaseException("Exception in getting NetworkMonitor by PK");
	        } finally {
	            session.close();
	        }

	        return dto;
	    }

	    @Override
	    public NetworkMonitorDTO findByIpAddress(String ipAddress) throws DatabaseException {

	        Session session = null;
	        NetworkMonitorDTO dto = null;

	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(NetworkMonitorDTO.class);
	            criteria.add(Restrictions.eq("ipAddress", ipAddress));

	            List list = criteria.list();
	            if (list.size() == 1) {
	                dto = (NetworkMonitorDTO) list.get(0);
	            }

	        } catch (HibernateException e) {
	            throw new DatabaseException("Exception in findByIpAddress " + e.getMessage());
	        } finally {
	            session.close();
	        }

	        return dto;
	    }

	    @Override
	    public List list() throws DatabaseException {
	        return list(0, 0);
	    }

	    public List list(int pageNo, int pageSize) throws DatabaseException {

	        Session session = null;
	        List list = null;

	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(NetworkMonitorDTO.class);

	            if (pageSize > 0) {
	                pageNo = (pageNo - 1) * pageSize;
	                criteria.setFirstResult(pageNo);
	                criteria.setMaxResults(pageSize);
	            }

	            list = criteria.list();

	        } catch (HibernateException e) {
	            throw new DatabaseException("Exception in NetworkMonitor list");
	        } finally {
	            session.close();
	        }

	        return list;
	    }

	    @Override
	    public List search(NetworkMonitorDTO dto, int pageNo, int pageSize) throws DatabaseException {

	        Session session = null;
	        List list = null;

	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(NetworkMonitorDTO.class);

	            if (dto != null) {

	                if (dto.getId() != null && dto.getId() > 0) {
	                    criteria.add(Restrictions.eq("id", dto.getId()));
	                }

	                if (dto.getIpAddress() != null && dto.getIpAddress().length() > 0) {
	                    criteria.add(Restrictions.like("ipAddress", dto.getIpAddress() + "%"));
	                }

	                if (dto.getBandwidth() > 0) {
	                    criteria.add(Restrictions.like("bandwidth", dto.getBandwidth()+ "%"));
	                }

	                if (dto.getStatus() != null && dto.getStatus().length() > 0) {
	                    criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
	                }

	                if (dto.getUptime() > 0) {
	                    criteria.add(Restrictions.like("uptime", dto.getUptime() + "%"));
	                }
	            }

	            if (pageSize > 0) {
	                criteria.setFirstResult((pageNo - 1) * pageSize);
	                criteria.setMaxResults(pageSize);
	            }

	            list = criteria.list();

	        } catch (HibernateException e) {
	            throw new DatabaseException("Exception in NetworkMonitor search");
	        } finally {
	            session.close();
	        }

	        return list;
	    }

}
