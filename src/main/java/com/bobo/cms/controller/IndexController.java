/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: IndexController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月20日 上午10:59:49 
 * @version: V1.0   
 */
package com.bobo.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bobo.cms.domain.Article;
import com.bobo.cms.domain.Category;
import com.bobo.cms.domain.Channel;
import com.bobo.cms.domain.Links;
import com.bobo.cms.service.ArticleService;
import com.bobo.cms.service.ChannelService;
import com.bobo.cms.service.LinksService;
import com.bobo.cms.util.ArticleEnum;
import com.bobo.cms.util.PageUtil;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: IndexController 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月20日 上午10:59:49  
 */

@Controller
public class IndexController {
	
	private Logger log = Logger.getLogger(IndexController.class);
	
	@Resource
	private ChannelService channelService;

	@Resource
	private ArticleService articleService;


	@Resource
	private LinksService linksService;
	
	
	@GetMapping(value = {"","/","index"})
	public String index(Model model,Article article,
			@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "5")Integer pageSize) {
		
		log.trace("index ........... start ");
		
		//开始时间
		long l = System.currentTimeMillis();
		
		article.setStatus(1);//只显示审核通过文章
		
		Thread t1 =null; //定义线程
		Thread t2 =null;
		Thread t3 =null;
		Thread t4 =null;
		Thread t5 =null;
		Thread t6 =null;
		Thread t7 =null;
		
		//线程1 获取  //左侧的栏目
		t1  =new  Thread(new Runnable() {
			
			@Override
			public void run() {
				//左侧的栏目
				List<Channel> channels = channelService.selects();
				model.addAttribute("channels", channels);	
			}
		});
		
	    //线程2 //热点文章
		t2  =new  Thread(new Runnable() {
			
			@Override
			public void run() {
				if(article.getChannelId()==null) {
					//热点文章
					article.setHot(1);//热点文章
					PageInfo<Article> hotArticles = articleService.selects(article, page, pageSize);
					 String pages = PageUtil.page(page, hotArticles.getPages(), "/", pageSize);
					model.addAttribute("hotArticles", hotArticles.getList());
					model.addAttribute("pages", pages);
				}
			}
		});
	
	   //线程 3 获取栏目分类
		t3 = new  Thread(new Runnable() {
			
			@Override
			public void run() {
				if(article.getChannelId()!=null) {
					   //显示栏目下的分类
						List<Category> categorys = channelService.selectsByCid(article.getChannelId());
						model.addAttribute("categorys", categorys);
						
						PageInfo<Article> pageInfo = articleService.selects(article, page, pageSize);
						//栏目或者分类下的文章
						model.addAttribute("articles", pageInfo.getList());
						String url="/?channelId="+article.getChannelId();
						//如果分类ID 不为null 则查询条件加上
						if(article.getCategoryId()!=null)
							url+="&categoryId="+article.getCategoryId();
						  String pages = PageUtil.page(page, pageInfo.getPages(),url, pageSize);
						   model.addAttribute("pages", pages);
					}
			}
		});
		
		// 线程4 
	t4 =	new Thread(new Runnable() {
			
			@Override
			public void run() {
				//最新10篇文章
				Article a = new Article();
				a.setStatus(1);
				
				PageInfo<Article> pageInfo = articleService.selects(a, 1, 10);
				
				model.addAttribute("lasts", pageInfo.getList());
			}
		});
	
	
	t5 =new  Thread(new Runnable() {
		
		@Override
		public void run() {
			//友情链接
			PageInfo<Links> links = linksService.selects(1, 10);
			model.addAttribute("links", links.getList());
		}
	});
	
	
	t6 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			Article a = new Article();
			//根据查询条件获取图片集
	    	a.setContentType(ArticleEnum.IMAGE.getCode());
	    	PageInfo<Article> info = articleService.selects(a, 1, 10);
	    	
	    	model.addAttribute("images", info.getList());
			
		}
	});
	
t7 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			Article a = new Article();
		  //获取json类型的文章..发起的投票文章
	    	a.setContentType(ArticleEnum.JSON.getCode());
	    	PageInfo<Article> info = articleService.selects(a, 1, 10);
	    	
	    	model.addAttribute("votes", info.getList());
			
		}
	});
	//启动线程
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	t5.start();
	t6.start();
	t7.start();
	
	
	//让子线程执行完在执行 主线程
	
	try {
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		t7.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		long l2 = System.currentTimeMillis();
		
		System.out.println("花费时间:"+(l2 -l));
		
		return "index/index";
	}
}
