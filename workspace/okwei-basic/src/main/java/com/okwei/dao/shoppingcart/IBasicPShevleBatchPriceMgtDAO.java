package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.PShevleBatchPrice;

public interface IBasicPShevleBatchPriceMgtDAO {
	/**
	 * 
	 * @param id
	 * @return
	 */
	 List<PShevleBatchPrice> getPShevleBatchPriceById(long id);
	 /**
	  * 上架批发价格表list
	  * @param idList
	  * @return
	  */
	 List<PShevleBatchPrice> getPShevleBatchPrice(List<Long> idList);
}
