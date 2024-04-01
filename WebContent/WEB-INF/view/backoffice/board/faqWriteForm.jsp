<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
	<!-- Navbar -->
	<%@ include file="/include/backoffice/common/nav.jsp" %>
	<!-- /.navbar -->

	<!-- Main Sidebar Container -->
	<%@ include file="/include/backoffice/common/lnb.jsp" %>
	<!-- /.Main Sidebar Container -->

	<form method="POST" action="/backoffice/board/faqWriteProc.web" enctype="multipart/form-data">
	<input type="hidden" name="cd_bbs_type" value="${cd_bbs_type}" />
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
					<!-- /.col -->
					<div class="col-md-9" style="margin:0 auto">
						<div class="card card-primary card-outline">
							<div class="card-header">
								<h3 class="card-title">자주찾는질문 등록</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="form-group">
									<input class="form-control" name="title" placeholder="제목:">
									<input type="checkbox" name="flg_top" value="Y" /> 최상위
								</div>
								<div class="form-group">
										<textarea id="compose-textarea" name="contents" class="form-control" style="height: 300px">
											
										</textarea>
								</div>
							</div>
							<!-- /.card-body -->
							<div class="card-footer">
								<div class="float-right">
									<button type="submit" class="btn btn-primary">등록</button>
								</div>
							</div>
							<!-- /.card-footer -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
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