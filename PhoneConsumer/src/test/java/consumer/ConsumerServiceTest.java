package consumer;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import com.rabbit.model.PhoneMessage;
import com.rabbit.service.ConsumerService;
import junit.framework.Assert;

public class ConsumerServiceTest {
		
	@Test
	public void test_receive_increases_counter_success() {

		ConsumerService service = new ConsumerService();
		
		PhoneMessage msgfixture = new PhoneMessage();
		msgfixture.setTelephoneNumber("+41794432372");
				
		service.receive(msgfixture);
		
		AtomicInteger counter = service.getCounter();
		Assert.assertEquals(counter.get(), 1);
		
		for(int x=0; x<10; x++) {
			service.receive(msgfixture);
		}
		
		counter = service.getCounter();
		Assert.assertEquals(counter.get(), 11);
		
	}
	
	@Test
	public void test_phones_are_grouped_success() {
		ConsumerService service = new ConsumerService();
		
		PhoneMessage msgfixture = new PhoneMessage();
		msgfixture.setTelephoneNumber("+41794432372");
		
		
		service.groupMessage(msgfixture);
		Assert.assertTrue(service.getPhoneMessageReport().containsKey("CH"));
		Assert.assertTrue(service.getPhoneMessageReport().get("CH").contains(msgfixture.getTelephoneNumber()));
		Assert.assertEquals(service.getPhoneMessageReport().get("CH").size(), 1);
		
		
		PhoneMessage msgfixture2 = new PhoneMessage();
		msgfixture2.setTelephoneNumber("+41794432342");
		
		service.groupMessage(msgfixture2);
		Assert.assertTrue(service.getPhoneMessageReport().containsKey("CH"));
		Assert.assertTrue(service.getPhoneMessageReport().get("CH").contains(msgfixture2.getTelephoneNumber()));
		Assert.assertEquals(service.getPhoneMessageReport().get("CH").size(), 2);
		
		
		PhoneMessage msgfixture3 = new PhoneMessage();
		msgfixture3.setTelephoneNumber("+306948483257");
		
		service.groupMessage(msgfixture3);
		Assert.assertTrue(service.getPhoneMessageReport().containsKey("GR"));
		Assert.assertTrue(service.getPhoneMessageReport().get("GR").contains(msgfixture3.getTelephoneNumber()));
		Assert.assertEquals(service.getPhoneMessageReport().get("GR").size(), 1);
		
	}

}
