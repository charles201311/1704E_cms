/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月15日 上午11:04:34 
 * @version: V1.0   
 */
package com.bobo.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bobo.cms.domain.Article;
import com.bobo.cms.domain.User;
import com.bobo.cms.domain.Vote;
import com.bobo.cms.service.ArticleService;
import com.bobo.cms.service.VoteService;
import com.bobo.cms.util.ArticleEnum;
import com.bobo.cms.util.CMSContant;
import com.bobo.cms.util.CookieUtil;
import com.bobo.cms.util.JsonUtil;
import com.bobo.cms.util.PageUtil;
import com.bobo.cms.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @ClassName: ArticleController
 * @Description: 文章模块
 * @author: charles
 * @date: 2019年8月15日 上午11:04:34
 */
@RequestMapping("article")
@Controller
public class ArticleController {

	@Value("${uppath}")
	String path;
//	CookieUtil data = new CookieUtil(new Gson());

	@Resource
	private ArticleService articleService;
	@Resource
	private VoteService voteService;

	Gson gson = new Gson();

	/**
	 * 更新访问量
	 * 
	 * @Title: updateHits
	 * @Description:
	 * @return: void
	 */
	@PostMapping("updateHits")
	public void updateHits(Article article, HttpServletRequest request, HttpServletResponse response) {
		// 获取hitId 的cookie,
		Cookie cookie = CookieUtil.getCookie(request, "hitId");
		// 如果已经更新过则不更新
		if (CookieUtil.isCheck(cookie, article.getId() + "", response)) {
			// 获取当前的文章点击量
			Article a2 = articleService.selectByPrimaryKey(article.getId());
			// //增加点击量+1
			article.setHits(a2.getHits() + 1);
			articleService.updateByPrimaryKeySelective(article);

		}

	}

