<%@page import="in.co.rays.project_3.controller.SalaryCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Salary View</title>

<style>
.p4 {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/user1.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	padding-top: 75px;
}
</style>

</head>

<body class="p4">

	<div class="header">
		<%@include file="Header.jsp"%>
	</div>

	<main>
	<form action="<%=ORSView.SALARY_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.SalaryDTO"
			scope="request" />

		<div class="row pt-3 pb-4">
			<div class="col-md-4"></div>

			<div class="col-md-4">
				<div class="card">
					<div class="card-body">

						<%
							if (dto.getId() != null && dto.getId() > 0) {
						%>
						<h3 class="text-center text-primary">Update Salary</h3>
						<%
							} else {
						%>
						<h3 class="text-center text-primary">Add Salary</h3>
						<%
							}
						%>

						<!-- Success Message -->
						<%
							if (!ServletUtility.getSuccessMessage(request).equals("")) {
						%>
						<div class="alert alert-success">
							<%=ServletUtility.getSuccessMessage(request)%>
						</div>
						<%
							}
						%>

						<!-- Error Message -->
						<%
							if (!ServletUtility.getErrorMessage(request).equals("")) {
						%>
						<div class="alert alert-danger">
							<%=ServletUtility.getErrorMessage(request)%>
						</div>
						<%
							}
						%>

						<input type="hidden" name="id" value="<%=dto.getId()%>">

						<!-- Salary Code -->
						<b>Salary Code *</b>
						<input type="text" name="salaryCode"
							class="form-control" placeholder="Enter Salary Code"
							value="<%=DataUtility.getStringData(dto.getSalaryCode())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("salaryCode", request)%>
						</font>
						<br>

						<!-- Employee Name -->
						<b>Employee Name *</b>
						<input type="text" name="employeeName"
							class="form-control" placeholder="Enter Employee Name"
							value="<%=DataUtility.getStringData(dto.getEmployeeName())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("employeeName", request)%>
						</font>
						<br>

						<!-- Salary Amount -->
						<b>Salary Amount *</b>
						<input type="text" name="salaryAmount"
							class="form-control" placeholder="Enter Salary Amount"
							value="<%=DataUtility.getStringData(dto.getSalaryAmount())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("salaryAmount", request)%>
						</font>
						<br>

						<!-- Salary Status -->
						<b>Salary Status *</b>
						<input type="text" name="salaryStatus"
							class="form-control" placeholder="Enter Salary Status"
							value="<%=DataUtility.getStringData(dto.getSalaryStatus())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("salaryStatus", request)%>
						</font>
						<br>

						<%
							if (dto.getId() != null && dto.getId() > 0) {
						%>
						<div class="text-center">
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=SalaryCtl.OP_UPDATE%>">
							<input type="submit" name="operation" class="btn btn-warning"
								value="<%=SalaryCtl.OP_CANCEL%>">
						</div>
						<%
							} else {
						%>
						<div class="text-center">
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=SalaryCtl.OP_SAVE%>">
							<input type="submit" name="operation" class="btn btn-warning"
								value="<%=SalaryCtl.OP_RESET%>">
						</div>
						<%
							}
						%>

					</div>
				</div>
			</div>

			<div class="col-md-4"></div>
		</div>

	</form>
	</main>

	<%@include file="FooterView.jsp"%>

</body>
</html>