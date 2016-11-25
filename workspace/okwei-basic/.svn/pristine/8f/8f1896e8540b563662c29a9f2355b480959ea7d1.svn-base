package com.okwei.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.vo.TransVO;
import com.okwei.cons.Kudi100;
import com.okwei.dao.IBaseDAO;
import com.okwei.service.ILogisticsService;

@Service
public class LogisticsService extends BaseService implements ILogisticsService {

	@Autowired
	private IBaseDAO baseDAO;

	@Override
	public TransVO getLogisticsInfo(String supOrderID) {
		// 订单信息
		String hql = "from OSupplyerOrder where supplierOrderId=? ";
		Object[] b = new Object[1];
		b[0] = supOrderID;
		OSupplyerOrder order = baseDAO.getUniqueResultByHql(hql, b);
		if (order != null) {
			TransVO t = new TransVO();
			t.setOrderNo(supOrderID);
			t.setSellerWeiid(order.getSupplyerId());
			t.setBuyerWeiid(order.getBuyerID()); 
			t.setOrderState(order.getState().intValue()); 
			// 物流公司名称
			String companyName = order.getDeliveryCompany();
			t.setTransCompany(companyName);
			// 物流公司代号
			String companyNo = order.getdComanpyNo();
			t.setTransCompanyNo(companyNo);
			// 物流单号
			String transNo = order.getDeliveryOrder();
			t.setTransNo(transNo);
			if (transNo == null || transNo == "") {
				return null;
			} else {
				// 首先直接
				if (companyNo != null && companyNo != "") {
					t.setWuliu(getInfoByNo(transNo, companyNo));
				} else if (companyName != null && companyName != "") {
					t.setWuliu(getInfoByName(transNo, companyName));
				} else {
					return null;
				}
				return t;
			}
		}
		return null;
	}

	@Override
	public String getInfoByNo(String orderNo, String companyNo) {
		String url = "http://api.ickd.cn/?id=" + Kudi100.API_KEY + "&secret=" + Kudi100.SECRET + "&com=" + companyNo + "&nu=" + orderNo + "&type="
				+ Kudi100.TYPE + "&encode=" + Kudi100.CODE + "&ord=" + Kudi100.ORD;
		StringBuilder json = new StringBuilder();
		try {
		    URL oracle = new URL(url);
		    URLConnection yc = oracle.openConnection();
		    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		    String inputLine = null;
		    while ((inputLine = in.readLine()) != null) {
			json.append(inputLine);
		    }
		    in.close();
		} catch (MalformedURLException e) {
		    return null;
		} catch (IOException e) {
		    return null;
		}
		return json.toString();
	}

	@Override
	public String getInfoByName(String orderNo, String companyName) {
		return getInfoByNo(orderNo, returnNo(companyName));
	}

	/**
	 * 根据物流公司返回物流公司代号
	 * 
	 * @param companyName
	 * @return
	 */
	private String returnNo(String companyName) {
		String companyNoString = null;
		try {
			String filename = "appSettings.xml";
			URL url = Configuration.class.getClassLoader().getResource(filename);
			String str = url.getFile();
			// 转换编码
			str = URLDecoder.decode(str, "utf-8");
			File conf = new File(str);
			SAXReader reader = new SAXReader();
			Document document = reader.read(conf);
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> chirdlist = root.element("transports").elements("trans");
			if (chirdlist != null && chirdlist.size() > 0) {
				for (Element ite : chirdlist) {
					String typeName = ite.attributeValue("typeName");
					if (typeName != null && typeName.equals(companyName)) {
						companyNoString = ite.attributeValue("typeNo");
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return companyNoString;
	}

}
