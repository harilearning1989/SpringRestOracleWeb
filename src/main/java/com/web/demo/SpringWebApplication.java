package com.web.demo;

import com.web.demo.entities.ClientAudit;
import com.web.demo.entities.LaserSearch;
import com.web.demo.entities.Person;
import com.web.demo.entities.ServiceAudit;
import com.web.demo.reposDev.LaserSearchRepository;
import com.web.demo.utils.IDemoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.transaction.Transactional;
import java.net.InetAddress;
import java.util.*;

@SpringBootApplication
@EnableJpaAuditing
public class SpringWebApplication implements CommandLineRunner {

	//http://localhost:8081/swagger-ui.html
	//localhost:8082/SpringRestOracleDemo/swagger-ui.html#/  tomcat deployment
	//http://192.168.1.200:8082/SpringRestOracleDemo/swagger-ui.html
	//Redis   https://github.com/microsoftarchive/redis/releases
    //https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringWebApplication.class);
	@Autowired
	private LaserSearchRepository laserSearchRepository;

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner===run==");
		intSummaryStatistics();
		//printEnvironmentDetails();
		//savePersonData();
		//saveLaserData();
	}

	private void intSummaryStatistics() {
		List<Integer> primes = Arrays.asList(1, 2, 3, 4, 5, 33, 22, 32, 43, 78, 3, 2, 22);
		IntSummaryStatistics stats = primes.stream().mapToInt(n -> n).summaryStatistics();
		System.out.println("Max value===" + stats.getMax() + "===Min Value:=" + stats.getMin()
				+ "===Avg Value:==" + stats.getAverage() + "===Count:=" + stats.getCount() + "==Sum:==" + stats.getSum());

		Properties properties = IDemoUtils.fetchProperties();
		System.out.println(properties.get("name"));
	}

	private void printEnvironmentDetails() throws Exception {
		LOGGER.info("=====================Logging Start====================================");
		LOGGER.info("JAVA_HOME==", env.getProperty("JAVA_HOME"));
		LOGGER.info("app.name==", env.getProperty("app.name"));
		LOGGER.info("local.server.port==:", env.getProperty("local.server.port"));

		LOGGER.info("getHostAddress==" + InetAddress.getLocalHost().getHostAddress());
		LOGGER.info("getHostName==" + InetAddress.getLocalHost().getHostName());
		LOGGER.info("======================Logging End======================================");
		System.out.println("=====================SOUT Starting==============================");

		System.out.println("JAVA_HOME==" + env.getProperty("JAVA_HOME"));
		System.out.println("app.name==" + env.getProperty("app.name"));
		System.out.println("local.server.port==:" + env.getProperty("local.server.port"));

		LOGGER.info("getHostAddress==" + InetAddress.getLocalHost().getHostAddress());
		LOGGER.info("getHostName==" + InetAddress.getLocalHost().getHostName());
		System.out.println("=====================SOUT Ending==============================");
	}

	private List<Person> createPersons() {
		return Arrays.asList(Person.create("Dana", "Whitley", "464 Gorsuch Drive"),
				Person.create("Robin", "Cash", "64 Zella Park"),
				Person.create("Chary", "Mess", "112 Yellow Hill"),
				Person.create("Rose", "Kantata", "2736 Kooter Lane"),
				Person.create("Mike", "Togglie", "111 Cool Dr"));
	}

	@Transactional
	public void saveLaserData() throws Exception {
		// save a couple of categories

		final LaserSearch search1 = new LaserSearch("LaserSearch A");
		Set<ServiceAudit> serviceList1 = new HashSet<>();
		Set<ClientAudit> clientList1 = new HashSet<>();
		ServiceAudit s1 = new ServiceAudit("ServiceAudit A1", search1);
		ClientAudit c1 = new ClientAudit("ClientAudit A1", s1);
		clientList1.add(c1);
		c1 = new ClientAudit("ClientAudit A2", s1);
		clientList1.add(c1);
		s1.setClientAudits(clientList1);
		serviceList1.add(s1);
		s1 = new ServiceAudit("ServiceAudit A2", search1);
		c1 = new ClientAudit("ClientAudit A3", s1);
		clientList1.add(c1);
		s1.setClientAudits(clientList1);
		serviceList1.add(s1);
		search1.setServiceAudits(serviceList1);

		final LaserSearch search2 = new LaserSearch("LaserSearch A");
		serviceList1 = new HashSet<>();
		clientList1 = new HashSet<>();
		ServiceAudit s2 = new ServiceAudit("ServiceAudit A1", search2);
		ClientAudit c2 = new ClientAudit("ClientAudit A1", s2);
		clientList1.add(c2);
		s2.setClientAudits(clientList1);
		serviceList1.add(s2);
		search2.setServiceAudits(serviceList1);

		laserSearchRepository.saveAll(Arrays.asList(search1, search2));

		// fetch all categories
		for (LaserSearch search : laserSearchRepository.findAll()) {
			LOGGER.info(search.toString());
		}
	}
}
