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
 * File			: StatsWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240318141757][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.order.controller;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.backoffice.order.dto.OrderDto;
import kr.co.himedia.ecommerce.backoffice.order.service.OrderSrvc;
import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-03-18
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.backoffice.order.controller.OrderWeb")
public class OrderWeb extends Common{

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(OrderWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	OrderSrvc orderSrvc;
	
	@RequestMapping(value = "/backoffice/order/orderList.web")
	public ModelAndView orderList(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			mav.addObject("paging"	, orderSrvc.countOrderList(pagingDto));
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			List<OrderDto> list = orderSrvc.selectOrderList(pagingDto);
			
			for(int i=0; i<list.size(); i++) {
				list.get(i).setCst_nm(aes.decode(list.get(i).getCst_nm()));
			}
			
			mav.addObject("list", list);
			
			mav.setViewName("/backoffice/order/orderList");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".orderList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/order/cancelList.web")
	public ModelAndView cancelList(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			mav.addObject("paging"	, orderSrvc.countCancelList(pagingDto));
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			List<OrderDto> list = orderSrvc.cancelList(pagingDto);
			
			for(int i=0; i<list.size(); i++) {
				list.get(i).setCst_nm(aes.decode(list.get(i).getCst_nm()));
			}
			
			mav.addObject("list", list);
			
			mav.setViewName("/backoffice/order/cancelList");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".cancelList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/order/updateShipping.json", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean updateShipping(@RequestBody final OrderDto orderDto) {
		
		boolean exist = false;
		
		try {
			orderDto.setUpdater(1);
			
			if (orderSrvc.updateShipping(orderDto)) exist = true;
			
			debuggingJSON(exist);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".updateShipping()] " + e.getMessage(), e);
		}
		finally {}
		
		return exist;
	}
	
	@RequestMapping(value = "/backoffice/order/shippingList.web")
	public ModelAndView shippingList(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			mav.addObject("paging"	, orderSrvc.countShippingList(pagingDto));
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			List<OrderDto> list = orderSrvc.selectShippingList(pagingDto);
			
			for(int i=0; i<list.size(); i++) {
				list.get(i).setCst_nm(aes.decode(list.get(i).getCst_nm()));
			}
			
			mav.addObject("list", list);
			
			mav.setViewName("/backoffice/order/shippingList");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".shippingList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
