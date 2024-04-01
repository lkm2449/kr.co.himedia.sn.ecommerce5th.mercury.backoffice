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
 * File			: LoginWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240320150418][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.login.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.backoffice.login.service.LoginSrvc;
import kr.co.himedia.ecommerce.common.Common;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-03-20
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.backoffice.login.controller.LoginWeb")
public class LoginWeb extends Common{

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(LoginWeb.class);
	
	@Inject
	LoginSrvc loginSrvc;
	
	//로그아웃
	@RequestMapping(value = "/backoffice/login/logout.web")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			HttpSession session = request.getSession(false);
			
			session.invalidate();

			request.setAttribute("script"	, "alert('로그아웃되었습니다')");
			request.setAttribute("redirect"	, "/backoffice/login/loginForm.web");
				
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".logout()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/login/loginProc.web")
	public ModelAndView loginProc(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			
			if(loginSrvc.loginProc(id) == null) {
				request.setAttribute("script", "alert('로그인 실패!')");
				request.setAttribute("redirect"	, "/backoffice/login/loginForm.web");
				
			} else if(loginSrvc.loginProc(id).equals(passwd)) {
				
				HttpSession session = request.getSession(true);

				session.setAttribute("SEQ_MNG", "1");
				
				request.setAttribute("redirect"	, "/backoffice/main/main.web");
			} else {
				request.setAttribute("script", "alert('로그인 실패!')");
				request.setAttribute("redirect"	, "/backoffice/login/loginForm.web");

			}
			
			mav.setViewName("forward:/servlet/result.web");

		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/login/loginForm.web")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if(getSession(request, "SEQ_MNG") != null && getSession(request, "SEQ_MNG") != "") {
				mav.setViewName("/backoffice/main/main");
			} else {
				mav.setViewName("/backoffice/login/loginForm");
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".loginForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}