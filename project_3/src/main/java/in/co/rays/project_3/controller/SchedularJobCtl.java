package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.SchedulerJobDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.SchedularJobModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

/**
 * role functionality controller.to perform add,delete ,update operation
 * 
 * @author Niraj Chopra
 *
 */
@WebServlet(urlPatterns = { "/ctl/SchedularJobCtl" })
public class SchedularJobCtl extends BaseCtl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SchedularJobCtl.class);

	protected boolean validate(HttpServletRequest request) {

		log.debug("SchedularJobCtl Method validate Started");

		boolean pass = true;
		System.out.println(request.getParameter("JobName") + "......" + request.getParameter("JobCode"));
		if (DataValidator.isNull(request.getParameter("JobName"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "JobName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("JobCode"))) {
			request.setAttribute("JobCode", PropertyReader.getValue("error.require", "JobCode"));
			pass = false;
		}

		log.debug("SchedularJob Method validate Ended");

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		SchedulerJobDTO dto = new SchedulerJobDTO();
		dto.setJobId(DataUtility.getLong(request.getParameter("jobId")));
		dto.setJobName(DataUtility.getString(request.getParameter("jobName")));
		dto.setJobCode(DataUtility.getString(request.getParameter("jobCode")));

		populateBean(dto, request);
		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = request.getParameter("operation");
		long jobId = DataUtility.getLong(request.getParameter("jobId"));
		SchedularJobModelInt model = ModelFactory.getInstance().getSchedularJobModel();
		if (jobId > 0 || op != null) {
			SchedulerJobDTO dto;
			try {
				dto = model.findByPK(jobId);
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = request.getParameter("operation");
		long jobId = DataUtility.getLong(request.getParameter("jobId"));
		SchedularJobModelInt model = ModelFactory.getInstance().getSchedularJobModel();
		System.out.println(" method do postkkkkkkkkk");
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			SchedulerJobDTO dto = (SchedulerJobDTO) populateDTO(request);
			System.out.println("kkkkkkkkkkkk" + dto);
			// System.out.println("kkkkk"+dto.getName()+"sdf"+dto.getDescription());
			try {
				if (jobId > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Successfully Updated", request);
				} else {
					try {
						// long pk =
						model.add(dto);
						ServletUtility.setSuccessMessage("Successfully Saved", request);
					} catch (ApplicationException e) {
						log.error(e);
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Schedular Job already exists", request);
					}

				}

				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Schedular Job already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			SchedulerJobDTO dto = (SchedulerJobDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.SCHEDULAR_JOB_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.SCHEDULAR_JOB_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.SCHEDULAR_JOB_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("SchedularJobCtl Method doPOst Ended");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SCHEDULAR_JOB_VIEW;
	}

}
