package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.HospitalDTO;
import in.co.rays.project_3.dto.NetworkMonitorDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.NetworkMonitorModelInt;
import in.co.rays.project_3.model.HospitalModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "NetworkMonitorCtl", urlPatterns = "/ctl/NetworkMonitorCtl")
public class NetworkMonitorCtl extends BaseCtl {

    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;
        String op = request.getParameter("operation");

        if (OP_RESET.equalsIgnoreCase(op) || OP_CANCEL.equalsIgnoreCase(op)) {
            return pass;
        }

        if (DataValidator.isNull(request.getParameter("ipAddress"))) {
            request.setAttribute("ipAddress",
                    PropertyReader.getValue("error.require", "IP Address"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("bandwidth"))) {
            request.setAttribute("bandwidth",
                    PropertyReader.getValue("error.require", "Bandwidth"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("status"))) {
            request.setAttribute("status",
                    PropertyReader.getValue("error.require", "Status"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("uptime"))) {
            request.setAttribute("uptime",
                    PropertyReader.getValue("error.require", "Uptime"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {

        NetworkMonitorDTO dto = new NetworkMonitorDTO();

        dto.setId(DataUtility.getLong(request.getParameter("id")));
        dto.setIpAddress(DataUtility.getString(request.getParameter("ipAddress")));
        dto.setBandwidth(DataUtility.getLong(request.getParameter("bandwidth")));
        dto.setStatus(DataUtility.getString(request.getParameter("status")));
        dto.setUptime(DataUtility.getLong(request.getParameter("uptime")));

        populateBean(dto, request);

        return dto;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long id = DataUtility.getLong(req.getParameter("id"));
        NetworkMonitorModelInt model = ModelFactory.getInstance().getNetworkMonitorModel();

        if (id > 0) {
            try {
                NetworkMonitorDTO dto = model.findByPK(id);
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
        NetworkMonitorModelInt model = ModelFactory.getInstance().getNetworkMonitorModel();

        if (OP_SAVE.equalsIgnoreCase(op)) {

        	NetworkMonitorDTO bean = (NetworkMonitorDTO) populateDTO(req);

            try {
                model.add(bean);
                ServletUtility.setDto(bean, req);
                ServletUtility.setSuccessMessage("NetworkMonitor Added Successfully !!!", req);

            } catch (DuplicateRecordException dre) {
                ServletUtility.setDto(bean, req);
                ServletUtility.setErrorMessage("NetworkMonitor ID Already Exist !!!", req);

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

        	NetworkMonitorDTO dto = (NetworkMonitorDTO) populateDTO(req);

            try {
                model.update(dto);
                ServletUtility.setDto(dto, req);
                ServletUtility.setSuccessMessage("NetworkMonitor Updated Successfully !!!", req);

            } catch (DuplicateRecordException dre) {
                ServletUtility.setDto(dto, req);
                ServletUtility.setErrorMessage("NetworkMonitor ID Already Exist !!!", req);

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

            ServletUtility.redirect(ORSView.NETWORK_MONITOR_CTL, req, resp);
            return;

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.NETWORK_MONITOR_LIST_CTL, req, resp);
            return;
        }

        ServletUtility.forward(getView(), req, resp);
    }

    @Override
    protected String getView() {
        return ORSView.NETWORK_MONITOR_VIEW;
    }
}