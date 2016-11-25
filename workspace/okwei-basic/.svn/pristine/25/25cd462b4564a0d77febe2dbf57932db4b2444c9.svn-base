package com.okwei.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * @author tan
 * 
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private String[] encryptPropNames = { "usermasterjdbc.username", "usermasterjdbc.password", "userslavejdbc.username", "userslavejdbc.password",
			"productmastejdbc.username", "productmastejdbc.password", "productslavejdbc.username", "productslavejdbc.password", "ordermasterjdbc.username",
			"ordermasterjdbc.password", "orderslavejdbc.username", "orderslavejdbc.password","jdbc.username", "jdbc.password","readjdbc.username", "readjdbc.password" };

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			String decryptValue = "";
			try {
				decryptValue = DES.decrypt(propertyValue, "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	/**
	 *
	 * 
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptProp(String propertyName) {
		for (String encryptpropertyName : encryptPropNames) {
			if (encryptpropertyName.equals(propertyName))
				return true;
		}
		return false;
	}

}
