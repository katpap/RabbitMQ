package consumer;

import org.junit.Test;

import com.rabbit.util.PhoneCountryCatalog;

import junit.framework.Assert;

public class PhoneConsumerTest {
	
	@Test
	public void test_getCountryByCallingCode_GR_success() {
		String country = PhoneCountryCatalog.getCountryByCallingCode("+306948483253");
		
		Assert.assertNotNull(country);
		Assert.assertEquals(country, "GR");
		
	}
	
	@Test
	public void test_getCountryByCallingCode_CH_success() {
		String country = PhoneCountryCatalog.getCountryByCallingCode("+41794432372");
		
		Assert.assertNotNull(country);
		Assert.assertEquals(country, "CH");
		
	}
	




}
