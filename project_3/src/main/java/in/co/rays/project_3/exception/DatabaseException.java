package in.co.rays.project_3.exception;

/**
 * @author Niraj Chopra
 */
public class DatabaseException extends ApplicationException{
	
	private static final long serialVersionUID = 1L;

	public DatabaseException(String msg){
		super(msg);
	}
}
