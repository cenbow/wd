package com.okwei.supplyportal.dao.impl;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.ModelType;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.supplyportal.dao.IProductManageDao;

@Repository
public class ProductManageDAO extends BaseDAO implements IProductManageDao {


	@Override
	public UBatchSupplyer getSupplyerMsg(Long weiid) {
		// TODO 自动生成的方法存根
		return get(UBatchSupplyer.class,weiid);
	}

	@Override
	public Long getOrderCountByState(Long weiid, Short state) {
		// TODO 自动生成的方法存根
		String hql = "select count(*) from OSupplyerOrder p where p.supplyerId=? and p.state=?";
		Object[] b=new Object[2];
		b[0]=weiid;
		b[1]=state;
		return count(hql, b);
	}

	@Override
	public Long getProductCountyByState(Long weiid, Short state,String content) {
		// TODO 自动生成的方法存根
		String like="";
		if(content!=null && !"".equals(content))
			like = " and ProductTitle like '%"+content+"%'";
		String hql = "select count(*) from PProducts p where p.supplierWeiId=? and p.state=?"+like;
		Object[] b=new Object[2];
		b[0]=weiid;
		b[1]=state;
		return count(hql, b);
	}

	@Override
	public UYunSupplier getYunSupplyerMsg(Long weiid) {
		// TODO 自动生成的方法存根
		return get(UYunSupplier.class,weiid);
	}

	@Override
	public USupplyer getBaseSupplyerMsg(Long weiid) {
		// TODO 自动生成的方法存根
		return get(USupplyer.class,weiid);
	}

