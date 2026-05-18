package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.AnnouncementDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DatabaseException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.AnnouncementModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "AnnouncementListCtl", urlPatterns = "/ctl/AnnouncementListCtl")
public class AnnouncementListCtl extends BaseCtl {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pageNo = 1;
        int pageSize = DataUtility.getInt(
                request.getParameter("pageSize") == null ? "5" : request.getParameter("pageSize"));

        AnnouncementDTO dto = new AnnouncementDTO();

        AnnouncementModelInt model = ModelFactory.getInstance().getAnnouncementModel();

        try {
            List list = model.search(dto, pageNo, pageSize);
            List nextList = model.search(dto, pageNo + 1, pageSize);

            request.setAttribute("list", list);

            if (nextList == null || nextList.size() == 0) {
                request.setAttribute("nextListSize", 0);
            } else {
                request.setAttribute("nextListSize", nextList.size());
            }

            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageSize", pageSize);

        } catch (DatabaseException e) {
            e.printStackTrace();
            ServletUtility.handleExceptionDBDown(e, request, response, getView());
            return;
        }

        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? 5 : pageSize;

        String op = DataUtility.getString(request.getParameter("operation"));

        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setAnnouncementCode(DataUtility.getString(request.getParameter("announcementCode")));
        dto.setTitle(DataUtility.getString(request.getParameter("title")));

        AnnouncementModelInt model = ModelFactory.getInstance().getAnnouncementModel();

        if (OP_SEARCH.equalsIgnoreCase(op)) {
            pageNo = 1;

        } else if (OP_NEXT.equalsIgnoreCase(op)) {
            pageNo++;

        } else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
            pageNo--;

        } else if (OP_NEW.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.ANNOUNCEMENT_CTL, request, response);
            return;

        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.ANNOUNCEMENT_LIST_CTL, request, response);
            return;

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            String[] ids = request.getParameterValues("ids");

            if (ids != null) {

                for (String id : ids) {
                    AnnouncementDTO deleteDto = new AnnouncementDTO();
                    deleteDto.setId(DataUtility.getLong(id));

                    try {
                        model.delete(deleteDto);
                    } catch (DatabaseException e) {
                        e.printStackTrace();
                        ServletUtility.handleException(e, request, response);
                        return;
                    }
                }

                ServletUtility.setSuccessMessage("Announcement Deleted Successfully", request);

            } else {
                ServletUtility.setErrorMessage("Select at least one record", request);
            }
        }

        try {
            List list = model.search(dto, pageNo, pageSize);
            List nextList = model.search(dto, pageNo + 1, pageSize);

            request.setAttribute("list", list);

            if (nextList == null || nextList.size() == 0) {
                request.setAttribute("nextListSize", 0);
            } else {
                request.setAttribute("nextListSize", nextList.size());
            }

            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageSize", pageSize);

            ServletUtility.forward(getView(), request, response);

        } catch (ApplicationException e) {
            e.printStackTrace();
            ServletUtility.handleException(e, request, response);
        }
    }

    @Override
    protected String getView() {
        return ORSView.ANNOUNCEMENT_LIST_VIEW;
    }
}