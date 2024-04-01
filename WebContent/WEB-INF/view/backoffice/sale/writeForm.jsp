<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/package/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/package/tinymce.js"></script>
	<script type="text/javascript" src="/js/backoffice/backoffice.js"></script>
	<script src="/plugins/jquery-ui/jquery-ui.js"></script>
	<script>
	
	// HTML Editor
	//tinymce.init({selector:'textarea'});
	
	var cd_ctg2 = "";
	var cd_ctg3 = "";
	
	function setCategory3(){
		
		var objStruct= new Array();
		
		// 사료(01)
		if (cd_ctg2 == "01") {
			objStruct.push({value:"01", text:"전연령용"});
			objStruct.push({value:"02", text:"자견용"});
			objStruct.push({value:"03", text:"성견용"});
			objStruct.push({value:"04", text:"노견용"});
			objStruct.push({value:"05", text:"분유"});
		}
		// 산책/이동장 (02)
		else if (cd_ctg2 == "02") {
			objStruct.push({value:"01", text:"가슴줄/하네스"});
			objStruct.push({value:"02", text:"리드줄"});
			objStruct.push({value:"03", text:"산책용품"});
			objStruct.push({value:"04", text:"이동가방"});
			objStruct.push({value:"05", text:"켄넬/이동장"});
			objStruct.push({value:"06", text:"유모차"});
		}
		// 장난감 (03)
		else if (cd_ctg2 == "03") {
			objStruct.push({value:"01", text:"봉제"});
			objStruct.push({value:"02", text:"공/원반"});
			objStruct.push({value:"03", text:"라텍스"});
			objStruct.push({value:"04", text:"치실/로프"});
			objStruct.push({value:"05", text:"터그놀이"});
			objStruct.push({value:"06", text:"노즈워크"});
		}
		
		// 대분류 코드가 없으면 중분류 초기화(있으면 대분류에 해당하는 중분류 설정)
		if (cd_ctg2 == "")	createOption("cd_ctg3", null, "선택", "");
		else						createOption("cd_ctg3", objStruct, "선택", cd_ctg3);
	}
	
	window.onload = function() {
		
		var objStruct = new Array();
		
		objStruct.push({value:"01", text:"사료"});
		objStruct.push({value:"02", text:"산책/이동장"});
		objStruct.push({value:"03", text:"장난감"});
		
		createSelect("span_cd_ctg2", "cd_ctg2", objStruct, "선택", cd_ctg2, 100);
		document.getElementById("cd_ctg2").onchange = function() {
			cd_ctg2 = this.value;
			cd_ctg_3 = "";
			setCategory3();
		}			
		
		createSelect("span_cd_ctg3", "cd_ctg3", null, "선택", "", 100);
		setCategory3();
		document.getElementById("cd_ctg3").onchange = function() {
			cd_ctg2 = getOptionValue(document.getElementById("cd_ctg2"));
			cd_ctg3 = this.value;
		}
	}
	
	
	$(function(){
		$('#datepicker').datepicker();
	})
	
	function writeProc() {
		
		if (getOptionValue(document.getElementById("cd_ctg2")) == "") {
			document.getElementById("cd_ctg2").focus();
			return;
		}
		
		if (getOptionValue(document.getElementById("cd_ctg3")) == "") {
			document.getElementById("cd_ctg3").focus();
			return;
		}
		
		// 강아지(01)로 고정
		document.getElementById("cd_ctg").value = "01"
											+ getOptionValue(document.getElementById("cd_ctg2"))
											+ getOptionValue(document.getElementById("cd_ctg3"));
		
		// 제출 전에 ","를 모두 제거
		document.getElementById("price_sale").value = document.getElementById("price_sale").value.replaceAll(",", "");
		
		var frmMain = document.getElementById("frmMain");
		frmMain.action = "/backoffice/sale/writeProc.web";
		frmMain.submit();
	}
	
	$( function() {
			
			from = $( "#start" )
			.datepicker({
			dateFormat: 'yy/mm/dd'
			, minDate:0,
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 3
			})
			.on( "change", function() {
				to.datepicker( "option", "minDate", getDate( this ) );
			}),
			to = $( "#end" ).datepicker({
			dateFormat: 'yy/mm/dd'
			, maxDate:365,
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 3
			})
			.on( "change", function() {
				from.datepicker( "option", "maxDate", getDate( this ) );
			});
		
		function getDate( element ) {
			var dateFormat = "yy/mm/dd"
			var date;
			try {
			date = $.datepicker.parseDate( dateFormat, element.value );
			} catch( error ) {
			date = null;
			}
			return date;
		}
	});
	
	$(function (){
		var today = new Date();
		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);
		
		var todayFormat = year + '/' + month  + '/' + day;
		
		document.getElementById('start').value = todayFormat;
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

	<form id="frmMain" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="cd_ctg" id="cd_ctg" />
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>상품 등록</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">상품 관리</a></li>
							<li class="breadcrumb-item active">상품 등록</li>
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
								<h3 class="card-title">상품 등록</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body table-responsive p-0">
								<table class="table table-head-fixed text-nowrap">
									<tr>
										<th width="15%">상품명</th>
										<td>
											<input type="text" id="sle_nm" name="sle_nm" style="width: 400px"/>
										</td>
									</tr>
									<tr>
										<th>제조사</th>
										<td>
											<input type="text" name="com_nm" id="com_nm"/>
										</td>
									</tr>
									<tr>
										<th>카테고리</th>
										<td>
											<span id="span_cd_ctg1"></span>
											<span id="span_cd_ctg2"></span>
											<span id="span_cd_ctg3"></span>
										</td>
									</tr>
									<tr>
										<th>상품 이미지</th>
										<td>
											<input type="file" name="fileName" />
										</td>
									</tr>
									<tr>
										<th>내용</th>
										<td>
											<div style="white-space:normal; width : 600px">
												<textarea name="desces" id="contents" style="width: 650px;height:200px;" maxlength="1000"></textarea>
											</div>
										</td>
									</tr>
									<tr>
										<th>가격</th>
										<td>
											<input type="text" id="price_sale" name="price_sale" style="width:100px; text-align:right" onkeyup="commaValue(this);" />
										</td>
									</tr>
									<tr>
										<th>판매 상태</th>
											<td>
												<select name="cd_state_sale" id="cd_state_sale" onchange = "state()" >
													<option value="1">판매중</option>
												</select>
											</td>
									</tr>
									<tr>
										<th>판매 기간</th>
										<td>
											<label for="start">시작일</label>
											<input type="text" id="start" name="dt_sale_start" disabled>
											<label for="end">종료일</label>
											<input type="text" id="end" name="dt_sale_end">
										</td>
									</tr>
								</table>
								<br />
								<div style="text-align:center">
									<input type="button" value="등록" onclick="javascript:writeProc();"/>
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