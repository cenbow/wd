package com.okwei.appinterface.service.impl.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.bean.vo.order.BigOLBookAssist;
import com.okwei.appinterface.bean.vo.order.BookAssistModel;
import com.okwei.appinterface.bean.vo.order.OLBookAssist;
import com.okwei.appinterface.bean.vo.order.OrderList;
import com.okwei.appinterface.bean.vo.order.OrderServiceListVO;
import com.okwei.appinterface.bean.vo.order.PriceLimit;
import com.okwei.appinterface.bean.vo.order.ProductOrderChild;
import com.okwei.appinterface.bean.vo.order.ProductOrderDetailsModel;
import com.okwei.appinterface.bean.vo.order.ProductOrderModel;
import com.okwei.appinterface.bean.vo.order.TOrderBackTotalVO;
import com.okwei.appinterface.bean.vo.order.UCustomerAddress;
import com.okwei.appinterface.dao.IProductDao;
import com.okwei.appinterface.service.BaseCommonService;
import com.okwei.appinterface.service.order.IOrderService;
import com.okwei.bean.domain.DAgentInfo;
import com.okwei.bean.domain.DBrandSupplier;
import com.okwei.bean.domain.OBookAssist;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.domain.UWeiCoinLog;
import com.okwei.bean.enums.BookAssistChangePriceType;
import com.okwei.bean.enums.BookPayTypeEnum;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.bean.enums.SupplyOrderType;
import com.okwei.bean.enums.TailPayTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.order.ParamOrderSearch;
import com.okwei.bean.vo.order.ProductOrderVO;
import com.okwei.bean.vo.order.SupplyBookOrderParam;
import com.okwei.bean.vo.order.SupplyOrderDetailsVO;
import com.okwei.common.CommonMethod;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.agent.IDAgentMgtDao;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.dao.user.IAgentDAO;
import com.okwei.service.IRegionService;
import com.okwei.service.address.IBasicAdressService;
import com.okwei.service.order.IBasicOrdersService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.BitOperation;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ParseHelper;

