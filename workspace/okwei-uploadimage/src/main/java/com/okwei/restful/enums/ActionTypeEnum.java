package com.okwei.restful.enums;

public enum ActionTypeEnum {

	/**
	 * 什么都不做 0
	 */
	Nothing(0),
	/**
	 * 保存到临时文件 1
	 */
	UploadTempImg(1), 
	/**
	 *  删除文件 2
	 */
	DeleteTempFile(2),
	/**
	 *  将临时文件保存到FastDFS并删除临时文件 3
	 */
	SaveToFastDFS(3),
	/**
	 * 上传小图 4
	 */
	UploadMiniImg(4),
	
	Others(5);

	private final int step;

	private ActionTypeEnum(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
}
