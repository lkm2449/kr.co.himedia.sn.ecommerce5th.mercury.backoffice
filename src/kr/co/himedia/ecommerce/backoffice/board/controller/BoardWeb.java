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
package kr.co.himedia.ecommerce.backoffice.board.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.himedia.ecommerce.common.Common;
import kr.co.himedia.ecommerce.common.dto.PagingDto;
import kr.co.himedia.ecommerce.common.dto.PagingListDto;
import kr.co.himedia.ecommerce.backoffice.board.dto.BoardDto;
import kr.co.himedia.ecommerce.backoffice.board.service.BoardSrvc;
import kr.co.himedia.ecommerce.backoffice.reply.dto.ReplyDto;
import kr.co.himedia.ecommerce.backoffice.reply.service.ReplySrvc;
import kr.co.himedia.ecommerce.util.Files;


/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2023-12-19
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
@Controller("kr.co.himedia.ecommerce.backoffice.board.controller.BoardWeb")
public class BoardWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(BoardWeb.class);
	
	@Autowired
	Properties staticProperties;
	@Inject 
	BoardSrvc boardSrvc;
	@Inject 
	ReplySrvc replySrvc;
	
	@RequestMapping(value = "/backoffice/board/faqView.web")
	public ModelAndView faqView(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			BoardDto _boardDto = boardSrvc.select(boardDto);
			
			mav.addObject("boardDto", _boardDto);
			mav.setViewName("/backoffice/board/faqView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".faqView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/backoffice/board/faqList.web")
	public ModelAndView faqList (HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (pagingDto.getCd_bbs_type() == null || pagingDto.getCd_bbs_type() == 0) {
				pagingDto.setCd_bbs_type(2);
			}

			PagingListDto pagingListDto = boardSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/backoffice/board/faqList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".faqList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/faqWriteForm.web")
	public ModelAndView faqWriteForm(HttpServletRequest request, HttpServletResponse response, String cd_bbs_type) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			mav.addObject("cd_bbs_type"	, cd_bbs_type);
			
			mav.setViewName("/backoffice/board/faqWriteForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".faqWriteForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/faqWriteProc.web", method = RequestMethod.POST)
	public ModelAndView faqWriteProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			Map<String, String> boardMap = upload(request, response);
			
			boardDto.setCd_bbs_type(Integer.parseInt(boardMap.get("cd_bbs_type")));	
			boardDto.setCd_bbs_tab(boardMap.get("cd_bbs_tab"));
			boardDto.setTitle(boardMap.get("title"));
			boardDto.setContents(boardMap.get("contents"));
			boardDto.setFlg_top("N");
			boardDto.setFlg_delete("N");
			boardDto.setRegister(1);
			
			if (boardSrvc.insert(boardDto)) {
				request.setAttribute("script"	, "alert('등록 성공');");
				request.setAttribute("redirect"	, "/backoffice/board/faqList.web");
			}
			else {
				request.setAttribute("script"	, "alert('등록 실패;')");
				request.setAttribute("redirect"	, "/backoffice/board/faqList.web");
			}
			
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".faqWriteProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/faqModifyForm.web")
	public ModelAndView faqModifyForm (HttpServletRequest request, HttpServletResponse response, String cd_bbs_type, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			BoardDto _boardDto = boardSrvc.select(boardDto); 

			mav.addObject("boardDto"	, _boardDto);

			mav.setViewName("/backoffice/board/faqModifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".faqModifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	} 
	
	@RequestMapping(value = "/backoffice/board/faqModifyProc.web", method = RequestMethod.POST)
	public ModelAndView faqModifyProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			Map<String, String> boardMap = upload(request, response);
			
			boardDto.setCd_bbs_type(Integer.parseInt(boardMap.get("cd_bbs_type")));	
			boardDto.setCd_bbs_tab(boardMap.get("cd_bbs_tab"));
			boardDto.setTitle(boardMap.get("title"));
			boardDto.setContents(boardMap.get("contents"));
			boardDto.setFlg_top(boardMap.get("flg_top"));
			boardDto.setSeq_bbs(Integer.parseInt(boardMap.get("seq_bbs")));
			boardDto.setFlg_delete("N");
			boardDto.setRegister(1);
			
			if (boardSrvc.update(boardDto)) {
				request.setAttribute("script"	, "alert('수정 성공');");
				request.setAttribute("redirect"	, "/backoffice/board/faqList.web");
			}
			else {
				request.setAttribute("script"	, "alert('수정 실패;')");
				request.setAttribute("redirect"	, "/backoffice/board/faqList.web");
			}
			
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".faqModifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/noticeList.web")
	public ModelAndView noticeList (HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (pagingDto.getCd_bbs_type() == null || pagingDto.getCd_bbs_type() == 0) {
				pagingDto.setCd_bbs_type(1);
			}
			
			PagingListDto pagingListDto = boardSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			
			mav.setViewName("/backoffice/board/noticeList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".noticeList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/noticeWriteForm.web")
	public ModelAndView noticeWriteForm(HttpServletRequest request, HttpServletResponse response, String cd_bbs_type) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			mav.addObject("cd_bbs_type"	, cd_bbs_type);

			mav.setViewName("/backoffice/board/noticeWriteForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".noticeWriteForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/noticeWriteProc.web", method = RequestMethod.POST)
	public ModelAndView noticeWriteProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			Map<String, String> boardMap = upload(request, response);
			
			String fileNameSave = boardMap.get("fileNameSave");
			String fileNameOrig = boardMap.get("fileNameOrig");
			
			boardDto.setCd_bbs_type(Integer.parseInt(boardMap.get("cd_bbs_type")));	
			boardDto.setCd_bbs_tab(boardMap.get("cd_bbs_tab"));
			boardDto.setTitle(boardMap.get("title"));
			boardDto.setContents(boardMap.get("contents"));
			boardDto.setFlg_top("N");
			boardDto.setFlg_delete("N");
			
			// 파일이 있을 경우에만
			if (fileNameOrig != null && fileNameOrig.length() != 0) {
						
				String dirUpload	= staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
				String newFolder	= "board";
						
				File srcFile = new File(dirUpload + File.separator + "temp" + File.separator + fileNameSave);
				File destDir = new File(dirUpload + "/" + newFolder);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				String dirUpload2	= staticProperties.getProperty("common.dirUpload2", "[UNDEFINED]");
				
				File srcFile2 = new File(dirUpload2 + File.separator + "temp" + File.separator + fileNameSave);
				File destDir2 = new File(dirUpload2 + "/" + newFolder);
				FileUtils.moveFileToDirectory(srcFile2, destDir2, true);
						
				boardDto.setFile_save("/" + newFolder + "/" + fileNameSave);
				boardDto.setFile_orig(fileNameOrig);
				
			}
			boardDto.setRegister(1);
		
			if (boardSrvc.insert(boardDto)) {
				request.setAttribute("script"	, "alert('등록 성공');");
				request.setAttribute("redirect"	, "/backoffice/board/noticeList.web");
			}
			else {
				request.setAttribute("script"	, "alert('등록 실패;')");
				request.setAttribute("redirect"	, "/backoffice/board/noticeList.web");
			}
			
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".noticeWriteProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/noticeModifyForm.web")
	public ModelAndView noticeModifyForm (HttpServletRequest request, HttpServletResponse response, String cd_bbs_type, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			BoardDto _boardDto = boardSrvc.select(boardDto); 

			mav.addObject("boardDto"	, _boardDto);

			mav.setViewName("/backoffice/board/noticeModifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".noticeModifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	} 
	
	@RequestMapping(value = "/backoffice/board/noticeModifyProc.web", method = RequestMethod.POST)
	public ModelAndView noticeModifyProc(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			Map<String, String> boardMap = upload(request, response);
			
			//boardDto.setCd_bbs_type(Integer.parseInt(boardMap.get("cd_bbs_type")));	
			boardDto.setCd_bbs_tab(boardMap.get("cd_bbs_tab"));
			boardDto.setTitle(boardMap.get("title"));
			boardDto.setContents(boardMap.get("contents"));
			boardDto.setFlg_top(boardMap.get("flg_top"));
			boardDto.setSeq_bbs(Integer.parseInt(boardMap.get("seq_bbs")));
			boardDto.setFlg_delete("N");
			boardDto.setRegister(1);
			
			if (boardSrvc.update(boardDto)) {
				request.setAttribute("script"	, "alert('수정 성공');");
				request.setAttribute("redirect"	, "/backoffice/board/noticeList.web");
			}
			else {
				request.setAttribute("script"	, "alert('수정 실패;')");
				request.setAttribute("redirect"	, "/backoffice/board/noticeList.web");
			}
			
			
			mav.setViewName("forward:/servlet/result.web");
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".noticeModifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/noticeView.web")
	public ModelAndView noticeView (HttpServletRequest request, HttpServletResponse response, String cd_bbs_type, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			BoardDto _boardDto = boardSrvc.select(boardDto); 

			mav.addObject("boardDto"	, _boardDto);
			
			mav.setViewName("/backoffice/board/noticeView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".noticeView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/inquiryList.web")
	public ModelAndView inquiryList (HttpServletRequest request, HttpServletResponse response, PagingDto pagingDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (pagingDto.getCd_bbs_type() == null || pagingDto.getCd_bbs_type() == 0) {
				pagingDto.setCd_bbs_type(3);
			}
			
			PagingListDto pagingListDto = boardSrvc.list(pagingDto);
			
			mav.addObject("paging"	, pagingListDto.getPaging());
			mav.addObject("list"	, pagingListDto.getList());
			
			mav.setViewName("/backoffice/board/inquiryList");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".inquiryList()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	

	@RequestMapping(value = "/backoffice/board/inquiryView.web")
	public ModelAndView inquiryView(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			BoardDto _boardDto = boardSrvc.select(boardDto);
			ReplyDto _replyDto = replySrvc.select(boardDto);
		
			
			mav.addObject("replyDto", _replyDto);

			mav.addObject("boardDto", _boardDto);
		
			mav.setViewName("/backoffice/board/inquiryView");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".inquiryView()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	@RequestMapping(value = "/backoffice/board/remove.web")
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response	, BoardDto boardDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (boardSrvc.deleteFlag(boardDto)) {
				request.setAttribute("script"	, "alert('삭제 성공');");
			}
			else {
				request.setAttribute("script"	, "alert('삭제 실패');");
			}
			
			request.setAttribute("redirect"	, "/backoffice/board/noticeList.web");
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".remove()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	
	@SuppressWarnings("rawtypes")
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String dirUpload = staticProperties.getProperty("common.dirUpload", "[UNDEFINED]");
		String dirUpload2 = staticProperties.getProperty("common.dirUpload2", "[UNDEFINED]");
		
		Map<String, String> boardMap = new HashMap<String, String>();
		
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
					boardMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
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
						boardMap.put("fileNameOrig", fileNameOrig);
						boardMap.put("fileNameSave", fileNameSave);
						
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
		return boardMap;
	}	
}