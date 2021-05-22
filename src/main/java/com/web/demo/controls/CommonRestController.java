package com.web.demo.controls;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.demo.dto.AllCountriesRegion;
import com.web.demo.dto.CountriesDTO;
import com.web.demo.dto.EmployeeDto;
import com.web.demo.entities.Car;
import com.web.demo.entities.CountriesEntity;
import com.web.demo.entities.CropInsurance;
import com.web.demo.entities.IndiaStates;
import com.web.demo.services.CommonService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
@RequestMapping("common")
@Api(value = "CommonRestController")
public class CommonRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRestController.class);

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public List<EmployeeDto> getCSVEmployeeData() throws IOException {
        LOGGER.info("EmployeeRestController===empDataTable()==");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("1000EmployeeData.csv");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        EmployeeDto empDto = null;

        List<EmployeeDto> humanDtoList = new ArrayList<>();
        String line1 = null;
        int count = 0;
        while ((line1 = reader.readLine()) != null) {
            count++;
            if (count > 1) {
                String[] data = line1.split(",");
                empDto = new EmployeeDto();
                empDto.setId(count);
                try {
                    empDto.setEmpID(Integer.parseInt(data[0]));
                    empDto.setNamePrefix(data[1]);
                    empDto.setFirstName(data[2]);
                    empDto.setMiddleInitial(data[3]);
                    empDto.setLastName(data[4]);
                    empDto.setGender(data[5]);
                    empDto.seteMail(data[6]);
                    empDto.setFathersName(data[7]);
                    empDto.setMothersName(data[8]);
                    empDto.setMothersMaidenName(data[9]);
					/*empDto.setDateofBirth(format.parse(data[10]));
	                        empDto.setTimeofBirth(format.parse(data[11]));*/
                    empDto.setAgeinYrs(Double.parseDouble(data[12]));
                    empDto.setWeightinKgs(Integer.parseInt(data[13]));
                    //empDto.setDateofJoining(format.parse(data[14]));
                    empDto.setQuarterofJoining(data[15]);
                    empDto.setHalfofJoining(data[16]);
                    empDto.setYearofJoining(data[17]);
                    empDto.setMonthofJoining(Integer.parseInt(data[18]));
                    empDto.setMonthNameofJoining(data[19]);
                    empDto.setShortMonth(data[20]);
                    empDto.setDayofJoining(Integer.parseInt(data[21]));
                    empDto.setdOWofJoining(data[22]);
                    empDto.setShortDOW(data[23]);
                    empDto.setAgeinCompanyYears(Double.parseDouble(data[24]));
                    empDto.setSalary(Integer.parseInt(data[25]));
                    // empDto.setLastHike(Integer.parseInt(data[26]));
                    empDto.setsSN(data[27]);
                    empDto.setPhoneNo(data[28]);
                    empDto.setPlaceName(data[29]);
                    empDto.setCounty(data[30]);
                    empDto.setCity(data[32]);
                    empDto.setState(data[33]);
                    //empDto.setZip(Integer.parseInt(data[34]));
                    empDto.setRegion(data[35]);
                    empDto.setUserName(data[36]);
                    humanDtoList.add(empDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("humanDtoList.size()=====" + humanDtoList.size());
        return humanDtoList;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public List<CountriesDTO> getCountriesData() throws IOException {
        LOGGER.info("JSONRestController=====getCountriesData()======info===");
        ObjectMapper objectMapper = new ObjectMapper();

        Resource resource = new ClassPathResource("countries.json");
        File file = resource.getFile();

        AllCountriesRegion customer = objectMapper.readValue(file, AllCountriesRegion.class);
        List<CountriesDTO> country = customer.getCountries();
        return country;
    }

    @GetMapping(value = "/countries/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CountriesEntity>> getAllCountries() {
        try {
            List<CountriesEntity> countries = new ArrayList<CountriesEntity>();
            commonService.getAllCountries().forEach(countries::add);
            if (countries.isEmpty()) {
                System.out.println("SOUT");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception at getAllCountries :=" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/crops/list")
    public ResponseEntity<List<CropInsurance>> getAllCropInsurance() {
        try {
            List<CropInsurance> CropInsurance = new ArrayList<CropInsurance>();
            commonService.getAllCropInsurance().forEach(CropInsurance::add);
            if (CropInsurance.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(CropInsurance, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception at getAllCropInsurance :=" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/states/list")
    public ResponseEntity<List<IndiaStates>> getAllStates() {
        try {
            List<IndiaStates> indiaStates = commonService.getAllStates();
            if (indiaStates.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(indiaStates, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception at getAllCropInsurance :=" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/state")
    public List<IndiaStates> findByState(
            @RequestParam(defaultValue = "ANDHRA PRADESH") String state) {
        return commonService.findByStateNameIgnoreCase(state);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity uploadFile(
            @RequestParam(value = "files") MultipartFile[] files) {
        try {
            for (final MultipartFile file : files) {
                commonService.saveCars(file.getInputStream());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    CompletableFuture<ResponseEntity> getAllCars() {
        return commonService.getAllCars().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetCarFailure);
    }

    private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetCarFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public @ResponseBody
    String getAllUsers(ModelMap model) {
        String jsonData = "[{\"id\":\"3253123\",\"firstname\":\"Chris\",\"lastname\":\"Johnson\",\"address\":\"211, Geoffrey Drive\",\"city\":\"Newark\",\"phone\":\"999-888-6666\",\"email\":\"chrisj@yahoo.com\"},{\"id\":\"67643837\",\"firstname\":\"Bill\",\"lastname\":\"Derkson\",\"address\":\"201, Sleepy Hollow Drive\",\"city\":\"Newark\",\"phone\":\"999-777-2222\",\"email\":\"billd@gmail.com\"}]";
        return jsonData;
    }

    @RequestMapping(value = "/getAllProfiles", method = RequestMethod.GET)
    public @ResponseBody
    String getAllProfiles(ModelMap model) {
        String jsonData = "[{\"firstname\":\"ajitesh\",\"lastname\":\"kumar\",\"address\":\"211/20-B,mgstreet\",\"city\":\"hyderabad\",\"phone\":\"999-888-6666\"},{\"firstname\":\"nidhi\",\"lastname\":\"rai\",\"address\":\"201,mgstreet\",\"city\":\"hyderabad\",\"phone\":\"999-876-5432\"}]";
        return jsonData;
    }
}
