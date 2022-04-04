package maintest;

import com.ds.Application;
import com.ds.webservice.impl.HelloServiceImpl;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Endpoint;


@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes={Application.class})//启动整个springboot工程
public class WebServiceTestDemo {

	
	
	@Test
	public void testOne(){
		//发布webservice
		String wsAddress = "http://localhost:6868/01-ws-java-server/ws";
		Endpoint endpoint = Endpoint.publish(wsAddress, new HelloServiceImpl());
		System.out.println("webservice发布成功：" + endpoint.isPublished());
	}

	@Before
	public void testBefore(){
		System.out.println("before");
	}

	@After
	public void testAfter(){
		System.out.println("after");
	}


}
