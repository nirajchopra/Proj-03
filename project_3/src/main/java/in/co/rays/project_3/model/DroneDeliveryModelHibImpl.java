package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.DroneDeliveryDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class DroneDeliveryModelHibImpl implements DroneDeliveryModelInt {

	@Override
	public long add(DroneDeliveryDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in DroneDelivery Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(DroneDeliveryDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in DroneDelivery Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(DroneDeliveryDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			System.out.println("before update");

			session.saveOrUpdate(dto);
			System.out.println("after update");
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in DroneDelivery update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(DroneDeliveryDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  DroneDelivery list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(DroneDeliveryDTO dto) throws ApplicationException {
		return search(dto, 0, 0);

	}

	@Override
	public List search(DroneDeliveryDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(DroneDeliveryDTO.class);
			if (dto.getId() != null && dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getDroneId() != null && dto.getDroneId().length() > 0) {
				criteria.add(Restrictions.eq("droneId", dto.getDroneId()));

			}
			if (dto.getDeliveryZone() != null && dto.getDeliveryZone().length() > 0) {
				criteria.add(Restrictions.like("deliveryZone", dto.getDeliveryZone() + "%"));

			}
			if (dto.getPayloadWeight() > 0) {
				criteria.add(Restrictions.eq("payloadWeight", dto.getPayloadWeight()));

			}
			if (dto.getFlightStatus() != null && dto.getFlightStatus().length() > 0) {
				criteria.add(Restrictions.like("flightStatus", dto.getFlightStatus() + "%"));
			}

			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in DroneDelivery search");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public DroneDeliveryDTO findByPK(long pk) throws ApplicationException {
		System.out.println("======" + pk + "----------------------------------");
		Session session = null;
		DroneDeliveryDTO dto = null;
		try {
			session = HibDataSource.getSession();

			dto = (DroneDeliveryDTO) session.get(DroneDeliveryDTO.class, pk);
			System.out.println(dto);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in getting DroneDelivery by pk");
		} finally {
			session.close();
		}
		System.out.println("++++" + dto);
		return dto;
	}

	@Override
	public DroneDeliveryDTO fingByDroneId(String droneId) throws ApplicationException {
		Session session = null;
		DroneDeliveryDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(DroneDeliveryDTO.class);
			criteria.add(Restrictions.eq("droneId", droneId));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (DroneDeliveryDTO) list.get(0);
			}
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting User by Login " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

}
