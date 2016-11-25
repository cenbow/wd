package com.okwei.appinterface.service.impl.shoppingcart;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.service.shopingcart.IShoppingCartMgtService;
import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActivity;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.enums.ActProductVerState;
import com.okwei.bean.vo.activity.ActivityModel;
import com.okwei.bean.vo.shoppingcart.ShoppingCar;
import com.okwei.bean.vo.shoppingcart.ShoppingCarList;
import com.okwei.bean.vo.shoppingcart.Style;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.DateUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class ShoppingCartMgtService extends BaseService implements IShoppingCartMgtService {

	@Autowired
	private IBaseActivityService actService;
	
	@Override
	public String getShoppingCarListToJson(List<ShoppingCar> list) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject(); 
		//Statu
		json.put("Statu",1);
		//StatusReson
		json.put("statusReson","成功");
		//BaseModel
		JSONArray baseModleList = new JSONArray();
		for(ShoppingCar item : list)
		{   
			//json 对象
			JSONObject shoppingCar = new JSONObject();
			shoppingCar.put("supplierWeiId", item.getSupplierWeiId());
			shoppingCar.put("companyName",item.getCompanyName());
			shoppingCar.put("buyType",item.getBuyType());
			shoppingCar.put("demandId",item.getDemandId());
			shoppingCar.put("firstOrderAmount",item.getFirstOrderAmount());
			shoppingCar.put("isFirstOrder", item.getIsFirstOrder());
			shoppingCar.put("source",item.getSource());
			//js对象ShoppingCarListJson  
			JSONArray shopCarListArrayJson = new JSONArray();
			for(ShoppingCarList shoppingCarList : item.getShoppingCarList())
			{   
				//shoppingCarList 对象json
				JSONObject shoppingCarListJson = new JSONObject();
				shoppingCarListJson.put("proNum",shoppingCarList.getProNum());
				
				/*-------------限时抢购的产品----------------------*/
				AActProductsShowTime act=actService.getAActProductsShowTime(shoppingCarList.getProNum(), true);
				boolean isGoing=false;
				ActivityModel model=null;
			    if(act!=null){
			     	AActivityProducts actProducts=super.getById(AActivityProducts.class, act.getProActId());	
			     	if(actProducts!=null&&actProducts.getState()==Short.parseShort(ActProductVerState.Ok.toString())){
			     		AActivity actModel=super.getById(AActivity.class, actProducts.getActId());
			     		if(actModel!=null){ 
			     			PProducts product=super.getById(PProducts.class, actProducts.getProductId());
			     			model=new ActivityModel();
			     			if(product!=null && product.getPublishType()!=null&&product.getPublishType()>0){
								model.setActId(0); 
							}else {
								model.setActId(actProducts.getActId().intValue());
							}
			         		
			         		model.setTitle(actModel.getTitle());
			         		model.setBeginTime(DateUtils.format(act.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
			         		model.setEndTime(DateUtils.format(act.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
			         		model.setServerTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") );
			         		model.setActPrice(actProducts.getPrice());
			         		model.setState(1);
			         		model.setStateText("抢购中");
			         		model.setBuyNumLimit(5);
			         		isGoing=true;
			         		
			     		}
			     	}
			     }
				/*----------------------------------------*/
				//Style 对象listjson
				JSONArray styleListArrayJson = new JSONArray();
				for(Style style : shoppingCarList.getStyle())
				{    
					//style 对象json
					JSONObject styleJson = new JSONObject();
					styleJson.put("styleId",style.getStyleId()); 
					styleJson.put("count",style.getCount());
					styleJson.put("price",style.getPrice());
					styleJson.put("property",style.getProperty());
					styleJson.put("image",style.getImage());
					styleJson.put("proTitle",style.getProTitle());
					styleJson.put("status",style.getStatus());
					styleJson.put("scid",style.getScid());
					styleJson.put("tradeWeiId",style.getTradeWeiId());
					styleJson.put("source",style.getSource());
					styleJson.put("displayPrice", style.getDisplayPrice());
					styleJson.put("shareOne", style.getShareOne());
					styleJson.put("sharePageProducer", style.getSharePageProducer());
					styleJson.put("sharePageId", style.getSharePageId());
					if(isGoing){
						styleJson.put("activityModel",model);
					}
					styleListArrayJson.add(styleJson);
				}
				//styleListjson
				shoppingCarListJson.put("style",styleListArrayJson);
				//"wholesalePriceList
				shoppingCarListJson.put("wholesalePrice", shoppingCarList.getWholesalePriceList() != null ? shoppingCarList.getWholesalePriceList() : "null");
				shopCarListArrayJson.add(shoppingCarListJson);
			}
			shoppingCar.put("shoppingCarList",shopCarListArrayJson);
			baseModleList.add(shoppingCar);
		}
		json.put("BaseModle",baseModleList);
		return json.toString();
	}


}
