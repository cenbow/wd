package com.okwei.restful.web;

import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.filteredQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import io.searchbox.core.search.sort.Sort;
import io.searchbox.core.search.sort.Sort.Sorting;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.restful.bean.dto.ProductDto;
import com.okwei.restful.bean.vo.EsQuery;
import com.okwei.restful.bean.vo.EsResult;
import com.okwei.restful.bean.vo.ProductIndex;
import com.okwei.restful.common.PropManager;
import com.okwei.restful.service.IEsAdvancedService;

/**
 * ES Search搜索产品
 * 
 * @author xiehz
 *
 */
@RequestMapping("/productIndex")
@RestController
public class ProductIndexController {

	private static final Log logger = LogFactory.getLog(ProductIndexController.class);

	private static final String INDEX_NAME = PropManager.getInstance().getProperty("restful.indexname"); // 索引
	private static final String INDEX_TYPE = PropManager.getInstance().getProperty("restful.indextype");// 索引类型
	private static final int DEFAULT_PAGEINDEX = 1;// 默认第一页
	private static final int DEFAULT_PAGESIZE = 10;// 默认每页显示10行

	private String basePath = PropManager.getInstance().getProperty("restful.extdicpath");

	@Autowired
	private IEsAdvancedService esAdvancedService;

	/**
	 * 搜索产品接口
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("/search")
	public EsResult<ProductIndex> searchProduct(ProductDto dto) {
//		logger.info("product search starting......");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder bool = boolQuery();

		String keyword = dto.getContent();
		if (null == dto.getContent() || StringUtils.isEmpty(dto.getContent())) {
			keyword = "*";
		}
		QueryStringQueryBuilder queryBuilder1 = new QueryStringQueryBuilder(keyword);
		queryBuilder1.analyzer("ik").field("productTitle");
		bool.must(queryBuilder1);
		// 高亮显示
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.field("productTitle").preTags("<span style=\"color:red\">").postTags("</span>");
		searchSourceBuilder.highlight(highlightBuilder);

		if (null != dto.getbType()) {
			bool.must(termQuery("firstClass", dto.getbType()));
		}
		if (null != dto.getmType()) {
			bool.must(termQuery("secondClass", dto.getmType()));
		}
		if (null != dto.getsType()) {
			bool.must(termQuery("thridClass", dto.getsType()));
		}
		if (null != dto.getMarketId()) {
			bool.must(termQuery("marketId", dto.getMarketId()));
		}
		if (null != dto.getBrandId()) {
			bool.must(termQuery("brandId", dto.getBrandId()));
		}
		if (null != dto.getWhere()) {
			bool.must(termQuery("where", dto.getWhere()));
		}
		if (null != dto.getSupWeiId()) {
			bool.must(termQuery("supplierWeiId", dto.getSupWeiId()));
		}
		//源供应商类型
		if(null != dto.getSupType())
		{
			//云商通
			if(dto.getSupType() == 1)
			{
				bool.must(termQuery("isYun",1));
			}
			//批发号
			if(dto.getSupType() == 2)
			{
				bool.must(termQuery("isBatch",1));
			}
			//平台号
			if(dto.getSupType() == 3)
			{
				bool.must(termQuery("isPlatform",1));
			}
			//品牌号
			if(dto.getSupType() == 4)
			{
				bool.must(termQuery("isBrandform",1));
			}
		}
		//pType 是否支持落地价
		if(null != dto.getpType() && dto.getpType()!=0)
		{
			bool.must(termQuery("pType", dto.getpType()));
		}
		if(null != dto.getActivity() && dto.getActivity()!=0)
		{
			bool.must(termQuery("isActivity", dto.getActivity()));
		}
		// 排序
		Sort sort = null;
		if (null != dto.getOrderBy()) {
			switch (dto.getOrderBy()) {
			case dcreatetime:
				sort = new Sort("createTime", Sorting.DESC);
				break;
			case acreatetime:
				sort = new Sort("createTime", Sorting.ASC);
				break;
			case dcount:
				sort = new Sort("totalCount", Sorting.DESC);
				break;
			case acount:
				sort = new Sort("totalCount", Sorting.ASC);
				break;
			case dprice:
				sort = new Sort("defaultPrice", Sorting.DESC);
				break;
			case aprice:
				sort = new Sort("defaultPrice", Sorting.ASC);
				break;
			case dscore:
				sort = new Sort("score", Sorting.DESC);
				break;
			default:
				break;
			}
		}
		// 价格区间过滤
		QueryBuilder queryBuilder = bool;

		if (null != dto.getSprice() && null != dto.getEprice()) {
			queryBuilder = filteredQuery(bool, rangeFilter("defaultPrice").from(dto.getSprice()).to(dto.getEprice()).includeLower(true).includeUpper(false));
		} else if (null != dto.getSprice()) {
			queryBuilder = filteredQuery(bool, rangeFilter("defaultPrice").from(dto.getSprice()).includeLower(true).includeUpper(false));
		} else if (null != dto.getEprice()) {
			queryBuilder = filteredQuery(bool, rangeFilter("defaultPrice").to(dto.getEprice()).includeLower(true).includeUpper(false));
		}
		searchSourceBuilder.query(queryBuilder);
		int pageIdex = dto.getPageIndex() == null ? DEFAULT_PAGEINDEX : dto.getPageIndex();
		int pageSize = dto.getPageSize() == null ? DEFAULT_PAGESIZE : dto.getPageSize();
		EsQuery esQuery = null;
		if (null != sort) {
			esQuery = EsQuery.buildQuery(searchSourceBuilder.toString(), pageIdex, pageSize, sort);
		} else {
			esQuery = EsQuery.buildQuery(searchSourceBuilder.toString(), pageIdex, pageSize);
		}
		return esAdvancedService.search(ProductIndex.class, INDEX_NAME, INDEX_TYPE, esQuery);
	}

	/**
	 * 索引产品
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping("/insertOrUpdate")
	public String editProduct(ProductIndex productIndex) {
		boolean result = esAdvancedService.insertOrUpdateDoc(productIndex.getProductId().toString(), productIndex, INDEX_NAME, INDEX_TYPE);
		Map<String, String> map = new HashMap<String, String>();
		if (result) {
			map.put("result", "succeed");
		} else {
			map.put("result", "fail");
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}

	/**
	 * 删除产品索引
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteProduct(Long productId) {
		boolean result = esAdvancedService.deleteDoc(productId.toString(), INDEX_NAME, INDEX_TYPE);
		Map<String, String> map = new HashMap<String, String>();
		if (result) {
			map.put("result", "succeed");
		} else {
			map.put("result", "fail");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}

	/**
	 * 添加扩展字典
	 * 
	 * @param word
	 * @return
	 */
	@RequestMapping("/addExtDic")
	public String addExtDic(String word) {
		File srcFile = new File(basePath + "ext_dict.txt");
		boolean result = false;
		try {
			FileUtils.writeStringToFile(srcFile, "\r\n" + word, "UTF-8", true);
			result = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		if (result) {
			map.put("result", "succeed");
		} else {
			map.put("result", "fail");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}

	/**
	 * 添加扩展停止字典
	 * 
	 * @param word
	 * @return
	 */
	@RequestMapping("/addStopWord")
	public String addStopWord(String word) {
		File srcFile = new File(basePath + "ext_stopword.txt");
		boolean result = false;
		try {
			FileUtils.writeStringToFile(srcFile, "\r\n" + word, "UTF-8", true);
			result = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		if (result) {
			map.put("result", "succeed");
		} else {
			map.put("result", "fail");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
	}

	@RequestMapping("/getExtDict")
	public String getExtDict(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		StringBuilder sb = new StringBuilder();

		File srcFile = new File(basePath + "ext_dict.txt");
		List<String> wordList = null;
		try {
			wordList = FileUtils.readLines(srcFile, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 获取所有分词，这里可以改进使用缓存等。
		String eTag = request.getHeader("If-None-Match");
		Long modified = request.getDateHeader("If-Modified-Since");

		// 设置头
		if (null == modified || -1 == modified) {
			// 如果没有，则使用当前时间
			modified = System.currentTimeMillis();
		}

		// 设置头信息。
		String oldEtag = wordList.size() + "";
		response.setDateHeader("Last-Modified", Long.valueOf(modified));
		response.setHeader("ETags", wordList.size() + "");

		if (!oldEtag.equals(eTag)) {
			// 拼装结果
			for (String tempWord : wordList) {
				// 分词之间以换行符连接
				if (StringUtils.isNotEmpty(sb.toString())) {
					sb.append("\r\n");
				}
				sb.append(tempWord);
			}
			result = sb.toString();
			// 更新时间
			response.setDateHeader("Last-Modified", System.currentTimeMillis());
		}
		return result;
	}

	@RequestMapping("/getExtStopWord")
	public String getExtStopWord(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		StringBuilder sb = new StringBuilder();

		File srcFile = new File(basePath + "ext_stopword.txt");
		List<String> wordList = null;
		try {
			wordList = FileUtils.readLines(srcFile, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 获取所有分词，这里可以改进使用缓存等。
		String eTag = request.getHeader("If-None-Match");
		Long modified = request.getDateHeader("If-Modified-Since");

		// 设置头
		if (null == modified || -1 == modified) {
			// 如果没有，则使用当前时间
			modified = System.currentTimeMillis();
		}

		// 设置头信息。
		String oldEtag = wordList.size() + "";
		response.setDateHeader("Last-Modified", Long.valueOf(modified));
		response.setHeader("ETags", wordList.size() + "");

		if (!oldEtag.equals(eTag)) {
			// 拼装结果
			for (String tempWord : wordList) {
				// 分词之间以换行符连接
				if (StringUtils.isNotEmpty(sb.toString())) {
					sb.append("\r\n");
				}
				sb.append(tempWord);
			}
			result = sb.toString();
			// 更新时间
			response.setDateHeader("Last-Modified", System.currentTimeMillis());
		}
		return result;
	}

}
