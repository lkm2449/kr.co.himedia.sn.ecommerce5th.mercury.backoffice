/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2023 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2023 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.sn.ecommerce5th.moon
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: MainWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20231219111626][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.reply.controller;

import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.backoffice.board.controller.BoardWeb;
import kr.co.himedia.ecommerce.backoffice.reply.dto.ReplyDto;
import kr.co.himedia.ecommerce.backoffice.reply.service.ReplySrvc;
import kr.co.himedia.ecommerce.common.Common;


/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-19
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */

@Controller("kr.co.himedia.ecommerce.backoffice.reply.controller.ReplyWeb")
public class ReplyWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BoardWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject 
	ReplySrvc replySrvc;
	
	@RequestMapping(value = "/backoffice/reply/replyProc.web", method = RequestMethod.POST)
	public ModelAndView replyProc(HttpServletRequest request, HttpServletResponse response, ReplyDto replyDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (replySrvc.insert(replyDto)) {
				request.setAttribute("script"	, "alert('등록 성공');");
				request.setAttribute("redirect"	, "/backoffice/main/main.web");
			}
			else {
				request.setAttribute("script"	, "alert('등록 실패;')");
				request.setAttribute("redirect"	, "/backoffice/main/main.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".replyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}
