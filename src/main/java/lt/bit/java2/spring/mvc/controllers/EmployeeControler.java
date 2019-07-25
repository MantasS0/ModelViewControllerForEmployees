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
        int pageSize = size.orElse(100);

        Page<Employee> employeePage = employeeService.getEmployeePages(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("employeePage", employeePage);

        int totalPages = employeePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
//
//            IntStream is1 = IntStream.range(getLowestRange(currentPage), currentPage).limit(5);
//            IntStream is2 = IntStream.range(currentPage, totalPages).limit(5);
//
//            Stream<Integer> stream1 = is1.boxed();
//            Stream<Integer> stream2 = Stream.concat(stream1, is2.boxed());

            //IntStream.range(getLowestRange(currentPage), totalPages)

//            IntStream is1 = IntStream.range(1, totalPages);
//            int min = is1.min().getAsInt();
//            int max = is1.max().getAsInt();

/*

            List<Integer> fixedPageNumbers = IntStream.range(getLowestRange(currentPage), totalPages)
                    .limit(5)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("fixedPageNumbers", fixedPageNumbers);
*/
        }
        //Math.abs(currentPage-5), currentPage+5


        return "employee-list-paginated";
    }

    private int getLowestRange(int currentPage) {
        int min = 1;
        int range = currentPage;
        int count = 0;
        int scope = 5;

        for (int i = min; i < currentPage; i++) {
            count++;
        }
        System.out.println("Count = " + count);
        System.out.println("Return value = " + (currentPage - count));


        return currentPage - count;

    }

    private Integer[] getRangeForPaginatin(Integer currentPage, Integer totalPages) {
        Integer[] range = new Integer[3];
        int min = 1;
        int max = totalPages;
        int scope = 5;
        range[2] = scope;
        if (currentPage - scope < min) {

        }


        return range;
    }


}