	@Override
	public UVerifier getBaseVerifierMsg(Long weiid) {
		// TODO 自动生成的方法存根
		return get(UVerifier.class,weiid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getProductsBySupID(Long supID, Short state,Integer pagesize,Integer pageindex,String content) {
		// TODO 自动生成的方法存根
		Integer begin = (pageindex - 1) * pagesize;
		//第一页要多查找一个数据，中间页要多次两个数据
		pagesize ++;
		if(begin!=0)
		{
			begin--;
			pagesize++;
		}
		
		String like="";
		if(content!=null && !content.equals(""))
			like=" and ProductTitle like '%"+content+"%'";
		
		String sql = "SELECT A.ProductID,A.DefaultPrice,A.DefaultImg,A.ProductTitle,A.Sort,A.UpdateTime,B.ShelvesCount,B.EvaluateCount from"
				     + " (SELECT ProductID,DefaultPrice,DefaultImg,ProductTitle,Sort,UpdateTime from P_Products "
				     + " where SupplierWeiID="+supID.toString()+" and State="+state.toString() + like
				     + " ORDER BY Sort desc,UpdateTime desc "
				     + " LIMIT "+ begin.toString() + "," + pagesize.toString()+") AS A"
				     + " LEFT JOIN P_ProductAssist as B ON A.ProductID=B.ProductID"
				     + " ORDER BY A.Sort desc,A.UpdateTime desc";
		return queryBySql(sql);
	}
    /**
     * 取消推荐
     */
	@Override
	public void updateProductSort(Long productid, Short sort,Long supweiid) {
		// TODO 自动生成的方法存根

        PProducts p = get(PProducts.class,productid);
        String hql = " from PClassProducts p where p.productId=? and p.weiId=?";
        Object[] b=new Object[2];
        b[0]=productid;
        b[1]=supweiid;
        List<PClassProducts> pcs = find(hql,b);
        PClassProducts pc = null;
        if(pcs!=null && pcs.size()>0)
        	pc=pcs.get(0);
        
        if(p!=null)
        {
        	p.setSort((short) 0);
        	p.setUpdateTime(new Date());
        	update(p);
        }
        
        if(pc!=null)
        {
        	pc.setSort((short) 0);
        	pc.setCreateTime(new Date());
        	update(pc);
        }
	}
    /**
     * 推荐产品
     */
	@Override
	public void updateProductSort(Long productid,Long supweiid) {
		// TODO 自动生成的方法存根
		PProducts p = get(PProducts.class,productid);
		String hql = " from PClassProducts p where p.productId=? and p.weiId=?";
        Object[] b=new Object[2];
        b[0]=productid;
        b[1]=supweiid;
        List<PClassProducts> pcs = find(hql,b);
        PClassProducts pc = null;
        if(pcs!=null && pcs.size()>0)
        	pc=pcs.get(0);
        
        if(p!=null)
        {
        	String sql = "SELECT IFNULL(MAX(Sort),0) from P_Products where SupplierWeiID="+supweiid.toString();
        	int num =Integer.parseInt(super.getUniqueSQLResult(sql).toString());
        	num++;
        	p.setSort((short) num);
        	p.setUpdateTime(new Date());
        	update(p);
        }
        
        if(pc!=null)
        {
        	String sql ="SELECT IFNULL(MAX(Sort),0) from P_ClassProducts where WeiID="+supweiid.toString();
        	int num = Integer.parseInt(super.getUniqueSQLResult(sql).toString());
        	num++;
        	pc.setSort((short) num);
        	pc.setCreateTime(new Date());
        	update(pc);
        }
	}
    /**
     * updown=0 下移；=1 上移
     */
	@Override
	public int moveposition(Long productid, Short updown,Long supweiid) {
		// TODO 自动生成的方法存根
		PProducts p = get(PProducts.class,productid);
		String hql_1 = " from PClassProducts p where p.productId=? and p.weiId=?";
        Object[] b_1=new Object[2];
        b_1[0]=productid;
        b_1[1]=supweiid;
        List<PClassProducts> pcs = find(hql_1,b_1);
        PClassProducts pc = null;
        if(pcs!=null && pcs.size()>0)
        	pc=pcs.get(0);
        
        if(p!=null)
        {
        	Short sortp = p.getSort();
        	String sql = "";
        	if(updown==0){
        		sql ="SELECT IFNULL(MAX(Sort),0) from P_Products where Sort<"+sortp.toString() + " and SupplierWeiID="+supweiid.toString();
        	}else if(updown==1){
        		sql ="SELECT IFNULL(MIN(Sort),0) from P_Products where Sort>"+sortp.toString() + " and SupplierWeiID="+supweiid.toString();
        	}else{
        		return 0;
        	}
        	
        	Integer num = Integer.parseInt(super.getUniqueSQLResult(sql).toString());
        	if(num!=null)
        	{
        		String hql = " from PProducts p where p.sort=? and p.supplierWeiId=? order by updateTime desc";
            	Object[] b=new Object[2];
            	b[0]=Short.parseShort(num.toString());
            	b[1]=supweiid;
            	List<PProducts> products = find(hql,b);
            	if(products!=null && products.size()>0)
            	{
            		PProducts tempp = products.get(0);
            		p.setUpdateTime(new Date());
            		p.setSort(tempp.getSort());
            		tempp.setSort(sortp);
            		tempp.setUpdateTime(new Date());
            		
            		update(p);
            		update(tempp);
            		
            		return 1;
            	}
            	else
            	{
            		return 0;
            	}
        	}
        	else
        	{
        		return 0;
        	}
        }
        
        if(pc!=null)
        {
        	Short sortpc = pc.getSort();
        	String sql = "";
        	if(updown==0){
        		sql ="SELECT IFNULL(MAX(Sort),0) from P_ClassProducts where Sort<"+sortpc.toString() + " and WeiID="+supweiid.toString();
        	}else if(updown==1){
        		sql ="SELECT IFNULL(MIN(Sort),0) from P_ClassProducts where Sort>"+sortpc.toString() + " and WeiID="+supweiid.toString();
        	}else{
        		return 0;
        	}
        	
        	Short num = Short.parseShort(super.getUniqueSQLResult(sql).toString());
        	if(num!=null)
        	{
        		String hql = " from PClassProducts p where p.sort=? and p.weiId=? order by createTime desc";
            	Object[] b=new Object[2];
            	b[0]=Short.parseShort(num.toString());
            	b[1]=supweiid;
            	List<PClassProducts> products = find(hql,b);
            	if(products!=null && products.size()>0)
            	{
            		PClassProducts tempp = products.get(0);
            		pc.setCreateTime(new Date());
            		pc.setSort(tempp.getSort());
            		tempp.setSort(sortpc);
            		tempp.setCreateTime(new Date());
            		
            		update(pc);
            		update(tempp);
            		
            		return 1;
            	}
            	else
            	{
            		return 0;
            	}
        	}
        	else
        	{
        		return 0;
        	}
        }
        
        return 1;
        
	}
    /**
     * 批量上架
     */
	@Override
	public void batchontop(String[] products,Long supweiid) {
		// TODO 自动生成的方法存根
		String sql_p = "SELECT IFNULL(MAX(Sort),0) from P_Products where SupplierWeiID="+supweiid.toString();
    	Integer num_p = Integer.parseInt(super.getUniqueSQLResult(sql_p).toString());
    	
    	String sql_c ="SELECT IFNULL(MAX(Sort),0) from P_ClassProducts where WeiID="+supweiid.toString();
    	Short num_c = Short.parseShort(super.getUniqueSQLResult(sql_c).toString());
		if(products!=null && products.length>0)
		{
			for(int i=products.length-1;i>=0;i--)
			{
				num_p++;
				num_c++;
				String sql_0 = "update P_Products set Sort="+num_p.toString()+",UpdateTime=now() where Sort=0 and ProductID="+products[i].toString();
				String sql_1 = "update P_ClassProducts set Sort="+num_c.toString()+",CreateTime=now() where Sort=0 and ProductID="+products[i].toString()+" and WeiID="+supweiid.toString();
				updateBySql(sql_0);
				updateBySql(sql_1);
			}
		}
	}
    /**
     * 批量下架
     */
	@Override
	public void batchoffshow(String[] products, Long supweiid) {
		// TODO 自动生成的方法存根
		if(products!=null && products.length>0)
		{
			for(String productid:products)
			{
				String sql_0 = "update P_Products set State=4,UpdateTime=now() where ProductID="+productid.toString();
				String sql_1 = "update P_ClassProducts set State=0,CreateTime=now() where ProductID="+productid.toString();
				updateBySql(sql_0);
				updateBySql(sql_1);
			}
		}
	}
    /**
     * 批量操作
     * optype=1 上架； optype=0 删除
     */
	@Override
	public void batchoperate(String[] products, Long supweiid, Short optype) {
		// TODO 自动生成的方法存根
		if(products!=null && products.length>0)
		{
			if(optype == 1)
			{
				for(String productid:products)
				{
					String sql_0 = "update P_Products set State=1,Sort=0,UpdateTime=now() where ProductID="+productid.toString();
					String sql_1 = "update P_ClassProducts set State=1,Sort=0,CreateTime=now() where ProductID="+productid.toString()+" and WeiID="+supweiid.toString();
					updateBySql(sql_0);
					updateBySql(sql_1);
				}
			}
			else if(optype == -1)
			{
				for(String productid:products)
				{
					String sql_0 = "update P_Products set State=5,UpdateTime=now() where ProductID="+productid.toString();
					String sql_1 = "update P_ClassProducts set State=0 where ProductID="+productid.toString();
					updateBySql(sql_0);
					updateBySql(sql_1);
				}
			}
		}
	}
    /**
     * 订单类型个数集合
     */
	@Override
	public List<Object[]> getOrderCount(Long weiid) {
		// TODO 自动生成的方法存根
		String sql = "select count(State),State from O_SupplyerOrder " 
					+" where supplyerId="+weiid.toString()
					+" GROUP BY State";

		return queryBySql(sql);
	}
    /**
     * 产品类型的个数集合
     */
	@Override
	public List<Object[]> getProductsCount(Long weiid) {
		// TODO 自动生成的方法存根
		String sql = "SELECT Count(State),State from P_Products "
					+" where SupplierWeiID="+weiid.toString()
					+" group by State";

		return queryBySql(sql);
	}
    /**
     * 获取排序的最小值
     */
	@Override
	public int getminSort(Long supweiid) {
		// TODO 自动生成的方法存根
		String sql_p = "SELECT IFNULL(MIN(Sort),0) from P_Products where Sort>0 and State=1 and SupplierWeiID="+supweiid.toString();
    	return Integer.parseInt(super.getUniqueSQLResult(sql_p).toString());
	}
    /**
     * 获取排序的最大值
     */
	@Override
	public int getmaxSort(Long supweiid) {
		// TODO 自动生成的方法存根
		String sql_p = "SELECT IFNULL(MAX(Sort),0) from P_Products where Sort>0 and State=1 and SupplierWeiID="+supweiid.toString();
    	return Integer.parseInt(super.getUniqueSQLResult(sql_p).toString());
	}

}
