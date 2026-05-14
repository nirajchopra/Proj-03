<%@page import="in.co.rays.project_3.controller.SettingListCtl"%>
<%@page import="in.co.rays.project_3.model.SettingModelInt"%>
<%@page import="in.co.rays.project_3.dto.SettingDTO"%>
<%@page import="in.co.rays.project_3.dto.StudentDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.StudentListCtl"%>
<%@page import="in.co.rays.project_3.dto.CollegeDTO"%>
<%@page import="in.co.rays.project_3.model.ModelFactory"%>
<%@page import="in.co.rays.project_3.model.CollegeModelInt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Setting List View</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
<style>
.p1 {
	padding: 8px;
}

.p4 {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/list2.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed; 
	background-size: cover;
	padding-top: 85px;
	
	/* background-size: 100%; */
}
</style>
</head>
<body class="p4">
	<div>
		<%@include file="Header.jsp"%>
	</div>
	<div>
		<form action="<%=ORSView.SETTING_LIST_CTL%>" method="post">



			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.SettingDTO"
				scope="request"></jsp:useBean>
			<%
				List list1 = (List) request.getAttribute("settingName");
			%>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);

				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List list = ServletUtility.getList(request);

				SettingDTO sbean1 = new SettingDTO();
				SettingModelInt smodel = ModelFactory.getInstance().getSettingModel();
				Iterator<SettingDTO> it = list.iterator();
				if (list.size() != 0) {
			%>

			<center>
				<h1 class="text-primary font-weight-bold pt-3"><font color="black">Setting List</h1></font>
			</center>

			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>

				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">

				<div class="col-sm-2"></div>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="settingName"
						placeholder="Enter SettingName" class="p1"
						value="<%=ServletUtility.getParameter("settingName", request)%>">
				</div>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="settingType"
						placeholder="Enter SettingType"
						value="<%=ServletUtility.getParameter("settingType", request)%>">
				</div>
				<div class="col-sm-3"><%=HTMLUtility.getList("settingId", String.valueOf(dto.getSettingId()), list1)%></div>
				<div class="col-sm-2">
					<input type="submit" class="btn btn-primary btn-md"
						style="font-size: 17px" name="operation"
						value="<%=SettingListCtl.OP_SEARCH%>">&emsp; <input
						type="submit" class="btn btn-dark btn-md" style="font-size: 17px"
						name="operation" value="<%=SettingListCtl.OP_RESET%>">
				</div>
				<div class="col-sm-1"></div>
			</div>



			</br>
			<div style="margin-bottom: 20px;" class="table-responsive">
				<table class="table table-dark table-bordered table-hover">
					<thead>
						<tr style="background-color: #8C8C8C;">

							<th width="10%"><input type="checkbox" id="select_all"
								name="Select" class="text"> Select All</th>
							<th class="text">S.NO</th>
							<th class="text">Setting Name</th>
							<th class="text">Setting Key</th>
							<th class="text">Setting Value</th>
							<th class="text">Setting type</th>
							<th class="text">Description</th>
							<th class="text">Status</th>
							<th class="text">Edit</th>

						</tr>
					</thead>
					<%
						while (it.hasNext()) {
								dto = it.next();
								SettingDTO sbean = smodel.findByPK(dto.getId());
					%>

					<tbody>
						<tr>
							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=dto.getSettingId()%>"></td>
							<td align="center"><%=index++%></td>
							<td align="center"><%=dto.getSettingName()%></td>
							<td align="center"><%=dto.getSettingKey()%></td>
							<td align="center"><%=sbean.getSettingValue()%></td>
							<td align="center"><%=dto.getSettingType()%></td>
							<td align="center"><%=dto.getDescription()%></td>
							<td align="center"><%=dto.getStatus()%></td>

							<td align="center"><a href="SettingCtl?id=<%=dto.getSettingId()%>">Edit</a></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
			</div>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						value="<%=SettingListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td><input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=SettingListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						class="btn btn-danger btn-md" style="font-size: 17px"
						value="<%=SettingListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						style="padding: 5px;" value="<%=SettingListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
				<tr></tr>
			</table>

			</br>

			<%
				}
				if (list.size() == 0) {
					System.out.println("user list view list.size==0");
			%>
			<center>
				<h1 class="text-primary font-weight-bold pt-3">Setting List</h1>
			</center>
			</br> </br>
			<div style="padding-left: 48%;">
				<input type="submit" name="operation" class="btn btn-primary btn-md"
					style="font-size: 17px" value="<%=SettingListCtl.OP_BACK%>">
			</div>
			<%
				}
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">


		</form>

	</div>
	</br>
	</br>
	<%@include file="FooterView.jsp"%>
</body>

</html>