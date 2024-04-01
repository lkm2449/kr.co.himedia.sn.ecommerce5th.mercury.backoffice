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
 * File			: SaleWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240319100845][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.backoffice.sale.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.backoffice.board.controller.BoardWeb;
import kr.co.himedia.ecommerce.backoffice.sale.dto.SaleDto;
import kr.co.himedia.ecommerce.backoffice.sale.service.SaleSrvc;
import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.util.Files;

/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-03-19
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.backoffice.sale.controller.SaleWeb")
public class SaleWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BoardWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Inject
	SaleSrvc saleSrvc;
	
	@RequestMapping(value = "/backoffice/sale/writeProc.web")
	public ModelAndView writeProc(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			Map<String, String> saleMap = upload(request, response);
			
			String fileNameSave = saleMap.get("fileNameSave");
			String fileNameOrig = saleMap.get("fileNameOrig");
			
			String cd_ctg3 = saleMap.get("cd_ctg").substring(2, 4);
			
			String cd_ctg = "";
			
			if (cd_ctg3.equals("01")) {
				cd_ctg = "feed";
			}
			if (cd_ctg3.equals("02")) {
				cd_ctg = "walk";
			}
			if (cd_ctg3.equals("03")) {
				cd_ctg = "toy";
			}
			
			// 파일이 있을 경우에만
			if (fileNameOrig != null && fileNameOrig.length() != 0) {
				
				String dirUpload	= staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
				
				File srcFile = new File(dirUpload + File.separator + "temp" + File.separator + fileNameSave);
				File destDir = new File(dirUpload + "/" + cd_ctg);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				String dirUpload2	= staticProperties.getProperty("common.dirUpload2", "[UNDEFINED]");

				File srcFile2 = new File(dirUpload2 + File.separator + "temp" + File.separator + fileNameSave);
				File destDir2 = new File(dirUpload2 + "/" + cd_ctg);
				FileUtils.moveFileToDirectory(srcFile2, destDir2, true);
				
				saleDto.setImg("/image/" + cd_ctg + "/" + fileNameSave);
			}
			
			saleDto.setSle_nm(saleMap.get("sle_nm"));
			saleDto.setDesces(saleMap.get("desces"));
			saleDto.setCd_ctg(saleMap.get("cd_ctg"));
			saleDto.setDt_sale_end(saleMap.get("dt_sale_end"));
			saleDto.setPrice_sale(Integer.parseInt(saleMap.get("price_sale")));
			saleDto.setCd_state_sale(Integer.parseInt(saleMap.get("cd_state_sale")));
			saleDto.setCom_nm(saleMap.get("com_nm"));
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (saleSrvc.insert(saleDto)) {
				request.setAttribute("redirect"	, "/backoffice/sale/list.web");
			}
			else {
				request.setAttribute("redirect"	, "/backoffice/sale/list.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/sale/writeForm.web")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("/backoffice/sale/writeForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".writeForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/sale/revList.web")
	public ModelAndView revList(HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			PagingListDto pagingListDto = saleSrvc.revList(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/backoffice/sale/revList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/sale/list.web")
	public ModelAndView list (HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			PagingListDto pagingListDto = saleSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/backoffice/sale/list");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".list()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/sale/view.web")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			SaleDto _saleDto = saleSrvc.select(saleDto);
			
			String cd_ctg1 = "00";
			String cd_ctg2 = "00";
			String cd_ctg3 = "00";
			
			cd_ctg1 = _saleDto.getCd_ctg().substring(0, 2);
			cd_ctg2 = _saleDto.getCd_ctg().substring(2, 4);
			cd_ctg3 = _saleDto.getCd_ctg().substring(4, 6);
			
			mav.addObject("cd_ctg1", cd_ctg1);		// 강아지
			mav.addObject("cd_ctg2", cd_ctg2);		// 사료, 산책/이동장, 장난감
			mav.addObject("cd_ctg3", cd_ctg3);		// 분류
			
			mav.addObject("saleDto"	, _saleDto);
			
			mav.setViewName("/backoffice/sale/view");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".view()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/sale/modifyForm.web")
	public ModelAndView modifyForm (HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			SaleDto _saleDto = saleSrvc.select(saleDto);
			
			String cd_ctg1 = "00";
			String cd_ctg2 = "00";
			String cd_ctg3 = "00";
			
			if (saleDto.getCd_ctg() != null &&
					_saleDto.getCd_ctg() != "") {
				cd_ctg1 = _saleDto.getCd_ctg().substring(0, 2);
				cd_ctg2 = _saleDto.getCd_ctg().substring(2, 4);
				cd_ctg3 = _saleDto.getCd_ctg().substring(4, 6);
			}
			
			
			mav.addObject("cd_ctg1", cd_ctg1);		// 강아지
			mav.addObject("cd_ctg2", cd_ctg2);		// 사료, 산책/이동장, 장난감
			mav.addObject("cd_ctg3", cd_ctg3);		// 분류
			mav.addObject("saleDto", _saleDto);
			mav.setViewName("/backoffice/sale/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/sale/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, SaleDto saleDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			Map<String, String> saleMap = upload(request, response);
			
			String fileNameSave = saleMap.get("fileNameSave");
			String fileNameOrig = saleMap.get("fileNameOrig");
			
			String cd_ctg2 = saleMap.get("cd_ctg").substring(2, 4);
			
			String cd_ctg3 = "";
			
			if (cd_ctg2.equals("01")) {
				cd_ctg3 = "feed";
			}
			if (cd_ctg2.equals("02")) {
				cd_ctg3 = "walk";
			}
			if (cd_ctg2.equals("03")) {
				cd_ctg3 = "toy";
			}
			
			// 파일이 있을 경우에만
			if (fileNameOrig != null && fileNameOrig.length() != 0) {
				
				String dirUpload	= staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
				
				File srcFile = new File(dirUpload + File.separator + "temp" + File.separator + fileNameSave);
				File destDir = new File(dirUpload + "/" + cd_ctg3);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				String dirUpload2	= staticProperties.getProperty("common.dirUpload2", "[UNDEFINED]");
				
				File srcFile2 = new File(dirUpload2 + File.separator + "temp" + File.separator + fileNameSave);
				File destDir2 = new File(dirUpload2 + "/" + cd_ctg3);
				FileUtils.moveFileToDirectory(srcFile2, destDir2, true);
				
				saleDto.setImg("/image/" + cd_ctg3 + "/" + fileNameSave);
			}
			
			saleDto.setSeq_sle(Integer.parseInt(saleMap.get("seq_sle")));
			saleDto.setSle_nm(saleMap.get("sle_nm"));
			saleDto.setCd_ctg(saleMap.get("cd_ctg"));
			saleDto.setDesces(saleMap.get("desces"));
			saleDto.setCom_nm(saleMap.get("com_nm"));
			saleDto.setCd_state_sale(Integer.parseInt(saleMap.get("cd_state_sale")));
			saleDto.setDt_sale_end(saleMap.get("dt_sale_end"));
			saleDto.setDt_sale_start(saleMap.get("dt_sale_start"));
			
			request.setAttribute("scriptSRC", "<script type=\"text/javascript\" src=\"/js/seller.js\"></script>");
			if (saleSrvc.update(saleDto)) {
				request.setAttribute("redirect"	, "/backoffice/sale/list.web");
			}
			else {
				request.setAttribute("redirect"	, "/backoffice/sale/list.web");
			}
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@SuppressWarnings("rawtypes")
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dirUpload = staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
		String dirUpload2 = staticProperties.getProperty("common.dirUpload2", "[UNDEFINED]");
		
		Map<String, String> saleMap = new HashMap<String, String>();
		
		String encoding = "utf-8";
		File currentDirPath = new File(dirUpload);
		File currentDirPath2 = new File(dirUpload2);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			
			for (int loop = 0; loop < items.size(); loop++) {
				
				FileItem fileItem = (FileItem) items.get(loop);
				
				// INPUT일 경우
				if (fileItem.isFormField()) {
					
					saleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}
				// FILE일 경우
				else {
					
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileNameOrig = fileItem.getName().substring(idx + 1);
						String fileNameSave = Files.getFileSaveName(fileNameOrig);
						saleMap.put("fileNameOrig", fileNameOrig);
						saleMap.put("fileNameSave", fileNameSave);
						
						File uploadFile = new File(currentDirPath + File.separator + "temp" + File.separator + fileNameSave);
						fileItem.write(uploadFile);
						
						File uploadFile2 = new File(currentDirPath2 + File.separator + "temp" + File.separator + fileNameSave);
						fileItem.write(uploadFile2);
					}
				}
			}
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".upload()] " + e.getMessage(), e);
		}
		return saleMap;
	}

}
