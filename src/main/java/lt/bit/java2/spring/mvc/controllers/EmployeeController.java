package lt.bit.java2.spring.mvc.controllers;

import lt.bit.java2.spring.mvc.Employee;
import lt.bit.java2.spring.mvc.services.EmployeeService;
import lt.bit.java2.spring.mvc.services.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/employee")
class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("{id}")
    public String getEmployee(@PathVariable int id, ModelMap map) {
        Employee employee = employeeService.getEmployeeById(id);
        map.addAttribute("employee", employee);
        return "employee";
    }

    @GetMapping
    public String getEmployeeSinglePage(
        @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
        @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
            ModelMap map){
    PageResult<Employee> result = employeeService.getRequestedEmployeePage(pageSize, pageNumber);
    map.addAttribute("result", result);
    return "employee-list-page";
}

}































