<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
	<style>
 		.form-control{  
 			display: inline-block; 
 		}
	</style>
	<script>	
		$(function() {
			$("#checkId").click(function() {
				
				var value = document.getElementById("id").value;
				
				if (value == null || value == "") {
					alert("아이디를 입력해주세요.");
					return;
				}
				
				$.ajax({
					type:"post",
					async:false, 
					url:"/backoffice/customer/exist.json",
					contentType: "application/json; charset=utf-8",
					data:"{\"id\":\"" + value + "\"}",
					success:function(data, textStatus) {
						if (data == true) {
							alert("중복된 아이디 입니다.");
						}
						else {
							alert("사용 가능한 아이디 입니다.");
							document.getElementById("checkIdResult").value = "true";
						}
					},
	 				error:function(data, textStatus) {
						alert("에러");
					}
				});	
			});
		});
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

	<form id="form" method="POST" encType="UTF-8">
	<input type="hidden" id="checkIdResult" value="false" />
	<input type="hidden" name="flg_sms" value="${flg_sms}" />
	<input type="hidden" name="flg_email" value="${flg_email}" />
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>회원 등록</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">회원 관리</a></li>
							<li class="breadcrumb-item active">회원 등록</li>
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
								<h3 class="card-title">회원 등록</h3>
							</div>
							
							<!-- /.card-header -->
							<div class="card-body table-responsive p-0">
								<table class="table table-head-fixed text-nowrap">
									<tr>
										<th width="15%"><label for="id">아이디</label></th>
										<td>
											<input type="text" id="id" name="id" class="form-control" style="width:20%; margin-right: 30px"/> <input type="button" value="중복 확인" id="checkId" />
										</td>
									</tr>
									<tr>
										<th><label for="passwd">비밀번호</label></th>
										<td>
											<input type="password" id="passwd" name="passwd" class="form-control" style="width:20%"/>
										</td>
									</tr>
									<tr>
										<th><label for="passwd_">비밀번호 재확인</label></th>
										<td>
											<input type="password" id="passwd_" name="passwd_" class="form-control" style="width:20%"/> <span id="checkPwd"></span>
										</td>
									</tr>
									<tr>
										<th><label for="cst_nm">성명</label></th>
										<td>
											<input type="text" id="cst_nm" name="cst_nm" class="form-control" style="width:20%"/>
										</td>
									</tr>
									<tr>
										<th><label for="phone">연락처</label></th>
										<td>
											<input type="text" id="phone" name="phone" class="form-control" style="width:20%" placeholder="-제외 입력"/>
										</td>
									</tr>
									<tr>
										<th><label for="postcode">우편번호</label></th>
										<td>
											<input type="text" id="postcode" name="postcode" size="5" class="form-control" style="width:20%"/>
										</td>
									</tr>
									<tr>
										<th><label for="addr1">도로명 주소</label></th>
										<td>
											<input type="text"		id="addr1"			name="addr1" size="40" class="form-control" style="width:50%"/>
											<input type="hidden"	id="roadAddr"		name="roadAddr" />
										</td>
									</tr>
									<tr>
										<th><label for="addr3">상세 주소</label></th>
										<td>
											<input type="text"		id="addr3"			name="addr3" size="20" class="form-control" style="width:20%; margin-right: 30px">
											<input type="hidden"	id="extraAddress"	name="extraAddress" />
											
											<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
										</td>
									</tr>
									<tr>
										<th><label for="cst_email">이메일</label></th>
										<td>
											<input type="text" id="cst_email" name="cst_email" class="form-control" style="width:50%"/>
										</td>
									</tr>
								</table>
								<br />
							</div>
							<!-- /.card-body -->
							<div class="card-footer">
								<div class="float-right">
									<button type="button" class="btn btn-primary" onclick="register()">회원등록</button>
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