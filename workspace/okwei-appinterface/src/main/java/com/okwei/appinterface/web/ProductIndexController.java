//package com.okwei.appinterface.web;
//
//
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
///**
// * ES Search搜索产品
// * 
// * @author xiehz
// *
// */
//@RequestMapping("/productIndex")
//@RestController
//public class ProductIndexController {
//
//	private static final Log logger = LogFactory.getLog(ProductIndexController.class);
//
//	
//
//	@Autowired
//	private IEsAdvancedService esAdvancedService;
//
//
//
//	/**
//	 * 索引产品
//	 * 
//	 * @param product
//	 * @return
//	 */
//	@RequestMapping("/insertOrUpdate")
//	public String editProduct(ProductIndex productIndex) {
//		boolean result = esAdvancedService.insertOrUpdateDoc(productIndex.getProductId().toString(), productIndex, INDEX_NAME, INDEX_TYPE);
//		Map<String, String> map = new HashMap<String, String>();
//		if (result) {
//			map.put("result", "succeed");
//		} else {
//			map.put("result", "fail");
//		}
//
//		JSONObject jsonObject = JSONObject.fromObject(map);
//		return jsonObject.toString();
//	}
//
//	/**
//	 * 删除产品索引
//	 * 
//	 * @param productId
//	 * @return
//	 */
//	@RequestMapping("/delete")
//	public String deleteProduct(Long productId) {
//		boolean result = esAdvancedService.deleteDoc(productId.toString(), INDEX_NAME, INDEX_TYPE);
//		Map<String, String> map = new HashMap<String, String>();
//		if (result) {
//			map.put("result", "succeed");
//		} else {
//			map.put("result", "fail");
//		}
//		JSONObject jsonObject = JSONObject.fromObject(map);
//		return jsonObject.toString();
//	}
//
//	
//}
