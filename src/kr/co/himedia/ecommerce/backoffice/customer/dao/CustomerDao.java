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
 * File			: CustomerDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240228140210][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.backoffice.common.dao.BaseDao;
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
@Repository("kr.co.himedia.ecommerce.backoffice.customer.dao.CustomerDao")
public class CustomerDao extends BaseDao{
	
	public int countList() {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.countList");
	}
	
	public List<CustomerDto> selectList(PagingDto pagingDto){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.selectList", pagingDto);
	}
	
	public List<String> selectEmail() {
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.selectEmail");
	}
	
	public int updateWithdrawal(int seq_cst) {
		return sqlSessionBackoffice.update("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.updateWithdrawal", seq_cst);
	}
	
	public List<CustomerDto> withdrawalList(PagingDto pagingDto){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.withdrawalList", pagingDto);
	}
	
	public int countWithdrawal() {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.countWithdrawal");
	}
	
	public int insertCustomer(CustomerDto customerDto) {
		return sqlSessionBackoffice.insert("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.insertCustomer", customerDto);
	}
	
	public int sequence() {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.sequence");
	}
		
	public int searchCustomer(CustomerDto customerDto) {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.searchCustomer", customerDto);
	}
	
	public List<CustomerDto> listing(String id) {
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.customer.Customer.listing", id);
	}
}
