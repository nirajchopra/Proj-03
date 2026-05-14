package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.SchedulerJobDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface SchedularJobModelInt {

	public long add(SchedulerJobDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(SchedulerJobDTO dto) throws ApplicationException;

	public void update(SchedulerJobDTO dto) throws ApplicationException, DuplicateRecordException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(SchedulerJobDTO dto) throws ApplicationException;

	public List search(SchedulerJobDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public SchedulerJobDTO findByPK(long pk) throws ApplicationException;

	public SchedulerJobDTO findByJobName(String jobName) throws ApplicationException;
}
