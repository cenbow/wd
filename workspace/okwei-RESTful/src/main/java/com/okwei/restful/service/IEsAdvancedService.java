package com.okwei.restful.service;

import java.util.List;

import com.okwei.restful.bean.vo.EsQuery;
import com.okwei.restful.bean.vo.EsResult;

public interface IEsAdvancedService {

	boolean createIndex(String indexName);

	boolean createType(String indexName, String indexType, String mappingString);

	String getMapping(String indexName, String typeName);

	boolean deleteIndex(String indexName);

	boolean deleteType(String indexName, String indexType);

	boolean deleteDoc(String indexId, String indexName, String indexType);

	boolean insertOrUpdateDoc(String indexId, Object indexObject, String indexName, String indexType);

	<T> T getDoc(Class<T> classType, String indexId, String indexName, String indexType);

	<T> List<T> getDocs(Class<T> classType, String indexName, String indexType, EsQuery esQuery);

	<T> EsResult<T> search(Class<T> classType, String indexName, String indexType, EsQuery esQuery);
}
