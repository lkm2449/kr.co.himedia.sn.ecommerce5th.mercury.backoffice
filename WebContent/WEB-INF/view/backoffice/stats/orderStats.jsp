<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/backoffice/common/header.jsp" %>
	<script>
		function selectmonth(){
			var frmMain = document.getElementById("frmMain");
			frmMain.method="POST";
			frmMain.action="/backoffice/stats/orderStats.web";
			
			frmMain.submit();
		}
		
		function selectyear(){
			var frmMain = document.getElementById("frmMain");
			frmMain.method="POST";
			frmMain.action="/backoffice/stats/orderStats.web";
			
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

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) month -->
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">주문 통계</h1>
					</div><!-- /.col -->
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">통계 분석</a></li>
							<li class="breadcrumb-item">주문 통계</li>
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
								<h3 class="card-title">표</h3>
								
								<div class="card-tools">
									<select name="month" onchange="selectmonth()">
										<c:forEach items="${month}" var="month">
											<option value="${month }" <c:if test="${myMonth == month}"> selected</c:if>>${month }월</option>
										</c:forEach>
									</select>
									<button type="button" class="btn btn-tool" data-card-widget="collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body table-responsive p-0">
								<table class="table table-hover text-nowrap">
									<thead>
										<tr>
											<th>일</th>
											<th>구매 수량</th>
											<th>총 판매금액</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty list2}">
												<tr>
													<td colspan="3" style="text-align: center;">구매한 상품이 없습니다.</td>
												</tr>
											</c:when>
											<c:otherwise>
											<c:forEach items="${list2}" var="list2">
												<tr>
													<td>
														${list2.dt_reg } 일
													</td>
													<td>
														${list2.count } 개
													</td>
													<td>
														${list2.price } 원
													</td>
												</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
							<!-- /.card-body -->
						</div>
						
					</div>
					<!-- /.col (LEFT) -->
					<div class="col-md-6">
						
						<!-- BAR CHART -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">그래프</h3>

								<div class="card-tools">
									<button type="button" class="btn btn-tool" data-card-widget="collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<div class="chart">
									<canvas id="barChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
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
		
		 var list = [
				["MONTH", "월 주문 수량"]
				, ["01월", 0]
				, ["02월", 0]
				, ["03월", 0]
				, ["04월", 0]
				, ["05월", 0]
				, ["06월", 0]
				, ["07월", 0]
				, ["08월", 0]
				, ["09월", 0]
				, ["10월", 0]
				, ["11월", 0]
				, ["12월", 0]
			];
			
			
			for(var i=1; i<13; i++){
				var loop_flag = false;
				<c:forEach items="${list}" var="list">
					if(!loop_flag){
						if(list[i][0] === "${list.dt_reg}월"){
							list[i][1] = ${list.sumSale};
							loop_flag = true;
				 		}
					}
				</c:forEach>
			}
		
		
		var ChartData = {
			labels	: ['01월', '02월', '03월', '04월', '05월', '06월', '07월', '08월', '09월', '10월', '11월', '12월'],
			datasets: [
				{
					label				: '${myYear}월별 주문통계',
					backgroundColor		: 'rgba(60,141,188,0.9)',
					borderColor			: 'rgba(60,141,188,0.8)',
					pointRadius			: false,
					pointColor			: '#3b8bba',
					pointStrokeColor	: 'rgba(60,141,188,1)',
					pointHighlightFill	: '#fff',
					pointHighlightStroke: 'rgba(60,141,188,1)',
					data				: [list[1][1], list[2][1], list[3][1], list[4][1], list[5][1], list[6][1], list[7][1], list[8][1], list[9][1], list[10][1], list[11][1], list[12][1]]
				}
			]
		}

		var ChartOptions = {
			maintainAspectRatio : false,
			responsive : true,
			legend: {
				display: false
			},
			scales: {
				xAxes: [{
					gridLines : {
						display : false,
					}
				}],
				yAxes: [{
					gridLines : {
						display : false,
					}
				}]
			}
		}

		//-------------
		//- BAR CHART -
		//-------------
		var barChartCanvas = $('#barChart').get(0).getContext('2d')
		var barChartData = $.extend(true, {}, ChartData)
		var temp0 = ChartData.datasets[0]

		barChartData.datasets[0] = temp0


		var barChartOptions = {
			responsive				: true,
			maintainAspectRatio		: false,
			datasetFill				: false
		}

		new Chart(barChartCanvas, {
			type: 'bar',
			data: barChartData,
			options: barChartOptions
		})
	})
</script>

</body>
</html>
