package com.okwei.bean.enums;

public enum ShelveType {
	/**
	 * 从别人哪里上架
	 * */
	Other {
		public short getNo() {
			return 0;
		}
	},
	/**
	 * 从自己哪里上架
	 * */
	Self {
		public short getNo() {
			return 1;
		}
	},

	/**
	 * 普通微店发布的产品上架，不能销售
	 * 
	 * @return
	 */
	NoSale {
		public short getNo() {
			return 2;
		}
	},
	/**
	 * 审核通过没有交钱的布的产品上架，不能销售
	 * 
	 * @return
	 */
	NoPay {
		public short getNo() {
			return 3;
		}
	},

	// 代理
	Proxy {
		public short getNo() {
			return 4;
		}
	},
	// 落地分销
	floor {
		public short getNo() {
			return 5;
		}
	};

	public abstract short getNo();

	// 根据value获取enum
	public static ShelveType setValue(short value) {
		ShelveType obj = null;
		for (ShelveType status : ShelveType.values()) {
			if (status.getNo() == value) {
				obj = status;
				break;
			}
		}
		return obj;
	}
}
