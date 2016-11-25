package com.okwei.dao.impl.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.IStaticsDAO;
@Repository
public class StaticsDAO extends BaseDAO implements IStaticsDAO {

	@Override
	public List<Object[]> getChildrenSupplierStatics(String childrenID) {
		// TODO 自动生成的方法存根
		StringBuilder sp=new StringBuilder();
		sp.append("SELECT SUM(A.Count*IFNULL(B.DefaultPrice,0)),COUNT(A.ProductID) from (");
        sp.append("SELECT ProductID,Count FROM O_ProductOrder where SupplierOrderID in (");
        sp.append("SELECT SupplierOrderID FROM O_SupplyerOrder where SubNo=? AND State=4)) AS A "); 
        sp.append("LEFT JOIN P_ProductRelation AS B ON A.ProductID=B.ProductID");
		return super.queryBySql(sp.toString(), childrenID);
	}

	@Override
	public long getSubSupplierOrderCount(String childrenID, Short state) {
		// TODO 自动生成的方法存根
		String sql ="SELECT count(*) FROM O_SupplyerOrder where SubNo=? AND State=?";
		return super.countBySql(sql, childrenID,state);
	}

	@Override
	public long getSubSupplierProductCount(String childrenID, Short state) {
		// TODO 自动生成的方法存根
		String sql ="SELECT count(*) FROM P_ProductSup where ChildrenID=? AND State=?";
		return super.countBySql(sql, childrenID,state);
	}
}
