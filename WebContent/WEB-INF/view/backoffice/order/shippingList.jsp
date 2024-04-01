<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
	<script>		
		function goPage(value) {
			
			var form = document.getElementById("form");
			
			form.currentPage.setAttribute("value", value);
			form.action = "/backoffice/order/shipping.web";
			form.submit();
		}
		
		function updateShipping(seq_buy_mst){
			
			var cd_state_delivery = document.getElementById(seq_buy_mst).value;
			
			$.ajax({
				type:"post",
				async:false, 
				url:"/backoffice/order/updateShipping.json",
				contentType: "application/json; charset=utf-8",
				data:"{\"seq_buy_mst\":\"" + seq_buy_mst + "\", \"cd_state_delivery\":\"" + cd_state_delivery + "\"}",
				success:function(data, textStatus) {
					if (data == true) {
						alert("변경 성공");
					}
					else {
						alert("변경 실패");
					}
				},
 				error:function(data, textStatus) {
					alert("에러");
				}
			});	
			
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
						<h1>배송 관리</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">주문 관리</a></li>
							<li class="breadcrumb-item active">배송 관리</li>
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
								<h3 class="card-title">배송 관리</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body table-responsive p-0">
								<table class="table table-hover text-nowrap">
									<thead>
										<tr>
											<th>번호</th>
											<th>구매자명</th>
											<th>구매 정보</th>
											<th>구매 총 가격</th>
											<th>요청 사항</th>
											<th>배송 상태</th>
											<th>구매일</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty list}">
												<tr><td colspan="7" style="text-align: center;">현재 결제된 상품이 없습니다</td></tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${list}" var="list" varStatus="status">
													<tr>
														<td>${list.rnum }</td>
														<td><plutozoneUtilTag:masking text="${list.cst_nm}" letter="*" count="1" /></td>
														<td>${list.buy_info }</td>
														<td><fmt:formatNumber value="${list.buy_t_price}" type="number" /> 원</td>
														<td>
															<c:choose>
																<c:when test="${empty list.request }">
																	X
																</c:when>
																<c:otherwise>
																	${list.request }
																</c:otherwise>
															</c:choose>
														</td>
														<td>
															<select id="${list.seq_buy_mst }" name="cd_state_delivery" onchange="updateShipping(${list.seq_buy_mst })">
																<option value="N" <c:if test="${list.cd_state_delivery == 'N'}">selected</c:if>>배송전</option>
																<option value="D" <c:if test="${list.cd_state_delivery == 'D'}">selected</c:if>>배송중</option>
																<option value="Y" <c:if test="${list.cd_state_delivery == 'Y'}">selected</c:if>>배송완료</option>
															</select>
														</td>
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