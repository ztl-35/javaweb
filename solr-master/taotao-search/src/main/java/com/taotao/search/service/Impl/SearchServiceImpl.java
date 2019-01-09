package com.taotao.search.service.Impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
/**
 * 搜索服务service
 * @author m1395
 *
 */
@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchDao searchDao;
	
	//service层主要在进行查询设置的补充，真正进行查询结果处理的是在dao层
	@Override
	public SearchResult search(String query, int page, int rows) throws Exception {
		//创建查询对象
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery(query);
		//设置分页
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		//设置默认搜素域
		solrQuery.set("df","item_keywords");
		//设置高亮显示
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		
		//执行查询:service主要在补充这部分的查询设置
		SearchResult searchResult = searchDao.search(solrQuery);
		
		//计算查询结果总页数
		int pageCount = (int) (searchResult.getRecordCount() / rows);
		if ((searchResult.getRecordCount() % rows)>0) {
			pageCount++; 
		}
		
		//将dao层没有补充的返回值对象补充完整
		//总页数和当前页码
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);
		return searchResult;
	}
	
}
