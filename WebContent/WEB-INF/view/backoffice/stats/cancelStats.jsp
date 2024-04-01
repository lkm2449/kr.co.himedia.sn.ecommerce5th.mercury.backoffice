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

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">취소/교환/환불 통계</h1>
					</div><!-- /.col -->
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">통계 분석</a></li>
							<li class="breadcrumb-item">취소/교환/환불 통계</li>
						</ol>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.container-fluid -->
		</div>
		<!-- /.content-header -->
		
		<form id="frmMain">
		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">최근 일주일간 취소/교환/환불 횟수</h3>
								<div class="card-tools">
									<button type="button" class="btn btn-tool" data-card-widget="collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<div class="position-relative mb-4">
									<canvas id="cancel-chart" height="200"></canvas>
								</div>
								<div class="d-flex flex-row justify-content-end">
									<span class="mr-2">
										<i class="fas fa-square" style="color:rgba(255,0,0,0.7)"></i> 취소/교환/환불
									</span>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						
					</div>
					
					<!-- /.col (LEFT) -->
					<div class="col-md-6">
						<!-- PIE CHART -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">취소/교환/환불 상품 TOP5</h3>

								<div class="card-tools">
									<button type="button" class="btn btn-tool" data-card-widget="collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<canvas id="pieChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						
					</div>
					<!-- /.col (RIGHT) -->
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

<script>
$(function () {
	/* ChartJS
	* -------
	* Here we will create a few charts using ChartJS
	*/
	var ticksStyle = {
		fontColor: '#495057',
		fontStyle: 'bold'
	}
	
	var mode = 'index'
	var intersect = true
	
	
	// 최근 일주일 날짜 구하기
	var today = Date.parse(new Date().toLocaleDateString())
	
	var result = []
	for (var i=0; i<7; i++) {
		today -= 86400000;
		
		var date = new Date(today);
		
		var date2 = leadingZeros(date.getMonth() + 1, 2) + '/' + leadingZeros(date.getDate(), 2); 
		
		result.push(date2)
	}
	result = result.reverse();
	
	// 배열 선언
	var cancelWeek = create2DArray(result.length, 2);
	
	// 배열 초기화
	for(var i=0; i<result.length; i++){
		cancelWeek[i][0] = result[i];
		cancelWeek[i][1] = 0;
		
		var loop_flag = false;
		<c:forEach items="${cancelWeek}" var="cancelWeek">
			if(!loop_flag){
				if(cancelWeek[i][0] === "${cancelWeek.dt_reg}"){
					cancelWeek[i][1] = ${cancelWeek.count};
					loop_flag = true;
		 		}
			}
		</c:forEach>
	}

	
	
	
	var $cancelChart = $('#cancel-chart')
		// eslint-disable-next-line no-unused-vars
	var cancelChart = new Chart($cancelChart, {
		data: {
			labels: result,
			datasets: [{
				type: 'line',
				data: [cancelWeek[0][1], cancelWeek[1][1], cancelWeek[2][1], cancelWeek[3][1], cancelWeek[4][1], cancelWeek[5][1], cancelWeek[6][1]],
				backgroundColor: 'transparent',
				borderColor: 'rgba(255,0,0,0.7)',
				pointBorderColor: 'rgba(255,0,0,0.7)',
				pointBackgroundColor: 'rgba(255,0,0,0.7)',
				fill: false
				// pointHoverBackgroundColor: '#007bff',
				// pointHoverBorderColor		: '#007bff'
			}]
		},
		options: {
			maintainAspectRatio: false,
			tooltips: {
				mode: mode,
				intersect: intersect
			},
			hover: {
				mode: mode,
				intersect: intersect
			},
			legend: {
				display: false
			},
			scales: {
				yAxes: [{
					// display: false,
					gridLines: {
						display: true,
						lineWidth: '4px',
						color: 'rgba(0, 0, 0, .2)',
						zeroLineColor: 'transparent'
					},
					ticks: $.extend({
						beginAtZero: true
					}, ticksStyle)
				}],
				xAxes: [{
					display: true,
					gridLines: {
						display: false
					},
					ticks: ticksStyle
				}]
			}
		}
	});
	

	
	// 배열 선언
	var cancelTop = create2DArray(${fn:length(cancelTop) }, 2);
	
	// 배열 초기화
	<c:forEach items="${cancelTop}" var="cancelTop" varStatus="stat">
		cancelTop[${stat.index}][0] = '${cancelTop.sle_nm}';
		cancelTop[${stat.index}][1] = ${cancelTop.count};
	</c:forEach>
	
	var label = [];
	for(var i=0; i<cancelTop.length; i++){
		label.push(cancelTop[i][0]);
	}
	
	var datas = [];
	for(var i=0; i<cancelTop.length; i++){
		datas.push(cancelTop[i][1]);
	}

	//-------------
	//- PIE CHART -
	//-------------
	
	var donutData = {
		labels: label,
		datasets: [
			{
				data: datas,
				backgroundColor : ['rgba(255, 99, 132, 0.8)', 'rgba(54, 162, 235, 0.8)', 'rgba(255, 206, 86, 0.8)', 'rgba(75, 192, 192, 0.8)', 'rgba(153, 102, 255, 0.8)'], 
			}
		]
	}
	
	
	// Get context with jQuery - using jQuery's .get() method.
	var pieChartCanvas = $('#pieChart').get(0).getContext('2d');
	var pieData				= donutData;
	var pieOptions		 = {
		maintainAspectRatio : false,
		responsive : true,
	}
	//Create pie or douhnut chart
	// You can switch between pie and douhnut using the method below.
	new Chart(pieChartCanvas, {
		type: 'pie',
		data: pieData,
		options: pieOptions
	});

})

function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString(); 

	if (n.length < digits) {
		for (i = 0; i < digits - n.length; i++)
			zero += '0';
	}

	return zero + n;
}

function create2DArray(rows, columns) {
	var arr = new Array(rows);
	for (var i = 0; i < rows; i++) {
		arr[i] = new Array(columns);
	}
	return arr;
}
</script>

</body>
</html>
