package com.web.demo.controls;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calc")
@Api(value = "CalculatorCucumber")
public class CalculatorCucumber {

    @ApiOperation(value = "add")
    @ApiResponses(value = {
            @ApiResponse(code = 100, message = "100 Message"),
            @ApiResponse(code = 200, message = "200 Success Message")
    })
    @RequestMapping("/add")
    public int add(int a, int b) {
        return a + b;
    }

    @ApiOperation(value = "subtract")
    @ApiResponses(value = {
            @ApiResponse(code = 100, message = "100 Message"),
            @ApiResponse(code = 200, message = "200 Success Message")
    })
    @RequestMapping("/sub")
    public int subtract(int a, int b) {
        return a - b;
    }

    @ApiOperation(value = "multiply")
    @ApiResponses(value = {
            @ApiResponse(code = 100, message = "100 Message"),
            @ApiResponse(code = 200, message = "200 Success Message")
    })
    @RequestMapping("/mul")
    public int multiply(int a, int b) {
        return a * b;
    }

    @ApiOperation(value = "divide")
    @ApiResponses(value = {
            @ApiResponse(code = 100, message = "100 Message"),
            @ApiResponse(code = 200, message = "200 Success Message")
    })
    @RequestMapping("/div")
    public int divide(int a, int b) {
        return a / b;
    }
}
