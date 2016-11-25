package com.okwei.restful.service.impl;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.okwei.restful.bean.vo.EsQuery;
import com.okwei.restful.bean.vo.EsResult;
import com.okwei.restful.bean.vo.ProductIndex;
import com.okwei.restful.service.IEsAdvancedService;

@Service
public class EsAdvancedService implements IEsAdvancedService {
	private static final String PWDKEY = "X-SCE-ES-PASSWORD";
	private static final String ES_INDEX_PASSWORD = "";
	private static Logger logger = LoggerFactory.getLogger(EsAdvancedService.class);

	@Autowired
	private JestClient jestClient;

	/**
	 * 创建索引
	 * 
	 * @param indexName
	 * @return
	 */
	public boolean createIndex(String indexName) {

		// Index setting 的设置方式有两种

		// u第一种方式: sing a JSON formatted string:
		/*
		 * String settings = "\"settings\" : {\n" +
		 * "        \"number_of_shards\" : 5,\n" +
		 * "        \"number_of_replicas\" : 1\n" + "    }\n";
		 * jestClient.execute(new
		 * CreateIndex.Builder("articles").settings(ImmutableSettings
		 * .builder().loadFromSource(settings).build().getAsMap()).build());
		 */

		// 第二种方式: using the SettingsBuilder helper class from Elasticsearch
		/*
		 * ImmutableSettings.Builder settingsBuilder =
		 * ImmutableSettings.settingsBuilder();
		 * settingsBuilder.put("number_of_shards",5);
		 * settingsBuilder.put("number_of_replicas",1); jestClient.execute(new
		 * CreateIndex
		 * .Builder("articles").settings(settingsBuilder.build().getAsMap
		 * ()).build());
		 */

		CreateIndex createIndex = new CreateIndex.Builder(indexName).build();
		try {
			JestResult result = jestClient.execute(createIndex);
			if (result == null || !result.isSucceeded()) {
				throw new Exception(result.getErrorMessage() + "创建索引失败!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 手动创建类型(map一旦定义创建，field只能新增，不能修改)
	 * 
	 * @param indexName
	 * @param indexType
	 * @param mappingString
	 * @return
	 */
	public boolean createType(String indexName, String indexType, String mappingString) {
		PutMapping.Builder builder = new PutMapping.Builder(indexName, indexType, mappingString);
		builder.setHeader(PWDKEY, getSecret());
		builder.refresh(true);
		try {
			JestResult result = jestClient.execute(builder.build());
			if (result == null || !result.isSucceeded()) {
				throw new RuntimeException(result.getErrorMessage() + "创建索引类型失败!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 获取索引类型mapping
	 * 
	 * @param typeName
	 *            类型名称
	 * @return
	 */
	public String getMapping(String indexName, String typeName) {
		GetMapping.Builder builder = new GetMapping.Builder();
		builder.addIndex(indexName).addType(typeName);
		builder.setHeader(PWDKEY, getSecret());
		try {
			JestResult result = jestClient.execute(builder.build());
			if (result != null && result.isSucceeded()) {
				return result.getSourceAsObject(JsonObject.class).toString();
			}
			logger.error("es get mapping Exception ", result.getErrorMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 删除类型
	 * 
	 * @param indexId
	 * @param indexName
	 * @param indexType
	 */
	public boolean deleteType(String indexName, String indexType) {
		Delete.Builder builder = new Delete.Builder(indexType);
		builder.setHeader(PWDKEY, getSecret());
		builder.refresh(true);
		Delete delete = builder.index(indexName).type(indexType).build();
		try {
			JestResult result = jestClient.execute(delete);
			if (result != null && result.isSucceeded()) {
				throw new RuntimeException(result.getErrorMessage() + "删除类型失败!");
			}
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
		return true;
	}

	/**
	 * 删除索引
	 * 
	 * @param indexId
	 * @param indexName
	 * @param indexType
	 */
	public boolean deleteIndex(String indexName) {
		Delete.Builder builder = new Delete.Builder(indexName);
		builder.setHeader(PWDKEY, getSecret());
		builder.refresh(true);
		Delete delete = builder.index(indexName).build();
		try {
			JestResult result = jestClient.execute(delete);
			if (result != null && result.isSucceeded()) {
				throw new RuntimeException(result.getErrorMessage() + "删除索引失败!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 删除文档
	 * 
	 * @param indexId
	 * @param indexName
	 * @param indexType
	 */
	public boolean deleteDoc(String indexId, String indexName, String indexType) {
		Delete.Builder builder = new Delete.Builder(indexId);
		builder.setHeader(PWDKEY, getSecret());
		builder.refresh(true);
		Delete delete = builder.index(indexName).type(indexType).build();
		try {
			JestResult result = jestClient.execute(delete);
			if (result != null && !result.isSucceeded()) {
				throw new RuntimeException(result.getErrorMessage() + "删除文档失败!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 插入或更新文档
	 * 
	 * @param indexId
	 * @param indexObject
	 *            : jsonString/map/pojo
	 * @param indexName
	 * @param indexType
	 * @return
	 */
	public boolean insertOrUpdateDoc(String indexId, Object indexObject, String indexName, String indexType) {
		Index.Builder builder = new Index.Builder(indexObject);
		builder.setHeader(PWDKEY, getSecret());
		builder.id(indexId);
		builder.refresh(true);
		Index index = builder.index(indexName).type(indexType).build();
		try {
			JestResult result = jestClient.execute(index);
			if (result != null && !result.isSucceeded()) {
				throw new RuntimeException(result.getErrorMessage() + "插入更新索引失败!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 通过id获取对象
	 */
	public <T> T getDoc(Class<T> classType, String indexId, String indexName, String indexType) {
		Get get = new Get.Builder(indexName, indexId).type(indexType).build();
		try {
			JestResult result = jestClient.execute(get);
			return result.getSourceAsObject(classType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过一次查询结果列表
	 */
	public <T> List<T> getDocs(Class<T> classType, String indexName, String indexType, EsQuery esQuery) {
		try {
			Search.Builder builder = new Search.Builder(esQuery.getQueryString());
			builder.addIndex(indexName).addType(indexType).setHeader(PWDKEY, getSecret()).setParameter("from", esQuery.getStart())
					.setParameter("size", esQuery.getSize());
			if (esQuery.getSort() != null) {
				builder.addSort(esQuery.getSort());
			}
			JestResult jestResult = jestClient.execute(builder.build());
			return jestResult.getSourceAsObjectList(classType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过一次查询就可获取查询的结果（分页）及总条数
	 * 
	 * @param classType
	 * @param indexName
	 * @param indexType
	 * @param esQuery
	 * @return
	 */
	public <T> EsResult<T> search(Class<T> classType, String indexName, String indexType, EsQuery esQuery) {
		try {
			Search.Builder builder = new Search.Builder(esQuery.getQueryString());
			builder.addIndex(indexName).addType(indexType).setHeader(PWDKEY, getSecret()).setParameter("from", esQuery.getStart())
					.setParameter("size", esQuery.getSize());
			if (esQuery.getSort() != null) {
				builder.addSort(esQuery.getSort());
			}

			Search search = builder.build();
			SearchResult searchResult = jestClient.execute(search);
			List<Hit<T, Void>> hits = searchResult.getHits(classType);

			List<T> list = new ArrayList<T>();
			for (Hit<T, Void> hit : hits) {
				T index = (T) hit.source;
				if(null!=hit.highlight){
					((ProductIndex) index).setProductTitle(hit.highlight.get("productTitle").get(0));
				}
				if(index != null)
					list.add(index);
			}

			int hitCount = searchResult.getJsonObject().get("hits").getAsJsonObject().get("total").getAsInt();
			EsResult<T> result = new EsResult<T>();
			result.setPageIndex(esQuery.getPageId());
			result.setPageSize(esQuery.getSize());
			result.setTotalCount(hitCount);
			result.setList(list);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*public <T> EsResult<T> search(Class<T> classType, String indexName, String indexType, EsQuery esQuery) {
		try {
			Search.Builder builder = new Search.Builder(esQuery.getQueryString());
			builder.addIndex(indexName).addType(indexType).setHeader(PWDKEY, getSecret()).setParameter("from", esQuery.getStart())
					.setParameter("size", esQuery.getSize());
			if (esQuery.getSort() != null) {
				builder.addSort(esQuery.getSort());
			}
			JestResult jestResult = jestClient.execute(builder.build());
			int hitCount = jestResult.getJsonObject().get("hits").getAsJsonObject().get("total").getAsInt();
			EsResult<T> result = new EsResult<T>();
			result.setPageIndex(esQuery.getPageId());
			result.setPageSize(esQuery.getSize());
			result.setTotalCount(hitCount);
			result.setList(jestResult.getSourceAsObjectList(classType));
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}*/

	protected static String getSecret() {
		long time = System.currentTimeMillis() / 1000;
		return time + "," + DigestUtils.md5Hex(time + ES_INDEX_PASSWORD).toUpperCase();
	}

}
