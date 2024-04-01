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
package kr.co.himedia.ecommerce.backoffice.stats.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.backoffice.common.dao.BaseDao;
import kr.co.himedia.ecommerce.backoffice.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.backoffice.sale.dto.SaleDto;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-02-28
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.backoffice.stats.dao.StatsDao")
public class StatsDao extends BaseDao{
	
	public List<SaleDto> cancelTop(){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.cancelTop");
	}
	
	public List<CustomerDto> cancelWeek(){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.cancelWeek");
	}
	
	public List<CustomerDto> countWeekApp(){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.countWeekApp");
	}
	
	public List<CustomerDto> countWeekWeb(){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.countWeekWeb");
	}
	
	public int countApp() {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.countApp");
	}
	
	public int countWeb() {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.countWeb");
	}
	
	public List<String> selectYears(){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.selectYears");
	}
	
	public List<CustomerDto> joinStats(CustomerDto customerDto){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.joinStats", customerDto);
	}
	
	public List<SaleDto> orderStats(SaleDto saleDto){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.orderStats", saleDto);
	}
	
	public List<String> selectMonths(){
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.selectMonths");
	}
	
	public List<SaleDto> select(SaleDto saleDto) {
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.stats.Stats.select", saleDto);
	}
	
}
