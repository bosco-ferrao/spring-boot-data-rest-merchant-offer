package demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import demo.model.MerchantGood;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@RunWith(SpringRunner.class)
@DatabaseSetup("classpath:MerchantGood.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:MerchantGood.xml")
@DirtiesContext
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MerchantGoodRepositoryTest {

	@Autowired
	MerchantGoodRepository merchantGoodRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testLoadMerchantGoodsFromDB() {
		List<MerchantGood> merchantGoods = (ArrayList<MerchantGood>) merchantGoodRepository
				.findAll();
		assertEquals("Did not get all goods", 3, merchantGoods.size());
	}

	@Test
	public void testInsertMerchantGoodForOfferAndCheck() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(
				"{\"name\":\"Tooth brush2\",\"price\":1.2}", headers);
		ResponseEntity<MerchantGood> responseEntity = restTemplate
				.postForEntity("/merchantGoods", entity, MerchantGood.class);
		MerchantGood merchantGood = responseEntity.getBody();

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("Tooth brush2", merchantGood.getName());
	}

}
