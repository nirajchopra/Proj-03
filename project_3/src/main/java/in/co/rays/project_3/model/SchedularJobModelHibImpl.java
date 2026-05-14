package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.RoleDTO;
import in.co.rays.project_3.dto.SchedulerJobDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class SchedularJobModelHibImpl implements SchedularJobModelInt {

	public long add(SchedulerJobDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		long pk = 0;

		SchedulerJobDTO existDto = findByJobName(dto.getJobName());

		if (existDto != null) {
			throw new DuplicateRecordException("SchedulerJob already exist");
		}
		session = HibDataSource.getSession();
		try {
			tx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in SchedulerJob Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	public void delete(SchedulerJobDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in SchedulerJob delete " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(SchedulerJobDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in SchedulerJob update " + e.getMessage());
		} finally {
			session.close();
		}

	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(SchedulerJobDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  SchedulerJob list");
		} finally {
			session.close();
		}
		return list;
	}

	public List search(SchedulerJobDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List search(SchedulerJobDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(SchedulerJobDTO.class);
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("jobId", dto.getJobId()));
			}
			if (dto.getJobName() != null && dto.getJobName().length() > 0) {
				criteria.add(Restrictions.like("jobName", dto.getJobName() + "%"));
			}
			if (dto.getJobCode() != null && dto.getJobCode().length() > 0) {
				criteria.add(Restrictions.like("jobCode", dto.getJobCode() + "%"));
			}
			if (dto.getCronExpression() != null && dto.getCronExpression().length() > 0) {
				criteria.add(Restrictions.like("cronExpression", dto.getCronExpression() + "%"));
			}
			if (dto.getStatus() != null && dto.getStatus().length() > 0) {
				criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
			}
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in status search");
		} finally {
			session.close();
		}
		return list;
	}

	public SchedulerJobDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();

		try {
			SchedulerJobDTO dto = (SchedulerJobDTO) session.get(SchedulerJobDTO.class, pk);
			return dto;
		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting SchedulerJob by pk");
		} finally {
			session.close();
		}

	}

	public SchedulerJobDTO findByJobName(String jobName) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		SchedulerJobDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(RoleDTO.class);
			criteria.add(Restrictions.eq("jobName", jobName));
			List list = criteria.list();

			if (list.size() > 0) {
				dto = (SchedulerJobDTO) list.get(0);
			}
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting SchedulerJob by Login " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

}
