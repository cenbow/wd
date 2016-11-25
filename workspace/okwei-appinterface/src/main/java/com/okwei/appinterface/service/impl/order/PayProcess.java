package com.okwei.appinterface.service.impl.order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.bean.vo.order.PayOrderReturn;
import com.okwei.appinterface.bean.vo.order.PayParamVO;
import com.okwei.appinterface.bean.vo.order.SettlementParam;
import com.okwei.appinterface.bean.vo.order.SettlementProductParam;
import com.okwei.appinterface.enums.ProductStatus;
import com.okwei.appinterface.service.order.IPayProcess;
import com.okwei.bean.domain.AActProducts;
import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActivity;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.OOrderAddr;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.PProductStyles;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.TShoppingCar;
import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiCoinLog;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.ActProductVerState;
import com.okwei.bean.enums.MobileBindEnum;
import com.okwei.bean.enums.OrderFrom;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.ShoppingCarSourceEnum;
import com.okwei.bean.enums.ShoppingCartTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.enums.VerifyCodeType;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.activity.ActivityModel;
import com.okwei.bean.vo.order.BAddressVO;
import com.okwei.bean.vo.order.BShoppingCartProductVO;
import com.okwei.bean.vo.order.BShoppingCartVO;
import com.okwei.bean.vo.order.PayOrderInfoVO;
import com.okwei.common.CommonMethod;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.IBasicShoppingCartService;
import com.okwei.service.IRegionService;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.service.impl.BaseService;
import com.okwei.service.order.IBasicPayService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.BitOperation;
import com.okwei.util.DES;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.MD5Encrypt;
import com.okwei.util.ObjectUtil;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;
import com.okwei.util.SignFactory;
import com.okwei.util.VerifyCode;

@Service
public class PayProcess extends BaseService implements IPayProcess{
	
	@Autowired
	private IBasicOrdersDao ordersService;
	@Autowired
	private IBasicPayService payService;
	@Autowired 
	private IBaseProductDao productDao;
	@Autowired
	private IBasicShoppingCartService cartService;
	@Autowired
	private IBaseCommonService commonService;
	@Autowired
	private IRegionService regionService;
	
	@Autowired
	private IBaseActivityService activityService;
	
	
	// 支付密码被允许输错次数
	private static int weiPayWrongCount = 10;
	
