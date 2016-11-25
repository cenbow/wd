package com.okwei.supplyportal.bean.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PProductClass;

public class CatagoryVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private Integer level3Id;
	private Integer level2Id;
	private Integer level1Id;
	private Integer templateId;

	private List<PProductClass> level1;
	private List<PProductClass> level2;
	private List<PProductClass> level3;
	private List<PParamModel> templates;

	private List<Map<String, String>> paths;

	public Integer getLevel3Id() {
		return level3Id;
	}

	public void setLevel3Id(Integer level3Id) {
		this.level3Id = level3Id;
	}

	public Integer getLevel2Id() {
		return level2Id;
	}

	public void setLevel2Id(Integer level2Id) {
		this.level2Id = level2Id;
	}

	public Integer getLevel1Id() {
		return level1Id;
	}

	public void setLevel1Id(Integer level1Id) {
		this.level1Id = level1Id;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public List<PProductClass> getLevel1() {
		return level1;
	}

	public void setLevel1(List<PProductClass> level1) {
		this.level1 = level1;
	}

	public List<PProductClass> getLevel2() {
		return level2;
	}

	public void setLevel2(List<PProductClass> level2) {
		this.level2 = level2;
	}

	public List<PProductClass> getLevel3() {
		return level3;
	}

	public void setLevel3(List<PProductClass> level3) {
		this.level3 = level3;
	}

	public List<PParamModel> getTemplates() {
		return templates;
	}

	public void setTemplates(List<PParamModel> templates) {
		this.templates = templates;
	}

	public List<Map<String, String>> getPaths() {
		return paths;
	}

	public void setPaths(List<Map<String, String>> paths) {
		this.paths = paths;
	}

}
