package com.taotao.search.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		//返回值对象
		SearchResult result = new SearchResult();
		//根据查询条件进行查询
		QueryResponse queryResponse = solrServer.query(query);
		//取出查询结果
		SolrDocumentList documentList = queryResponse.getResults();
		//取出查询结果的总条数，并且保存到返回值对象中
		long recordCount = documentList.getNumFound();
		result.setRecordCount(recordCount);
		
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		//商品返回列表
		List<Item> itemlist = new ArrayList<>();
		for (SolrDocument solrDocument : documentList) {
			Item item = new Item();
			item.setId((String)solrDocument.get("id"));
			//取高亮显示的结果
			List<String> listTitle = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (listTitle != null && listTitle.size()>0) {
				title = listTitle.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			itemlist.add(item);
		}
		result.setItemList(itemlist);
		return result;
	}
	
}
