package in.co.rays.project_3.model;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.VehicleDTO;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class VehicleModelHibImp implements VehicleModelInt {

    /**
     * Hibernate implementation of Vehicle model
     * @author Anand Choudhary
     */

    private static Logger log = Logger.getLogger(VehicleModelHibImp.class.getName());

    @Override
    public long add(VehicleDTO dto) throws DatabaseException, DuplicateRecordException {

        log.info("VehicleModelHibImp add started");

        VehicleDTO existDto = findByVehicleNumber(dto.getVehicleNumber());
        if (existDto != null) {
            throw new DuplicateRecordException("Vehicle Number already exists");
        }
        System.out.println(dto);

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
            throw new DatabaseException("Exception in Vehicle add " + e.getMessage());
        } finally {
            session.close();
        }

        return dto.getId();
    }

    @Override
    public void delete(VehicleDTO dto) throws DatabaseException {

        log.info("VehicleModelHibImp delete started");

        VehicleDTO vDto = findByPK(dto.getId());

        Session session = null;
        Transaction tx = null;

        try {
            session = HibDataSource.getSession();
            tx = session.beginTransaction();
            session.delete(vDto);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new DatabaseException("Exception in Vehicle delete " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(VehicleDTO dto) throws DatabaseException, DuplicateRecordException {

        log.info("VehicleModelHibImp update started");

        VehicleDTO existDto = findByVehicleNumber(dto.getVehicleNumber());
        if (existDto != null && existDto.getId() != dto.getId()) {
            throw new DuplicateRecordException("Vehicle Number already exists");
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
            throw new DatabaseException("Exception in Vehicle update " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public VehicleDTO findByPK(long pk) throws DatabaseException {

        Session session = null;
        VehicleDTO dto = null;

        try {
            session = HibDataSource.getSession();
            dto = (VehicleDTO) session.get(VehicleDTO.class, pk);

        } catch (HibernateException e) {
            throw new DatabaseException("Exception in getting Vehicle by PK");
        } finally {
            session.close();
        }

        return dto;
    }

    @Override
    public VehicleDTO findByVehicleNumber(String vehicleNumber) throws DatabaseException {

        Session session = null;
        VehicleDTO dto = null;

        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(VehicleDTO.class);
            criteria.add(Restrictions.eq("vehicleNumber", vehicleNumber));

            List list = criteria.list();
            if (list.size() == 1) {
                dto = (VehicleDTO) list.get(0);
            }

        } catch (HibernateException e) {
            throw new DatabaseException("Exception in findByVehicleNumber " + e.getMessage());
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
            Criteria criteria = session.createCriteria(VehicleDTO.class);

            if (pageSize > 0) {
                pageNo = (pageNo - 1) * pageSize;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();

        } catch (HibernateException e) {
            throw new DatabaseException("Exception in Vehicle list");
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public List search(VehicleDTO dto, int pageNo, int pageSize) throws DatabaseException {

        Session session = null;
        List list = null;

        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(VehicleDTO.class);

            if (dto != null) {

                if (dto.getId() != null && dto.getId() > 0) {
                    criteria.add(Restrictions.eq("id", dto.getId()));
                }

                if (dto.getVehicleNumber() != null && dto.getVehicleNumber().length() > 0) {
                    criteria.add(Restrictions.like("vehicleNumber", dto.getVehicleNumber() + "%"));
                }

                if (dto.getOwnerName() != null && dto.getOwnerName().length() > 0) {
                    criteria.add(Restrictions.like("ownerName", dto.getOwnerName() + "%"));
                }

                if (dto.getServiceType() != null && dto.getServiceType().length() > 0) {
                    criteria.add(Restrictions.like("serviceType", dto.getServiceType() + "%"));
                }

                if (dto.getMechanicName() != null && dto.getMechanicName().length() > 0) {
                    criteria.add(Restrictions.like("mechanicName", dto.getMechanicName() + "%"));
                }
                
            }

            if (pageSize > 0) {
                criteria.setFirstResult((pageNo - 1) * pageSize);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();

        } catch (HibernateException e) {
            throw new DatabaseException("Exception in Vehicle search");
        } finally {
            session.close();
        }

        return list;
    }
}