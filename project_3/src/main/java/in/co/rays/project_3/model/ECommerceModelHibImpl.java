package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.ECommerceDTO;
import in.co.rays.project_3.dto.ProductDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class ECommerceModelHibImpl implements ECommerceModelInt{
	
	@Override
	public long add(ECommerceDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
//		ProductDTO duplicateProductName = fingByName(dto.getProductName());
//		if (duplicateProductName != null) {
//			throw new DuplicateRecordException("product name already exist");
//		}
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
			throw new ApplicationException("Exception in ECommerce Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(ECommerceDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in ECommerce Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(ECommerceDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();

			session.saveOrUpdate(dto);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in ECommerce update" + e.getMessage());
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
			Criteria criteria = session.createCriteria(ECommerceDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  ECommerce list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(ECommerceDTO dto) throws ApplicationException {
		return search(dto, 0, 0);

	}

	@Override
	public List search(ECommerceDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ECommerceDTO.class);
			if (dto != null) {
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));

				}
				if (dto.getProductName() != null && dto.getProductName().length() > 0) {
					criteria.add(Restrictions.like("productName", dto.getProductName() + "%"));
				}
				if (dto.getQuantity() > 0) {
					criteria.add(Restrictions.like("quantity", dto.getQuantity() + "%"));
				}
				if (dto.getPrice() > 0) {
					criteria.add(Restrictions.like("price", dto.getPrice()+ "%"));
				}
				if (dto.getPaymentStatus() != null && dto.getPaymentStatus().length() > 0) {
					criteria.add(Restrictions.like("paymentStatus", dto.getPaymentStatus() + "%"));
				}

			}
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in ECommerce search");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public ECommerceDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		ECommerceDTO dto = null;
		try {
			session = HibDataSource.getSession();

			dto = (ECommerceDTO) session.get(ECommerceDTO.class, pk);
			System.out.println(dto);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in getting ECommerce by pk");
		} finally {
			session.close();
		}
		System.out.println("++++" + dto);
		return dto;
	}

	@Override
	public ECommerceDTO fingByName(String name) throws ApplicationException {
		Session session = null;
		ECommerceDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ECommerceDTO.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (ECommerceDTO) list.get(0);
			}
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting User by Login " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

}
