package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.DroneDeliveryDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.DroneDeliveryModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/DroneDeliveryCtl" })
public class DroneDeliveryCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DroneDeliveryCtl.class);

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("droneId"))) {
			request.setAttribute("droneId", PropertyReader.getValue("error.require", "droneId"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("deliveryZone"))) {
			request.setAttribute("deliveryZone", PropertyReader.getValue("error.require", "deliveryZone"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("droneId"))) {
			request.setAttribute("deliveryZone", "deliveryZone contain alphabets only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("payloadWeight"))) {
			request.setAttribute("payloadWeight", PropertyReader.getValue("error.require", "payloadWeight"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("flightStatus"))) {
			request.setAttribute("flightStatus", PropertyReader.getValue("error.require", "flightStatus"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("flightStatus"))) {
			request.setAttribute("flightStatus", "flightStatus must contain alphabets only");
			pass = false;
		}
		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		DroneDeliveryDTO dto = new DroneDeliveryDTO();
		dto.setDroneId(request.getParameter("droneId"));
		dto.setDeliveryZone(request.getParameter("deliveryZone"));
		dto.setPayloadWeight(DataUtility.getLong(request.getParameter("payloadWeight")));
		dto.setFlightStatus(request.getParameter("flightStatus"));

		populateBean(dto, request);
		return dto;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = request.getParameter("operation");
		long id = DataUtility.getLong(request.getParameter("id"));
		DroneDeliveryModelInt model = ModelFactory.getInstance().getDroneDeliveryModel();
		if (id > 0 || op != null) {
			DroneDeliveryDTO dto;
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

		DroneDeliveryModelInt model = ModelFactory.getInstance().getDroneDeliveryModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			DroneDeliveryDTO dto = (DroneDeliveryDTO) populateDTO(request);

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
				ServletUtility.setErrorMessage("DroneId Already Exists", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.DRONE_DELIVERY_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DRONE_DELIVERY_LIST_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.DRONE_DELIVERY_VIEW;
	}

}
