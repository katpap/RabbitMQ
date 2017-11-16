package com.rabbit.util;

import java.util.List;
import java.util.stream.Collectors;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneCountryCatalog {
	
	static List<String> supportedRegions;
	static PhoneNumberUtil phoneUtil;
	static{
		phoneUtil = PhoneNumberUtil.getInstance();
		supportedRegions = phoneUtil.getSupportedRegions().stream().collect(Collectors.toList());
	}
	
	
	public static String getCountryByCallingCode(String number){	
		
		try{
			PhoneNumber phoneNumber = phoneUtil.parse(number, "");
			return phoneUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";
		
	}
	

}
