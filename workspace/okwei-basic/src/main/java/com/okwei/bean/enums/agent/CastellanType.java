package com.okwei.bean.enums.agent;

public enum CastellanType {
	 /**
     * 城主
     */
	castellan(1),
	/**
     * 副城主
     */
    viceCastellan(2);
    private final int Type;

    private CastellanType(int step)
    {
        this.Type = step;
    }

    public String toString()
    {
        return String.valueOf(this.Type);
    }	
}
