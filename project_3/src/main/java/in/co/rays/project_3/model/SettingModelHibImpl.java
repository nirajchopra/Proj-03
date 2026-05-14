package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.SettingDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class SettingModelHibImpl implements SettingModelInt {

	public long add(SettingDTO dto) throws ApplicationException, DuplicateRecordException {

		SettingDTO duplicate = findBySettingKey(dto.getSettingKey());

		if (duplicate != null) {
			throw new DuplicateRecordException("Setting Key already exists");
		}

		Session session = HibDataSource.getSession();
		Transaction tx = null;
		long pk = 0;

		try {
			tx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Setting Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	public void delete(SettingDTO dto) throws ApplicationException {

		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Setting Delete " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(SettingDTO dto) throws ApplicationException, DuplicateRecordException {

		SettingDTO duplicate = findBySettingKey(dto.getSettingKey());

		if (duplicate != null && duplicate.getSettingId() != dto.getSettingId()) {
			throw new DuplicateRecordException("Setting Key already exists");
		}

		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Setting Update " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public SettingDTO findByPK(long pk) throws ApplicationException {

		Session session = HibDataSource.getSession();
		SettingDTO dto = null;

		try {
			dto = (SettingDTO) session.get(SettingDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in getting Setting by PK " + e.getMessage());
		} finally {
			session.close();
		}
		return dto;
	}

	public SettingDTO findBySettingKey(String settingKey) throws ApplicationException {

		Session session = HibDataSource.getSession();
		SettingDTO dto = null;

		try {
			Criteria criteria = session.createCriteria(SettingDTO.class);
			criteria.add(Restrictions.eq("settingKey", settingKey));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (SettingDTO) list.get(0);
			}

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in getting Setting by Setting Key " + e.getMessage());
		} finally {
			session.close();
		}
		return dto;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(SettingDTO.class);

			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Setting list " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	public List search(SettingDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	public List search(SettingDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(SettingDTO.class);

			if (dto != null) {
				if (dto.getSettingId() != null) {
					criteria.add(Restrictions.eq("id", dto.getSettingId()));
				}
				if (dto.getSettingId() != null && dto.getSettingId().length() > 0) {
					criteria.add(Restrictions.like("settingId", dto.getSettingId() + "%"));
				}
				if (dto.getSettingName() != null && dto.getSettingName().length() > 0) {
					criteria.add(Restrictions.like("settingName", dto.getSettingName() + "%"));
				}
				if (dto.getSettingKey() != null && dto.getSettingKey().length() > 0) {
					criteria.add(Restrictions.like("settingKey", dto.getSettingKey() + "%"));
				}
				if (dto.getSettingValue() != null && dto.getSettingValue().length() > 0) {
					criteria.add(Restrictions.like("settingValue", dto.getSettingValue() + "%"));
				}
				if (dto.getSettingType() != null && dto.getSettingType().length() > 0) {
					criteria.add(Restrictions.like("settingType", dto.getSettingType() + "%"));
				}
				if (dto.getStatus() != null && dto.getStatus().length() > 0) {
					criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
				}
			}

			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Setting search " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}
}
