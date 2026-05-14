package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.PodCastDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface PodCastModelInt {
	
	public long add(PodCastDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(PodCastDTO dto)throws ApplicationException;
	public void update(PodCastDTO dto)throws ApplicationException,DuplicateRecordException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(PodCastDTO dto)throws ApplicationException;
	public List search(PodCastDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public PodCastDTO findByPK(long pk)throws ApplicationException;
	public PodCastDTO fingByHostName(String hostName)throws ApplicationException;

}