	public ReturnModel updateWalletPay(PayParamVO param,long weiid){
		ReturnModel rq=new ReturnModel();
		if(param==null||ObjectUtil.isEmpty(param.getOrderNo())
				||ObjectUtil.isEmpty(param.getPwd())
				||ObjectUtil.isEmpty(param.getSign())){
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("参数有误");
			return rq;
		}
		// logger.error("in");
		String payWrongPwd_name = "WrongPwdCountNew_" + weiid;
		String sendMsgCount_name = "SendMsgCountNew_" + param.getOrderNo();
		
		// 支付密码输错次数
		int weiPayCount = ParseHelper.toInt(String.valueOf(RedisUtil.getObject(payWrongPwd_name)));
		// 此单 短信验证码发送数量
		int sendMsgCount = ParseHelper.toInt(String.valueOf(RedisUtil.getObject(sendMsgCount_name)));
		if (weiPayCount > weiPayWrongCount) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("支付密码被锁定");
			return rq;
		}
		boolean isneedImgCode = false;
		if (sendMsgCount > 5) // 需要图片验证码
		{
			isneedImgCode = true;
//			String imgCodeCheck = RedisUtil.GetObjectByTiket(tiket + "VERIFYCODE").toString();
//			if ("".equals(imgCodeCheck) || null == imgCodeCheck) {
//				rq.setStatu(ReturnStatus.NeedParam);
//				rq.setStatusreson("图片验证码获取失败");
//				return rq;
//			}
//			if (!imgCodeCheck.equals(param.getCheckCode())) {
//				rq.setStatu(ReturnStatus.NeedParam);
//				rq.setStatusreson("图片验证码错误");
//				return rq;
//			}
		}
		JSONObject signJson = new JSONObject();
		signJson.put("order_no", param.getOrderNo());
		signJson.put("pwd", param.getPwd());
		// 签名匹对（）
		if (!param.getSign().equals(SignFactory.addSign(signJson))) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("签名有误");
			return rq;
		}
		OPayOrder orderInfo= payService.getOPayOrder(param.getOrderNo(), true);//super.get(OPayOrder.class, param.getOrderNo());
		if (orderInfo == null || orderInfo.getWeiId() == null || weiid != orderInfo.getWeiId()) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("您不能支付别人的订单！");
			return rq;
		} else if (orderInfo.getState() != null && Short.parseShort(OrderStatusEnum.Tovoided.toString()) == orderInfo.getState()) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("支付单已失效！");
			return rq;
		}
		// 需要支付的总金额
		double totalprice = ParseHelper.getDoubleDefValue(orderInfo.getTotalPrice());
		UWallet userWallet = productDao.get(UWallet.class, weiid);
		if (userWallet == null || userWallet.getBalance() == null ) {//|| userWallet.getBalance() <= 0
			rq.setStatusreson("余额不足！");
			rq.setStatu(ReturnStatus.SystemError);
			return rq;
		}
		if (ObjectUtil.isEmpty(userWallet.getPayPassword())) {
			rq.setStatusreson("用户未设置支付密码！");
			rq.setStatu(ReturnStatus.SystemError);
			return rq;
		}
		String pwd = DES.decrypt(param.getPwd(), "");
		if (ObjectUtil.isEmpty(pwd)) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("支付密码有误！");
			return rq;
		}
		pwd = MD5Encrypt.encrypt(pwd);
		if (isneedImgCode)
			rq.setStatu(ReturnStatus.NeedParam);
		else
			rq.setStatu(ReturnStatus.SystemError);
		if (!pwd.equals(userWallet.getPayPassword())) {
			weiPayCount++;
			RedisUtil.setObject(payWrongPwd_name, weiPayCount,300); 
			int countPut = weiPayWrongCount - weiPayCount;
			rq.setStatusreson("密码输入错误,还有" + countPut + "次机会");
			return rq;
		}
		
		//12先钱包后金币，21先金币后钱包，1仅钱包（没用金币），2仅微金币（没用钱包）
		String paySort=param.getPayOrder();
		String paytype="1";//兼容老版本默认是钱包支付
		if(!ObjectUtil.isEmpty(paySort))
			paytype=paySort; 
		
		// 3.微钱包金额是否合法
		if (paytype.contains("1")&&(userWallet.getBalance() == null || userWallet.getBalance() < 0.01)) {
		    rq.setStatu(ReturnStatus.SystemError);
		    rq.setStatusreson("微钱包余额不足");
		    return rq;
		}
		// 4.微金币金额是否合法
		if (paytype.contains("2")&&(userWallet.getWeiDianCoin() == null || userWallet.getWeiDianCoin() < 0.01)) {
			rq.setStatu(ReturnStatus.SystemError);
		    rq.setStatusreson("微金币余额不足");
		    return rq;
		}
		if(paytype.contains("2") && (orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.BatchGys.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.BatchPortNoServiceFee.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.BatchRzy.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.ChongZhi.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.DaoHang.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.GysAndVerifier.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.Reward.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.YunGys.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.YunGysNoServiceFee.toString())
				|| orderInfo.getTypeState()==Short.parseShort(OrderTypeEnum.YunRzy.toString()))){
			rq.setStatu(ReturnStatus.SystemError);
		    rq.setStatusreson("微金币只能用于购物！");
		    return rq;
		}
		Double walletAmout = 0D;// 默认微钱包付款金额为0
		Double weicoinAmout=0D;//默认微金币付款金额为0
	
		// 2、数据处理（包括：佣金分配，订单修改，钱包金额处理）
		if(param.getIsCoin()>0){
			totalprice=totalprice-param.getCoinAmount();
		}
		
		if(paytype.equals("12")) //先选择了微钱包支付后选择微金币支付
		{
			if (userWallet.getBalance() >= totalprice) {
			    walletAmout = totalprice;// 如果微钱包的余额大于需要支付的订单金额，支付的微钱包为订单金额总价
			} else {
			    walletAmout = userWallet.getBalance();// 微钱包金额不足于支付订单是，微钱包有多少支付多少
			    if(userWallet.getWeiDianCoin()>=(totalprice-walletAmout))
			    {
			    	weicoinAmout=totalprice-walletAmout;
			    }
			    else //微金币金额不足，需要第三方支付
			    {
			    	weicoinAmout=userWallet.getWeiDianCoin();
			    }
			}
			
		}
		else  if(paytype.equals("21")) //先选择了微金币支付后选择微钱包支付
		{
			if(userWallet.getWeiDianCoin()>=totalprice)
			{
				weicoinAmout=totalprice;
			} else {
				weicoinAmout=userWallet.getWeiDianCoin();
				if(userWallet.getBalance()>=(totalprice-weicoinAmout)){
					walletAmout=totalprice-weicoinAmout;
				}else {//钱包金额不足，需要第三方支付
					walletAmout=userWallet.getBalance();
				}
			}
			
		}
		else if(paytype.equals("1")) //选择了微钱包支付
		{
			if (userWallet.getBalance() >= totalprice) {
			    walletAmout = totalprice;// 如果微钱包的余额大于需要支付的订单金额，支付的微钱包为订单金额总价
			} else { //需要第三方支付
			    walletAmout = userWallet.getBalance();// 微钱包金额不足于支付订单是，微钱包有多少支付多少
			}
		}
		else if(paytype.equals("2")) //选择了微金币支付
		{
			if(userWallet.getWeiDianCoin()>=totalprice)
				weicoinAmout=totalprice;
			else {//需要第三方支付
				weicoinAmout=userWallet.getWeiDianCoin();
			}
		}
		
		int payType=param.getIsFullPay();//是否全额支付
		if ( ((walletAmout+weicoinAmout) > 200) || (payType == 0 && totalprice > 200)) {
			if (ObjectUtil.isEmpty(param.getvCode())) {
				rq.setStatusreson("请输入短信验证码！");
				return rq;
			}
			UWeiSeller seller = productDao.get(UWeiSeller.class, weiid);
			if (seller == null || seller.getStates() == null || seller.getStates().shortValue() != 1) {
				rq.setStatusreson("用户信息异常！");
				return rq;
			}
			if (null == seller.getMobilePhone() || "".equals(seller.getMobilePhone()) || Short.parseShort(MobileBindEnum.Bind.toString()) != seller.getMobileIsBind().shortValue()) {
				rq.setStatusreson("手机号未绑定！");
				return rq;
			}
			sendMsgCount++;
			RedisUtil.setObject(sendMsgCount_name, sendMsgCount);
			if (!VerifyCode.checkVerifyCode(seller.getMobilePhone(), VerifyCodeType.pay, param.getvCode())) {
				rq.setStatusreson("短信验证码有误！");
				return rq;
			}
		}
		
		//modify by tan 修改增加微金币支付
		// ** 钱包全额支付
