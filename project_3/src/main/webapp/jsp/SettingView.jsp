<%@page import="in.co.rays.project_3.controller.SettingCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.controller.StudentCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setting View</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	padding-bottom: 11px;
	background-color: #ebebe0;
}

.p4 {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/user1.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	padding-top: 75px;

	/* background-size: 100%; */
}
</style>
</head>
<body class="p4">
	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<div>
		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.SettingDTO"
			scope="request"></jsp:useBean>
		<main>
		<form action="<%=ORSView.SETTING_CTL%>" method="post">

			<div class="row pt-3 pb-3">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card">
						<div class="card-body">
							<%
							long id = DataUtility.getLong(request.getParameter("SettingId"));

							if (dto.getSettingId() != null) {
							%>
							<h3 class="text-center default-text text-primary">Update
								Setting</h3>
							<%
							} else {
							%>
							<h3 class="text-center default-text text-primary">Add
								Setting</h3>
							<%
							}
							%>
							<!--Body-->
							<div>


								<H4 align="center">
									<%
									if (!ServletUtility.getSuccessMessage(request).equals("")) {
									%>
									<div class="alert alert-success alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getSuccessMessage(request)%>
									</div>
									<%
									}
									%>
								</H4>

								<H4 align="center">
									<%
									if (!ServletUtility.getErrorMessage(request).equals("")) {
									%>
									<div class="alert alert-danger alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getErrorMessage(request)%>
									</div>
									<%
									}
									%>

								</H4>

								<input type="hidden" name="id" value="<%=dto.getSettingId()%>">
							</div>
							<%
							List li = (List) request.getAttribute("settingName");
							%>

							<span class="pl-sm-5"><b>Setting ID</b><span
								style="color: red;">*</span></span> </br>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-building grey-text" style="font-size: 1rem;"></i>
										</div>
									</div>
									<%=HTMLUtility.getList("settingId", String.valueOf(dto.getSettingId()), li)%>
								</div>
							</div>
							<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("settingId", request)%></font></br>

							<span class="pl-sm-5"><b>Setting Name</b> <span
								style="color: red;">*</span></span> </br>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-user-alt grey-text" style="font-size: 1rem;"></i>
										</div>
									</div>
									<input type="text" class="form-control" name="settingName"
										placeholder="Setting Name"
										value="<%=DataUtility.getStringData(dto.getSettingName())%>">
								</div>
							</div>
							<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("settingName", request)%></font></br>

							<span class="pl-sm-5"><b>Setting Key</b> <span
								style="color: red;">*</span></span></br>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-user-circle grey-text"
												style="font-size: 1rem;"></i>
										</div>
									</div>
									<input type="text" class="form-control" name="settingKey"
										placeholder="Setting Key"
										value="<%=DataUtility.getStringData(dto.getSettingKey())%>">
								</div>
							</div>
							<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></br>

							<span class="pl-sm-5"><b>Email Id</b><span
								style="color: red;">*</span></span> </br>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-envelope grey-text" style="font-size: 1rem;"></i>
										</div>
									</div>
									<input type="text" name="settingId" class="form-control"
										placeholder="Setting Id"
										value="<%=DataUtility.getStringData(dto.getSettingId())%>">
								</div>
							</div>
							<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("emailId", request)%></font></br>

							<span class="pl-sm-5"><b>Setting Value</b> <span
								style="color: red;">*</span></span> </br>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-phone-square grey-text"
												style="font-size: 1rem;"></i>
										</div>
									</div>
									<input type="text" class="form-control" name="settingValue"
										placeholder="Setting Value"
										value="<%=DataUtility.getStringData(dto.getSettingValue())%>">
								</div>
							</div>
							<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("settingValue", request)%></font></br>

							<span class="pl-sm-5"><b>settingType</b> <span
								style="color: red;">*</span></span></br>
							<div class="col-sm-12">
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fa fa-calendar grey-text" style="font-size: 1rem;"></i>
										</div>
									</div>
									<input type="text" name="settingType" class="form-control"
										placeholder="Setting Type"
										value="<%=DataUtility.getStringData(dto.getSettingType())%>">
								</div>
							</div>
							<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("settingType", request)%></font></br>
							<%
							if (id > 0) {
							%>
							<div class="text-center">

								<input type="submit" class="btn btn-success" name="operation"
									value="<%=SettingCtl.OP_UPDATE%>"> <input type="submit"
									class="btn btn-warning" name="operation"
									value="<%=SettingCtl.OP_CANCEL%>">

							</div>
							<%
							} else {
							%>
							<div class="text-center">

								<input type="submit" name="operation"
									class="btn btn-success btn-md" style="font-size: 17px"
									value="<%=SettingCtl.OP_SAVE%>"> <input type="submit"
									name="operation" class="btn btn-warning btn-md"
									style="font-size: 17px" value="<%=SettingCtl.OP_RESET%>">

							</div>
							<%
							}
							%>
						</div>
					</div>
				</div>

			</div>
			<div class="col-md-4 mb-4"></div>
	</div>

	</form>
	</main>


	</div>

</body>
<%@include file="FooterView.jsp"%>
</html>