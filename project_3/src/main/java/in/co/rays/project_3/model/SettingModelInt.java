package in.co.rays.project_3.model;

  import java.util.List;

  import in.co.rays.project_3.dto.SettingDTO;
  import in.co.rays.project_3.exception.ApplicationException;
  import in.co.rays.project_3.exception.DuplicateRecordException;

  /**
   * Interface of Setting model
   */
  public interface SettingModelInt {

  	public long add(SettingDTO dto) throws ApplicationException, DuplicateRecordException;

  	public void delete(SettingDTO dto) throws ApplicationException;

  	public void update(SettingDTO dto) throws ApplicationException, DuplicateRecordException;

  	public List list() throws ApplicationException;

  	public List list(int pageNo, int pageSize) throws ApplicationException;

  	public List search(SettingDTO dto) throws ApplicationException;

  	public List search(SettingDTO dto, int pageNo, int pageSize) throws ApplicationException;

  	public SettingDTO findByPK(long pk) throws ApplicationException;

  	public SettingDTO findBySettingKey(String settingKey) throws ApplicationException;

  }