//		if ( 0 == payType) {
			if(Math.abs(totalprice -(walletAmout+weicoinAmout))<0.001)
			{	
				// 1、更新支付单钱包金额部分
				orderInfo.setWalletmoney(walletAmout);
				orderInfo.setWeiDianCoin(weicoinAmout);
				BResultMsg msgPay = payService.editOrderDataProcess(orderInfo, PayTypeEnum.WeiWallet);
				if (msgPay.getState() == 1) {
					rq.setStatu(ReturnStatus.Success);
					rq.setStatusreson("支付成功");
					Map<String, Object> mapResult=new HashMap<String, Object>();
					mapResult.put("wapUrl", "http://"+AppSettingUtil.getSingleValue("currentDomain")+"/v5/payment/payok?orderno="+orderInfo.getPayOrderId());
					rq.setBasemodle(mapResult);
				} else {
					rq.setStatu(ReturnStatus.SystemError);
					rq.setStatusreson(msgPay.getMsg());
				}
			}
			else
			{
//				rq.setStatu(ReturnStatus.SystemError);
//				rq.setStatusreson("支付有误，余额不足");
				orderInfo.setWalletmoney(walletAmout);
				orderInfo.setWeiDianCoin(weicoinAmout);
				super.update(orderInfo);
				rq.setStatu(ReturnStatus.Success);
				rq.setStatusreson("成功！");
			}
