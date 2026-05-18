<%@page import="in.co.rays.project_3.controller.VehicleCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Vehicle View</title>

<style>
.p4 {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/user1.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	padding-top: 75px;
}
</style>
<script type="text/javascript">
$(function() {
    $("#datepicker4").datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: '1950:2030',   // auto-updates years
           // up to today's date
    });
});

$(function() {
    $("#datepicker10").datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: '1950:2030',   // auto-updates years
           // up to today's date
    });
});

</script>

</head>

<body class="p4">

	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp" %>
	</div>

	<main>
	<form action="<%=ORSView.VEHICLE_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.VehicleDTO"
			scope="request" />

		<div class="row pt-3 pb-4">
			<div class="col-md-4"></div>

			<div class="col-md-4">
				<div class="card">
					<div class="card-body">

						<%
							if (dto.getId() != null && dto.getId() > 0) {
						%>
						<h3 class="text-center text-primary">Update Vehicle</h3>
						<%
							} else {
						%>
						<h3 class="text-center text-primary">Add Vehicle</h3>
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

						<!-- Vehicle Number -->
						<b>Vehicle Number *</b>
						<input type="text" name="vehicleNumber"
							class="form-control" placeholder="Enter Vehicle Number "
							value="<%=DataUtility.getStringData(dto.getVehicleNumber())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("vehicleNumber", request)%>
						</font>
						<br>

						<!-- Owner Name -->
						<b>Owner Name *</b>
						<input type="text" name="ownerName"
							class="form-control" placeholder="Enter Owner Name "
							value="<%=DataUtility.getStringData(dto.getOwnerName())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("ownerName", request)%>
						</font>
						<br>

						<!-- Service Type -->
						<b>Service Type *</b>
						<input type="text" name="serviceType"
							class="form-control" placeholder="Enter Service Type"
							value="<%=DataUtility.getStringData(dto.getServiceType())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("serviceType", request)%>
						</font>
						<br>

						<!-- Service Date -->
						<b>Service Date *</b>
						<input type="text" name="serviceDate" id="datepicker4"
							class="form-control" placeholder="Enter Service Date"
							value="<%=DataUtility.getDateString(dto.getServiceDate())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("serviceDate", request)%>
						</font>
						<br>

						<!-- Mechanic Name -->
						<b>Mechanic Name *</b>
						<input type="text" name="mechanicName"
							class="form-control" placeholder="Enter Mechanic Name "
							value="<%=DataUtility.getStringData(dto.getMechanicName())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("mechanicName", request)%>
						</font>
						<br>

						<!-- Service Cost -->
						<b>Service Cost *</b>
						<input type="text" name="serviceCost"
							class="form-control" placeholder="Enter Service Cost "
							value="<%=DataUtility.getStringData(dto.getServiceCost())%>">
						<font color="red">
							<%=ServletUtility.getErrorMessage("serviceCost", request)%>
						</font>
						<br>

						<%-- <!-- Next Service Date -->
						<b>Next Service Date</b>
						<input type="text" name="nextServiceDate" id="datepicker4"
							class="form-control "
							value="<%=DataUtility.getDateString(dto.getNextServiceDate())%>">
						<br><br> --%>

						<%
							if (dto.getId() != null && dto.getId() > 0) {
						%>
						<div class="text-center">
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=VehicleCtl.OP_UPDATE%>">
							<input type="submit" name="operation" class="btn btn-warning"
								value="<%=VehicleCtl.OP_CANCEL%>">
						</div>
						<%
							} else {
						%>
						<div class="text-center">
							<input type="submit" name="operation" class="btn btn-success"
								value="<%=VehicleCtl.OP_SAVE%>">
							<input type="submit" name="operation" class="btn btn-warning"
								value="<%=VehicleCtl.OP_RESET%>">
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