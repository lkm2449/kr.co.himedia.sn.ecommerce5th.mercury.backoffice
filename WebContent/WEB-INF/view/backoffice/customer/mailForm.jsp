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

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>메일 일괄 발송</h1>
						<p>(메일 발송에 7~8초 가량 소모됩니다)</p>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">회원 관리</a></li>
							<li class="breadcrumb-item active">메일 일괄 발송</li>
						</ol>
					</div>
				</div>
			</div><!-- /.container-fluid -->
		</section>
		<form id="form" method="post" action="/backoffice/customer/mailProc.web">
		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="row">
					<!-- /.col -->
					<div class="col-md-9" style="margin:0 auto">
						<div class="card card-primary card-outline">
							<div class="card-header">
								<h3 class="card-title">메일 일괄 발송</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="form-group">
									<input class="form-control" name="subject" placeholder="제목:">
								</div>
								<div class="form-group">
										<textarea id="compose-textarea" name="message" class="form-control" style="height: 300px">
											
										</textarea>
								</div>
							</div>
							<!-- /.card-body -->
							<div class="card-footer">
								<div class="float-right">
									<button type="submit" class="btn btn-primary"><i class="far fa-envelope"></i>메일 일괄 발송</button>
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
		</form>
	</div>
	<!-- /.content-wrapper -->

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