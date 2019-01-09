package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.returnResult.TaotaoResult;
import com.taotao.search.service.ItemService;

@Controller
public class itemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/manager/importAll")
	@ResponseBody
	public TaotaoResult importAllItems() {
		TaotaoResult result = itemService.importAllItem();
		return result;
	}
}
