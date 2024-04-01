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
 * File			: MainWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240226093321][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.main.controller;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.backoffice.board.service.BoardSrvc;
import kr.co.himedia.ecommerce.backoffice.order.dto.OrderDto;
import kr.co.himedia.ecommerce.backoffice.order.service.OrderSrvc;
import kr.co.himedia.ecommerce.backoffice.stats.service.StatsSrvc;
import kr.co.himedia.ecommerce.util.security.SKwithAES;


/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-02-26
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.backoffice.main.controller.MainWeb")
public class MainWeb {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(MainWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	StatsSrvc statsSrvc;
	
	@Inject
	BoardSrvc boardSrvc;
	
	@Inject
	OrderSrvc orderSrvc;
	
	@RequestMapping(value = "/backoffice/main/main.web")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			List<OrderDto> orderList = orderSrvc.mainList();
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			for(int i=0; i<orderList.size(); i++) {
				orderList.get(i).setCst_nm(aes.decode(orderList.get(i).getCst_nm()));
			}
			
			mav.addObject("orderList", orderList);
			mav.addObject("cancelWeek", statsSrvc.cancelWeek());
			mav.addObject("countWeekApp", statsSrvc.countWeekApp());
			mav.addObject("countWeekWeb", statsSrvc.countWeekWeb());
			mav.addObject("noticeList", boardSrvc.mainList());
			
			mav.setViewName("/backoffice/main/main");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".main()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}