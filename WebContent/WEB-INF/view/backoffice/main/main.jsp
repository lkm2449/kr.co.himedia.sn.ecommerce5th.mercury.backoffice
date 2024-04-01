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
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">함께할개</h1>
					</div><!-- /.col -->
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
						</ol>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.container-fluid -->
		</div>
		<!-- /.content-header -->

		<!-- Main content -->
		<div class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-6">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">최근 일주일간 구매 현황(Web/App)</h3>

								<div class="card-tools">
									<a href="/backoffice/stats/WAStats.web" class="btn btn-tool btn-sm">
										<i class="fas fa-bars"></i>
									</a>
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
						<!-- /.card -->

						<div class="card">
							<div class="card-header border-0">
								<h3 class="card-title">주문 목록</h3>
								<div class="card-tools">
									<a href="/backoffice/order/orderList.web" class="btn btn-tool btn-sm">
										<i class="fas fa-bars"></i>
									</a>
									<button type="button" class="btn btn-tool" data-card-widget="collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="card-body table-responsive p-0">
								<table class="table table-hover text-nowrap">
									<thead>
									<tr>
										<th>번호</th>
										<th>구매자명</th>
										<th>구매 정보</th>
										<th>구매 총 가격</th>
										<th>결제 상태</th>
										<th>요청 사항</th>
										<th>배송 상태</th>
										<th>구매일</th>
									</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty orderList}">
												<tr><td colspan="8" style="text-align: center;">현재 주문된 상품이 없습니다</td></tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${orderList}" var="orderList" varStatus="status">
													<tr>
														<td>${orderList.rnum }</td>
														<td><plutozoneUtilTag:masking text="${orderList.cst_nm}" letter="*" count="1" /></td>
														<td>${orderList.buy_info }</td>
														<td><fmt:formatNumber value="${orderList.buy_t_price}" type="number" /> 원</td>
														<td>${orderList.cd_state_pay }</td>
														<td>
															<c:choose>
																<c:when test="${empty orderList.request }">
																	X
																</c:when>
																<c:otherwise>
																	${orderList.request }
																</c:otherwise>
															</c:choose>
														</td>
														<td>
															${orderList.cd_state_delivery }
														</td>
														<td>${orderList.dt_reg }</td>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>

						</div>
						<!-- /.card -->
					</div>
					
					
					<!-- /.col-md-6 -->
					<div class="col-lg-6">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">최근 일주일간 취소/교환/환불 횟수</h3>
								<div class="card-tools">
									<a href="/backoffice/stats/cancelStats.web" class="btn btn-tool btn-sm">
										<i class="fas fa-bars"></i>
									</a>
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

						<div class="card">
							<div class="card-header border-0">
								<h3 class="card-title">공지 사항</h3>
								<div class="card-tools">
									<a href="/backoffice/board/noticeList.web" class="btn btn-tool btn-sm">
										<i class="fas fa-bars"></i>
									</a>
									<button type="button" class="btn btn-tool" data-card-widget="collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="card-body table-responsive p-0">
								<table class="table table-hover text-nowrap">
									<thead>
									<tr>
										<th>No</th>
										<th>제목</th>
										<th>등록일</th>
									</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${empty noticeList}">
												<tr>
													<td colspan="3" style="text-align: center;">등록된 공지 사항이 없습니다.</td>
												</tr>
											</c:when>
											<c:otherwise>
											<c:forEach items="${noticeList}" var="noticeList">
												<tr>
													<td>
														${noticeList.rnum}
													</td>
													<td style="text-align: left">
														<a href="/backoffice/board/noticeView.web?seq_bbs=${noticeList.seq_bbs}">${noticeList.title}
														</a>	
													</td>
													<td>
														${noticeList.dt_reg}
													</td>
												</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>

						</div>
						<!-- /.card -->
					</div>
					</div>
					<!-- /.col-md-6 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /.content -->
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
	
	//-------------
	//- PIE CHART -
	//-------------
})

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
