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
						<h1 class="m-0">WEB/APP 통계</h1>
					</div><!-- /.col -->
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">통계 분석</a></li>
							<li class="breadcrumb-item">WEB/APP 통계</li>
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
								<h3 class="card-title">최근 일주일간 판매 현황</h3>

								<div class="card-tools">
									<button type="button" class="btn btn-tool" data-card-widget="collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<div class="position-relative mb-4">
									<canvas id="purchase-chart" height="200"></canvas>
								</div>
								<div class="d-flex flex-row justify-content-end">
									<span class="mr-2">
										<i class="fas fa-square" style="color:#00a65a"></i> Web
									</span>
									<span>
										<i class="fas fa-square" style="color:#f56954"></i> App
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
								<h3 class="card-title">총 판매 현황</h3>

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
		var listWeb = create2DArray(result.length, 2);
		
		// 배열 초기화
		for(var i=0; i<result.length; i++){
			listWeb[i][0] = result[i];
			listWeb[i][1] = 0;
			
			var loop_flag = false;
			<c:forEach items="${countWeekWeb}" var="countWeekWeb">
				if(!loop_flag){
					if(listWeb[i][0] === "${countWeekWeb.dt_reg}"){
						listWeb[i][1] = ${countWeekWeb.count};
						loop_flag = true;
			 		}
				}
			</c:forEach>
		}
		
		// 배열 선언
		var listApp = create2DArray(result.length, 2);
		
		// 배열 초기화
		for(var i=0; i<result.length; i++){
			listApp[i][0] = result[i];
			listApp[i][1] = 0;
			
			var loop_flag = false;
			<c:forEach items="${countWeekApp}" var="countWeekApp">
				if(!loop_flag){
					if(listApp[i][0] === "${countWeekApp.dt_reg}"){
						listApp[i][1] = ${countWeekApp.count};
						loop_flag = true;
			 		}
				}
			</c:forEach>
		}
		
		
		
		var $purchaseChart = $('#purchase-chart')
			// eslint-disable-next-line no-unused-vars
		var purchaseChart = new Chart($purchaseChart, {
			data: {
				labels: result,
				datasets: [{
					type: 'line',
					data: [listWeb[0][1], listWeb[1][1], listWeb[2][1], listWeb[3][1], listWeb[4][1], listWeb[5][1], listWeb[6][1]],
					backgroundColor: 'transparent',
					borderColor: '#00a65a',
					pointBorderColor: '#00a65a',
					pointBackgroundColor: '#00a65a',
					fill: false
					// pointHoverBackgroundColor: '#007bff',
					// pointHoverBorderColor		: '#007bff'
				},
				{
					type: 'line',
					data: [listApp[0][1], listApp[1][1], listApp[2][1], listApp[3][1], listApp[4][1], listApp[5][1], listApp[6][1]],
					backgroundColor: 'tansparent',
					borderColor: '#f56954',
					pointBorderColor: '#f56954',
					pointBackgroundColor: '#f56954',
					fill: false
					// pointHoverBackgroundColor: '#ced4da',
					// pointHoverBorderColor		: '#ced4da'
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
		
		//-------------
		//- PIE CHART -
		//-------------
		
		var donutData = {
			labels: [
					'Web',
					'App',
			],
			datasets: [
				{
					data: [${countWeb}, ${countApp}],
					backgroundColor : ['#00a65a', '#f56954'],
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
