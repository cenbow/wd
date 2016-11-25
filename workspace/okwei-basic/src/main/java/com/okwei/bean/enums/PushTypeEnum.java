package com.okwei.bean.enums;

/**
 * 推送类型
 */
public enum PushTypeEnum
{
	/**
	 * 上架产品
	 * */
	ShelveProduct {
		public short getNo() {
			return 0;
		}
	},
	/**
	 * 发布产品
	 * */
	NewProduct {
		public short getNo() {
			return 1;
		}
	},
	/**
	 * 删除多余的产品分类
	 * */
	DelProductClassTemp {
		public short getNo() {
			return 2;
		}
	};

	public abstract short getNo();
}
