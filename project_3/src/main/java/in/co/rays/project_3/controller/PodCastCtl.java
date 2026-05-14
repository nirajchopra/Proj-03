package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.PodCastDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.PodCastModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

public class PodCastCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CollegeCtl.class);

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("podcastId"))) {
			request.setAttribute("podcastId", PropertyReader.getValue("error.require", "podcastId"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("podcastCode"))) {
			request.setAttribute("podcastCode", "podcastCode must contain alphabets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("podcastTitle"))) {
			request.setAttribute("podcastTitle", PropertyReader.getValue("error.require", "podcastTitle"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("hostName"))) {
			request.setAttribute("hostName", PropertyReader.getValue("error.require", "hostName"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("status"))) {
			request.setAttribute("status", "status must contain alphabets only");
			pass = false;
		}
		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		PodCastDTO dto = new PodCastDTO();
		dto.setPodcastId(DataUtility.getLong(request.getParameter("podcastId")));
		dto.setPodcastCode(request.getParameter("podcastCode"));
		dto.setPodcastTitle(request.getParameter("podcastTitle"));
		dto.setHostName(request.getParameter("hostName"));
		dto.setStatus(request.getParameter("status"));

		populateBean(dto, request);
		return dto;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = request.getParameter("operation");
		long id = DataUtility.getLong(request.getParameter("id"));
		PodCastModelInt model = ModelFactory.getInstance().getPodCastModel();
		if (id > 0 || op != null) {
			PodCastDTO dto;
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = request.getParameter("operation");
		long id = DataUtility.getLong(request.getParameter("id"));

		PodCastModelInt model = ModelFactory.getInstance().getPodCastModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			PodCastDTO dto = (PodCastDTO) populateDTO(request);

			try {
				if (id > 0) {
					dto.setId(id);
					model.update(dto);
					ServletUtility.setDto(dto, request);

					ServletUtility.setSuccessMessage("Record Successfully Updated", request);

				} else {
					System.out.println("college add" + dto + "id...." + id);
					// long pk
					model.add(dto);
					ServletUtility.setSuccessMessage("Record Successfully Saved", request);
				}
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("ProductName Already Exists", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PODCAST_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PODCAST_LIST_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PODCAST_VIEW;
	}

}
