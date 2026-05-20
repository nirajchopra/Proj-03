<%@page import="in.co.rays.project_3.controller.NetworkMonitorListCtl"%>
<%@page import="in.co.rays.project_3.dto.NetworkMonitorDTO"%>
<%@page import="in.co.rays.project_3.controller.HospitalListCtl"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.dto.HospitalDTO"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>

<html>
<head>
<title>NetworkMonitor List</title>

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

		<jsp:useBean id="dto" class="in.co.rays.project_3.dto.NetworkMonitorDTO"
			scope="request"></jsp:useBean>

		<h2 class="text-center text-light font-weight-bold">NetworkMonitor List</h2>

		<form action="<%=ORSView.NETWORK_MONITOR_LIST_CTL%>" method="post">

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List<NetworkMonitorDTO> list = (List<NetworkMonitorDTO>) ServletUtility.getList(request);
				List<NetworkMonitorDTO> ipAddress = (List<NetworkMonitorDTO>) request.getAttribute("ipAddress");

				Iterator<NetworkMonitorDTO> it = list.iterator();
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

								<!-- Hospital ID -->
								<div class="mx-2">
									<label><b>IP Addess :</b></label>
								</div>
								<div class="mx-2">
									<input type="text" class="form-control form-control-sm"
										name="ipAddress"
										value="<%=ServletUtility.getParameter("ipAddress", request)%>">
								</div>

								<!-- Hospital Name (Dynamic Preload) -->
								<div class="mx-2">
									<label><b>Bandwidth :</b></label>
								</div>
								<div class="mx-2">
									<select name="bandwidth"
										class="form-control form-control-sm">
										<option value="">--Select--</option>

										<%
											if (ipAddress != null) {
												for (NetworkMonitorDTO h : ipAddress) {
										%>
										<option value="<%=h.getIpAddress()%>"
											<%=h.getIpAddress().equals(ServletUtility.getParameter("ipAddress", request))
							? "selected"
							: ""%>>
											<%=h.getIpAddress()%>
										</option>
										<%
											}
											}
										%>
									</select>
								</div>

								<!-- City -->
								<div class="mx-2">
									<label><b>Status :</b></label>
								</div>
								<div class="mx-2">
									<input type="text" class="form-control form-control-sm"
										name="status"
										value="<%=ServletUtility.getParameter("status", request)%>">
								</div>

								<!-- Buttons -->
								<div class="mx-2">
									<input type="submit" class="btn btn-sm btn-primary"
										name="operation" value="<%=NetworkMonitorListCtl.OP_SEARCH%>">

									<input type="submit"
										class="btn btn-sm btn-outline-secondary ml-1" name="operation"
										value="<%=NetworkMonitorListCtl.OP_RESET%>">
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
							<th>IpAddress</th>
							<th>Bandwidth</th>
							<th>Status</th>
							<th>Uptime</th>
							<th>Edit</th>
						</tr>
					</thead>

					<tbody>
						<%
							while (it.hasNext()) {
								dto = it.next();
						%>
						<tr>
							<td><input type="checkbox" name="ids"
								value="<%=dto.getId()%>"></td>
							<td><%=index++%></td>
							<td><%=dto.getIpAddress()%></td>
							<td><%=dto.getBandwidth()%></td>
							<td><%=dto.getStatus()%></td>
							<td><%=dto.getUptime()%></td>
							<td><a href="HospitalCtl?id=<%=dto.getId()%>"
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
						value="<%=NetworkMonitorListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>

					<td width="25%" class="text-center"><input type="submit"
						class="btn btn-outline-success" name="operation"
						value="<%=NetworkMonitorListCtl.OP_NEW%>"></td>

					<td width="25%" class="text-center"><input type="submit"
						class="btn btn-outline-danger" name="operation"
						value="<%=NetworkMonitorListCtl.OP_DELETE%>"></td>

					<td width="25%" class="text-right"><input type="submit"
						class="btn btn-outline-primary" name="operation"
						value="<%=NetworkMonitorListCtl.OP_NEXT%>"
						<%=nextPageSize != 0 ? "" : "disabled"%>></td>

				</tr>
			</table>

		</form>
	</div>

	<%@include file="FooterView.jsp"%>

</body>
</html>