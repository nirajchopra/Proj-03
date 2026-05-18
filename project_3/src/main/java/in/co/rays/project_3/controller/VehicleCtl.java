package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.VehicleDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.VehicleModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "VehicleCtl", urlPatterns = "/ctl/VehicleCtl")
public class VehicleCtl extends BaseCtl {

    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;
        String op = request.getParameter("operation");

        if (OP_RESET.equalsIgnoreCase(op) || OP_CANCEL.equalsIgnoreCase(op)) {
            return pass;
        }

        if (DataValidator.isNull(request.getParameter("vehicleNumber"))) {
            request.setAttribute("vehicleNumber",
                    PropertyReader.getValue("error.require", "Vehicle Number"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("ownerName"))) {
            request.setAttribute("ownerName",
                    PropertyReader.getValue("error.require", "Owner Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("serviceType"))) {
            request.setAttribute("serviceType",
                    PropertyReader.getValue("error.require", "Service Type"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("serviceDate"))) {
            request.setAttribute("serviceDate",
                    PropertyReader.getValue("error.require", "Service Date"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("mechanicName"))) {
            request.setAttribute("mechanicName",
                    PropertyReader.getValue("error.require", "Mechanic Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("serviceCost"))) {
            request.setAttribute("serviceCost",
                    PropertyReader.getValue("error.require", "Service Cost"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {

        VehicleDTO dto = new VehicleDTO();

        dto.setId(DataUtility.getLong(request.getParameter("id")));
        dto.setVehicleNumber(DataUtility.getString(request.getParameter("vehicleNumber")));
        dto.setOwnerName(DataUtility.getString(request.getParameter("ownerName")));
        dto.setServiceType(DataUtility.getString(request.getParameter("serviceType")));
        dto.setServiceDate(DataUtility.getDate(request.getParameter("serviceDate")));
        dto.setMechanicName(DataUtility.getString(request.getParameter("mechanicName")));
        dto.setServiceCost(DataUtility.getString(request.getParameter("serviceCost")));
        dto.setNextServiceDate(DataUtility.getDate(request.getParameter("nextServiceDate")));

        populateBean(dto, request);

        return dto;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long id = DataUtility.getLong(req.getParameter("id"));
        VehicleModelInt model = ModelFactory.getInstance().getVehicleModel();

        if (id > 0) {
            try {
                VehicleDTO dto = model.findByPK(id);
                ServletUtility.setDto(dto, req);

            } catch (DatabaseException e) {
                e.printStackTrace();
                ServletUtility.handleExceptionDBDown(e, req, resp, getView());
                return;

            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, req, resp);
                return;
            }
        }

        ServletUtility.forward(getView(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String op = DataUtility.getString(req.getParameter("operation"));
        VehicleModelInt model = ModelFactory.getInstance().getVehicleModel();

        if (OP_SAVE.equalsIgnoreCase(op)) {

            VehicleDTO bean = (VehicleDTO) populateDTO(req);

            try {
                model.add(bean);
                ServletUtility.setDto(bean, req);
                ServletUtility.setSuccessMessage("Vehicle Added Successfully !!!", req);

            } catch (DuplicateRecordException dre) {
                ServletUtility.setDto(bean, req);
                ServletUtility.setErrorMessage("Vehicle Number Already Exist !!!", req);

            } catch (DatabaseException de) {
                de.printStackTrace();
                ServletUtility.handleExceptionDBDown(de, req, resp, getView());
                return;

            } catch (ApplicationException ae) {
                ae.printStackTrace();
                ServletUtility.handleException(ae, req, resp);
                return;
            }

        } else if (OP_UPDATE.equalsIgnoreCase(op)) {

            VehicleDTO dto = (VehicleDTO) populateDTO(req);

            try {
                model.update(dto);
                ServletUtility.setDto(dto, req);
                ServletUtility.setSuccessMessage("Vehicle Updated Successfully !!!", req);

            } catch (DuplicateRecordException dre) {
                ServletUtility.setDto(dto, req);
                ServletUtility.setErrorMessage("Vehicle Number Already Exist !!!", req);

            } catch (DatabaseException de) {
                de.printStackTrace();
                ServletUtility.handleException(de, req, resp);
                return;

            } catch (ApplicationException ae) {
                ae.printStackTrace();
                ServletUtility.handleException(ae, req, resp);
                return;
            }

        } else if (OP_RESET.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.VEHICLE_CTL, req, resp);
            return;

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.VEHICLE_LIST_CTL, req, resp);
            return;
        }

        ServletUtility.forward(getView(), req, resp);
    }

    @Override
    protected String getView() {
        return ORSView.VEHICLE_VIEW;
    }
}