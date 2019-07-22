package lt.bit.java2.spring.mvc.controllers;

import lt.bit.java2.spring.mvc.Employee;
import lt.bit.java2.spring.mvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/employee")
class EmployeeControler {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("{id}")
    public String getEmployee(@PathVariable int id, ModelMap map) {
        Employee employee = employeeService.getEmployeeById(id);
        map.addAttribute("employee", employee);
        return "employee";
    }

/*
    @GetMapping
    public String getEmployeesList(ModelMap map) {
        List<Employee> employeeList = employeeService.getEmployeeList();
        map.addAttribute("employeeList", employeeList);

        return "employees-list";
    }
*/

    @GetMapping
    public String getEmployeeListPaginated(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Employee> employeePage = employeeService.getEmployeePages(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("employeePage", employeePage);

        int totalPages = employeePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "employee-list-paginated";
    }


//    @Autowired
//    private BookService bookService;
//
//    @RequestMapping(value = "/listBooks", method = RequestMethod.GET)
//
//
//    public String listBooks(
//            Model model,
//            @RequestParam("page") Optional<Integer> page,
//            @RequestParam("size") Optional<Integer> size) {
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(5);
//
//        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
//
//        model.addAttribute("bookPage", bookPage);
//
//        int totalPages = bookPage.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        return "listBooks.html";
//    }

}
