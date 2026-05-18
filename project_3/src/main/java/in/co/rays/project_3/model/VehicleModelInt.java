package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.VehicleDTO;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;

/**
 * Interface of Vehicle model
 * 
 * @author Anand Choudhary
 *
 */
public interface VehicleModelInt {

	public long add(VehicleDTO dto) throws DatabaseException, DuplicateRecordException;

	public void delete(VehicleDTO dto) throws DatabaseException;

	public void update(VehicleDTO dto) throws DatabaseException, DuplicateRecordException;

	public List list() throws DatabaseException;

	public List search(VehicleDTO dto, int pageNo, int pageSize) throws DatabaseException;

	public VehicleDTO findByPK(long pk) throws DatabaseException;

	public VehicleDTO findByVehicleNumber(String vehicleNumber) throws DatabaseException;

}