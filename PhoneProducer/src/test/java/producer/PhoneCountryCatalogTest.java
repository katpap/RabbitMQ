package producer;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.rabbit.util.PhoneCountryCatalog;

import junit.framework.Assert;

public class PhoneCountryCatalogTest {
		
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void test_generateCountryCode_success(){
		String countryCode = PhoneCountryCatalog.generateCountryCode();
		
		Assert.assertNotNull(countryCode);
		Assert.assertEquals(countryCode.length(), 2);
	}	
	
	//@Ignore
	@Test(expected = NullPointerException.class)
	public void test_generatePhoneNumber_UK_fail(){
		
		PhoneCountryCatalog.generatePhoneNumber("UK");
						
	}
	
	@Test
	public void test_generatePhoneNumber_GB_success(){
		String phone = PhoneCountryCatalog.generatePhoneNumber("GB");
		
		Assert.assertNotNull(phone);
		Assert.assertEquals(phone.substring(0, 3), "+44");
		
	}
	
	@Test
	public void test_generatePhoneNumber_GR_success(){
		String phone = PhoneCountryCatalog.generatePhoneNumber("GR");
		
		Assert.assertNotNull(phone);
		Assert.assertEquals(phone.substring(0, 3), "+30");
	}
	
	@Test
	public void test_generatePhoneNumber_RW_success(){
		String phone = PhoneCountryCatalog.generatePhoneNumber("RW");
		
		Assert.assertNotNull(phone);
		Assert.assertTrue(phone.startsWith("+250"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_validate_phone_fail() {
					
		PhoneCountryCatalog.isValidPhoneNumber("1234567890");		
	}
	
	@Test
	public void test_validate_phone_success() {
				
		Assert.assertTrue(PhoneCountryCatalog.isValidPhoneNumber("+306948483253"));
	
	}	

}
