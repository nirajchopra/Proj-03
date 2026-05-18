package in.co.rays.project_3.model;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.AnnouncementDTO;
import in.co.rays.project_3.dto.SessionDTO;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class AnnouncementModelHibImp implements AnnouncementModelInt {

    private static Logger log = Logger.getLogger(AnnouncementModelHibImp.class.getName());

    @Override
    public long add(AnnouncementDTO dto) throws DatabaseException, DuplicateRecordException {

        log.info("AnnouncementModelHibImp add started");

        AnnouncementDTO existDto = findByCode(dto.getAnnouncementCode());
        if (existDto != null) {
            throw new DuplicateRecordException("Announcement Code already exists");
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
            throw new DatabaseException("Exception in Announcement add " + e.getMessage());
        } finally {
            session.close();
        }

        return dto.getId();
    }

    @Override
    public void delete(AnnouncementDTO dto) throws DatabaseException {

        log.info("AnnouncementModel delete started");

        AnnouncementDTO annoucementDto = findByPK(dto.getId());

        Session session = null;
        Transaction tx = null;

        try {
            session = HibDataSource.getSession();
            tx = session.beginTransaction();
            session.delete(annoucementDto);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new DatabaseException("Exception in Announcement delete " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(AnnouncementDTO dto) throws DatabaseException, DuplicateRecordException {

        log.info("AnnouncementModel update started");

        AnnouncementDTO existDto = findByCode(dto.getAnnouncementCode());
        if (existDto != null && existDto.getId() != dto.getId()) {
            throw new DuplicateRecordException("Announcement Code already exists");
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
            throw new DatabaseException("Exception in Announcement update " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public AnnouncementDTO findByPK(long pk) throws DatabaseException {

        Session session = null;
        AnnouncementDTO dto = null;

        try {
            session = HibDataSource.getSession();
            dto = (AnnouncementDTO) session.get(AnnouncementDTO.class, pk);

        } catch (HibernateException e) {
            throw new DatabaseException("Exception in getting Announcement by PK");
        } finally {
            session.close();
        }

        return dto;
    }

    public AnnouncementDTO findByCode(String code) throws DatabaseException {

        Session session = null;
        AnnouncementDTO dto = null;

        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(AnnouncementDTO.class);
            criteria.add(Restrictions.eq("announcementCode", code));

            List list = criteria.list();
            if (list.size() == 1) {
                dto = (AnnouncementDTO) list.get(0);
            }

        } catch (HibernateException e) {
            throw new DatabaseException("Exception in findByCode " + e.getMessage());
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
            Criteria criteria = session.createCriteria(AnnouncementDTO.class);

            if (pageSize > 0) {
                criteria.setFirstResult((pageNo - 1) * pageSize);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();

        } catch (HibernateException e) {
            throw new DatabaseException("Exception in Announcement list");
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public List search(AnnouncementDTO dto, int pageNo, int pageSize) throws DatabaseException {

        Session session = null;
        List list = null;

        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(AnnouncementDTO.class);

            if (dto != null) {

                if (dto.getId() != null && dto.getId() > 0) {
                    criteria.add(Restrictions.eq("id", dto.getId()));
                }

                if (dto.getAnnouncementCode() != null && dto.getAnnouncementCode().length() > 0) {
                    criteria.add(Restrictions.like("announcementCode", dto.getAnnouncementCode() + "%"));
                }

                if (dto.getTitle() != null && dto.getTitle().length() > 0) {
                    criteria.add(Restrictions.like("title", dto.getTitle() + "%"));
                }

                if (dto.getPublishDate() != null) {
                    criteria.add(Restrictions.eq("publishDate", dto.getPublishDate()));
                }
            }

            if (pageSize > 0) {
                criteria.setFirstResult((pageNo - 1) * pageSize);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();

        } catch (HibernateException e) {
            throw new DatabaseException("Exception in Announcement search");
        } finally {
            session.close();
        }

        return list;
    }
}