<%@page import="in.co.rays.project_3.controller.PlacementListCtl"%>
<%@page import="in.co.rays.project_3.controller.VehicleListCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.dto.VehicleDTO"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<html>
<head>
<title>Vehicle List</title>

<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

<style>
.p4 {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/Linkme.jpg');
	background-size: cover;
	background-position: center;
	background-attachment: fixed;
	min-height: 100vh;
	padding-top: 70px;
	padding-bottom: 80px;
}
</style>
</head>

<body class="p4">

	<%@include file="Header.jsp"%>

	<div class="container-fluid">

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.VehicleDTO"
			scope="request"></jsp:useBean>

		<h2 class="text-center text-light font-weight-bold">Vehicle List</h2>

		<form action="<%=ORSView.VEHICLE_LIST_CTL%>" method="post">

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List<VehicleDTO> list = (List<VehicleDTO>) ServletUtility.getList(request);
				List<VehicleDTO> serviceTypeList = (List<VehicleDTO>) request.getAttribute("serviceTypeList");

				Iterator<VehicleDTO> it = list.iterator();
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

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

			<!-- SEARCH FILTER -->
			<div class="table-responsive">
				<table
					class="table table-borderless w-100 text-center bg-light rounded shadow-sm">
					<tr>
						<td>
							<div
								class="d-flex justify-content-center align-items-center flex-wrap p-3">

								<!-- Vehicle Number -->
								<div class="mx-2">
									<label><b>Vehicle Number :</b></label>
								</div>
								<div class="mx-2">
									<input type="text" class="form-control form-control-sm"
										name="vehicleNumber" placeholder="Enter vehicle Number"
										value="<%=ServletUtility.getParameter("vehicleNumber", request)%>">
								</div>

								<!-- Owner Name -->
								<div class="mx-2">
									<label><b>Owner Name :</b></label>
								</div>
								<div class="mx-2">
									<input type="text" class="form-control form-control-sm"
										name="ownerName" placeholder="Enter Owner Name"
										value="<%=ServletUtility.getParameter("ownerName", request)%>">
								</div>

								<!-- Service Type (Preload Dropdown) -->
								<div class="mx-2">
									<label><b>Service Type :</b></label>
								</div>
								<div class="mx-2">
									<select name="serviceType" class="form-control form-control-sm">
										<option value="">--Select--</option>

										<%
											if (serviceTypeList != null) {
												for (VehicleDTO v : serviceTypeList) {
										%>
										<option value="<%=v.getServiceType()%>"
											<%=v.getServiceType().equals(ServletUtility.getParameter("serviceType", request)) ? "selected"
									: ""%>>
											<%=v.getServiceType()%>
										</option>
										<%
											}
											}
										%>
									</select>
								</div>

								<!-- Buttons -->
								<div class="mx-2">
									<input type="submit" class="btn btn-sm btn-primary"
										name="operation" value="<%=VehicleListCtl.OP_SEARCH%>">

									<input type="submit"
										class="btn btn-sm btn-outline-secondary ml-1" name="operation"
										value="<%=VehicleListCtl.OP_RESET%>">
								</div>

							</div>
						</td>
					</tr>
				</table>
			</div>

			<br>

			<!-- DATA TABLE -->
			<div class="table-responsive">
				<table
					class="table table-bordered table-hover w-100 text-center bg-white shadow-sm">

					<thead class="thead-light">
						<tr>
							<th><input type="checkbox" id="select_all"> Select
								All</th>
							<th>S.No</th>
							<th>Vehicle Number</th>
							<th>Owner Name</th>
							<th>Service Type</th>
							<th>Service Date</th>
							<th>Service Cost</th>
							<th>Edit</th>
						</tr>
					</thead>

					<tbody>
						<%
							while (it.hasNext()) {
								dto = it.next();
						%>
						<tr>
							<td><input type="checkbox" class="checkbox" name="ids"
								value="<%=dto.getId()%>"></td>
							<td><%=index++%></td>
							<td><%=dto.getVehicleNumber()%></td>
							<td><%=dto.getOwnerName()%></td>
							<td><%=dto.getServiceType()%></td>
							<td><%=DataUtility.getDateString(dto.getServiceDate())%></td>
							<td><%=dto.getServiceCost()%></td>
							<td><a href="VehicleCtl?id=<%=dto.getId()%>"
								class="btn btn-link btn-sm p-0">Edit</a></td>
						</tr>
						<%
							}
						%>
					</tbody>

				</table>
			</div>

			<!-- PAGINATION -->
			<table class="table w-100">
				<tr>

					<td width="25%"><input type="submit"
						class="btn btn-outline-primary" name="operation"
						value="<%=VehicleListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>

					<td width="25%" class="text-center"><input type="submit"
						class="btn btn-outline-success" name="operation"
						value="<%=VehicleListCtl.OP_NEW%>"></td>

					<td width="25%" class="text-center"><input type="submit"
						class="btn btn-outline-danger" name="operation"
						value="<%=VehicleListCtl.OP_DELETE%>"></td>

					<td width="25%" class="text-right"><input type="submit"
						class="btn btn-outline-primary" name="operation"
						value="<%=VehicleListCtl.OP_NEXT%>"
						<%=nextPageSize != 0 ? "" : "disabled"%>></td>

				</tr>
			</table>
			
			<%
			

				if (list.size() == 0) {
			%>

			<table class="table w-100">
				<tr>
					<td class="text-right"><input type="submit"
						class="btn btn-warning" name="operation"
						value="<%=VehicleListCtl.OP_BACK%>"></td>
				</tr>
			</table>

			<%
				}
			%>
			

		</form>
	</div>

	<%@include file="FooterView.jsp"%>

</body>
</html>