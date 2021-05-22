package com.web.demo.controls;

import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.entities.Employee;
import com.web.demo.entities.MyUserDetails;
import com.web.demo.repos.EmployeeRepo;
import com.web.demo.services.CSVReadService;
import com.web.demo.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

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

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.addAttribute("fullName", fullName);
        model.addAttribute("localLocation", localLocation);
        return "home";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()+ ", You can not access this page!");
        } else {
            model.addObject("msg","You can not access this page!");
        }
        model.setViewName("accessDenied");
        return model;
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView getAccessDenied(@AuthenticationPrincipal UserDetails user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("msg", "Hi " + user.getUsername()+ ", You can not access this page!");
        } else {
            model.addObject("msg","You can not access this page!");
        }
        model.setViewName("accessDenied");
        return model;
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
    public String viewHomePage(Model model,
                               Principal principal,
                               Authentication authentication,
                               HttpServletRequest request,
                               @AuthenticationPrincipal MyUserDetails myUserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());

        Principal principalServlet = request.getUserPrincipal();

        String userName = myUserDetails.getUsername();
        UserDetails user = userDetailsService.loadUserByUsername(userName);

        List<Employee> listEmployee = employeeRepo.findAll();
        model.addAttribute("listEmployee", listEmployee);

        return "employeeView";
    }

    @RequestMapping(value = "/authPrinciple", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    UserDetails authPrinciple(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @RequestMapping("/new")
    public ModelAndView showNewProductForm() {
        return new ModelAndView("new_product", "command", new Employee());
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id) {
        service.delete(id);

        return "redirect:/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CREATOR')")
    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public String helloWorldJquery(Map<String, Object> model) {
        LOGGER.info("CommonWebController===helloWorldJquery()==");
        model.put("message", "HowToDoInJava Reader !!");

        return "HelloWorldJquery";
    }

    @PreAuthorize("#username == authentication.principal.username")
    public String getMyRoles(String username) {
        //...
        return username;
    }

    @RequestMapping("/emp")
    public String empDataTable() {
        LOGGER.info("CommonWebController===empDataTable()==");
        return "dataTable";
    }

    @RequestMapping("/dataTable")
    public String empDataTableOffline() {
        LOGGER.info("CommonWebController===empDataTableOffline()==");
        return "dataTableOffline";
    }

    @RequestMapping("/export")
    public String dataTableExports() {
        LOGGER.info("CommonWebController===dataTableExports()==");
        return "dataTableExports";
    }

    @RequestMapping("/countriesTable")
    public String dataTableCountries() {
        LOGGER.info("CommonWebController===dataTableCountries()==");
        return "dataTableCountries";
    }

    @RequestMapping("/widgets")
    public String jqueryWidgets() {
        LOGGER.info("CommonWebController===jqueryWidgets()==");
        return "JQueryWidgets";
    }

}