	// 投票
	@ResponseBody
	@PostMapping("vote")
	public boolean vote(Vote vote, HttpServletRequest request, HttpServletResponse response) {

		try {
			// 获取cookie,如果已经投过票则不能再投了
			// Cookie cookie = CookieUtil.getCookie(request, "voteId");
			// 检查当前电脑的cookie中是否包含此 文章ID,如果包含也不能投票
			// if (CookieUtil.isCheck(cookie, vote.getArticleId() + "", response)) {
			HttpSession session = request.getSession();
			// 从session获取对象
			User user = (User) session.getAttribute(CMSContant.SESSION_GENERAL);
			// 投票人
			if (null != user) {
				vote.setUserId(user.getId());
			}
			// 执行投票
			voteService.insert(vote);
			return true;
			// }
			// return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查看投票
	 * 
	 * @Title: selectVote
	 * @Description: TODO
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping(value = "selectVote")
	public String selectVote(Integer id, Model model) {
//获取单个文章
		Article article = articleService.selectByPrimaryKey(id);

		// 把json转为java
		List<Map<String, Object>> list = JsonUtil.gsonToListMaps(article.getContent());

		model.addAttribute("article", article);

		model.addAttribute("mapList", list);

		// 查询投票结果
		List<Map> voteMap = voteService.select(id);

		model.addAttribute("voteMapList", voteMap);

		return "article/detailvote";

	}

	/**
	 * 发起投票
	 * 
	 * @Title: publishVote
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publishVote")
	public boolean publishVote(String[] descr, Article article, HttpServletRequest request) {

		List<Map> list = new ArrayList<>();

		// 遍历选项内容
		if (null != descr && descr.length > 0) {
			for (int i = 0; i < descr.length; i++) {
				Map<String, Object> map = new HashMap<>();
				// 封装选项
				char x = 'A';
				map.put(String.valueOf((char) (x + i)), descr[i]);
				list.add(map);
			}

		}
//调用枚举类,定义文章的类型
		article.setContentType(ArticleEnum.JSON.getCode());
		// 把java转为json
		String content = JsonUtil.gsonString(list);
		// 封装文章内容
		article.setContent(content);

		article.setHits(0);
		article.setStatus(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		HttpSession session = request.getSession(false);
		if (null == session)
			return false;
		User user = (User) request.getSession().getAttribute(CMSContant.SESSION_GENERAL);
		if (null != user)
			article.setUserId(user.getId());
		article.setDeleted(0);
		article.setHot(0);

		return articleService.insertSelective(article) > 0;

	}

	/**
	 * 去发起投票
	 * 
	 * @Title: publishVote
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("publishVote")
	public String publishVote() {

		return "article/publishvote";
	}

	/**
	 * 获取所有的图片集
	 * 
	 * @Title: selectsPic
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("selectPic")
	public String selectsPic(Model model, Integer id) {
		List<ArticleVO> list = new ArrayList<>();

		Article article = articleService.selectByPrimaryKey(id);
		// 获取json串
		String string = article.getContent();
		// 解析字符串
		JsonArray jsonArray = new JsonParser().parse(string).getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
			// 把json串转为java对象
			ArticleVO vo = gson.fromJson(jsonElement, ArticleVO.class);
			list.add(vo);
		}

		model.addAttribute("images", list);

		return "article/detailpic";// 显示图片明细

	}

	/**
	 * 去发布图片集
	 * 
	 * @Title: publishPic
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("publishPic")
	public String publishPic() {

		return "article/publishpic";

	}

	/**
	 * 去发布图片集
	 * 
	 * @Title: publishPic
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@PostMapping("publishPic")
	public boolean publishPic(ArticleVO article, String[] descr, MultipartFile[] files, HttpServletRequest request) {

		// 发布文章类型

		article.setContentType(ArticleEnum.IMAGE.getCode());

		List<ArticleVO> list = new ArrayList<>();
		String newFilename = "";

		if (files.length > 0) {
			int i = 0;
			for (MultipartFile file : files) {

				if (!file.isEmpty()) {

					ArticleVO vo = new ArticleVO();

					//String path = "d:/pic/";// 文件上传的路径

					// 获取原始名称
					String filename = file.getOriginalFilename();
					// 为了防止文件名称重复.采用随机的UUID的值
					newFilename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));

					File f = new File(path + newFilename);

					try {
						file.transferTo(f);// 把文件写入硬盘
						// article.setPicture(newFilename);
						// 封装图片描述
						vo.setDescr(descr[i]);
						i++;
						// 封装的url
						vo.setUrl(newFilename);
						list.add(vo);

					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}

		// 图片集封面
		article.setPicture(newFilename);
		String json = gson.toJson(list);
		//
		article.setContent(json);

		article.setHits(0);
		article.setStatus(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		HttpSession session = request.getSession(false);
		if (null == session)
			return false;
		User user = (User) request.getSession().getAttribute(CMSContant.SESSION_GENERAL);
		article.setUserId(user.getId());
		article.setDeleted(0);
		article.setHot(0);

		return articleService.insertSelective(article) > 0;

	}

	/**
	 * 
	 * @Title: check
	 * @Description: 审核文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("check")
	public boolean check(Article article) {
		return articleService.updateByPrimaryKeySelective(article) > 0;

	}

	/**
	 * 显示文章明细
	 * 
	 * @Title: select
	 * @Description: TODO
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("select")
	public String select(Integer id, Model model) {
		Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "article/detail";

	}

	/**
	 * 显示文章明细
	 * 
	 * @Title: select
	 * @Description: TODO
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("selectByAdmin")
	public String selectByAdmin(Integer id, Model model) {
		Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "admin/article/detail";

	}

	/**
	 * 管理员 查询文章标题
	 * 
	 * @Title: select
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param article
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("selectsByAdmin")
	public String selectsByAdmin(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize, Article article, Model model) {
		PageInfo<Article> info = articleService.selects(article, page, pageSize);

		String pages = PageUtil.page(page, info.getPages(), "/article/selectsByAdmin", pageSize);

		model.addAttribute("articles", info.getList());
		model.addAttribute("pages", pages);
		model.addAttribute("article", article);
		return "admin/article/titles";

	}

	/**
	 * 返回到文章标题
	 * 
	 * @Title: select
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param article
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("selects")
	public String select(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize, Article article, Model model) {
		PageInfo<Article> info = articleService.selects(article, page, pageSize);

		String pages = PageUtil.page(page, info.getPages(), "/article/selects", pageSize);

		model.addAttribute("articles", info.getList());
		model.addAttribute("pages", pages);
		model.addAttribute("article", article);
		return "article/titles";

	}

	/**
	 * 去发布文章页面
	 * 
	 * @Title: publish
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish(Model model) {
		//文章类型
		ArticleEnum[] types = ArticleEnum.values();
		model.addAttribute("types", types);
		return "article/publish";
	}

	/**
	 * 发布文章
	 * 
	 * @Title: publish
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article article, MultipartFile file, HttpServletRequest request) {
		// 文章类型
		article.setContentType(ArticleEnum.HTML.getCode());
		if (!file.isEmpty()) {
			//String path = "d:/pic/";// 文件上传的路径
			// 获取原始名称
			String filename = file.getOriginalFilename();
			// 为了防止文件名称重复.采用随机的UUID的值
			String newFilename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));

			File f = new File(path + newFilename);

			try {
				file.transferTo(f);// 把文件写入硬盘
				article.setPicture(newFilename);

			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
		
		
		
		article.setHits(0);
		article.setStatus(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		HttpSession session = request.getSession(false);
		if (null == session)
			return false;
		User user = (User) request.getSession().getAttribute(CMSContant.SESSION_GENERAL);
		article.setUserId(user.getId());
		article.setDeleted(0);
		article.setHot(0);

		return articleService.insertSelective(article) > 0;

	}

}
