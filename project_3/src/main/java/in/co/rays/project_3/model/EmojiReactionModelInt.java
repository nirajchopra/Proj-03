package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.EmojiReactionDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface EmojiReactionModelInt {

	public long add(EmojiReactionDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(EmojiReactionDTO dto) throws ApplicationException;

	public void update(EmojiReactionDTO dto) throws ApplicationException, DuplicateRecordException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(EmojiReactionDTO dto) throws ApplicationException;

	public List search(EmojiReactionDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public EmojiReactionDTO findByPK(long pk) throws ApplicationException;

	public EmojiReactionDTO fingByUserName(String nuserName) throws ApplicationException;

}
