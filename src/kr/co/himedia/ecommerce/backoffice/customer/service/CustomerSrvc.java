/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.mercury
 * Description	:
 * Environment	: JRE 1.8 or more
 * File			: CustomerSrvc.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240228140715][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.customer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.co.himedia.ecommerce.backoffice.customer.dao.CustomerDao;
import kr.co.himedia.ecommerce.backoffice.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.common.dto.PagingDto;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-02-28
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Service("kr.co.himedia.ecommerce.backoffice.customer.service.CustomerSrvc")
public class CustomerSrvc {
	
	@Inject
	CustomerDao customerDao;
	
	public List<CustomerDto> selectList(PagingDto pagingDto) {
		return customerDao.selectList(pagingDto);
	}
	
	public PagingDto countList(PagingDto pagingDto) {
				
		int totalLine = customerDao.countList();
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		if(totalPage !=0 && pagingDto.getCurrentPage() == 0) {
			pagingDto.setCurrentPage(1);
		}
		
		return pagingDto;
	}
	
	public List<String> selectEmail(){
		return customerDao.selectEmail();
	}
	
	@Transactional("txBackoffice")
	public boolean updateWithdrawal(int seq_cst) {
		int result = 0;
		
		result = customerDao.updateWithdrawal(seq_cst);
		if(result == 1) {
			return true;
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public PagingDto countWithdrawal(PagingDto pagingDto) {
		
		int totalLine = customerDao.countWithdrawal();
		int totalPage = (int)Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(0);
		if(totalPage !=0 && pagingDto.getCurrentPage() == 0) {
			pagingDto.setCurrentPage(1);
		}
		
		return pagingDto;
	}
	
	public List<CustomerDto> withdrawalList(PagingDto pagingDto) {
		return customerDao.withdrawalList(pagingDto);
	}
	
	public int searchCustomer(CustomerDto customerDto) {
		return customerDao.searchCustomer(customerDto);
	}
	
	@Transactional("txBackoffice")
	public boolean insertCustomer(CustomerDto customerDto) {
		
		customerDto.setSeq_cst(customerDao.sequence());
		customerDto.setRegister(customerDto.getSeq_cst());
		
		int result = customerDao.insertCustomer(customerDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	public List<CustomerDto> listing(String id) {
		return customerDao.listing(id);
	}
	
}
