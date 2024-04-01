<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
		<!-- Brand Logo -->
		<a href="/backoffice/main/main.web" class="brand-link">
			<img src="/image/backoffice/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light">함께할개</span>
		</a>

		<!-- Sidebar -->
		<div class="sidebar">
			<!-- Sidebar user panel (optional) -->

			<!-- Sidebar Menu -->
			<nav class="mt-2">
				<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
					<!-- Add icons to the links using the .nav-icon class
							 with font-awesome or any other icon font library -->
					<li class="nav-item">
						<a href="#" class="nav-link">
							<i class="nav-icon fas fa-copy"></i>
							<p>
								회원 관리
								<i class="fas fa-angle-left right"></i>
								<span class="badge badge-info right"></span>
							</p>
						</a>
						<ul class="nav nav-treeview">
							<li class="nav-item">
								<a href="/backoffice/customer/list.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>회원 목록</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/customer/writeForm.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>회원 등록</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/customer/mailForm.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>메일 일괄 발송</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/customer/withdrawalForm.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>탈퇴 회원 관리</p>
								</a>
							</li>
						</ul>
					</li>
					<li class="nav-item">
						<a href="#" class="nav-link">
							<i class="nav-icon fas fa-copy"></i>
							<p>
								상품 관리
								<i class="fas fa-angle-left right"></i>
								<span class="badge badge-info right"></span>
							</p>
						</a>
						<ul class="nav nav-treeview">
							<li class="nav-item">
								<a href="/backoffice/sale/list.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>상품 목록</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/sale/revList.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>상품 리뷰 목록</p>
								</a>
							</li>
						</ul>
					</li>
					<li class="nav-item">
						<a href="#" class="nav-link">
							<i class="nav-icon fas fa-copy"></i>
							<p>
								고객 지원
								<i class="fas fa-angle-left right"></i>
								<span class="badge badge-info right"></span>
							</p>
						</a>
						<ul class="nav nav-treeview">
							<li class="nav-item">
								<a href="/backoffice/board/noticeList.web?cd_bbs_type=1" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>공지사항</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/board/faqList.web?cd_bbs_type=2" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>자주찾는질문</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/board/inquiryList.web?cd_bbs_type=3" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>고객 문의 내역</p>
								</a>
							</li>
						</ul>
					</li>
					<li class="nav-item">
						<a href="#" class="nav-link">
							<i class="nav-icon fas fa-copy"></i>
							<p>
								통계 분석
								<i class="fas fa-angle-left right"></i>
								<span class="badge badge-info right"></span>
							</p>
						</a>
						<ul class="nav nav-treeview">
							<li class="nav-item">
								<a href="/backoffice/stats/joinStats.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>가입 통계</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/stats/orderStats.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>주문 통계</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/stats/WAStats.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>WEB/APP 통계</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/stats/cancelStats.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>취소/교환/환불 통계</p>
								</a>
							</li>
						</ul>
					</li>
					<li class="nav-item">
						<a href="#" class="nav-link">
							<i class="nav-icon fas fa-copy"></i>
							<p>
								주문 관리
								<i class="fas fa-angle-left right"></i>
								<span class="badge badge-info right"></span>
							</p>
						</a>
						<ul class="nav nav-treeview">
							<li class="nav-item">
								<a href="/backoffice/order/orderList.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>주문 목록</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/order/shippingList.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>배송 관리</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="/backoffice/order/cancelList.web" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>취소/교환/환불 목록</p>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</div>
		<!-- /.sidebar -->
	</aside>