package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.NetworkMonitorDTO;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface NetworkMonitorModelInt {
	
	public long add(NetworkMonitorDTO dto) throws DatabaseException, DuplicateRecordException;

	public void delete(NetworkMonitorDTO dto) throws DatabaseException;

	public void update(NetworkMonitorDTO dto) throws DatabaseException, DuplicateRecordException;

	public List list() throws DatabaseException;

	public List search(NetworkMonitorDTO dto, int pageNo, int pageSize) throws DatabaseException;

	public NetworkMonitorDTO findByPK(long pk) throws DatabaseException;

	public NetworkMonitorDTO findByIpAddress(String ipAddress) throws DatabaseException;

}
