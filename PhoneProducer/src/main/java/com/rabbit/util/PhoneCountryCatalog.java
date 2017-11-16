package com.rabbit.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneCountryCatalog {
	
	static List<String> supportedRegions;
	static PhoneNumberUtil phoneUtil;
	static{
		phoneUtil = PhoneNumberUtil.getInstance();
		supportedRegions = phoneUtil.getSupportedRegions().stream().collect(Collectors.toList());
	}
	
	public static String generateCountryCode() {
		
        int index = ThreadLocalRandom.current().nextInt(0, supportedRegions.size()-1);
	    return supportedRegions.get(index);
	}
	
	public static String generatePhoneNumber(String regionCode){	
		PhoneNumber number = phoneUtil.getExampleNumber(regionCode);
		return phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
	}
	
	//method used only if input is provided by Rest API (PhoneController)
	public static boolean isValidPhoneNumber(String phone) {	
		PhoneNumber phoneNumner;
		try {
			phoneNumner = phoneUtil.parse(phone, "");
		}catch(NumberParseException e) {	
			throw new IllegalArgumentException();
		}
		
		if(!phoneUtil.isValidNumber(phoneNumner)) {
			throw new IllegalArgumentException();
		}
		
		return true;
	
	}
	

}
