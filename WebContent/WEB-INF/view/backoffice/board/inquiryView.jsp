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

	<form method="POST" action="/backoffice/reply/replyProc.web">
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
						<h1>공지 사항</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">고객 지원</a></li>
							<li class="breadcrumb-item active">공지 사항</li>
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
								<h3 class="card-title">공지사항</h3>
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
										<th>분류</th>
										<td>
										${boardDto.cd_bbs_tab}
										</td>
									</tr>
									<tr>
										<th>내용</th>
										<td>
											${boardDto.contents}
										</td>
									</tr>
									<c:if test="${boardDto.file_save != null}">
										<tr>
											<th>이미지</th>
											<td>
												<img id="img" src="/image/${boardDto.file_save}" height="250px" alt="이미지" style="cursor: pointer" onclick="window.open(this.src)" />
											</td>
										</tr>
									</c:if>
									<tr>
										<th>등록일</th>
										<td>
											${fn:substring(boardDto.dt_reg, 0, 10)}
										</td>
									</tr>
								</table>
								<br />
							</div>
								
							<div id="comment">
								<c:if test="${not empty replyDto.contents}">
									<table class="table table-head-fixed text-nowrap">		
										<tr>
											<th width="15%">작성일</th>
											<td>
												<div>
													${fn:substring(replyDto.dt_reg, 0, 10)}
												</div>
											</td>
										</tr>	
										<tr>
											<th width="15%">답글 내용</th>
											<td>
												<div>
													${replyDto.contents}
												</div>
											</td>
										</tr>
									</table>
								</c:if>
								<br />
								
								<c:if test="${empty replyDto.contents}">	
									<table class="table table-head-fixed text-nowrap">	
										<tr>
											<td>
												<div>
													<textarea name="contents" rows="4" cols="70" ></textarea>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div>
													<input type="submit" value="등록" />
												</div>
											</td>
										</tr>
									</table>
								</c:if>
							</div>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
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