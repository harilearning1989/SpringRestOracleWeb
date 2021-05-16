package com.web.demo.controls;

import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.entities.Employee;
import com.web.demo.repos.EmployeeRepo;
import com.web.demo.services.CSVReadService;
import com.web.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Controller
public class HomeController {

    @Value("${fullName}")
    private String fullName;
    @Value("${localLocation}")
    private String localLocation;

    @Autowired
    private CSVReadService csvReadService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.addAttribute("fullName", fullName);
        model.addAttribute("localLocation", localLocation);
        return "home";
    }

    @GetMapping(value = "/viewEmp")
    public String viewEmployeeDetails(ModelMap model) {
        CompletableFuture<List<EmployeeDTO>> empFuture =
                supplyAsync(() -> csvReadService.readEmployeeInfo());
        try {
            model.addAttribute("empDataList", empFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "empView";
    }

    @GetMapping(value = "/agGridFaker")
    public String agGridFakerExample() {
        return "agGridFaker";
    }

    @GetMapping(value = "/agGridExample")
    public String agGridExample(ModelMap model) {
        CompletableFuture<List<EmployeeDTO>> empFuture =
                supplyAsync(() -> csvReadService.readEmployeeInfo());
        try {
            model.addAttribute("empDataList", empFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "agGridExample";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> listEmployee = employeeRepo.findAll();
        model.addAttribute("listEmployee", listEmployee);

        return "employeeView";
    }

    @RequestMapping("/new")
    public String showNewProductForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("employee") Employee employee) {
        service.save(employee);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("edit_employee");

        Employee employee = service.get(id);
        mav.addObject("employee", employee);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id) {
        service.delete(id);

        return "redirect:/";
    }

}
