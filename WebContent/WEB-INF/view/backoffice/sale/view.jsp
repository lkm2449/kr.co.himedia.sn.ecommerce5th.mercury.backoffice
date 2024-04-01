<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
	<script>
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

	<form id="frmMain" method="POST" action="/backoffice/sale/modifyForm.web">
	<input type="hidden" name="seq_sle" id="seq_sle" value="${saleDto.seq_sle}"/>
	<input type="hidden" name="cd_ctg" id="cd_ctg" value="${saleDto.cd_ctg}"/>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>상품 상세보기</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">상품 관리</a></li>
							<li class="breadcrumb-item active">상품 상세보기</li>
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
								<h3 class="card-title">상품 상세보기</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body table-responsive p-0">
								<table class="table table-head-fixed text-nowrap">
									<tr>
										<th width="15%">상품명</th>
										<td>
											${saleDto.sle_nm}
										</td>
									</tr>
									<tr>
										<th width="15%">제조사</th>
										<td>
											${saleDto.com_nm}
										</td>
									</tr>
									<tr>
										<th>카테고리</th>
										<td>
											<c:if test="${cd_ctg2 == 01}"> 사료
												<c:if test="${cd_ctg3 == 01}"> > 전연령용</c:if>
												<c:if test="${cd_ctg3 == 02}"> > 자견용</c:if>
												<c:if test="${cd_ctg3 == 03}"> > 성견용</c:if>
												<c:if test="${cd_ctg3 == 04}"> > 노견용</c:if>
												<c:if test="${cd_ctg3 == 05}"> > 분유</c:if>
											</c:if>
											<c:if test="${cd_ctg2 == 02}"> 산책/이동장
												<c:if test="${cd_ctg3 == 01}"> > 가슴줄/하네스</c:if>
												<c:if test="${cd_ctg3 == 02}"> > 리드줄</c:if>
												<c:if test="${cd_ctg3 == 03}"> > 산책용품</c:if>
												<c:if test="${cd_ctg3 == 04}"> > 이동가방</c:if>
												<c:if test="${cd_ctg3 == 05}"> > 켄넬/이동장</c:if>
												<c:if test="${cd_ctg3 == 06}"> > 유모차</c:if>
											</c:if>
											<c:if test="${cd_ctg2 == 03}"> 장난감
												<c:if test="${cd_ctg3 == 01}"> > 봉제</c:if>
												<c:if test="${cd_ctg3 == 02}"> > 공/원반</c:if>
												<c:if test="${cd_ctg3 == 03}"> > 라텍스</c:if>
												<c:if test="${cd_ctg3 == 04}"> > 치실/로프</c:if>
												<c:if test="${cd_ctg3 == 05}"> > 터그놀이</c:if>
												<c:if test="${cd_ctg3 == 06}"> > 노즈워크</c:if>
											</c:if>
										</td>
									</tr>
									<tr>
										<th>상품 이미지</th>
										<td>
											<img class="img-fluid" style="height: 200px" src="${saleDto.img}" alt="이미지">
										</td>
									</tr>
									<tr>
										<th>내용</th>
										<td>
											<div style="white-space:normal; width : 600px">${saleDto.desces}</div>
										</td>
									</tr>
									<tr>
										<th>가격</th>
										<td>
											${saleDto.price_sale}
										</td>
									</tr>
									<tr>
										<th>판매 상태</th>
											<td>
												<c:if test="${saleDto.cd_state_sale == 1}"> 판매중</c:if>
												<c:if test="${saleDto.cd_state_sale == 2}"> 중지</c:if>
											</td>
									</tr>
									<tr>
										<th>판매 기간</th>
										<td>
											${saleDto.dt_sale_start} ~ ${saleDto.dt_sale_end}
										</td>
									</tr>
									<tr>
										<th>등록일</th>
										<td>
											${saleDto.dt_reg}
										</td>
									</tr>
									<tr>
										<th>수정일</th>
										<td>
											${saleDto.dt_upt}
										</td>
									</tr>
								</table>
								<br />
								<div style="text-align:center">
									<input type="submit" value="수정" />
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