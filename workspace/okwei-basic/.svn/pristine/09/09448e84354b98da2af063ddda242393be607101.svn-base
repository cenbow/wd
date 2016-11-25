package com.okwei.util;

import java.util.ArrayList;
import java.util.List;

import com.okwei.bean.vo.KeyValue;

public class SellAttrMapUtil {

	
	public static List<List<KeyValue>> Tp(List<List<KeyValue>> L)
	{
		List<KeyValue> current;
		List<List<KeyValue>> aim= new ArrayList<List<KeyValue>>();
		if(L.size()>1)
		{
			current=L.get(0);
			L.remove(0);
			List<List<KeyValue>> Tempaim=Tp(L);
			for(List<KeyValue> lkv : Tempaim)
			{
				 for (KeyValue kv : current)
				 {
					 List<KeyValue> kvList= new ArrayList<KeyValue>();
					 kvList.addAll(lkv);
					 kvList.add(kv);
					 aim.add(kvList);
					  
				 }
				
			}
		}
		else
		{
			current=L.get(0);
			for(KeyValue kvs :current){
				List<KeyValue> kvList= new ArrayList<KeyValue>();
				kvList.add(kvs);
				aim.add(kvList);
			}
			
		}
		return aim;
	}
}
