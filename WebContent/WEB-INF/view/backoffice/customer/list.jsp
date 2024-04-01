<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
	<script>		
		function goPage(value) {
			
			var form = document.getElementById("form");
			
			form.currentPage.setAttribute("value", value);
			form.action = "/backoffice/customer/list.web";
			form.submit();
		}
	</script>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
	<!-- Navbar -->
	<%@ include file="/include/backoffice/common/nav.jsp" %>
	<!-- /.navbar -->

	<!-- Main Sidebar Container -->
	<%@ include file="/include/backoffice/common/lnb.jsp" %>
	<!-- /.Main Sidebar Container -->
	
	<form id="form" method="POST" >
	<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>회원 목록</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">회원 관리</a></li>
							<li class="breadcrumb-item active">회원 목록</li>
						</ol>
					</div>
				</div>
			</div><!-- /.container-fluid -->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="row">
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">회원 목록</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body table-responsive p-0">
								<table class="table table-hover text-nowrap">
									<thead>
										<tr>
											<th>번호</th>
											<th>아이디</th>
											<th>회원명</th>
											<th>이메일</th>
											<th>연락처</th>
											<th>SMS 수신 동의</th>
											<th>이메일 수신 동의</th>
											<th>가입일</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty list}">
												<tr><td colspan="6" style="text-align: center;">현재 회원 탈퇴를 신청한 회원이 없습니다</td></tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${list}" var="list" varStatus="status">
													<tr>
														<td>${list.rnum }</td>
														<td>${list.id }</td>
														<td><plutozoneUtilTag:masking text="${list.cst_nm}" letter="*" count="1" /></td>
														<td>${list.cst_email }</td>
														<td>${list.phone }</td>
														<td>${list.flg_sms }</td>
														<td>${list.flg_email }</td>
														<td>${list.dt_reg }</td>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								<br />
								<div style="text-align:center">
									<plutozoneUtilTag:page styleID="admin_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
				</div>
			</div><!-- /.container-fluid -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	</form>

	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Control sidebar content goes here -->
	</aside>
	<!-- /.control-sidebar -->
	<%@ include file="/include/backoffice/common/footer.jsp" %>
	<!-- Main Footer -->

</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<%@ include file="/include/backoffice/common/js.jsp" %>
</body>
</html>	