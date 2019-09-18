/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinksController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月22日 上午10:33:27 
 * @version: V1.0   
 */
package com.bobo.cms.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bobo.cms.domain.Links;
import com.bobo.cms.service.LinksService;
import com.bobo.cms.util.CMSRuntimeException;
import com.bobo.cms.util.PageUtil;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName: LinksController
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月22日 上午10:33:27
 */
@RequestMapping("links")
@Controller
public class LinksController {

	@Resource
	private LinksService linksService;

	/**
	 * 显示友情链接列表
	 * 
	 * @Title: selects
	 * @Description: TODO
	 * @param model
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@GetMapping("selects")
	public String selects(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize) {

		PageInfo<Links> info = linksService.selects(page, pageSize);

		String pages = PageUtil.page(page, info.getPages(), "/links/selects", pageSize);

		model.addAttribute("links", info.getList());
		model.addAttribute("pages", pages);

		return "admin/links/links";

	}

	/**
	 * 去添加
	 * 
	 * @Title: add
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("add")
	public String add() {

		return "admin/links/add";

	}

	@ResponseBody
	@PostMapping(value = "save",produces = "text/html; charset=UTF-8")
	public String add(Links links, Model model) {
         links.setCreated(new Date());
         
		String msg = "ok";
		try {
			linksService.insert(links);
		} catch (CMSRuntimeException e) {

			msg = e.getMessage();
			

		}

		catch (Exception e) {
			msg = "系统错误";
		}

		return msg;

	}

}
