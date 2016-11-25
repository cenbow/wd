package com.okwei.appinterface.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.dao.IKeywordsDao;
import com.okwei.appinterface.service.IKeywordsService;
import com.okwei.appinterface.util.KeywordTool;
import com.okwei.bean.domain.BFilterWords;
import com.okwei.util.RedisUtil;

@Service("keywordsService")
public class KeywordsService implements IKeywordsService {

	@Autowired
	private IKeywordsDao  keyDao;
	@Override
	public String addKeyWords(String keywords) {
		
		String strKeywords=RedisUtil.getString("filterkeywordset");
		if(strKeywords!=null)
		{
			strKeywords=strKeywords+","+keywords;
			RedisUtil.setString("filterkeywordset", strKeywords);			
			//回写数据库
			BFilterWords fw=keyDao.getFilterWords();
			if(fw==null)
			{
				fw= new BFilterWords();
				fw.setFilterWords(strKeywords);
				fw.setFlag((short) 1);
				keyDao.saveFilterWords(fw);
			}
			else
			{
				fw.setFilterWords(strKeywords);
				keyDao.saveFilterWords(fw);
			}
			
			
			
			
		}
		else
		{
			
			BFilterWords fw=keyDao.getFilterWords();
			if(fw==null)
			{
				strKeywords=keywords;
				RedisUtil.setString("filterkeywordset", strKeywords);
				fw= new BFilterWords();
				fw.setFilterWords(strKeywords);
				fw.setFlag((short) 1);
				keyDao.saveFilterWords(fw);
				
			}
			else
			{
				strKeywords=fw.getFilterWords();
				strKeywords=strKeywords+","+keywords;
				RedisUtil.setString("filterkeywordset", strKeywords);			
				//回写数据库
				fw.setFilterWords(strKeywords);
				keyDao.saveFilterWords(fw);
				
				
			}
			
			
		}
		KeywordTool kw=KeywordTool.getInstance();
		kw.resetKeywords();
		return "1";
	}

	@Override
	public String checkKeyWords(String keywords) {
		//将之前评论中含有连续数字和英文字母的评论删除（连续两个数字或英文字母）
		if(RegexName(keywords))
			return "1";
		KeywordTool kw=KeywordTool.getInstance();
		String strKeywords=RedisUtil.getString("filterkeywordset");
		if(strKeywords==null)
		{
			BFilterWords fw=keyDao.getFilterWords();
			if(fw!=null)
			{				
				strKeywords=fw.getFilterWords();
				RedisUtil.setString("filterkeywordset", strKeywords);	
				kw.resetKeywords();
			}
		}
		
		boolean b=kw.isContentKeyWords(keywords);
		if(b)
			return "1";
		else
			return "0";
	}
	
	public  boolean RegexName(String str)
	 {
		String regex ="[\\s\\S]*[0-9]{2,}[\\s\\S]*|[\\s\\S]*[a-zA-Z]{2,}[\\s\\S]*";
		return str.matches(regex);
	
	 }

}