/**
 * @author fuhao
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	public IBasicOrdersService basicOrdersService;

	@Autowired
	public IBasicOrdersDao basicOrdersDao;

	@Autowired
	public BaseCommonService baseCommonService;

	@Autowired
	private IRegionService regionService;

	@Autowired
	private IProductDao productDao;

	@Autowired
	public IBasicAdressService basicAdressService;
	@Autowired
	public CommonMethod commonMethod;
	@Autowired
	private IDAgentMgtDao agentMgtDao;

	/**
	 * 新增收货地址
	 * 
	 * @param modelJson
	 * @param weiId
	 * @return
	 */
	public ReturnModel insertAddress(String modelJson, long weiId) {
		ReturnModel returnModel = new ReturnModel();// 定义返回实体

		// 数据转换BEGIN
		JSONObject jsonObj = JSONObject.fromObject(modelJson);
		if (jsonObj == null) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("model json串错误！");
			return returnModel;
		}
		// 收货地址
		UCustomerAddr address = new UCustomerAddr();
		// 组合收货地址信息
		try {
			address.setReceiverName(jsonObj.getString("receiverName"));
			address.setProvince(jsonObj.getInt("province"));
			address.setCity(jsonObj.getInt("city"));
			address.setDistrict(jsonObj.getInt("district"));
			address.setStreet(jsonObj.getInt("street"));
			address.setDetailAddr(String.valueOf(jsonObj.get("detailAddr")));
			address.setMobilePhone(String.valueOf(jsonObj.get("mobilePhone")));
			address.setQq(String.valueOf(jsonObj.get("qq")));
			address.setIsDefault((short) jsonObj.getInt("isDefault"));
			// 是否落地店
			address.setIsShopAddress(jsonObj.getInt("isStoreAddress"));
		} catch (Exception e) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("model参数错误！" + e.getMessage());
			return returnModel;
		}

		// 更新数据
		try {
			address.setWeiId(weiId);
			basicAdressService.saveOrUpdateAdd(address);
			returnModel.setStatu(ReturnStatus.Success);// 返回状态
			returnModel.setStatusreson("新增成功");// 状态描述
		} catch (Exception e) {
			returnModel.setStatu(ReturnStatus.SystemError);// 返回状态
			returnModel.setStatusreson(e.getMessage());// 状态描述
		}

		return returnModel;
	}

	/**
	 * 修改收货地址
	 * 
	 * @param modelJson
	 *            收货地址实体Json字符串
	 * @return
	 */
	public ReturnModel updateAddress(String modelJson, long weiId) {
		ReturnModel returnModel = new ReturnModel();// 定义返回实体

		// 数据转换BEGIN
		JSONObject jsonObj;

		jsonObj = JSONObject.fromObject(modelJson);
		if (jsonObj == null) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("model json串错误！");
			return returnModel;
		}

		if (jsonObj.containsKey("addressID") == false) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("model json串错误，addressID key不能为空！");
			return returnModel;
		}
		// 收货地址
		UCustomerAddr address = getCustomerAddressById(jsonObj.getInt("addressID"), weiId);
		if (address == null || address.getCaddrId() <= 0) {
			// 数据不存在
			returnModel.setStatu(ReturnStatus.DataError);// 返回状态
			returnModel.setStatusreson("数据不存在");// 状态描述
			return returnModel;
		}
		// 组合收货地址信息
		try {
			address.setCaddrId(jsonObj.getInt("addressID"));
			address.setReceiverName(jsonObj.getString("receiverName"));
			address.setProvince(jsonObj.getInt("province"));
			address.setCity(jsonObj.getInt("city"));
			address.setDistrict(jsonObj.getInt("district"));
			address.setStreet(jsonObj.getInt("street"));
			address.setDetailAddr(jsonObj.getString("detailAddr"));
			address.setMobilePhone(String.valueOf(jsonObj.get("mobilePhone")));
			address.setQq(String.valueOf(jsonObj.get("qq")));
			address.setIsDefault((short) jsonObj.getInt("isDefault"));
			// 是否落地店
			address.setIsShopAddress(jsonObj.getInt("isStoreAddress"));
			address.setWeiId(weiId);
		} catch (Exception e) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("model参数错误！" + e.getMessage());
			return returnModel;
		}
		if (null == address.getCaddrId() || address.getCaddrId().intValue() < 0) {
			returnModel.setStatu(ReturnStatus.ParamError);// 返回状态
			returnModel.setStatusreson("修改地址Id不能为空！");// 状态描述
			return returnModel;
		}

		basicAdressService.saveOrUpdateAdd(address);
		returnModel.setStatu(ReturnStatus.Success);// 返回状态
		returnModel.setStatusreson("修改成功");// 状态描述
		return returnModel;
	}

	/**
	 * 删除收货地址
	 * 
	 * @param modelJson
	 *            收货地址实体Json字符串
	 * @return
	 */
	public ReturnModel deleteAddress(String modelJson, long weiId) {
		ReturnModel returnModel = new ReturnModel();// 定义返回实体

		// 数据转换BEGIN
		JSONObject jsonObj;

		jsonObj = JSONObject.fromObject(modelJson);
		if (jsonObj == null) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("model json串错误！");
			return returnModel;
		}

		if (jsonObj.containsKey("addressID") == false) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("model json串错误，addressID key不能为空！");
			return returnModel;
		}
		// 收货地址
		UCustomerAddr address = getCustomerAddressById(jsonObj.getInt("addressID"), weiId);
		if (address == null || address.getCaddrId() <= 0) {
			// 数据不存在
			returnModel.setStatu(ReturnStatus.DataError);// 返回状态
			returnModel.setStatusreson("数据不存在");// 状态描述
			return returnModel;
		}

		basicAdressService.deleteAddress(weiId, address.getCaddrId());
		returnModel.setStatu(ReturnStatus.Success);// 返回状态
		returnModel.setStatusreson("修改成功");// 状态描述
		return returnModel;
	}

	/**
	 * 修改收货地址
	 * 
	 */
	public void updateAddress(UCustomerAddress address) {
		if (address.getIsStoreAddress() == 1) {
			String notDefaultHQL = " update UProductShop as t set t.province=:province,t.city=:city,t.area=:area,t.address=:address where t.weiId=:weiId";// 查询语句
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("province", address.getProvince());
			map.put("city", address.getCity());
			map.put("area", address.getDistrict());
			// 省市区
			String addresst = regionService.getNameByCode(address.getProvince()) + regionService.getNameByCode(address.getCity()) + regionService.getNameByCode(address.getDistrict());
			map.put("address", addresst);
			map.put("weiId", address.getWeiID());
			basicOrdersDao.executeHqlByMap(notDefaultHQL, map);
		}
		if (address.getAddressID() > 0) {
			if (address.getIsDefault() != null && address.getIsDefault().shortValue() == 1) {
				// 如果是默认收货地址
				// 将其他收货地址更新为非默认
				String notDefaultHQL = " update UCustomerAddr as t set t.updateTime=?,t.isDefault=? where t.weiId=?";// 查询语句
				Object[] notDefaultb = new Object[3];// 参数列表
				notDefaultb[0] = new Date();
				notDefaultb[1] = (short) 0;
				notDefaultb[2] = address.getWeiID();
				basicOrdersDao.executeHql(notDefaultHQL, notDefaultb);
			}

			String hql = " update UCustomerAddr as t set t.receiverName=?,t.province=?,t.city=?,t.district=?,t.street=?,t.detailAddr=?,t.mobilePhone=?,t.qq=?,t.updateTime=?,t.isDefault=? where t.caddrId=?";// 查询语句
			Object[] b = new Object[11];// 参数列表
			b[0] = address.getReceiverName();
			b[1] = address.getProvince();
			b[2] = address.getCity();
			b[3] = address.getDistrict();
			b[4] = address.getStreet();
			b[5] = address.getDetailAddr();
			b[6] = address.getMobilePhone();
			b[7] = address.getQq();
			b[8] = new Date();
			b[9] = address.getIsDefault();
			b[10] = address.getAddressID();
			basicOrdersDao.executeHql(hql, b);
		}
	}

	/**
	 * 根据Id获取收货地址
	 * 
	 */
	public UCustomerAddr getCustomerAddressById(int addressId, long weiId) {
		String hql = " from UCustomerAddr t where t.caddrId=? and t.weiId=? ";// 查询语句
		Object[] b = new Object[2];// 参数列表
		b[0] = addressId;
		b[1] = weiId;
		return basicOrdersDao.getUniqueResultByHql(hql, b);// 查询返回结果
	}

	/**
	 * 新增收货地址 ， 此方法被临时修改
	 * 
	 */
	public int insertAddress(UCustomerAddress address) {
		UCustomerAddr addrModel = null;
		if (address.getIsStoreAddress() == 1) {
			String notDefaultHQL = " update UProductShop as t set t.province=:province,t.city=:city,t.area=:area,t.address=:address where t.weiId=:weiId";// 查询语句
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("province", address.getProvince());
			map.put("city", address.getCity());
			map.put("area", address.getDistrict());
			// 省市区
			String addresst = regionService.getNameByCode(address.getProvince()) + regionService.getNameByCode(address.getCity()) + regionService.getNameByCode(address.getDistrict());
			map.put("address", addresst);
			map.put("weiId", address.getWeiID());
			basicOrdersDao.executeHqlByMap(notDefaultHQL, map);
		}

		if (address != null) {
			if (address.getIsDefault() != null && address.getIsDefault().shortValue() == 1) {
				// 如果是默认收货地址
				// 将其他收货地址更新为非默认
				String notDefaultHQL = " update UCustomerAddr as t set t.updateTime=?,t.isDefault=? where t.weiId=?";// 查询语句
				int modifiedCount = basicOrdersDao.executeHql(notDefaultHQL, new Date(), 0, address.getWeiID());
				return modifiedCount;
			}
			addrModel = new UCustomerAddr();
			addrModel.setCity(address.getCity());
			addrModel.setDetailAddr(address.getDetailAddr());
			addrModel.setDistrict(address.getDistrict());
			addrModel.setMobilePhone(address.getMobilePhone());
			addrModel.setProvince(address.getProvince());
			addrModel.setQq(address.getQq());
			addrModel.setReceiverName(address.getReceiverName());
			addrModel.setRegisterTime(new Date());
			addrModel.setUpdateTime(new Date());
			addrModel.setWeiId(address.getWeiID());
			addrModel.setIsDefault(address.getIsDefault());
			int newId = (int) basicOrdersDao.save(addrModel);
			return newId;
		}
		return 0;
	}

	/**
	 * 获取收货地址列表
	 * 
	 * @param weiIdParam微店号参数
	 * @return ReturnModel
	 */
	public ReturnModel getCustomerAddressesList(Long weiId) {
		ReturnModel returnModel = new ReturnModel();// 定义返回实体
		if (null == weiId || weiId <= 0) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("参数错误！");
			return returnModel;
		}
		// 获取数据
		try {
			List<UCustomerAddr> list = basicOrdersDao.getCustomerAddressByWeiID(weiId);
			// 收货地址列表
			List<UCustomerAddress> returnAddressList = null;
			if (list != null && list.size() > 0) {
				// 返回JSON的List
				returnAddressList = new ArrayList<UCustomerAddress>();
				for (UCustomerAddr address : list) {
					if (address != null) {
						returnAddressList.add(assemblyAddress(address));
					}
				}
			}
			// 返回数据
			returnModel.setBasemodle(returnAddressList);
			returnModel.setStatu(ReturnStatus.Success);// 返回状态
			returnModel.setStatusreson("成功");// 状态描述
			// }

		} catch (Exception e) {
			returnModel.setStatu(ReturnStatus.SystemError);// 返回状态
			returnModel.setStatusreson(e.getMessage());// 状态描述
		}

		return returnModel;
	}

	public ReturnModel insertBookOrder(String orderJson, long supplyerId) {
		ReturnModel returnModel = new ReturnModel();// 定义返回实体
		if (orderJson == null || orderJson.equals("")) {
			// ordernum参数不能为空
			returnModel.setStatu(ReturnStatus.ParamError);// 返回状态
			returnModel.setStatusreson("order参数不能为空");// 状态描述
			return returnModel;
		}
		// 参数Model
		SupplyBookOrderParam inputParam = new SupplyBookOrderParam();
		// 订单状态
		int state = 0;
		JSONObject jsonObj;
		double totalProductPrice = 0.0;
		// try {
		jsonObj = JSONObject.fromObject(orderJson);
		if (jsonObj == null) {
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("order json串错误！");
			return returnModel;
		}
		inputParam.setWeiid(supplyerId);
		Object Object1 = jsonObj.get("action");
		String wen = "";
		if (Object1 == null) {
			returnModel.setStatu(ReturnStatus.ParamError);// 返回状态
			returnModel.setStatusreson("action参数不能为空");// 状态描述
			return returnModel;
		} else {
			wen = jsonObj.getString("action");
		}
		// 确认铺货订单
		if (wen.equals("confirm")) {
			inputParam.setSured(true);
		} else if (wen.equals("reject")) {
			inputParam.setSured(false);
		} else {
			returnModel.setStatu(ReturnStatus.ParamError);// 返回状态
			returnModel.setStatusreson("action参数错误");// 状态描述
			return returnModel;
		}
		Object Object2 = jsonObj.get("supplierOrderId");
		if (Object2 == null) {
			returnModel.setStatu(ReturnStatus.ParamError);// 返回状态
			returnModel.setStatusreson("supplierOrderId参数不能为空");// 状态描述
			return returnModel;
		}
		inputParam.setSupplyOrderid((String) Object2);
		// 订单状态
		Object Object8 = jsonObj.get("state");
		if (Object8 != null) {
			state = jsonObj.getInt("state");
		}
		// 当取消预定单时 只需要订单Id,和订单状态 即可 不需要验证其他参数
		if (inputParam.isSured()) {
			Object Object3 = jsonObj.get("bookPayType");
			if (Object3 == null) {
				returnModel.setStatu(ReturnStatus.ParamError);// 返回状态
				returnModel.setStatusreson("bookPayType参数不能为空");// 状态描述
				return returnModel;
			}
			inputParam.setPayType((int) Object3);
			Object Object4 = jsonObj.get("percent");
			if (Object4 != null) {
				inputParam.setFirstPercent(jsonObj.getInt("percent"));
			}
			Object Object5 = jsonObj.get("amount");
			if (Object5 != null) {
				inputParam.setFirstPrice(jsonObj.getDouble("amount"));
			}
			Object Object6 = jsonObj.get("tailPayType");
			if (Object6 == null) {
				returnModel.setStatu(ReturnStatus.ParamError);// 返回状态
				returnModel.setStatusreson("tailPayType参数不能为空");// 状态描述
				return returnModel;
			}
			for (TailPayTypeEnum tte : TailPayTypeEnum.values()) {
				if (ParseHelper.toInt(tte.toString()) == jsonObj.getInt("tailPayType")) {
					inputParam.setTailPayType(tte);
				}
			}
			Object Object7 = jsonObj.get("preSendTime");
			if (Object7 != null) {
				inputParam.setPreDeliverTime(DateUtils.parseDate(jsonObj.getString("preSendTime")));
			}

			Object Object9 = jsonObj.get("remark");
			if (Object9 != null) {
				inputParam.setMessage(jsonObj.getString("remark"));
			}
			Object Object10 = jsonObj.get("isChangePrice");
			if (Object10 != null) {
				inputParam.setEditPriceType(jsonObj.getInt("isChangePrice"));
			}
			Object Object11 = jsonObj.get("totalAmout");
			if (Object11 != null) {
				inputParam.setEditTotalPrice(jsonObj.getDouble("totalAmout"));
			}
			Object Object12 = jsonObj.get("postfee");
			if (Object12 != null) {
				inputParam.setEditPostPrice(jsonObj.getDouble("postfee"));
			}
			Object Object13 = jsonObj.get("priceList");
			if (Object13 == null) {
				returnModel.setStatu(ReturnStatus.ParamError);// 返回状态
				returnModel.setStatusreson("商品信息不能为空");// 状态描述
				return returnModel;
			}
			JSONArray priceJsonArray = (JSONArray) Object13;
			if (priceJsonArray == null || priceJsonArray.size() == 0) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("产品列表不能为空");
				return returnModel;
			}
			// 组合ProductList
			List<OProductOrder> priceList = new ArrayList<OProductOrder>();
			for (int j = 0; j < priceJsonArray.size(); j++) // 遍历value
			{
				JSONObject jsonvalue = (JSONObject) priceJsonArray.get(j);
				OProductOrder priceModel = new OProductOrder();
				priceModel.setPrice(jsonvalue.getDouble("price"));
				priceModel.setProductOrderId(jsonvalue.getString("productOrderId"));
				priceList.add(priceModel);
				totalProductPrice += (priceModel.getPrice() * priceModel.getCount());
			}
			inputParam.setProducts(priceList);

			// 传入参数数据校验
			if (inputParam.getSupplyOrderid() == null || inputParam.getSupplyOrderid().equals("")) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("SupplierOrderId不能为空！");
				return returnModel;
			}

			if (inputParam.getPayType() < 0 || inputParam.getPayType() > 2) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("BookPayType参数错误！");
				return returnModel;
			}

			if (inputParam.getPayType() == Short.parseShort(BookPayTypeEnum.percent.toString())) {
				// 百分比支付
				if (inputParam.getFirstPercent() < 6 || inputParam.getFirstPercent() > 100) {
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("Persent参数错误（百分比必须是6-99之间的整数）！");
					return returnModel;
				}
			}

			if (inputParam.getPreDeliverTime() == null || inputParam.getPreDeliverTime().equals("")) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("PreSendTime不能为空！");
				return returnModel;
			}

			if (inputParam.getEditPriceType() < 0 || inputParam.getEditPriceType() > 2) {
				returnModel.setStatu(ReturnStatus.ParamError);
				returnModel.setStatusreson("IsChangePrice参数错误！");
				return returnModel;
			}

			if (inputParam.getEditPriceType() == Short.parseShort(BookAssistChangePriceType.changeTotalAmout.toString())) {
				// 修改总价，铺货单总价不能少于1[20150427]
				if (inputParam.getEditTotalPrice() < 1) {
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("订单总价必须大于等于1！");
					return returnModel;
				}
			}

			// 指定首款支付金额
			if (inputParam.getPayType() == Short.parseShort(BookPayTypeEnum.specifyamount.toString())) {
				// 指定金额不能大于订单金额

				if (inputParam.getFirstPrice() < 0) {
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("首款金额必须大于0！");
					return returnModel;
				}

				if (inputParam.getEditPriceType() == Short.parseShort(BookAssistChangePriceType.changeTotalAmout.toString())) {
					// 修改了订单总价
					if (inputParam.getFirstPrice() > inputParam.getEditTotalPrice()) {
						returnModel.setStatu(ReturnStatus.ParamError);
						returnModel.setStatusreson("首款金额不能大于订单总价！");
						return returnModel;
					}
					// 首款不能小于订单总额的5%
					if (inputParam.getFirstPrice() < (inputParam.getEditTotalPrice() * 0.05)) {
						returnModel.setStatu(ReturnStatus.ParamError);
						returnModel.setStatusreson("首款金额不能小于订单总额的5%！");
						return returnModel;
					}
				} else if (inputParam.getEditPriceType() == Short.parseShort(BookAssistChangePriceType.changeProductPrice.toString())) {
					// 修改了订单产品价格
					if (inputParam.getFirstPrice() > (totalProductPrice + inputParam.getEditPostPrice())) {
						returnModel.setStatu(ReturnStatus.ParamError);
						returnModel.setStatusreson("首款支付不能大于订单总价！");
						return returnModel;
					}
					// 首款不能小于订单总额的5%
					if (inputParam.getFirstPrice() < ((totalProductPrice + inputParam.getEditPostPrice()) * 0.05)) {
						returnModel.setStatu(ReturnStatus.ParamError);
						returnModel.setStatusreson("首款金额不能小于订单总额的5%！");
						return returnModel;
					}
				}
			}
		}
		// 判断订单状态
		OSupplyerOrder order = basicOrdersDao.get(OSupplyerOrder.class, inputParam.getSupplyOrderid());
		if (order == null || order.getSupplierOrderId().equals("")) {
			// 订单不存在
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("订单不存在！");
			return returnModel;
		}

		if (inputParam.getPayType() == Short.parseShort(BookPayTypeEnum.specifyamount.toString())) {
			if (inputParam.getEditPriceType() == Short.parseShort(BookAssistChangePriceType.notChange.toString())) {
				// 没有修改价格，从数据库获取订单价格对比
				if (inputParam.getFirstPrice() > (order.getTotalPrice() + order.getPostage())) {
					returnModel.setStatu(ReturnStatus.ParamError);
					returnModel.setStatusreson("首款支付不能大于订单总价！");
					return returnModel;
				}
			}
		}

		if (order.getOrderType().intValue() != Integer.parseInt(OrderTypeEnum.BookOrder.toString()) && order.getOrderType().intValue() != Integer.parseInt(OrderTypeEnum.Puhuo.toString())) {
			// 订单不是铺货单
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("该订单不是铺货单！");
			return returnModel;
		}

		if (order.getSupplyerId().longValue() != supplyerId) {
			// 该铺货单不是当前供应商的
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("该铺货单不是当前供应商的！");
			return returnModel;
		}

		if (state != ParseHelper.toInt(OrderStatusEnum.WaitSure.toString())) {
			// 已经处理过了
			returnModel.setStatu(ReturnStatus.ParamError);
			returnModel.setStatusreson("该订单已经处理过了！");
			return returnModel;
		}
		try {
			// 铺货单处理
			returnModel = basicOrdersService.insertBookOrder(inputParam);
			return returnModel;
		} catch (Exception e) {
			returnModel.setStatu(ReturnStatus.SystemError);
			returnModel.setStatusreson("系统错误！" + e.getMessage());
			return returnModel;
		}
	}

	@Override
	public ReturnModel findProductOrderModelList(OrderServiceListVO order) {
		ReturnModel rm = new ReturnModel();
		// 1表示买家，2表示卖家
		if (!order.getUserState().equals("2")) {
			// 1表示买家
			rm = GetBuyerProductOrderModelList(order.getWeiId(), order.getState(), order.getPageindex(), order.getPagesize(), order.getType());
		} else {
			// 2表示卖家
			rm = GetSellerProductOrderModelList(order.getWeiId(), order.getState(), order.getPageindex(), order.getPagesize(), order.getType());

		}
		return rm;
	}

	public ReturnModel getOrderDetails(String supplierOrderId, int userType, Long weiId) {
		ReturnModel rm = new ReturnModel();
		if (userType == 2) {
			// 获取卖家订单详情
			rm = GetSellerrOrderDetails(supplierOrderId, weiId);
		} else if (userType == 1) {
			// 获取买家家订单详情
			rm = GetBuyerOrderDetails(supplierOrderId);
		} else {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数错误！");
		}
		return rm;
	}

	/**
	 * 买家订单详情
	 * 
	 * @param supplierOrderId
	 *            供应商订单号
	 * @return
	 */
	public ReturnModel GetBuyerOrderDetails(String supplierOrderId) {
		ReturnModel RqStatus = new ReturnModel();
		SupplyOrderDetailsVO orderDetails = basicOrdersService.getOrderDetails(supplierOrderId, 1);
		if(orderDetails.getProList()==null||orderDetails.getProList().size()<=0){
			RqStatus.setStatusreson("找不到产品"); 
			RqStatus.setStatu(ReturnStatus.SystemError);
			RqStatus.setBasemodle(orderDetails); 
			return RqStatus;
		}
		Double Postage = (double) 0;
		ProductOrderDetailsModel Model = new ProductOrderDetailsModel();
		List<ProductOrderChild> ProductOrderChildList = new ArrayList<ProductOrderChild>();
		// 总价
		Double TotalPrice = (double) 0;
		// 订单类型
		int OrderType = -1;
		// 订单号
		Model.setsupplierOrderId(orderDetails.getOrderNo());
		String hql = " from UWeiCoinLog where supplyOrderId=?  and useType=2 ";
		List<UWeiCoinLog> find = basicOrdersDao.find(hql, orderDetails.getOrderNo());
		if (find != null && find.size() > 0) {
			UWeiCoinLog uWeiCoinLog = find.get(0);
			Model.setCashCoupon(Math.abs(uWeiCoinLog.getCoinAmount()));
		}
		String strHql = " from OPayOrder p where p.payOrderId=?";
		OPayOrder po = basicOrdersDao.getNotUniqueResultByHql(strHql, orderDetails.getPayOrderNo());
		if (po != null && po.getWeiDianCoin() != null) {
			Model.setOkweiCoin(po.getWeiDianCoin() == null ? 0.0 : po.getWeiDianCoin());
		} else {
			Model.setOkweiCoin(0.0);
		}
		// 查询收货地址
		OSupplyerOrder PostPageSupplyerOrder = basicOrdersDao.getSupplyerOrder(supplierOrderId, 1);
		if (PostPageSupplyerOrder == null) {
			RqStatus.setStatu(ReturnStatus.ParamError);
			RqStatus.setStatusreson("供应商表为空!");
			RqStatus.setBasemodle(null);
			return RqStatus;
		}
		Model.setShopWeiId(PostPageSupplyerOrder.getSupplyerId());
		// 订单类型
		OrderType = PostPageSupplyerOrder.getOrderType() != null ? PostPageSupplyerOrder.getOrderType() : -1;
		// APP需要转换成他们能识别的类型
		Model.setOrderType(get_OrderTypes(OrderType));
		// 订单状态
		short state = PostPageSupplyerOrder.getState() != null ? PostPageSupplyerOrder.getState() : -1;
		Model.setState(state);
		Model.setPayOrderID(PostPageSupplyerOrder.getPayOrderId());
		// 订单时间
		Model.setOrderTime(orderDetails.getOrderTimeStr());
		if (PostPageSupplyerOrder.getSupplyerId() == null) {
			RqStatus.setStatu(ReturnStatus.DataError);
			RqStatus.setStatusreson("供应商微店号为空!");
			return RqStatus;
		}
		// 供应商微店名称
		String WeiName = baseCommonService.getNickNameById(PostPageSupplyerOrder.getSupplyerId());
		Model.setWeiName(WeiName);
		// 联系人微店名称
		Model.setLinkName(WeiName);
		// 联系人头像
		Model.setLinkImg(baseCommonService.getImageById(PostPageSupplyerOrder.getSupplyerId()));
		// 联系人微店号
		Model.setLinkWeiID(PostPageSupplyerOrder.getSupplyerId());
		// 运费
		Postage = PostPageSupplyerOrder.getPostage() != null ? PostPageSupplyerOrder.getPostage() : 0;
		Model.setPostage(Postage);
		// 订单总价
		TotalPrice = PostPageSupplyerOrder.getTotalPrice() != null ? PostPageSupplyerOrder.getTotalPrice() : (double) 0;
		Model.setTotalPrice(TotalPrice + Postage);
		// 买家留言
		String Message = PostPageSupplyerOrder.getMessage() != null ? PostPageSupplyerOrder.getMessage() : "";
		Model.setMessage(Message);
		// 供应商ID
		Model.setSellerID(PostPageSupplyerOrder.getSupplyerId() != null ? PostPageSupplyerOrder.getSupplyerId() : 0);
		List<ProductOrderVO> proList = orderDetails.getProList();
		// 因为一个订单共用一个申请退款的记录,所以每个详情只有一个退款订单Id
		// 记录退款订单Id
		TOrderBackTotalVO tOrderBackTotal = null;// 备份不等于空的退款对象
		if (proList != null && proList.size() > 0) {

			// 商品ID
			List<Long> productIdS = new ArrayList<Long>();
			// 产品列表
			for (ProductOrderVO item : proList) {
				ProductOrderChild ChildList = new ProductOrderChild();

				productIdS.add(item.getProductId());

				ChildList.setBackOrder(item.getRefundId());
				// 退款详情web链接 isSell = 1买家 2卖家
				ChildList.setWapUrl("http://" + AppSettingUtil.getSingleValue("wapDomain") + "/app/refund?tiket={tiket}&backid=" + item.getRefundId() + "&isSell=1");
				// 产品佣金
				ChildList.setCommission(item.getCommission());
				// 产品标题
				ChildList.setProdcutTitle(item.getProductTitle());
				// 产品图片 给图片加域名
				ChildList.setProductImg(item.getProductImg());
				// 款式描述
				ChildList.setStyleDes(item.getProperty().replace("-1", "默认"));
				// 单价
				ChildList.setPrice(item.getPrice());
				// 数量
				ChildList.setCount(item.getCount());
				// 产品ID
				ChildList.setProductId(item.getProductId());
				ChildList.setProductOrderId(item.getProductOrderid());

				ChildList.setShareOne(item.getShareOne());
				ChildList.setSharePageProducer(item.getSharePageProducer());
				if (item.getSharePageId() != null) {
					ChildList.setSharePageId(item.getSharePageId());
				}
				if (item.getRefundState() != -1) {
					tOrderBackTotal = new TOrderBackTotalVO();
					tOrderBackTotal.setRefundId(item.getRefundId());
					tOrderBackTotal.setRefundState((short) item.getRefundState());
					tOrderBackTotal.setRefundType((short) item.getRefundType());
					ChildList.setStates(item.getRefundState());
					if (item.getRefundState() == 7) {
						ChildList.setStates(-1);
					}
				} else {
					ChildList.setStates(-1);
				}
				ProductOrderChildList.add(ChildList);
			}
			List<PProducts> productList1 = productDao.find_ProductByProductIDS(productIdS);
			if (productList1 != null && productList1.size() > 0) {
				for (ProductOrderChild ProductOrder : ProductOrderChildList) {
					for (PProducts product : productList1) {
						if (ProductOrder.getProductId() == product.getProductId().longValue()) {
							ProductOrder.setDisplayPrice(commonMethod.getDisplayPrice(product.getDefaultPrice(), product.getOriginalPrice(), product.getPercent()));
						}
					}
				}
			}

		}
		// “取消退款申请”按钮显示条件：
		// 仅退款：（1）提出退款申请，至供应商同意前
		// （2）供应商拒绝退款申请后，至微店网客服介入前
		// 退款退货：（1）提出退款申请，至供应商同意后，买家发货前
		// （2）供应商拒绝退款申请后，至微店网客服介入（选择支持卖家后，不显示该按钮，选择支持买家，依旧显示该按钮）
		// （3）微店网客服介入，支持买家后，至买家发货前
		// 供应商同意退款1 并且 退款类型为 2. 退货 时显示发货按钮
		if (tOrderBackTotal != null) {
			Model.setBackOrder(tOrderBackTotal.getRefundId().toString());

			short shortValue = tOrderBackTotal.getRefundState();
			short shortValue2 = 0 != tOrderBackTotal.getRefundType() ? tOrderBackTotal.getRefundType() : 0;
			switch (shortValue2) {
			case 1:
				if (shortValue == 0 || shortValue == 2) {
					// 显示取消申请按钮
					Model.setIsShow(1);
				}
				break;
			case 2:
				if (shortValue == 0 || shortValue == 1 || shortValue == 2 || shortValue == 3 || shortValue == 4) {
					// 显示取消申请按钮
					Model.setIsShow(1);
				}
				break;
			default:
				RqStatus.setStatu(ReturnStatus.ParamError);
				RqStatus.setStatusreson("获取订单退款类型失败！");
				return RqStatus;
			}
			// 退款完成的订单，可能还会出现“申请退款退货”按钮，需同时满足以下三个条件：
			// 1、上一个退款流程，供应商已确认收到退货商品（退款退货）或供应商同意退款（仅退款）；
			// 2、订单中有未退款的商品；
			// 3、买家确认收货7天内（买家确认收货超过7天，则不显示该按钮）ReFundStatusEnum 和shortValue字段的值一致
			if (state == 1 || state == 2 || state == 3 || shortValue == 1 || shortValue == 5 || shortValue == 7) {
				// 显示申请退款按钮
				Model.setIsShowSupply(1);
			}
			if (tOrderBackTotal.getRefundType() == 2 && tOrderBackTotal.getRefundState() == 1) {
				// 显示发货按钮
				Model.setIsShipments(1);
			}
		}
		// 将详情中的标记为显示
		if (Model.getIsShow() == 1) {
			for (ProductOrderChild object : ProductOrderChildList) {
				object.setIsShow(1);
			}
		}
		// 增加产品列表
		Model.setProductList(ProductOrderChildList);
		// 如果是预订单
		if (OrderType == Integer.parseInt(OrderTypeEnum.BookOrder.toString()) || OrderType == Integer.parseInt(OrderTypeEnum.Puhuo.toString())) {
			ReturnModel getbookAssists = getbookAssists(supplierOrderId, PostPageSupplyerOrder.getIsChagePrice());
			if (ParseHelper.toLong(getbookAssists.getStatu().toString()) != 1) {
				getbookAssists.setStatu(ReturnStatus.SystemError);
				// getbookAssists.setStatusreson("获取订单详情失败！！");
				return getbookAssists;
			}
			BookAssistModel basemodle = (BookAssistModel) getbookAssists.getBasemodle();
			if (basemodle != null) {
				Model.setBookAssistContent(basemodle);
				if (null != basemodle.getBookPayType() && basemodle.getBookPayType().shortValue() == (short) 0) {
					Model.setPayOrderID(basemodle.gethOrder());
				}
			}
		}
		// 收货人
		Model.setReceiverName(orderDetails.getReciverName());
		// 电话号码
		Model.setMobilePhone(orderDetails.getReciverPhoneNumber());
		// 收获地址
		Model.setAddress(orderDetails.getReciverAddress());
		RqStatus.setStatu(ReturnStatus.Success);
		RqStatus.setStatusreson("获取订单详情成功！！");
		RqStatus.setBasemodle(Model);
		return RqStatus;
	}

	/**
	 * 将类型转换为APP可识别的
	 * 
	 * @param OrderTypeState
	 * @return
	 */
	private int get_OrderTypes(int OrderTypeState) {
		int orderType = 0;
		switch (OrderTypeState) {
		case 1:// 零售订单
		case 8:
		case 24:
			orderType = 8;
			break;
		case 9:// 批发订单
			orderType = 9;
			break;
		case 20:// 铺货订单
			orderType = 13;
			break;
		case 12:// 预定订单
			orderType = 12;
			break;
		case 19:// 进货订单
			orderType = 14;
			break;
		default:
			orderType = OrderTypeState;
			break;
		}
		return orderType;
	}

	/**
	 * 卖家订单详情
	 * 
	 * @param supplierOrderId
	 *            供应商订单号
	 * @return
	 */
	public ReturnModel GetSellerrOrderDetails(String supplierOrderId, Long weiId) {
		ReturnModel RqStatus = new ReturnModel();
		SupplyOrderDetailsVO orderDetails = basicOrdersService.getOrderDetails(supplierOrderId, 2);
		if(orderDetails.getProList()==null||orderDetails.getProList().size()<=0){
			RqStatus.setStatusreson("找不到产品"); 
			RqStatus.setStatu(ReturnStatus.SystemError);
			RqStatus.setBasemodle(orderDetails); 
			return RqStatus;
		}
		Double Postage = (double) 0;
		ProductOrderDetailsModel Model = new ProductOrderDetailsModel();
		List<ProductOrderChild> ProductOrderChildList = new ArrayList<ProductOrderChild>();
		// 总价
		Double TotalPrice = (double) 0;
		// 订单类型
		int OrderType = -1;
		// 订单号
		Model.setsupplierOrderId(orderDetails.getOrderNo());
		OPayOrder po = basicOrdersDao.get(OPayOrder.class, orderDetails.getPayOrderNo());// (strHql,
																							// orderDetails.getPayOrderNo());
		if (po != null && po.getWeiDianCoin() != null) {
			Model.setOkweiCoin(po.getWeiDianCoin() == null ? 0.0 : po.getWeiDianCoin());
		} else {
			Model.setOkweiCoin(0.0);
		}

		String hql = " from UWeiCoinLog where supplyOrderId=? and useType=2";
		List<UWeiCoinLog> find = basicOrdersDao.find(hql, orderDetails.getOrderNo());
		if (find != null && find.size() > 0) {
			UWeiCoinLog uWeiCoinLog = find.get(0);
			Model.setCashCoupon(Math.abs(uWeiCoinLog.getCoinAmount()));
		}
		// 查询收货地址
		OSupplyerOrder PostPageSupplyerOrder = basicOrdersDao.getSupplyerOrder(supplierOrderId, 2);
		if (PostPageSupplyerOrder == null) {
			RqStatus.setStatu(ReturnStatus.ParamError);
			RqStatus.setStatusreson("供应商表为空!");
			RqStatus.setBasemodle(null);
			return RqStatus;
		}
		Model.setShopWeiId(PostPageSupplyerOrder.getBuyerID());
		Model.setPayOrderID(PostPageSupplyerOrder.getPayOrderId());
		// 订单类型
		// APP需要转换成他们能识别的类型
		Model.setOrderType(get_OrderTypes(orderDetails.getOrderType()));
		// 订单状态
		short state = PostPageSupplyerOrder.getState() != null ? PostPageSupplyerOrder.getState() : -1;
		OrderType = orderDetails.getOrderType();
		Model.setState(state);
		// 订单时间
		Model.setOrderTime(orderDetails.getOrderTimeStr());
		Long SupplyeriId = PostPageSupplyerOrder.getSupplyerId() != null ? PostPageSupplyerOrder.getSupplyerId() : -1;
		// 微店名称
		String WeiName = baseCommonService.getNickNameById(SupplyeriId);
		Model.setWeiName(WeiName);
		if (PostPageSupplyerOrder.getBuyerID() == null) {
			RqStatus.setStatu(ReturnStatus.DataError);
			RqStatus.setStatusreson("买家的微店号空!");
			return RqStatus;
		}
		// 消息推送联系人名称
		Model.setLinkName(baseCommonService.getNickNameById(PostPageSupplyerOrder.getBuyerID()));
		// 消息推送联系人头像
		Model.setLinkImg(baseCommonService.getImageById(PostPageSupplyerOrder.getBuyerID()));
		// 消息推送联系微店号
		Model.setLinkWeiID(PostPageSupplyerOrder.getBuyerID());
		// 运费
		Postage = PostPageSupplyerOrder.getPostage() != null ? PostPageSupplyerOrder.getPostage() : 0;
		Model.setPostage(Postage);
		// 订单总价
		TotalPrice = PostPageSupplyerOrder.getTotalPrice() != null ? PostPageSupplyerOrder.getTotalPrice() : (double) 0;
		Model.setTotalPrice(TotalPrice + Postage);
		// 买家留言
		String Message = PostPageSupplyerOrder.getMessage() != null ? PostPageSupplyerOrder.getMessage() : "";
		Model.setMessage(Message);
		// 供应商ID
		Model.setSellerID(orderDetails.getSupplyerWeiid());
		List<ProductOrderVO> proList = orderDetails.getProList();
		// 记录是否显示发货按钮
		boolean tOrderBackTotal = false;
		if (proList != null && proList.size() > 0) {

			// 商品ID
			List<Long> productIdS = new ArrayList<Long>();

			// 产品列表
			for (ProductOrderVO item : proList) {
				productIdS.add(item.getProductId());
				ProductOrderChild ChildList = new ProductOrderChild();
				// isSell= 1买家 2卖家
				ChildList.setWapUrl("http://" + AppSettingUtil.getSingleValue("wapDomain") + "/app/refund?tiket={tiket}&backid=" + item.getRefundId() + "&isSell=2");
				ChildList.setCommission(item.getCommission());

				// 产品标题
				ChildList.setProdcutTitle(item.getProductTitle());
				// 产品图片 给图片加域名
				ChildList.setProductImg(item.getProductImg());
				// 款式描述
				ChildList.setStyleDes(item.getProperty().replace("-1", "默认"));
				// 单价
				ChildList.setPrice(item.getPrice());
				// 数量
				ChildList.setCount(item.getCount());
				// 产品ID
				ChildList.setProductId(item.getProductId());
				ChildList.setProductOrderId(item.getProductOrderid());

				ChildList.setShareOne(item.getShareOne());
				ChildList.setSharePageProducer(item.getSharePageProducer());
				if (item.getSharePageId() != null) {
					ChildList.setSharePageId(item.getSharePageId());
				}
				if (item.getRefundState() != -1) {
					tOrderBackTotal = true;
					ChildList.setStates(item.getRefundState());
					if (item.getRefundState() == 7) {
						ChildList.setStates(-1);
					}
				} else {
					ChildList.setStates(-1);
				}
				ProductOrderChildList.add(ChildList);
			}
			// 产品是否属于代理区供应商提供（是否是代理区产品）
			DBrandSupplier brandSupplier = basicOrdersDao.get(DBrandSupplier.class, PostPageSupplyerOrder.getSupplyerId());
			int identity = 0;
			if (brandSupplier != null) {
				identity = agentMgtDao.getUserIdentityForBrand(weiId, brandSupplier.getBrandId());
			}
			List<PProducts> productList1 = productDao.find_ProductByProductIDS(productIdS);
			if (productList1 != null && productList1.size() > 0) {
				for (ProductOrderChild ProductOrder : ProductOrderChildList) {
					// TODO 修改价格(代理区产品)
					if (brandSupplier != null && weiId.longValue() != brandSupplier.getWeiId()) {
						OProductOrder productOrders = basicOrdersDao.get(OProductOrder.class, ProductOrder.getProductOrderId());
						if (productOrders != null) {
							PriceLimit priceLimit = new PriceLimit();
							PProductStyles styles = basicOrdersDao.get(PProductStyles.class, productOrders.getStyleId());
							if (styles != null) {
								if (BitOperation.isIdentity(identity, UserIdentityType.AgentDuke)) {
									priceLimit.setPriceMin(styles.getDukePrice());
								} else if (BitOperation.isIdentity(identity, UserIdentityType.AgentDeputyDuke)) {
									priceLimit.setPriceMin(styles.getDeputyPrice());
								} else {
									priceLimit.setPriceMin(styles.getAgentPrice());
								}

								priceLimit.setPriceMax(styles.getPrice());
								ProductOrder.setPriceLimit(priceLimit);
							}
						}
					}
					for (PProducts product : productList1) {
						if (ProductOrder.getProductId() == product.getProductId().longValue()) {
							ProductOrder.setDisplayPrice(commonMethod.getDisplayPrice(product.getDefaultPrice(), product.getOriginalPrice(), product.getPercent()));
						}
					}
				}
			}
		}
		// 订单在退款中5，已支付1 或退订单 在 申请中0 时显示发货按钮
		if (tOrderBackTotal) {
			// if (state == 5 || state == 1 ||
			// tOrderBackTotal1.getBackStatus().shortValue() == 0) {
			if (state == 1) {
				// 显示发货按钮
				// 当子账号不为空时 用来和登录用户比较确定是否是 当前的用户发货
				if (PostPageSupplyerOrder.getSubNo() != null) {
					if (weiId.toString().equals(PostPageSupplyerOrder.getSubNo())) {
						Model.setIsShipments(1);
					}
				} else {
					if (weiId.toString().equals(PostPageSupplyerOrder.getSupplyerId())) {
						Model.setIsShipments(1);
					}
				}
			}
		}
		// 将详情中的标记 是否显示取消申请按钮
		if (Model.getIsShow() == 1) {
			for (ProductOrderChild object : ProductOrderChildList) {
				object.setIsShow(1);
			}
		}
		// 增加产品列表
		Model.setProductList(ProductOrderChildList);

		// 如果是预订单
		if (OrderType == Integer.parseInt(OrderTypeEnum.BookOrder.toString()) || OrderType == Integer.parseInt(OrderTypeEnum.Puhuo.toString())) {
			ReturnModel getbookAssists = getbookAssists(supplierOrderId, PostPageSupplyerOrder.getIsChagePrice());
			if (ParseHelper.toLong(getbookAssists.getStatu().toString()) != 1) {
				getbookAssists.setStatu(ReturnStatus.SystemError);
				// getbookAssists.setStatusreson("获取订单详情失败！！");
				return getbookAssists;
			}
			if (ParseHelper.toLong(getbookAssists.getStatu().toString()) != 1) {
				getbookAssists.setStatu(ReturnStatus.SystemError);
				getbookAssists.setStatusreson("获取订单详情失败！！");
				return getbookAssists;
			}
			BookAssistModel basemodle = (BookAssistModel) getbookAssists.getBasemodle();
			if (basemodle != null) {
				Model.setBookAssistContent(basemodle);
				if (null != basemodle.getBookPayType() && basemodle.getBookPayType().shortValue() == (short) 0) {
					Model.setPayOrderID(basemodle.gethOrder());
				}
			}
		}
		// 收货人
		Model.setReceiverName(orderDetails.getReciverName());
		// 电话号码
		Model.setMobilePhone(orderDetails.getReciverPhoneNumber());
		// 收获地址
		Model.setAddress(orderDetails.getReciverAddress());
		RqStatus.setStatu(ReturnStatus.Success);
		RqStatus.setStatusreson("获取订单详情成功！！");
		RqStatus.setBasemodle(Model);
		return RqStatus;
	}

	/**
	 * 获取铺货单信息
	 * 
	 * @param supplierOrderId
	 *            供应商订单号
	 * @param IsChagePrice
	 *            是否修改价格
	 * @return
	 */
	private ReturnModel getbookAssists(String supplierOrderId, Short IsChagePrice) {
		ReturnModel RqStatus = new ReturnModel();
		RqStatus.setStatu(ReturnStatus.Success);
		// 铺货单的内容
		BookAssistModel BookModel = new BookAssistModel();
		List<OPayOrder> BookDepositOrderList = new ArrayList<OPayOrder>();
		OPayOrder BookHOrder = null, BookTOrder = null;
		/**
		 * 判断是否修改过价格
		 */
		if (IsChagePrice != null) {
			if (IsChagePrice == Short.parseShort(BookAssistChangePriceType.changeProductPrice.toString()) || IsChagePrice == Short.parseShort(BookAssistChangePriceType.changeTotalAmout.toString())) {
				BookModel.setModifyPrice((short) 1);
			} else {
				BookModel.setModifyPrice((short) 0);
			}
		}
		OBookAssist BookAssist = basicOrdersDao.get(OBookAssist.class, supplierOrderId);
		// 是全款预付订单的时候
		if (BookAssist != null) {
			if (BookAssist.getBookPayType() == null) {
				RqStatus.setStatu(ReturnStatus.ParamError);
				RqStatus.setStatusreson("铺货订单的支付方式为null");
				return RqStatus;
			}
			// 判断是否是预订单全款支付
			if (BookAssist.getBookPayType() == Short.parseShort(BookPayTypeEnum.Full.toString())) {
				String DPayhql = "from OPayOrder p where  p.supplierOrder=? ";
				Object[] b6 = new Object[1];
				b6[0] = supplierOrderId;
				BookDepositOrderList = basicOrdersDao.find(DPayhql, b6);
				if (BookDepositOrderList.size() != 1) {
					RqStatus.setStatu(ReturnStatus.ParamError);
					RqStatus.setStatusreson("OPayOrder的订单必须只有一条!");
					return RqStatus;
				}
				BookModel.setBookPayType(Short.parseShort(BookPayTypeEnum.Full.toString()));
				if (BookAssist.getPreSendTime() == null) {
					RqStatus.setStatu(ReturnStatus.ParamError);
					RqStatus.setStatusreson("OBookAssist表的PreSendTime为空!");
					return RqStatus;
				}
				BookModel.setAmount(BookDepositOrderList.get(0).getTotalPrice());
				BookModel.setPreSendTime(BookAssist.getPreSendTime());
				BookModel.sethOrder(BookDepositOrderList.get(0).getPayOrderId());
			}
			/**
			 * 订金
			 */
			else if (BookAssist.getBookPayType() == Short.parseShort(BookPayTypeEnum.percent.toString()) || BookAssist.getBookPayType() == Short.parseShort(BookPayTypeEnum.specifyamount.toString())) {
				String DPayhql = "from OPayOrder p where  p.supplierOrder=? ";
				Object[] b6 = new Object[1];
				b6[0] = supplierOrderId;
				BookDepositOrderList = basicOrdersDao.find(DPayhql, b6);
				if (BookDepositOrderList.size() == 2) {
					for (OPayOrder Ditem : BookDepositOrderList) {
						if (Short.parseShort(OrderTypeEnum.BookHeadOrder.toString()) == Ditem.getTypeState() || Short.parseShort(OrderTypeEnum.PuhuoHeader.toString()) == Ditem.getTypeState()) {
							BookHOrder = Ditem;
						} else if (Short.parseShort(OrderTypeEnum.BookTailOrder.toString()) == Ditem.getTypeState() || Short.parseShort(OrderTypeEnum.PuhuoTail.toString()) == Ditem.getTypeState()) {
							BookTOrder = Ditem;
						} else {
							RqStatus.setStatu(ReturnStatus.ParamError);
							RqStatus.setStatusreson("铺货支付订单的类型错误!");
							return RqStatus;
						}
					}
					if (BookHOrder == null || BookTOrder == null) {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("铺货支付订单有误,没有首款和尾款订单！");
						RqStatus.setBasemodle(null);
						return RqStatus;
					}
					BookModel.setBookPayType(BookAssist.getBookPayType());
					// 定金百分比
					if (BookAssist.getPersent() != null) {
						BookModel.setPercent(BookAssist.getPersent());
					}
					// 定金
					if (BookHOrder.getTotalPrice() == null) {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("支付订单的定金为空!");
						RqStatus.setBasemodle(null);
						return RqStatus;
					}
					BookModel.setAmount(BookHOrder.getTotalPrice());
					BookModel.sethOrder(BookHOrder.getPayOrderId());
					/**
					 * 尾款金额
					 */
					if (BookTOrder.getTotalPrice() == null) {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("支付订单的定金为空!");
						RqStatus.setBasemodle(null);
						return RqStatus;
					}
					BookModel.setBalancepayment(BookTOrder.getTotalPrice());
					BookModel.settOrder(BookTOrder.getPayOrderId());
					// 尾款支付方式
					if (BookAssist.getTailPayType() == null) {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("OBookAssist表的TailPayType为空!");
						RqStatus.setBasemodle(null);
						return RqStatus;
					}
					BookModel.setTailPayType(BookAssist.getTailPayType());
					// 发货时间
					if (BookAssist.getPreSendTime() == null) {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("OBookAssist表的PreSendTime为空!");
						RqStatus.setBasemodle(null);
						return RqStatus;
					}
					BookModel.setPreSendTime(BookAssist.getPreSendTime());
					// 订金是否支付
					// 未支付
					if (BookHOrder.getState() == null) {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("订单首款的支付状态为null");
						RqStatus.setBasemodle(null);
						return RqStatus;
					}
					if (BookHOrder.getState().equals(Short.parseShort(OrderStatusEnum.Payed.toString()))) {
						BookModel.setPaydeposit(Short.parseShort(OrderStatusEnum.Payed.toString()));
					} else if (BookHOrder.getState().equals(Short.parseShort(OrderStatusEnum.Nopay.toString()))) {
						BookModel.setPaydeposit(Short.parseShort(OrderStatusEnum.Nopay.toString()));
					} else {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("订单首款的支付状态错误");
						RqStatus.setBasemodle(null);
						return RqStatus;
					}
					// 尾款是否支付
					if (BookTOrder.getState() == null) {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("订单首款的支付状态不能为空");
						RqStatus.setBasemodle(null);
						return RqStatus;
					}
					if (BookTOrder.getState().equals(Short.parseShort(OrderStatusEnum.Nopay.toString()))) {
						BookModel.setPaydeadline(Short.parseShort(OrderStatusEnum.Nopay.toString()));
					}
					// 支付
					else if (BookTOrder.getState().equals(Short.parseShort(OrderStatusEnum.Payed.toString()))) {
						BookModel.setPaydeadline(Short.parseShort(OrderStatusEnum.Payed.toString()));
					} else {
						RqStatus.setStatu(ReturnStatus.ParamError);
						RqStatus.setStatusreson("订单首款的支付状态不能为空");
						return RqStatus;
					}
				} else {
					RqStatus.setStatu(ReturnStatus.ParamError);
					RqStatus.setStatusreson("订金的支付订单必须是两条!");
					return RqStatus;
				}
			} else {
				RqStatus.setStatu(ReturnStatus.DataError);
				RqStatus.setStatusreson("预订单表的支付状态错误!");
				return RqStatus;
			}
		}
		RqStatus.setBasemodle(BookModel);
		return RqStatus;
	}

	public ReturnModel GetSellerProductOrderList(Long weiNo, Short state, int pageindex, int pageSize) {
		ReturnModel rq = new ReturnModel();
		ParamOrderSearch param = new ParamOrderSearch();
		param.setOrderType(Integer.parseInt(SupplyOrderType.RetailAgent.toString()));
		param.setState(state);
		PageResult<OSupplyerOrder> supplyResult = basicOrdersDao.find_OSupplyerOrderByAgent(weiNo, param, Limit.buildLimit(pageindex, pageSize));
		if (supplyResult == null || supplyResult.getList().size() <= 0) {
			rq.setStatu(ReturnStatus.Success);
			rq.setStatusreson("没有订单！");
			rq.setBasemodle(null);
			return rq;
		}
		OrderList result = new OrderList();
		result.setTotalcount((long) supplyResult.getTotalCount());
		List<ProductOrderModel> orderlist = new ArrayList<ProductOrderModel>();
		for (OSupplyerOrder ss : supplyResult.getList()) {
			ProductOrderModel model = new ProductOrderModel();
			model.setSupplierOrderId(ss.getSupplierOrderId());
			model.setPayOrderID(ss.getPayOrderId());
			model.setTotalPrice(ss.getTotalPrice() + ss.getPostage());
			model.setOrderTime(ss.getOrderDate());
			model.setSupplierWeiId(ss.getSupplyerId());
			model.setSupplierWeiName(baseCommonService.getNickNameById(ss.getSupplyerId()));
			model.setBuyerWeiId(ss.getBuyerID());
			model.setBuyerWeiName(baseCommonService.getNickNameById(ss.getBuyerID()));
			model.setOrderType(Integer.parseInt(SupplyOrderType.RetailAgent.toString()));
			model.setWeiName(model.getSupplierWeiName());
			model.setOrderState(ss.getState()); 
			model.setAgencyOrder(1);// 代理区订单
			model.setForbidModify(1);// 不能修改订单
			model.setForbidSendOut(1);// 不能发货
			List<OProductOrder> prolist = basicOrdersDao.find_ProductOrderBySupplyOrderId(ss.getSupplierOrderId());
			if (prolist != null && prolist.size() > 0) {
				List<ProductOrderChild> proModelList = new ArrayList<ProductOrderChild>();
				for (OProductOrder pp : prolist) {
					ProductOrderChild proModel = new ProductOrderChild();
					proModel.setPrice(pp.getPrice());
					proModel.setCount(pp.getCount());
					proModel.setProductId(pp.getProductId());
					proModel.setShareOne(pp.getShareWeiId());
					proModel.setStyleDes(pp.getStyleDes());
					proModel.setProductImg(ImgDomain.GetFullImgUrl(pp.getProductImg()));
					proModel.setProdcutTitle(pp.getProdcutTitle());
					proModelList.add(proModel);
				}
				model.setProductOrderChildList(proModelList);
				orderlist.add(model);
			}
		}
		result.setList(orderlist);
		rq.setStatu(ReturnStatus.Success);
		rq.setBasemodle(result);
		return rq;
	}

	/**
	 * 获取卖家订单列表
	 * 
	 * @param weiNo
	 * @param state
	 *            订单状态
	 * @param pageindex
	 * @param pagesize
	 * @param orderType
	 *            订单类型
	 * @return
	 */
	public ReturnModel GetSellerProductOrderModelList(Long weiNo, short state, int pageindex, int pagesize, int orderType) {
		// ProductOrderModel List对象
		ReturnModel RqStatus = new ReturnModel();
		List<ProductOrderModel> ProductOrderModelList = new ArrayList<ProductOrderModel>();
		// OPayOrder 对象
		List<OProductOrder> ProductOrderList = new ArrayList<OProductOrder>();
		List<OSupplyerOrder> SupplyerOrderList = new ArrayList<OSupplyerOrder>();
		Long TotalPage, TotalCount;
		OrderList List = new OrderList();
		// 设置查询参数
		ParamOrderSearch param = new ParamOrderSearch();
		// orderType 8-零售；9-批发；13-预定/铺货；14-进货
		// 记录页数
		Limit limit = Limit.buildLimit(pageindex, pagesize);
		// 记录返回的订单集合
		PageResult<OSupplyerOrder> find_SellerOSupplyerOrder = null;// new
																	// PageResult<OSupplyerOrder>();
		if (state != -1) {
			param.setState(state);
		}
		switch (orderType) {
		case 8:// 零售
			param.setOrderType(1);
			find_SellerOSupplyerOrder = basicOrdersDao.find_SellerOSupplyerOrder(param, weiNo, limit);
			SupplyerOrderList = find_SellerOSupplyerOrder.getList();
			TotalCount = (long) find_SellerOSupplyerOrder.getTotalCount();
			break;
		case 13:// 13-铺货/预订单
			param.setOrderType(3);
			find_SellerOSupplyerOrder = basicOrdersDao.find_SellerOSupplyerOrder(param, weiNo, limit);
			SupplyerOrderList = find_SellerOSupplyerOrder.getList();
			TotalCount = (long) find_SellerOSupplyerOrder.getTotalCount();
			break;
		case 14:// 14-进货
			param.setOrderType(4);
			find_SellerOSupplyerOrder = basicOrdersDao.find_SellerOSupplyerOrder(param, weiNo, limit);
			SupplyerOrderList = find_SellerOSupplyerOrder.getList();
			TotalCount = (long) find_SellerOSupplyerOrder.getTotalCount();
			break;
		case 9:// 批发订单
			param.setOrderType(2);
			find_SellerOSupplyerOrder = basicOrdersDao.find_SellerOSupplyerOrder(param, weiNo, limit);
			SupplyerOrderList = find_SellerOSupplyerOrder.getList();
			TotalCount = (long) find_SellerOSupplyerOrder.getTotalCount();
			break;
		case 27:// 代理订单
			param.setOrderType(27);
			find_SellerOSupplyerOrder = basicOrdersDao.find_SellerOSupplyerOrder(param, weiNo, limit);
			SupplyerOrderList = find_SellerOSupplyerOrder.getList();
			TotalCount = (long) find_SellerOSupplyerOrder.getTotalCount();
			break;
		default: // 全部
			param.setOrderType(orderType); 
			find_SellerOSupplyerOrder = basicOrdersDao.find_SellerOSupplyerOrder(param, weiNo, limit);
			SupplyerOrderList = find_SellerOSupplyerOrder.getList();
			TotalCount = (long) find_SellerOSupplyerOrder.getTotalCount();
			break;
		}
		if (SupplyerOrderList == null || SupplyerOrderList.size() == 0) {
			RqStatus.setStatu(ReturnStatus.Success);
			RqStatus.setStatusreson("没有订单！");
			RqStatus.setBasemodle(null);
			return RqStatus;
		}
		TotalPage = (long) find_SellerOSupplyerOrder.getTotalPage();
		List.setTotalcount(TotalCount);
		List.setTotalPage(TotalPage);
		List.setPageIndex(limit.getPageId());
		List.setPageSize(limit.getSize());

		// 2015-4-18日修改, 修改订单列表性能
		// 先查出产品
		String[] SOrder = new String[pagesize];
		int i = 0;
		for (OSupplyerOrder item : SupplyerOrderList) {
			SOrder[i] = item.getSupplierOrderId();
			i++;
		}
		String hql1 = " from UWeiCoinLog where supplyOrderId in (:supplyOrderId)  and useType=2 ";
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("supplyOrderId", SOrder);
		List<UWeiCoinLog> find1 = basicOrdersDao.findByMap(hql1, map2);
		ProductOrderList = basicOrdersDao.getProductOrderListBySupplyOrderIds(SOrder, weiNo, 2);
		for (OSupplyerOrder item : SupplyerOrderList) {
			if(item.getOrderType()!=null&&item.getOrderType().intValue()==Integer.parseInt(SupplyOrderType.RetailAgent.toString())&&
					(item.getState()==null||item.getState()==Short.parseShort(OrderStatusEnum.Nopay.toString()))){
				continue;
			}
			ProductOrderModel Model = new ProductOrderModel();
			if (find1 != null && find1.size() > 0) {
				for (UWeiCoinLog uWeiCoinLog : find1) {
					if (item.getSupplierOrderId().equals(uWeiCoinLog.getSupplyOrderId())) {
						Model.setCashCoupon(Math.abs(uWeiCoinLog.getCoinAmount()));
					}
				}
			}
			List<ProductOrderChild> ProductOrderChildList = new ArrayList<ProductOrderChild>();
			// 供应商订单
			Model.setSupplierOrderId(item.getSupplierOrderId());
			Model.setPayOrderID(item.getPayOrderId());
			// 卖家微店号
			Long weiId = item.getSupplyerId() != null ? item.getSupplyerId() : (long) -1;
			Model.setSupplierWeiId(weiId);
			Model.setSupplierWeiName(baseCommonService.getNickNameById(weiId));
			Model.setWeiName(baseCommonService.getNickNameById(item.getBuyerID()));
			// APP需要转换成他们能识别的类型
			Model.setOrderType(get_OrderTypes(item.getOrderType()));
			orderType = ParseHelper.toShort(item.getOrderType().toString());
			if (null != item.getBuyerID() && item.getBuyerID().longValue() != 0) {
				// 记录买家信息
				Model.setBuyerWeiId(item.getBuyerID());
				Model.setBuyerWeiName(baseCommonService.getNickNameById(item.getBuyerID()));
			}
			// 商品ID
			List<Long> productIdS = new ArrayList<Long>();

			// 记录退款单号的Id
			List<Long> ids = new ArrayList<Long>();
			for (OProductOrder ProductOrderItem : ProductOrderList) {
				productIdS.add(ProductOrderItem.getProductId());
				// 只有进货单和铺货单 需要身份
				// 购买者身份，1-微店主2-落地店3-代理商4-供应商
				if (Model.getBuyerIdentity() == null) {
					if (item.getOrderType().intValue() == ParseHelper.toInt(OrderTypeEnum.Puhuo.toString()) || item.getOrderType().intValue() == ParseHelper.toInt(OrderTypeEnum.Jinhuo.toString())) {
						Model.setBuyerIdentity(basicOrdersDao.getIdentity(item.getBuyerID(), ProductOrderItem.getProductId()));
					}
				}
				if (ProductOrderItem.getSupplierOrderId().equals(item.getSupplierOrderId())) {
					ProductOrderChild ProductChild = new ProductOrderChild();
					if (ProductOrderItem.getBackOrder() != null && ProductOrderItem.getBackOrder().longValue() != 0) {
						// 记录退款单号的Id
						ids.add(ProductOrderItem.getBackOrder());
						ProductChild.setBackOrder(ProductOrderItem.getBackOrder());
						// isSell=1 1买家 2卖家
						ProductChild.setWapUrl("http://" + AppSettingUtil.getSingleValue("wapDomain") + "/app/refund?tiket={tiket}&backid=" + ProductOrderItem.getBackOrder() + "&isSell=2");
					}
					if (ProductOrderItem.getShareID() != null) {
						ProductChild.setSharePageId(ProductOrderItem.getShareID());
					}
					ProductChild.setSellWeiId(ProductOrderItem.getSellerWeiid());
					// 产品ID
					String ProductOrderID = ProductOrderItem.getProductOrderId() != null ? ProductOrderItem.getProductOrderId() : "-1";
					ProductChild.setProductOrderId(ProductOrderID);
					// 产品图片
					String ProductImg = ProductOrderItem.getProductImg() != null ? ProductOrderItem.getProductImg() : "";
					// 给图片加域名
					ProductImg = ImgDomain.GetFullImgUrl(ProductImg);
					ProductChild.setProductImg(ProductImg);
					// 产品名称
					String ProdcutTitle = ProductOrderItem.getProdcutTitle() != null ? ProductOrderItem.getProdcutTitle() : "";
					ProductChild.setProdcutTitle(ProdcutTitle);
					// 款式属性
					String StyleDes = ProductOrderItem.getStyleDes() != null ? ProductOrderItem.getStyleDes() : "";
					StyleDes = "-1".equals(StyleDes) ? "" : StyleDes;
					ProductChild.setStyleDes(ReplaceSlash(StyleDes)); // 替换|为""
					// 数量
					int Count = ProductOrderItem.getCount() != null ? ProductOrderItem.getCount() : 0;
					ProductChild.setCount(Count);
					// 单价
					double Price = ProductOrderItem.getPrice() != null ? ProductOrderItem.getPrice() : 0;
					ProductChild.setPrice(Price);

					ProductChild.setShareOne(ProductOrderItem.getShareWeiId());
					ProductChild.setSharePageProducer(ProductOrderItem.getMakerWeiId());
					ProductOrderChildList.add(ProductChild);

				}
			}
			List<PProducts> productList1 = productDao.find_ProductByProductIDS(productIdS);
			if (productList1 != null && productList1.size() > 0) {
				for (ProductOrderChild ProductOrder : ProductOrderChildList) {
					for (PProducts product : productList1) {
						if (ProductOrder.getProductId() == product.getProductId().longValue()) {
							ProductOrder.setDisplayPrice(commonMethod.getDisplayPrice(product.getDefaultPrice(), product.getOriginalPrice(), product.getPercent()));
						}
					}
				}
			}

			Map<String, Object> map1 = new HashMap<String, Object>();
			// 查询退款订单
			List<Object[]> find = null;
			if (ids != null && ids.size() > 0) {
				String hql = "select backOrder,backStatus,type from TOrderBackTotal where backOrder in (:backOrder)";
				map1 = new HashMap<String, Object>();
				map1.put("backOrder", ids);
				find = basicOrdersDao.findByMap(hql, map1);
			}
			if (item.getState().shortValue() == 1 || item.getState().shortValue() == 2 || item.getState().shortValue() == 3 || item.getState().shortValue() == 5) {
				// 获取退款订单的状态
				if (find != null && find.size() > 0) {
					for (ProductOrderChild ProductOrder : ProductOrderChildList) {
						for (Object[] objects : find) {
							if (ProductOrder.getBackOrder() != 0) {
								if (objects[0] != null && objects[0].toString().equals(ProductOrder.getBackOrder())) {
									Model.setBackOrder(ProductOrder.getBackOrder());
									int backStatus = ProductOrder.getStates();
									ProductOrder.setStates(ParseHelper.toInt(objects[1].toString()));
									int type = 0;
									if (objects[2] != null) {
										type = ParseHelper.toInt(objects[2].toString());
									}
									switch (type) {
									case 1:
										if (backStatus == 0 || backStatus == 2)// 是否显示取消申请退款按钮（1显示
																				// 0不显示）
											ProductOrder.setIsShow(1);
										break;
									case 2:
										if (backStatus == 0 || backStatus == 1 || backStatus == 2 || backStatus == 3 || backStatus == 4) // 是否显示取消申请退款按钮（1显示
																																			// 0不显示）
											ProductOrder.setIsShow(1);
										break;
									default:
										break;
									}
									continue;
								}
							}
						}
					}
				}
			}

			Model.setProductOrderChildList(ProductOrderChildList);
			// 订单时间
			if (item.getOrderTime() != null) {
				Model.setOrderTime(item.getOrderTime());
			} else {
				Date date = new Date();
				Model.setOrderTime(date);
			}
			Double TotalPrice = item.getTotalPrice() != null ? item.getTotalPrice() : (double) 0;
			Double Postage = item.getPostage() != null ? item.getPostage() : 0.0;
			Model.setTotalPrice(TotalPrice + Postage);
			// 订单状态
			Model.setOrderState(item.getState() != null ? item.getState() : (short) -1);
			// 判断是否是预订单
			ProductOrderModelList.add(Model);
		}
		List.setList(ProductOrderModelList);
		RqStatus.setStatu(ReturnStatus.Success);
		RqStatus.setStatusreson("成功！");
		RqStatus.setBasemodle(List);
		return RqStatus;
	}

	/**
	 * 获取买家订单列表
	 * 
	 * @param weiNo
	 * @param state
	 *            订单状态
	 * @param pageindex
	 * @param pagesize
	 * @param orderType
	 *            订单类型
	 * @return
	 */
	public ReturnModel GetBuyerProductOrderModelList(Long weiNo, short state, int pageindex, int pagesize, short orderType) {
		// ProductOrderModel List对象
		ReturnModel RqStatus = new ReturnModel();
		List<ProductOrderModel> ProductOrderModelList = new ArrayList<ProductOrderModel>();
		// OPayOrder 对象
		List<OProductOrder> ProductOrderList = new ArrayList<OProductOrder>();
		List<OSupplyerOrder> SupplyerOrderList = new ArrayList<OSupplyerOrder>();
		Long TotalPage, TotalCount = null;
		OrderList List = new OrderList();
		List<BigOLBookAssist> BookAssistContentList = new ArrayList<BigOLBookAssist>();
		short BookPayTypeValue = -1;
		// 设置查询参数
		ParamOrderSearch param = new ParamOrderSearch();
		// 记录页数
		Limit limit = Limit.buildLimit(pageindex, pagesize);
		// 记录返回的订单集合
		PageResult<OSupplyerOrder> find_BuyerOSupplyerOrder = new PageResult<OSupplyerOrder>();
		if (state == -1) {
			param.setState(null);
			switch (orderType) {
			case 8:// 零售
				param.setOrderType(1);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			case 13:// 13-预定/铺货
				param.setOrderType(3);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			case 14:// 14-进货
				param.setOrderType(4);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			case 9:// 批发订单
				param.setOrderType(2);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			default:// 全部
				param.setOrderType((int)orderType); 
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			}

		} else {
			param.setState(state);
			switch (orderType) {
			case 8:// 零售 订单
				param.setOrderType(1);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			case 13:// 13-预定/铺货
				param.setOrderType(3);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			case 14:// 14-进货
				param.setOrderType(4);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			case 9:// 批发订单
				param.setOrderType(2);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			default: // 全部
				param.setOrderType((int)orderType);
				find_BuyerOSupplyerOrder = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiNo, limit);
				SupplyerOrderList = find_BuyerOSupplyerOrder.getList();
				TotalCount = (long) find_BuyerOSupplyerOrder.getTotalCount();
				break;
			}

		}
		if (SupplyerOrderList == null || SupplyerOrderList.size() == 0) {
			RqStatus.setStatu(ReturnStatus.Success);
			RqStatus.setStatusreson("没有订单！");
			RqStatus.setBasemodle(null);
			return RqStatus;
		}
		TotalPage = (TotalCount / pagesize) + (TotalCount % pagesize > 0 ? 1 : 0);
		List.setTotalcount(TotalCount);
		List.setTotalPage(TotalPage);
		List.setPageIndex(pageindex);
		List.setPageSize(pagesize);

		// 先查出产品
		String[] SOrder = new String[pagesize];
		int i = 0;
		for (OSupplyerOrder item : SupplyerOrderList) {
			SOrder[i] = item.getSupplierOrderId();
			i++;
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		ProductOrderList = basicOrdersDao.getProductOrderListBySupplyOrderIds(SOrder, weiNo, 1);

		String hql1 = " from UWeiCoinLog where supplyOrderId in (:supplyOrderId)  and useType=2 ";
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("supplyOrderId", SOrder);
		List<UWeiCoinLog> find1 = basicOrdersDao.findByMap(hql1, map2);
		// 组合数据
		for (OSupplyerOrder item : SupplyerOrderList) {

			ProductOrderModel Model = new ProductOrderModel();
			List<ProductOrderChild> ProductOrderChildList = new ArrayList<ProductOrderChild>();
			if (find1 != null && find1.size() > 0) {
				for (UWeiCoinLog uWeiCoinLog : find1) {
					if (item.getSupplierOrderId().equals(uWeiCoinLog.getSupplyOrderId())) {
						Model.setCashCoupon(Math.abs(uWeiCoinLog.getCoinAmount()));
					}
				}
			}

			// 供应商订单
			Model.setSupplierOrderId(item.getSupplierOrderId());
			// 支付订单
			Model.setPayOrderID(item.getPayOrderId());
			// 卖家微店号
			Long weiId = item.getSupplyerId() != null ? item.getSupplyerId() : (long) -1;
			Model.setWeiName(baseCommonService.getNickNameById(weiId));
			// 供应商 微店名称
			Model.setSupplierWeiId(weiId);
			Model.setSupplierWeiName(baseCommonService.getNickNameById(weiId));
			// APP需要转换成他们能识别的类型
			Model.setOrderType(get_OrderTypes(item.getOrderType()));
			orderType = ParseHelper.toShort(item.getOrderType().toString());
			if (null != item.getBuyerID() && item.getBuyerID().longValue() != 0) {
				// 记录买家信息
				Model.setBuyerWeiId(item.getBuyerID());
				Model.setBuyerWeiName(baseCommonService.getNickNameById(item.getBuyerID()));
			}
			// TODO Model.setBuyerIdentity(buyerIdentity);
			// "buyerIdentity": "1", //购买者身份，1-微店主2-落地店3-代理商4-供应商
			// 记录退款单号的Id
			List<Long> ids = new ArrayList<Long>();
			// 商品ID
			List<Long> productIdS = new ArrayList<Long>();
			for (OProductOrder ProductOrderItem : ProductOrderList) {
				productIdS.add(ProductOrderItem.getProductId());
				if (ProductOrderItem.getSupplierOrderId().equals(item.getSupplierOrderId())) {
					ProductOrderChild ProductChild = new ProductOrderChild();
					if (ProductOrderItem.getBackOrder() != null && ProductOrderItem.getBackOrder().longValue() != 0) {
						// 记录退款单号的Id
						ids.add(ProductOrderItem.getBackOrder());
						ProductChild.setBackOrder(ProductOrderItem.getBackOrder());
						// isSell=1 1买家 2卖家
						ProductChild.setWapUrl("http://" + AppSettingUtil.getSingleValue("wapDomain") + "/app/refund?tiket={tiket}&backid=" + ProductOrderItem.getBackOrder() + "&isSell=1");
					}
					if (ProductOrderItem.getShareID() != null) {
						ProductChild.setSharePageId(ProductOrderItem.getShareID());
					}
					ProductChild.setSellWeiId(ProductOrderItem.getSellerWeiid());
					// 产品ID
					String ProductOrderID = ProductOrderItem.getProductOrderId() != null ? ProductOrderItem.getProductOrderId() : "-1";
					ProductChild.setProductOrderId(ProductOrderID);
					// 产品图片
					String ProductImg = ProductOrderItem.getProductImg() != null ? ProductOrderItem.getProductImg() : "";
					// 给图片加域名
					ProductImg = ImgDomain.GetFullImgUrl(ProductImg);
					ProductChild.setProductImg(ProductImg);
					// 产品名称
					String ProdcutTitle = ProductOrderItem.getProdcutTitle() != null ? ProductOrderItem.getProdcutTitle() : "";
					ProductChild.setProdcutTitle(ProdcutTitle);
					// 款式属性
					String StyleDes = ProductOrderItem.getStyleDes() != null ? ProductOrderItem.getStyleDes() : "";
					StyleDes = "-1".equals(StyleDes) ? "" : StyleDes;
					ProductChild.setStyleDes(ReplaceSlash(StyleDes));
					// 数量
					int Count = ProductOrderItem.getCount() != null ? ProductOrderItem.getCount() : 0;
					ProductChild.setCount(Count);
					// 单价
					double Price = ProductOrderItem.getPrice() != null ? ProductOrderItem.getPrice() : 0;
					ProductChild.setPrice(Price);

					ProductChild.setShareOne(ProductOrderItem.getShareWeiId());
					ProductChild.setSharePageProducer(ProductOrderItem.getMakerWeiId());

					ProductOrderChildList.add(ProductChild);
				}
			}

			List<PProducts> productList1 = productDao.find_ProductByProductIDS(productIdS);
			if (productList1 != null && productList1.size() > 0) {
				for (ProductOrderChild ProductOrder : ProductOrderChildList) {
					for (PProducts product : productList1) {
						if (ProductOrder.getProductId() == product.getProductId().longValue()) {
							ProductOrder.setDisplayPrice(commonMethod.getDisplayPrice(product.getDefaultPrice(), product.getOriginalPrice(), product.getPercent()));
						}
					}
				}
			}
			// 查询退款订单
			List<Object[]> find = null;
			if (ids != null && ids.size() > 0) {
				String hql = "select backOrder,backStatus,type from TOrderBackTotal where backOrder in (:backOrder) ";
				map1 = new HashMap<String, Object>();
				map1.put("backOrder", ids);
				find = basicOrdersDao.findByMap(hql, map1);
			}

			if (item.getState().shortValue() == 1 || item.getState().shortValue() == 2 || item.getState().shortValue() == 3 || item.getState().shortValue() == 5) {
				// 获取退款订单的状态
				if (find != null && find.size() > 0) {
					for (ProductOrderChild ProductOrder : ProductOrderChildList) {
						for (Object[] objects : find) {
							Model.setBackOrder(ParseHelper.toLong(objects[0].toString()));
							if (ProductOrder.getBackOrder() != 0 && !"".equals(ProductOrder.getBackOrder())) {
								if (ProductOrder.getBackOrder() != 0 && objects[0] != null && ParseHelper.toLong(objects[0].toString()) == ProductOrder.getBackOrder()) {
									ProductOrder.setStates(ParseHelper.toInt(objects[1].toString()));
									int backStatus = ProductOrder.getStates();
									int type = 0;
									if (objects[2] != null)
										type = ParseHelper.toInt(objects[2].toString());
									switch (type) {
									case 1:
										if (backStatus == 0 || backStatus == 2)// 是否显示取消申请退款按钮（1显示
																				// 0不显示）
											ProductOrder.setIsShow(1);
										break;
									case 2:
										if (backStatus == 0 || backStatus == 1 || backStatus == 2 || backStatus == 3 || backStatus == 4)// 是否显示取消申请退款按钮（1显示
																																		// 0不显示）
											ProductOrder.setIsShow(1);
										break;
									default:
										break;
									}
									continue;
								}
							}
						}
					}
				}
			}
			Model.setProductOrderChildList(ProductOrderChildList);
			// 订单时间
			if (item.getOrderTime() != null) {
				Model.setOrderTime(item.getOrderTime());
			} else {
				Date date = new Date();
				Model.setOrderTime(date);
			}
			Double TotalPrice = item.getTotalPrice() != null ? item.getTotalPrice() : (double) 0;
			Double Postage = item.getPostage() != null ? item.getPostage() : 0.0;
			Model.setTotalPrice(TotalPrice + Postage);
			// 订单状态
			Short State = item.getState() != null ? item.getState() : (short) -1;
			Model.setOrderState(State);
			ProductOrderModelList.add(Model);
		}
		// 查是否是预订单
		if (orderType == Short.parseShort(OrderTypeEnum.BookOrder.toString()) || orderType == Short.parseShort(OrderTypeEnum.Puhuo.toString())) {
			// 查询payorder
			List<OPayOrder> bPayOrderList = basicOrdersDao.getOPayOrderlistBySupplyOrderIds(SOrder);
			// 查询OBookAssist
			List<OBookAssist> oBookAssistList = basicOrdersDao.getOBookAssistlistBySupplyOrderIds(SOrder);
			for (OSupplyerOrder item : SupplyerOrderList) {
				OLBookAssist content = new OLBookAssist();
				BigOLBookAssist bigOLBContent = new BigOLBookAssist();
				bigOLBContent.setSupplierOrderId(item.getSupplierOrderId()); // 订单号
				short bState = item.getState() != null ? item.getState() : (short) -11;
				if (bState == Short.parseShort(OrderStatusEnum.Sured.toString())) {
					for (OBookAssist bitem : oBookAssistList) {
						if (bitem.getBookPayType() == Short.parseShort(BookPayTypeEnum.Full.toString())) // 全款
						{
							if (bPayOrderList.size() > 0) {
								for (OPayOrder pitem : bPayOrderList) {
									String itemSID = item.getSupplierOrderId() != null ? item.getSupplierOrderId() : "-1";
									String pitemSID = pitem.getSupplierOrder() != null ? pitem.getSupplierOrder() : "-2";
									short bPTypeState = pitem.getTypeState() != null ? pitem.getTypeState() : (short) -10;
									if (itemSID.equals(pitemSID) && (bPTypeState == Short.parseShort(OrderTypeEnum.BookFullOrder.toString()) || bPTypeState == Short.parseShort(OrderTypeEnum.PuhuoFull.toString()))) {
										String bpayOrder = pitem.getPayOrderId() != null ? pitem.getPayOrderId() : "-11";
										content.sethOrder(bpayOrder); // 全款订单号
										content.setShow((short) 3); // 显示支付全款
										content.setPrice(item.getTotalPrice() + item.getPostage()); // 显示全款金额
									}
								}
							}
						} else// 订金
						{
							if (bPayOrderList.size() > 0) {
								for (OPayOrder pitem : bPayOrderList) {
									String itemSID = item.getSupplierOrderId() != null ? item.getSupplierOrderId() : "-1";
									String pitemSID = pitem.getSupplierOrder() != null ? pitem.getSupplierOrder() : "-2";
									short bPTypeState = pitem.getTypeState() != null ? pitem.getTypeState() : (short) -10;
									if (itemSID.equals(pitemSID) && (bPTypeState == Short.parseShort(OrderTypeEnum.BookHeadOrder.toString()) || bPTypeState == Short.parseShort(OrderTypeEnum.PuhuoHeader.toString()))) {
										String horder = pitem.getPayOrderId() != null ? pitem.getPayOrderId() : "-10";
										content.sethOrder(horder); // 首款订单号
										content.setShow((short) 2); // 显示支付订金
										double price = pitem.getTotalPrice() != null ? pitem.getTotalPrice() : 0.0;
										content.setPrice(price);
									}
								}
							}
						}
					}
				}
				if (bState == Short.parseShort(OrderStatusEnum.PayedDeposit.toString())) // 已付订金
																							// //显示付尾款
				{
					for (OBookAssist bitem : oBookAssistList) {
						// 2015-4-29 修改
						String itemDSID = item.getSupplierOrderId() != null ? item.getSupplierOrderId() : "-1"; // 供应商订单号

						String bitemSID = bitem.getSupplierOrderId() != null ? bitem.getSupplierOrderId() : "-2"; // 预订单辅助表订单号
						//
						if (itemDSID.equals(bitemSID)) // 两个订单号相等时
						{
							if (bitem.getBookPayType() == Short.parseShort(BookPayTypeEnum.percent.toString()) || bitem.getBookPayType() == Short.parseShort(BookPayTypeEnum.specifyamount.toString())) {
								Short fahuoqian = bitem.getTailPayType() != null ? bitem.getTailPayType() : -11;
								if (fahuoqian.equals(Short.parseShort(TailPayTypeEnum.predelivery.toString()))) {
									if (bPayOrderList.size() > 0) {
										for (OPayOrder pitem : bPayOrderList) {
											String itemSID = item.getSupplierOrderId() != null ? item.getSupplierOrderId() : "-1";
											String pitemSID = pitem.getSupplierOrder() != null ? pitem.getSupplierOrder() : "-2";
											short bPTypeState = pitem.getTypeState() != null ? pitem.getTypeState() : (short) -10;
											if (itemSID.equals(pitemSID) && (bPTypeState == Short.parseShort(OrderTypeEnum.BookTailOrder.toString()) || bPTypeState == Short.parseShort(OrderTypeEnum.PuhuoTail.toString()))) {
												String tOrder = pitem.getPayOrderId() != null ? pitem.getPayOrderId() : "-11";
												content.settOrder(tOrder);
												content.setShow((short) 1);
												double price = pitem.getTotalPrice() != null ? pitem.getTotalPrice() : 0.0;
												content.setPrice(price);
											}
										}
									}
								}
							}
						}
					}
				}
				// 已收货
				if (bState == Short.parseShort(OrderStatusEnum.Accepted.toString())) // 显示付尾款
				{
					for (OBookAssist bitem : oBookAssistList) {
						String itemSID = item.getSupplierOrderId() != null ? item.getSupplierOrderId() : "-1";
						String bitemSID = bitem.getSupplierOrderId() != null ? bitem.getSupplierOrderId() : "-2";
						if (itemSID.equals(bitemSID)) {
							BookPayTypeValue = bitem.getBookPayType() != null ? bitem.getBookPayType() : (short) -1;
							if (BookPayTypeValue == Short.parseShort(BookPayTypeEnum.percent.toString()) || BookPayTypeValue == Short.parseShort(BookPayTypeEnum.specifyamount.toString())) {
								Short tailPayType = bitem.getTailPayType() != null ? bitem.getTailPayType() : -1;

								if (tailPayType == Short.parseShort(TailPayTypeEnum.afterdelivery.toString())) {
									for (OPayOrder pitem : bPayOrderList) {
										String pitemSID = pitem.getSupplierOrder() != null ? pitem.getSupplierOrder() : "-2";
										short bPState = pitem.getState() != null ? pitem.getState() : (short) -10;
										short bPTypeState = pitem.getTypeState() != null ? pitem.getTypeState() : -10;
										if (itemSID.equals(pitemSID) && bPState == Short.parseShort(OrderStatusEnum.Nopay.toString()) && (bPTypeState == Short.parseShort(OrderTypeEnum.BookTailOrder.toString()) || bPTypeState == Short.parseShort(OrderTypeEnum.PuhuoTail.toString()))) {
											content.setShow((short) 1);
											double balancepayment = pitem.getTotalPrice() != null ? pitem.getTotalPrice() : -1;
											// content.setBalancepayment(balancepayment);
											content.setPrice(balancepayment); // 支付尾款
											content.setShow((short) 1);
											String torder = pitem.getPayOrderId() != null ? pitem.getPayOrderId() : "-11";
											content.settOrder(torder);
										}
									}
								}
							}
						}
					}
				}
				bigOLBContent.setContent(content);
				BookAssistContentList.add(bigOLBContent);
			}
			// 遍历一下供应商订单
			if (ProductOrderModelList.size() > 0 && BookAssistContentList.size() > 0) {
				for (ProductOrderModel model : ProductOrderModelList) {
					for (BigOLBookAssist olitem : BookAssistContentList) {
						if (model.getSupplierOrderId().equals(olitem.getSupplierOrderId())) {
							model.setBookAssist(olitem.getContent());
						}
					}
				}
			}
		}
		List.setList(ProductOrderModelList);
		RqStatus.setStatu(ReturnStatus.Success);
		RqStatus.setStatusreson("成功！");
		RqStatus.setBasemodle(List);
		return RqStatus;
	}

	// 替换字符串中的|
	public String ReplaceSlash(String StyleDes) {
		String result = StyleDes.replace("|", "");
		return result;
	}

	/**
	 * 重新组合收货地址（用于API）
	 * 
	 * @param address
	 * @return
	 */
	private UCustomerAddress assemblyAddress(UCustomerAddr address) {
		UCustomerAddress model = new UCustomerAddress();
		model.setIsStoreAddress(address.getIsShopAddress() != null ? address.getIsShopAddress().intValue() : 0);
		model.setAddressID(address.getCaddrId());
		model.setDetailAddr(address.getDetailAddr());
		model.setIsDefault(address.getIsDefault());
		model.setMobilePhone(address.getMobilePhone());
		model.setQq(address.getQq());
		model.setWeiID(address.getWeiId());
		model.setReceiverName(address.getReceiverName());
		// String addressAll = "";
		if (address.getProvince() != null && address.getProvince() > 0) {
			model.setProvince(address.getProvince());
			model.setProvinceName(regionService.getNameByCode(address.getProvince()));
			// addressAll+=model.getProvinceName();
		}

		if (address.getCity() != null && address.getCity() > 0) {
			model.setCity(address.getCity());
			model.setCityName(regionService.getNameByCode(address.getCity()));
			// addressAll+=model.getCityName();
		}

		if (address.getDistrict() != null && address.getDistrict() > 0) {
			model.setDistrict(address.getDistrict());
			model.setDistrictName(regionService.getNameByCode(address.getDistrict()));
			// addressAll+=model.getDistrictName();
		}

		if (address.getStreet() != null && address.getStreet() > 0) {
			model.setStreet(address.getStreet());
			model.setStreetName(regionService.getNameByCode(address.getStreet()));
			// addressAll+=model.getStreetName();
		}
		return model;
	}

}
