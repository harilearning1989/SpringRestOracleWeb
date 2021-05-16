package com.web.demo.controls;

import com.web.demo.entities.CropInsurance;
import com.web.demo.repos.IMyTableRepository;
import com.web.demo.services.IPlsqlService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("plsql")
@Api(value = "PlsqlRestController")
public class PlsqlRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlsqlRestController.class);

    @Autowired
    private IMyTableRepository myTableRepository;

    @Autowired
    private IPlsqlService iPlsqlService;

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
