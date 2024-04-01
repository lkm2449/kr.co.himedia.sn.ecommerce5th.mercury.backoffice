<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
	<script>
		function changeState(seq_cst){
			if(confirm("정말로 회원 탈퇴를 승인하시겠습니까?")){
				var form = document.getElementById("form");
				document.getElementById("seq_cst").value = seq_cst;
				form.action="/backoffice/customer/withdrawalProc.web"
				form.submit();
			} else{
				var form = document.getElementById("form");
				form.action="/backoffice/customer/withdrawalForm.web"
				form.submit();
			}
		}
		
		function goPage(value) {
			
			var form = document.getElementById("form");
			
			form.currentPage.setAttribute("value", value);
			form.action = "/backoffice/customer/withdrawalForm.web";
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
	<input type="hidden" name="seq_cst" id="seq_cst" value="" />
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>탈퇴 회원 관리</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">회원 관리</a></li>
							<li class="breadcrumb-item active">탈퇴 회원 관리</li>
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
								<h3 class="card-title">탈퇴 회원 관리</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body table-responsive p-0">
								<table class="table table-hover text-nowrap">
									<thead>
										<tr>
											<th>번호</th>
											<th>회원명</th>
											<th>아이디</th>
											<th>이메일</th>
											<th>연락처</th>
											<th>상태</th>
											<th>수정일</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty list}">
												<tr><td colspan="6" style="text-align: center;">현재 회원 탈퇴를 신청한 회원이 없습니다</td></tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${list}" var="list" varStatus="status">
													<tr <c:if test="${list.tc_state == 3}">style="color:red"</c:if>>
														<td>${list.rnum }</td>
														<td><plutozoneUtilTag:masking text="${list.cst_nm}" letter="*" count="1" /></td>
														<td>${list.id }</td>
														<td>${list.cst_email }</td>
														<td>${list.phone }</td>
														<c:if test="${list.tc_state == 2}">
															<td>
																<select name="tc_state" onchange="changeState(${list.seq_cst });">
																	<option value="2" selected>탈퇴 대기</option>
																	<option value="3">탈퇴 승인</option>
																</select>
															</td>
														</c:if>
														<c:if test="${list.tc_state == 3}">
															<td>
																탈퇴 승인
															</td>
														</c:if>
														<td>${list.dt_upt }</td>
													
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