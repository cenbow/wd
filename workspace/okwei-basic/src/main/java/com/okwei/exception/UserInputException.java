/**
 * 
 */
package com.okwei.exception;

/**
 * 
 * @ClassName: UserInputException
 * @Description: 自定义异常
 * @author xiehz
 * @date 2015年6月5日 下午4:09:23
 *
 */
public class UserInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7132495062317028028L;

	public UserInputException() {
	}

	public UserInputException(String message) {
		super(message);
	}

}
