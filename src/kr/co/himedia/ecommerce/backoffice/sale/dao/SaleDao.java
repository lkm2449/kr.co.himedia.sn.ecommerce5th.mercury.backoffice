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
 * Environment	: JRE 1.7 or more
 * File			: SaleDao.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240319100910][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.sale.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.himedia.ecommerce.backoffice.common.dao.BaseDao;
import kr.co.himedia.ecommerce.backoffice.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.common.dto.PagingDto;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-03-19
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Repository("kr.co.himedia.ecommerce.backoffice.sale.dao.SaleDao")
public class SaleDao extends BaseDao {
	
	public int insert(SaleDto saleDto) {
		return sqlSessionBackoffice.insert("kr.co.himedia.ecommerce.backoffice.mybatis.sale.Sale.insert", saleDto); 
	}
	
	public int sequence() {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.sale.Sale.sequence"); 
	}
	
	public int revCount(PagingDto pagingDto) {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.sale.Sale.revCount", pagingDto);
	}
	
	public List<SaleDto> revList(PagingDto pagingDto) {
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.sale.Sale.revList", pagingDto);
	}
	
	public int count(PagingDto pagingDto) {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.sale.Sale.count", pagingDto);
	}
	
	public List<SaleDto> list(PagingDto pagingDto) {
		return sqlSessionBackoffice.selectList("kr.co.himedia.ecommerce.backoffice.mybatis.sale.Sale.list", pagingDto);
	}
	
	public SaleDto select(SaleDto saleDto) {
		return sqlSessionBackoffice.selectOne("kr.co.himedia.ecommerce.backoffice.mybatis.sale.Sale.select", saleDto);
	}
	
	public int update(SaleDto saleDto) {
		return sqlSessionBackoffice.update("kr.co.himedia.ecommerce.backoffice.mybatis.sale.Sale.update", saleDto);
	}

}
