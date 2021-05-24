package com.web.demo.controls;

import com.web.demo.entities.CropInsurance;
import com.web.demo.entities.Person;
import com.web.demo.repos.EmployeeRepo;
import com.web.demo.repos.IMyTableRepository;
import com.web.demo.repos.PersonRepository;
import com.web.demo.services.IPlsqlService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("plsql")
@Api(value = "PlsqlRestController")
public class PlsqlRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlsqlRestController.class);

    @Autowired
    private IMyTableRepository myTableRepository;

    @Autowired
    private IPlsqlService iPlsqlService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PersonRepository repo;

    @GetMapping(value = "/callHelloWorld")
    public String callHelloWorld(@RequestParam(defaultValue = "Hari") String name) {
        return employeeRepo.callHelloWorld(name);
    }

    @GetMapping(value = "/getFirstName")
    public String getFirstName(@RequestParam(required = false, defaultValue = "1") Integer empId) {
        return employeeRepo.getFirstName(empId);
    }

    @GetMapping(value = "/savePerson")
    public String savePerson() {
        List<Person> persons = createPersons();
        repo.saveAll(persons);

        System.out.println(" -- getting all persons --");
        Iterable<Person> iterable = repo.findAll();
        List<Person> allPersons = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        allPersons.forEach(System.out::println);

        Person person = allPersons.get(0);
        System.out.printf("-- moving person with id %s to history --%n", person.getId());
        String b = repo.movePersonToHistory(person.getId());
        System.out.println("output status: " + b);

        person = allPersons.get(1);
        System.out.printf("-- moving person with id %s to history --%n", person.getId());
        b = repo.movePersonToHistory(person.getId());
        System.out.println("output status: " + b);

        System.out.println("-- getting all persons again --");
        repo.findAll().forEach(System.out::println);

        System.out.println("-- fetching from  person history --");
        repo.fetchPersonHistory().forEach(System.out::println);
        return "Saved Person Data";
    }

    private List<Person> createPersons() {
        return Arrays.asList(Person.create("Dana", "Whitley", "464 Gorsuch Drive"),
                Person.create("Robin", "Cash", "64 Zella Park"),
                Person.create("Chary", "Mess", "112 Yellow Hill"),
                Person.create("Rose", "Kantata", "2736 Kooter Lane"),
                Person.create("Mike", "Togglie", "111 Cool Dr"));
    }

    @GetMapping(value = "/empFirstNameProc")
    public Object[] getEmpByFirstName() {
        return employeeRepo.getPeopleData("Art");
    }

    @GetMapping(value = "/inOnlyTest")
    public String getCountryCurrency() {
        myTableRepository.inOnlyTest("Reddy");
        return myTableRepository.inAndOutTest("Hari==");
    }

    @GetMapping(value = "/procTest")
    public String procedureTest() {
        return iPlsqlService.procedureTest();
    }

    @GetMapping(value = "/procCursor")
    public List<CropInsurance> fetchCropInsuranceProcCursor() {
        return iPlsqlService.fetchCropInsuranceProcCursor();
    }

    @GetMapping(value = "/arrFunc")
    public List<String> getArrayFunc() {
        return iPlsqlService.getArrayFunc();
    }

}
