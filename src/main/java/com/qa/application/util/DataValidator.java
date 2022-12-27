package com.qa.application.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class DataValidator {
	public boolean validateString(String data) {
		if (data == null){
			return false;
		}
		if(data.isEmpty()){
			return false;
		}
		return true;
	}
	public boolean validateImage(String data) {
		boolean result = validateString(data);
		try {
			new URL(data).toURI();
		} catch (MalformedURLException | URISyntaxException e) {
			result = false;
		}
		return result;
	}
	public boolean validateVoucher(String data) {
		return (validateString(data) && isAlphaNumeric(data));
	}
	public static boolean isAlphaNumeric(String data) {
		return data.matches("^(?=.*[a-z])(?=.*[0-9])[a-z0-9]+$");
	}
}