//		}
//		// **钱包 部分支付
//		else if ( payType > 0) {
//			try {
//				orderInfo.setWalletmoney(walletAmout);
//				orderInfo.setWeiDianCoin(weicoinAmout);
//				super.update(orderInfo);
//				rq.setStatu(ReturnStatus.Success);
//				rq.setStatusreson("成功！");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			rq.setStatusreson("余额不足！");
//		}
		return rq;
	}
	
	public ReturnModel updateWalletPayTest(PayParamVO param,long weiid){
		ReturnModel rq=new ReturnModel();
		if(param==null||ObjectUtil.isEmpty(param.getOrderNo())
				||ObjectUtil.isEmpty(param.getPwd())){
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("参数有误");
			return rq;
		}
		OPayOrder orderInfo= payService.getOPayOrder(param.getOrderNo(), true);
		if (orderInfo == null || orderInfo.getWeiId() == null || weiid != orderInfo.getWeiId()) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("您不能支付别人的订单！");
			return rq;
		} else if (orderInfo.getState() != null && Short.parseShort(OrderStatusEnum.Tovoided.toString()) == orderInfo.getState()) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("支付单已失效！");
			return rq;
		} 
		// 需要支付的总金额
		double totalprice = ParseHelper.getDoubleDefValue(orderInfo.getTotalPrice());

		UWallet userWallet = productDao.get(UWallet.class, weiid);// getUWallet(weiid);
		if (userWallet == null || userWallet.getBalance() == null || userWallet.getBalance() <= 0) {
			rq.setStatusreson("余额不足！");
			rq.setStatu(ReturnStatus.SystemError);
			return rq;
		}
		if (ObjectUtil.isEmpty(userWallet.getPayPassword())) {
			rq.setStatusreson("用户未设置支付密码！");
			rq.setStatu(ReturnStatus.SystemError);
			return rq;
		}
		String pwd = DES.decrypt(param.getPwd(), "");
		if (ObjectUtil.isEmpty(pwd)) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("支付密码有误！");
			return rq;
		}
		pwd = MD5Encrypt.encrypt(pwd);
		
		if (!pwd.equals(userWallet.getPayPassword())) {
			rq.setStatusreson("密码错误");
			return rq;
		}
		int payType=param.getIsFullPay();
		// ** 钱包全额支付
		if (userWallet.getBalance() >= totalprice && 0 == payType) {
			// 2、数据处理（包括：佣金分配，订单修改，钱包金额处理）
			if(param.getIsCoin()>0){
				totalprice=totalprice-param.getCoinAmount();
			}
			orderInfo.setWalletmoney(totalprice); 
			BResultMsg msgPay = payService.editOrderDataProcess(orderInfo, PayTypeEnum.WeiWallet);
			if (msgPay.getState() == 1) {
				rq.setStatu(ReturnStatus.Success);
				rq.setStatusreson("支付成功");
				Map<String, Object> mapResult=new HashMap<String, Object>();
				mapResult.put("wapUrl", "http://"+AppSettingUtil.getSingleValue("currentDomain")+"/v5/payment/payok?orderno="+orderInfo.getPayOrderId());
				rq.setBasemodle(mapResult);
			} else {
				rq.setStatu(ReturnStatus.SystemError);
				rq.setStatusreson(msgPay.getMsg());
			}
		}
		// **钱包 部分支付
		else if (userWallet.getBalance() < totalprice && payType > 0) {
			try {
				double walletMoney = userWallet.getBalance();
				orderInfo.setWalletmoney(walletMoney);
				super.update(orderInfo);
				rq.setStatu(ReturnStatus.Success);
				rq.setStatusreson("成功！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			rq.setStatusreson("余额不足！");
		}
		return rq;
	}
	
	public ReturnModel getOrderPayInfo(List<String> channelIds,int orderType, long weiid) throws Exception{
		ReturnModel rq=new ReturnModel();
		try {
			BResultMsg result=payService.update_OrderPayInfo(weiid, OrderFrom.APP.getNo(),orderType, channelIds);
			if(result.getState()==1){
				rq.setStatu(ReturnStatus.Success);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("orderId", result.getMsg());
				rq.setBasemodle(map);
			}else {
				rq.setStatu(ReturnStatus.SystemError);
				rq.setStatusreson(result.getMsg());
			}
		} catch (Exception e) {
			rq.setStatu(ReturnStatus.ParamError);
			rq.setBasemodle(e.getMessage());
			rq.setStatusreson(e.getMessage()); 
			throw new RuntimeException(e.getMessage());
		}
		return rq;
	}
	
	public ReturnModel getSettlementData(List<SettlementParam> paramlist,long weiid){
		ReturnModel rq=new ReturnModel();
		if(paramlist!=null&&paramlist.size()>0){
			Map<String, Object> mapResult=new HashMap<String, Object>();
			List<BShoppingCartVO> resultlist=new ArrayList<BShoppingCartVO>();
			for (SettlementParam settle : paramlist) {
				if(settle.getProductList()!=null&&settle.getProductList().size()>0){
					BShoppingCartVO vo=new BShoppingCartVO();
					vo.setSupplierId(settle.getSupplierWeiId());
					vo.setBuyType(settle.getBuyType());
					vo.setSource(settle.getSource()); 
					vo.setDemandId(String.valueOf(settle.getDemandId()));
					vo.setSupplierName(commonService.getShopNameByWeiId(settle.getSupplierWeiId())); 
					vo.setIsFirstOrder(0); 
					vo.setCurrentLogisticId("0");  
					List<BShoppingCartProductVO> prolist=new ArrayList<BShoppingCartProductVO>();
					double totalPrice=0;
					for (SettlementProductParam pp : settle.getProductList()) {
						PProducts products=productDao.get(PProducts.class, pp.getProNum());
						PProductStyles style=super.getById(PProductStyles.class, pp.getStyleId());
						if (style != null) {
							BShoppingCartProductVO pro = new BShoppingCartProductVO();
							pro.setImageUrl(ImgDomain.GetFullImgUrl(products.getDefaultImg(), 24));
							pro.setProductPrice(pp.getPrice());
							if (settle.getSource() == null) {
								pro.setProductPrice(pp.getPrice());
							} else if (settle.getSource().shortValue() == Short.parseShort(ShoppingCarSourceEnum.Landi.toString())) {
								if (style.getLandPrice() != null && style.getLandPrice() > 0) {
									pro.setProductPrice(style.getLandPrice());
								}
							} else if (settle.getSource().shortValue() == Short.parseShort(ShoppingCarSourceEnum.agency.toString())) {
								if (style.getAgencyPrice() != null && style.getAgencyPrice() > 0) {
									pro.setProductPrice(style.getAgencyPrice());
								}
							}
							pro.setProNum(pp.getProNum());
							pro.setScid(String.valueOf(pp.getScid()));
							pro.setCount(pp.getCount());
							pro.setBuyShopId(pp.getBuyShopId());
							pro.setProductTitle(products.getProductTitle());
							pro.setProductStyleName(cartService.getProductStyleName(pp.getProNum(), pp.getStyleId()));
							pro.setProductStyleId(String.valueOf(pp.getStyleId()));
							//分享需求添加制作人、分享人
							pro.setSharePageProducer(pp.getSharePageProducer());
							pro.setShareOne(pp.getShareOne());
							pro.setSharePageId(pp.getSharePageId());
							//下面是添加原价
                            pro.setDisplayPrice(CommonMethod.getInstance().getDisplayPrice(products.getDefaultPrice(), products.getOriginalPrice(), products.getPercent()));

                            /*----------产品是否在限时抢购中----------------*/
                            AActProductsShowTime act=activityService.getAActProductsShowTime(pro.getProNum(), true);
                            if(act!=null){
                            	AActivityProducts actProducts=productDao.get(AActivityProducts.class, act.getProActId());	
                            	if(actProducts!=null&&actProducts.getState()==Short.parseShort(ActProductVerState.Ok.toString())){
                            		AActivity actModel=productDao.get(AActivity.class, actProducts.getActId());
                            		if(actModel!=null){ 
                            			ActivityModel model=new ActivityModel();
                            			if(products.getPublishType()!=null && products.getPublishType()>0)
                            			{
                            				model.setActId(0);
                            			}
                            			else
                            			{
                            				model.setActId(actProducts.getActId().intValue());
                            			}
                                		model.setTitle(actModel.getTitle());
                                		model.setBeginTime(DateUtils.format(act.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
                                		model.setEndTime(DateUtils.format(act.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
                                		model.setActPrice(actProducts.getPrice());
                                		model.setState(1);
                                		//看数量限制
                                		String key="BuyLimitCount_"+weiid+"_"+act.getActPid()+"_"+act.getProductId();
                                 		int count= ParseHelper.toInt(String.valueOf(RedisUtil.getObject(key))) ;
                                 		if((count+pp.getCount())>5){
                                 			
                                 			rq.setStatu(ReturnStatus.ValidCodeError);
                                			rq.setBasemodle(mapResult);
                                			rq.setStatusreson("超过本次购买数量");
                                			return rq;
                                 		}else {
                                 			model.setBuyNumLimit(5-count-pp.getCount());
                        				}
                                 		pro.setActivityModel(model); 
                                		mapResult.put("payTimeLimit", 600);
                            		}
                            	}
                            }
							prolist.add(pro);
							totalPrice += pro.getProductPrice().doubleValue() * pro.getCount().intValue();
						}
					}
					
					if(settle.getSource().shortValue()==Short.parseShort(ShoppingCarSourceEnum.Landi.toString())&&prolist!=null&&prolist.size()>0){
						int identity=payService.isShop(weiid, prolist.get(0).getProNum());
						if(!BitOperation.verIdentity(identity, UserIdentityType.Ground))
						{
							if(settle.getDemandId()!=null){
								USupplyDemand demandMod=productDao.get(USupplyDemand.class, settle.getDemandId());
								if(demandMod!=null&&demandMod.getOrderAmout()!=null){
									if(totalPrice>=demandMod.getOrderAmout().doubleValue()){
										vo.setIsFirstOrder(1); 
									}
								}
							}
						}
					}
					if(prolist!=null&&prolist.size()>0){
						vo.setProductList(prolist);
						resultlist.add(vo);
					}
				}
			}
			List<BShoppingCartVO> result=new ArrayList<BShoppingCartVO>();
			BAddressVO addressVO=null;
			String hql_add="from UCustomerAddr u where u.weiId=? and u.isDefault=1";
			UCustomerAddr addr=productDao.getUniqueResultByHql(hql_add, weiid);
			if(addr!=null){
				addressVO=new BAddressVO();
				addressVO.setAddressId(addr.getCaddrId().toString());
				addressVO.setProvince(addr.getProvince()); 
				addressVO.setCity(addr.getCity());
				addressVO.setDistrict(addr.getDistrict());
				addressVO.setPhone(addr.getMobilePhone());
				addressVO.setReceiveName(addr.getReceiverName());
				addressVO.setAddress(addr.getDetailAddr());
				addressVO.setAddress(addr.getDetailAddr()); 
			}
			if(resultlist!=null&&resultlist.size()>0){
				for (BShoppingCartVO cart : resultlist) {
					cart=cartService.getCartModel(cart, addressVO);
					result.add(cart);
				}
			}
			
			mapResult.put("receiveInfo", addressVO);
			mapResult.put("supplierOrderList", result);
			rq.setStatu(ReturnStatus.Success);
			rq.setBasemodle(mapResult);
			rq.setStatusreson("成功");
		}
		return rq;
	}
	
	public ReturnModel getSettlementDataSure(List<BShoppingCartVO> resultlist,BAddressVO addressVO,long weiid){
		ReturnModel rq = new ReturnModel();
		Map<String, Object> mapResult = new HashMap<String, Object>();
		List<BShoppingCartVO> result = new ArrayList<BShoppingCartVO>();
		if (resultlist != null && resultlist.size() > 0) {
			for (BShoppingCartVO cart : resultlist) {
				cart = cartService.getCartModel(cart, addressVO);
				result.add(cart);
				if(cart.getProductList()!=null&&cart.getProductList().size()>0){
					for (BShoppingCartProductVO pp : cart.getProductList()) {
						if(pp.getActivityModel()!=null&&pp.getActivityModel().getActId()>0){
							PProducts pd= super.getById(PProducts.class, pp.getProNum());
							if(pd!=null && pd.getPublishType()!=null &&pd.getPublishType()>0)
							{
								pp.getActivityModel().setActId(0);
							}
							mapResult.put("payTimeLimit", 600);
						}
					}
				}
			}
		}
	
		mapResult.put("receiveInfo", addressVO);
		mapResult.put("supplierOrderList", result);
		rq.setStatu(ReturnStatus.Success);
		rq.setBasemodle(mapResult);
		rq.setStatusreson("成功");
		return rq;
	}

	public ReturnModel getPayParamVO(String orderno,long weiid){
		ReturnModel rqModel=new ReturnModel();
		PayOrderReturn result=new PayOrderReturn();
		OPayOrder order=payService.getOPayOrder(orderno, true);// super.get(OPayOrder.class, orderno);
		if(order!=null){
			if(order.getWeiId()==null||order.getWeiId()!=weiid){
				rqModel.setStatu(ReturnStatus.SystemError);
				rqModel.setStatusreson("不是本人订单，无法查看");
				return rqModel;
			}
			result.setOrderNo(order.getPayOrderId()); 
			result.setOrderState(order.getState());
			result.setOrderType(order.getTypeState()); 
			result.setTotalPrice(order.getTotalPrice()); 
			boolean isHaveAddress=false;
			long addressId=0;
			if(Short.parseShort(OrderTypeEnum.BookHeadOrder.toString()) == order.getTypeState()
					|| Short.parseShort(OrderTypeEnum.BookTailOrder.toString()) == order.getTypeState()
					|| Short.parseShort(OrderTypeEnum.BookFullOrder.toString()) == order.getTypeState()
					|| Short.parseShort(OrderTypeEnum.Puhuo.toString()) == order.getTypeState()
					|| Short.parseShort(OrderTypeEnum.PuhuoFull.toString()) == order.getTypeState()
					|| Short.parseShort(OrderTypeEnum.PuhuoTail.toString()) == order.getTypeState()
					|| Short.parseShort(OrderTypeEnum.PuhuoHeader.toString()) == order.getTypeState()){
				isHaveAddress=true;
				OSupplyerOrder supplyerOrder=productDao.get(OSupplyerOrder.class, order.getSupplierOrder());
				if(supplyerOrder!=null)
					addressId=supplyerOrder.getAddrId();
			}
			else if (Short.parseShort(OrderTypeEnum.BatchGys.toString()) != order.getTypeState() 
					&& Short.parseShort(OrderTypeEnum.BatchRzy.toString()) != order.getTypeState() 
					&& Short.parseShort(OrderTypeEnum.GysAndVerifier.toString()) != order.getTypeState()
					&& Short.parseShort(OrderTypeEnum.ChongZhi.toString()) != order.getTypeState() 
					&& Short.parseShort(OrderTypeEnum.YunGys.toString()) != order.getTypeState() 
					&& Short.parseShort(OrderTypeEnum.BatchPortNoServiceFee.toString()) != order.getTypeState()
					&& Short.parseShort(OrderTypeEnum.YunGysNoServiceFee.toString()) != order.getTypeState()
					&& Short.parseShort(OrderTypeEnum.Reward.toString()) != order.getTypeState()
					&& Short.parseShort(OrderTypeEnum.ChouDian.toString()) != order.getTypeState()) {
				isHaveAddress=true;
				List<OSupplyerOrder> supplyerOrders=ordersService.find_SupplyerOrderByOrderID(order.getPayOrderId());
				if(supplyerOrders!=null&&supplyerOrders.size()>0)
					addressId=supplyerOrders.get(0).getAddrId();
				List<String> supplyOrderIds=new ArrayList<String>();
				for (OSupplyerOrder ss : supplyerOrders) {
					supplyOrderIds.add(ss.getSupplierOrderId());
				}
				if(supplyOrderIds!=null&&supplyOrderIds.size()>0){
					String hqlString=" from UWeiCoinLog w where w.supplyOrderId in(:supplyOrderIds) and w.useType=1 ";
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("supplyOrderIds", supplyOrderIds);
					List<UWeiCoinLog> coinLogs=productDao.findByMap(hqlString, map);
					if(coinLogs!=null&&coinLogs.size()>0){
						result.setFirstShop(1);
						double coinTotal=0;
						for (UWeiCoinLog cc : coinLogs) {
							coinTotal+=cc.getCoinAmount().doubleValue();
						}
						result.setCoinAmount(coinTotal);
					}
				}
			}
			if(isHaveAddress){
				OOrderAddr addr=productDao.get(OOrderAddr.class, addressId);
				if(addr!=null){
					BAddressVO addressVO=new BAddressVO();
					addressVO.setAddressId(String.valueOf(addressId));
					addressVO.setReceiveName(addr.getReceiverName());
					addressVO.setPhone(addr.getMobilePhone()); 
					String address=regionService.getNameByCode(addr.getProvince())
					+regionService.getNameByCode(addr.getCity())
					+regionService.getNameByCode(addr.getDistrict())
					+addr.getDetailAddr();
					addressVO.setAddress(address);//addr.getDetailAddr()
					result.setAddress(addressVO);
				}
			}
		}
		rqModel.setStatu(ReturnStatus.Success);
		rqModel.setBasemodle(result);
		return rqModel;
	}
	
	public ReturnModel getPayOrderMsg(String orderId,long weiid){
		ReturnModel rq=new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		if(ObjectUtil.isEmpty(orderId)){
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("订单号为空");
			return rq;
		}
//		Map<String, Object> resultMap=new HashMap<String, Object>();
//		OPayOrder payOrder=payService.getOPayOrder(orderId, true);// super.get(OPayOrder.class, orderId);
//		if(payOrder!=null) {
//			resultMap.put("orderId", orderId);
//			resultMap.put("amount", payOrder.getTotalPrice());
//			double coinTotal=0;
//			boolean useCash=false;
//			UWallet wallet=productDao.get(UWallet.class, weiid);
//			if(wallet==null||wallet.getTotalCoin()==null||wallet.getTotalCoin().doubleValue()<=0){
//				resultMap.put("useCash", 0);
//				resultMap.put("cashAmount", 0);
//				rq.setBasemodle(resultMap);
//				return rq;
//			}else {
//				coinTotal=wallet.getTotalCoin();
//			}
//			double canUseCoin=0;
//			if(payOrder.getTypeState()!=null&&payOrder.getTypeState()==Short.parseShort(OrderTypeEnum.Jinhuo.toString())){
//				List<OSupplyerOrder> supplyerOrders=ordersService.find_SupplyerOrderByOrderID(orderId);
//				if(supplyerOrders!=null&&supplyerOrders.size()>0){
//					for (OSupplyerOrder ss : supplyerOrders) {
//						List<OProductOrder> productOrders=ordersService.find_ProductOrderBySupOrderIds(new String[]{ss.getSupplierOrderId()});
//						if(productOrders!=null&&productOrders.size()>0){
//							int identity=payService.isShop(weiid, productOrders.get(0).getProductId());
//							if(!BitOperation.verIdentity(identity, UserIdentityType.Agent)){
//								canUseCoin+= ss.getTotalPrice()*0.1;
//								useCash=true;
//							}
//						}
//					}
//				}
//			}
//			if(useCash){
//				resultMap.put("useCash", 1);
//				if(coinTotal>=canUseCoin){
//					resultMap.put("cashAmount", canUseCoin);
//				}else {
//					resultMap.put("cashAmount", coinTotal);
//				}
//			}else {
//				resultMap.put("useCash",0);
//				resultMap.put("cashAmount", 0);
//			}
//			rq.setBasemodle(resultMap);
//		}else {
//			rq.setStatu(ReturnStatus.SystemError);
//			rq.setStatusreson("订单号有误");
//		}
		
		PayOrderInfoVO result=payService.getPayOrderInfo(orderId, weiid, true);
		if(result!=null){
			rq.setStatu(ReturnStatus.Success);
			rq.setBasemodle(result); 
		}else {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson("订单号有误");
		}  
		return rq;
	}
}
