package in.co.rays.project_3.controller;

  import java.io.IOException;

  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;

  import org.apache.log4j.Logger;

  import in.co.rays.project_3.dto.BaseDTO;
  import in.co.rays.project_3.dto.SettingDTO;
  import in.co.rays.project_3.exception.ApplicationException;
  import in.co.rays.project_3.exception.DuplicateRecordException;
  import in.co.rays.project_3.model.ModelFactory;
  import in.co.rays.project_3.model.SettingModelInt;
  import in.co.rays.project_3.util.DataUtility;
  import in.co.rays.project_3.util.DataValidator;
  import in.co.rays.project_3.util.PropertyReader;
  import in.co.rays.project_3.util.ServletUtility;

  /**
   * Setting functionality CRUD operation
   */
  @WebServlet(urlPatterns = { "/ctl/SettingCtl" })
  public class SettingCtl extends BaseCtl {

  	private static Logger log = Logger.getLogger(SettingCtl.class);

  	@Override
  	protected boolean validate(HttpServletRequest request) {

  		log.debug("SettingCtl Method validate Started");

  		boolean pass = true;

  		if (DataValidator.isNull(request.getParameter("settingId"))) {
  			request.setAttribute("settingId", PropertyReader.getValue("error.require", "Setting Id"));
  			pass = false;
  		}

  		if (DataValidator.isNull(request.getParameter("settingName"))) {
  			request.setAttribute("settingName", PropertyReader.getValue("error.require", "Setting Name"));
  			pass = false;
  		}

  		if (DataValidator.isNull(request.getParameter("settingKey"))) {
  			request.setAttribute("settingKey", PropertyReader.getValue("error.require", "Setting Key"));
  			pass = false;
  		}

  		if (DataValidator.isNull(request.getParameter("settingValue"))) {
  			request.setAttribute("settingValue", PropertyReader.getValue("error.require", "Setting Value"));
  			pass = false;
  		}

  		if (DataValidator.isNull(request.getParameter("settingType"))) {
  			request.setAttribute("settingType", PropertyReader.getValue("error.require", "Setting Type"));
  			pass = false;
  		}

  		if (DataValidator.isNull(request.getParameter("description"))) {
  			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
  			pass = false;
  		}

  		if (DataValidator.isNull(request.getParameter("status"))) {
  			request.setAttribute("status", PropertyReader.getValue("error.require", "Status"));
  			pass = false;
  		}

  		log.debug("SettingCtl Method validate Ended");

  		return pass;
  	}

  	@Override
  	protected BaseDTO populateDTO(HttpServletRequest request) {

  		log.debug("SettingCtl Method populateDTO Started");

  		SettingDTO dto = new SettingDTO();

  		dto.setSettingId(DataUtility.getString(request.getParameter("settingId")));
  		dto.setSettingName(DataUtility.getString(request.getParameter("settingName")));
  		dto.setSettingKey(DataUtility.getString(request.getParameter("settingKey")));
  		dto.setSettingValue(DataUtility.getString(request.getParameter("settingValue")));
  		dto.setSettingType(DataUtility.getString(request.getParameter("settingType")));
  		dto.setDescription(DataUtility.getString(request.getParameter("description")));
  		dto.setStatus(DataUtility.getString(request.getParameter("status")));

  		populateBean(dto, request);

  		log.debug("SettingCtl Method populateDTO Ended");

  		return dto;
  	}

  	protected void doGet(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {

  		log.debug("SettingCtl Method doGet Started");

  		String op = DataUtility.getString(request.getParameter("operation"));
  		long id = DataUtility.getLong(request.getParameter("id"));

  		SettingModelInt model = ModelFactory.getInstance().getSettingModel();

  		if (id > 0 || op != null) {
  			SettingDTO dto;
  			try {
  				dto = model.findByPK(id);
  				ServletUtility.setDto(dto, request);
  			} catch (ApplicationException e) {
  				log.error(e);
  				ServletUtility.handleException(e, request, response);
  				return;
  			}
  		}

  		ServletUtility.forward(getView(), request, response);

  		log.debug("SettingCtl Method doGet Ended");
  	}

  	protected void doPost(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {

  		log.debug("SettingCtl Method doPost Started");

  		String op = DataUtility.getString(request.getParameter("operation"));
  		long id = DataUtility.getLong(request.getParameter("id"));

  		SettingModelInt model = ModelFactory.getInstance().getSettingModel();

  		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

  			SettingDTO dto = (SettingDTO) populateDTO(request);

  			try {
  				if (id > 0) {
  					model.update(dto);
  					ServletUtility.setSuccessMessage("Data is successfully updated", request);
  				} else {
  					model.add(dto);
  					ServletUtility.setSuccessMessage("Data is successfully saved", request);
  				}

  				ServletUtility.setDto(dto, request);

  			} catch (ApplicationException e) {
  				log.error(e);
  				ServletUtility.handleException(e, request, response);
  				return;
  			} catch (DuplicateRecordException e) {
  				ServletUtility.setDto(dto, request);
  				ServletUtility.setErrorMessage("Setting Key already exists", request);
  			}

  		} else if (OP_DELETE.equalsIgnoreCase(op)) {

  			SettingDTO dto = (SettingDTO) populateDTO(request);

  			try {
  				model.delete(dto);
  				ServletUtility.redirect(ORSView.SETTING_LIST_CTL, request, response);
  				return;
  			} catch (ApplicationException e) {
  				log.error(e);
  				ServletUtility.handleException(e, request, response);
  				return;
  			}

  		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

  			ServletUtility.redirect(ORSView.SETTING_LIST_CTL, request, response);
  			return;

  		} else if (OP_RESET.equalsIgnoreCase(op)) {

  			ServletUtility.redirect(ORSView.SETTING_CTL, request, response);
  			return;
  		}

  		ServletUtility.forward(getView(), request, response);

  		log.debug("SettingCtl Method doPost Ended");
  	}

  	@Override
  	protected String getView() {
  		return ORSView.SETTING_VIEW;
  	}
  }

  
