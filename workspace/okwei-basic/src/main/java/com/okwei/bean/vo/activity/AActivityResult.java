package com.okwei.bean.vo.activity;

import java.io.Serializable;

import com.okwei.bean.domain.AActivity;

public class AActivityResult  extends AActivity  implements Serializable{
	private static final long serialVersionUID = -1999441290201606195L;
	
	 
	/**
	 * 是否已经报名
	 */
	private int isApplied;
	/**
	 * 活动是否开始
	 */
	private int stepState;
	
	public int getIsApplied() {
		return isApplied;
	}
	public void setIsApplied(int isApplied) {
		this.isApplied = isApplied;
	}
	public int getStepState() {
		return stepState;
	}
	public void setStepState(int stepState) {
		this.stepState = stepState;
	} 
	
	
	 
}
