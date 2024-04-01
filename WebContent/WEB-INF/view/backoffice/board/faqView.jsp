<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
	<script>
		function remove() {
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/backoffice/board/remove.web";
			frmMain.submit();
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

	<form id="frmMain" method="POST" action="/backoffice/board/faqModifyForm.web">
	<input type="hidden" name="cd_bbs_type"	value="${boardDto.cd_bbs_type}" />
	<input type="hidden" name="seq_bbs"		value="${boardDto.seq_bbs}" />
	<input type="hidden" name="file_save"	value="${boardDto.file_save}" />
	<input type="hidden" name="file_orig"	value="${boardDto.file_orig}" />
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>자주찾는질문</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">고객 지원</a></li>
							<li class="breadcrumb-item active">자주찾는질문</li>
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
								<h3 class="card-title">자주찾는질문</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body table-responsive p-0">
								<table class="table table-head-fixed text-nowrap">
									<tr>
										<th width="15%">제목</th>
										<td>
											${boardDto.title}
										</td>
									</tr>
									<tr>
										<th>내용</th>
										<td>
											${boardDto.contents}
										</td>
									</tr>
									<tr>
										<th>등록일</th>
										<td>
											${fn:substring(boardDto.dt_reg, 0, 10)}
										</td>
									</tr>
								</table>
								<br />
								<div style="text-align:center">
									<input type="submit" value="수정" />
									<a href="javascript:remove();" class="btnBasic"> 삭제 </a>
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