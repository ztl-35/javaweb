package com.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.returnResult.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import com.taotao.utils.ExceptionUtil;

@Controller
public class searchController {
	@Autowired
	private SearchService searchService;
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam("q") String queryString,
			@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows) {
		//首先判断查询条件不能为空
		if(StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件不能为空！");
		}
		SearchResult searchResult = null;
		try {
			//防止查询的字符串为乱码
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			searchResult = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(searchResult);
	}
}
