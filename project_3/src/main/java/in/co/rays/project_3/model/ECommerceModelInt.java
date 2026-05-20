package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.ECommerceDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface ECommerceModelInt {
	
	public long add(ECommerceDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(ECommerceDTO dto)throws ApplicationException;
	public void update(ECommerceDTO dto)throws ApplicationException,DuplicateRecordException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(ECommerceDTO dto)throws ApplicationException;
	public List search(ECommerceDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public ECommerceDTO findByPK(long pk)throws ApplicationException;
	public ECommerceDTO fingByName(String name)throws ApplicationException;

}
