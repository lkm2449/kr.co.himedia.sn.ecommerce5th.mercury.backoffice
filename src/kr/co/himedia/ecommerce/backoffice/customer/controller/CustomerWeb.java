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
 * File			: CustomerWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240228120708][dlrkdals1997@gmail.com][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.customer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.backoffice.customer.dto.CustomerDto;
import kr.co.himedia.ecommerce.backoffice.customer.service.CustomerSrvc;
import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.common.component.EmailCmpn;
import kr.co.himedia.ecommerce.common.dto.EmailDto;
import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.util.security.HSwithSHA;
import kr.co.himedia.ecommerce.util.security.SKwithAES;

/**
 * @version 1.0.0
 * @author dlrkdals1997@gmail.com
 * 
 * @since 2024-02-28
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.backoffice.customer.controller.CustomerWeb")
public class CustomerWeb extends Common{

	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(CustomerWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Autowired
	private MessageSourceAccessor dynamicProperties;
	
	@Autowired
	private EmailCmpn emailCmpn;
	
	@Inject
	CustomerSrvc customerSrvc;
	
	
	@RequestMapping(value = "/backoffice/customer/list.web")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.addObject("paging"	, customerSrvc.countList(pagingDto));
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			List<CustomerDto> list = customerSrvc.selectList(pagingDto);

			for(int i=0; i<list.size(); i++) {
				list.get(i).setCst_nm(aes.decode(list.get(i).getCst_nm()));
				list.get(i).setCst_email(aes.decode(list.get(i).getCst_email()));

				
				// 이메일 마스킹 처리
				String email = list.get(i).getCst_email();
				int email_length = email.length();
				
				if(email_length > 3) {

					list.get(i).setCst_email(email.replaceAll("(?<=.{3}).(?=.*@)", "*"));
					
				}
				
				list.get(i).setPhone(aes.decode(list.get(i).getPhone()));
				
				String phone = list.get(i).getPhone();
				int phone_length = phone.length();
				
				if(phone_length == 11) {
					String phone1 = phone.substring(0, 3);
					
					// String phone2 = phone.substring(3, 7);
					String phone3 = phone.substring(7, 11);
					
					// String masking_phone = phone1 + "-" + phone2 + "-" + phone3;
					
					String masking_phone = phone1 + " - **** - " + phone3;
					
					list.get(i).setPhone(masking_phone);
				}
			}
			
			mav.addObject("list", list);
			
			mav.setViewName("/backoffice/customer/list");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/customer/withdrawalProc.web")
	public ModelAndView withdrawalProc(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			int seq_cst = Integer.parseInt(request.getParameter("seq_cst"));
			
			customerSrvc.updateWithdrawal(seq_cst);

			mav.addObject("paging"	, customerSrvc.countWithdrawal(pagingDto));
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			List<CustomerDto> list = customerSrvc.withdrawalList(pagingDto);

			for(int i=0; i<list.size(); i++) {
				list.get(i).setCst_nm(aes.decode(list.get(i).getCst_nm()));
				list.get(i).setCst_email(aes.decode(list.get(i).getCst_email()));
				
				// 이메일 마스킹 처리
				String email = list.get(i).getCst_email();
				int email_length = email.length();
				
				if(email_length > 3) {

					list.get(i).setCst_email(email.replaceAll("(?<=.{3}).(?=.*@)", "*"));
					
				}
				
				list.get(i).setPhone(aes.decode(list.get(i).getPhone()));
				
				String phone = list.get(i).getPhone();
				int phone_length = phone.length();
				
				if(phone_length == 11) {
					String phone1 = phone.substring(0, 3);
					
					// String phone2 = phone.substring(3, 7);
					String phone3 = phone.substring(7, 11);
					
					// String masking_phone = phone1 + "-" + phone2 + "-" + phone3;
					
					String masking_phone = phone1 + " - **** - " + phone3;
					
					list.get(i).setPhone(masking_phone);
				}
			}
			mav.addObject("list", list);
			
			mav.setViewName("/backoffice/customer/withdrawalForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".withdrawalProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/customer/withdrawalForm.web")
	public ModelAndView withdrawalForm(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.addObject("paging"	, customerSrvc.countWithdrawal(pagingDto));
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			List<CustomerDto> list = customerSrvc.withdrawalList(pagingDto);

			for(int i=0; i<list.size(); i++) {
				list.get(i).setCst_nm(aes.decode(list.get(i).getCst_nm()));
				list.get(i).setCst_email(aes.decode(list.get(i).getCst_email()));
				
				// 이메일 마스킹 처리
				String email = list.get(i).getCst_email();
				int email_length = email.length();
				
				if(email_length > 3) {

					list.get(i).setCst_email(email.replaceAll("(?<=.{3}).(?=.*@)", "*"));
					
				}
				
				list.get(i).setPhone(aes.decode(list.get(i).getPhone()));
				
				String phone = list.get(i).getPhone();
				int phone_length = phone.length();
				
				if(phone_length == 11) {
					String phone1 = phone.substring(0, 3);
					
					// String phone2 = phone.substring(3, 7);
					String phone3 = phone.substring(7, 11);
					
					// String masking_phone = phone1 + "-" + phone2 + "-" + phone3;
					
					String masking_phone = phone1 + " - **** - " + phone3;
					
					list.get(i).setPhone(masking_phone);
				}
			}
			mav.addObject("list", list);
			
			mav.setViewName("/backoffice/customer/withdrawalForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".withdrawalForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/customer/mailProc.web")
	public ModelAndView mailProc(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			String subject = "(광고)" + request.getParameter("subject");

			String com_info = "감사합니다. 함께할게 드림<br/>"
					+ "<br/><strong>회사 정보</strong><br/>"
					+ "상호명 및 호스팅 서비스 제공 : 하이미디어 컴퓨터학원<br/>"
					+ "대표 이사           : 이석미<br/>"
					+ "경기 성남시 중원구 성남대로 1133 메트로칸 빌딩 5층<br/>"
					+ "사업자등록번호       : 226-88-02480<br/>"
					+ "통신판매업신고       : 제2017-성남중원-0111호<br/>"
					+ "기관등록번호        : 제6631호";
			
			String message = request.getParameter("message") + com_info;
			
			List<String> list = customerSrvc.selectEmail();
			List<String> purifyList = new ArrayList<String>();
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			for(int i=0; i<list.size(); i++) {
				if(aes.decode(list.get(i)).endsWith(".com")) {
					purifyList.add(aes.decode(list.get(i)));
				}
			}
			
			String[] arrTo = new String[purifyList.size()];	
			for(int i=0; i<purifyList.size(); i++) {
				arrTo[i] = purifyList.get(i);
			}
			
			EmailDto emailDto	= new EmailDto();
			emailDto.setSender(dynamicProperties.getMessage("email.sender.mail"));
			emailDto.setBc(arrTo);
			emailDto.setSubject(subject);
			emailDto.setMessage(message);

			emailCmpn.send(emailDto);
			
			mav.setViewName("/backoffice/main/main");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".mailProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/customer/mailForm.web")
	public ModelAndView mailForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {	
			mav.setViewName("/backoffice/customer/mailForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".mailForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/customer/writeProc.web")
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, CustomerDto customerDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (customerDto.getFlg_sms() == null || customerDto.getFlg_sms() == "") {
				customerDto.setFlg_sms("N");
			}
			
			if (customerDto.getFlg_email() == null || customerDto.getFlg_email() == "") {
				customerDto.setFlg_email("N");
			}

			// 해쉬 암호화
			customerDto.setPasswd(HSwithSHA.encode(customerDto.getPasswd()));
			
			// 대칭키 암호화
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			customerDto.setCst_nm(aes.encode(customerDto.getCst_nm()));
			customerDto.setPhone(aes.encode(customerDto.getPhone()));
			customerDto.setPostcode(aes.encode(customerDto.getPostcode()));
			customerDto.setAddr1(aes.encode(customerDto.getAddr1()));
			customerDto.setAddr3(aes.encode(customerDto.getAddr3()));
			customerDto.setCst_email(aes.encode(customerDto.getCst_email()));
			customerDto.setSso("H");
			customerDto.setRegister(0);
			
			if(customerSrvc.searchCustomer(customerDto) == 1) {
				request.setAttribute("script", "alert('이미 등록된 회원입니다.')");
				request.setAttribute("redirect"	, "/backoffice/main/main.web");
			}else {

				if (customerSrvc.insertCustomer(customerDto)) {
					request.setAttribute("script", "alert('회원 등록에 성공하였습니다')");
					request.setAttribute("redirect"	, "//backoffice/main/main.web");
				}
				else {
					request.setAttribute("script", "alert('회원 등록에 실패하였습니다. 잠시 후에 다시 이용해주세요')");
					request.setAttribute("redirect"	, "/backoffice/main/main.web");
				}
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/customer/exist.json", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean exist(@RequestBody final CustomerDto customerDto) {
		
		boolean exist = true;
		List<CustomerDto> listCustomerDto = null;
		
		try {
			listCustomerDto	= customerSrvc.listing(customerDto.getId());
			
			if (listCustomerDto.size() == 0) exist = false;
			
			debuggingJSON(exist);
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".exist()] " + e.getMessage(), e);
		}
		finally {}
		
		return exist;
	}
	
	@RequestMapping(value = "/backoffice/customer/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {	
			mav.setViewName("/backoffice/customer/writeForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}

	
}
