package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.AnnouncementDTO;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;

/**
 * Interface of Announcement model
 * 
 * @author Anand Choudhary
 *
 */
public interface AnnouncementModelInt {

    public long add(AnnouncementDTO dto) 
            throws DatabaseException, DuplicateRecordException;

    public void delete(AnnouncementDTO dto) 
            throws DatabaseException;

    public void update(AnnouncementDTO dto) 
            throws DatabaseException, DuplicateRecordException;

    public List list() 
            throws DatabaseException;

    public List search(AnnouncementDTO dto, int pageNo, int pageSize) 
            throws DatabaseException;

    public AnnouncementDTO findByPK(long pk) 
            throws DatabaseException;

    public AnnouncementDTO findByCode(String code) 
            throws DatabaseException;

}