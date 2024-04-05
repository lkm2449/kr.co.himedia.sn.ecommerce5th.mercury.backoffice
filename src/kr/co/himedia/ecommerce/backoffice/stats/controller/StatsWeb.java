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
package kr.co.himedia.ecommerce.backoffice.stats.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.backoffice.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.backoffice.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.backoffice.stats.service.StatsSrvc;
import kr.co.himedia.ecommerce.util.Datetime;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-03-18
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.backoffice.stats.controller.StatsWeb")
public class StatsWeb {

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(StatsWeb.class);
	
	@Inject
	StatsSrvc statsSrvc;
	
	@RequestMapping(value = "/backoffice/stats/cancelStats.web")
	public ModelAndView cancelStats(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.addObject("cancelWeek", statsSrvc.cancelWeek());
			mav.addObject("cancelTop", statsSrvc.cancelTop());
			
			mav.setViewName("/backoffice/stats/cancelStats");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".cancelStats()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/stats/WAStats.web")
	public ModelAndView WAStats(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {

			mav.addObject("countWeekApp", statsSrvc.countWeekApp());
			mav.addObject("countWeekWeb", statsSrvc.countWeekWeb());
			
			mav.addObject("countApp", statsSrvc.countApp());
			mav.addObject("countWeb", statsSrvc.countWeb());
			
			mav.setViewName("/backoffice/stats/WAStats");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".WAStats()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/stats/joinStats.web")
	public ModelAndView joinStats(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			if(customerDto.getYear() == null || customerDto.getYear() == "" ) {
				String year = Datetime.getNow("yyyy");
				customerDto.setYear(year);
			}
			
			mav.addObject("myYear", customerDto.getYear());
			
			List<CustomerDto> list = statsSrvc.joinStats(customerDto);
			
			mav.addObject("list", list);
			
			List<String> years = statsSrvc.selectYears();
			mav.addObject("year", years);
			
			mav.setViewName("/backoffice/stats/joinStats");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".joinStats()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/stats/orderStats.web")
	public ModelAndView orderStats(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if(saleDto.getYear() == null || saleDto.getYear() == "" ) {
				String year = Datetime.getNow("yyyy");
				saleDto.setYear(year);
			}
			
			if(saleDto.getMonth() == null || saleDto.getMonth() == "" ) {
				String month = Datetime.getNow("MM");
				saleDto.setMonth(month);
			}
			
			List<SaleDto> list = statsSrvc.select(saleDto);
			mav.addObject("list", list);
			
			List<SaleDto> list2 = statsSrvc.orderStats(saleDto);
			mav.addObject("list2", list2);
			mav.addObject("myYear", saleDto.getYear());
			mav.addObject("myMonth", saleDto.getMonth());
			
			List<String> months = statsSrvc.selectMonths();
			mav.addObject("month", months);
			
			mav.setViewName("/backoffice/stats/orderStats");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".orderStats()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
