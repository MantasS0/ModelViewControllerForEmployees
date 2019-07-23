package lt.bit.java2.spring.mvc.services;

import lt.bit.java2.spring.mvc.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EmployeeRowMapper employeeRowMapper;

    public Employee getEmployeeById(int id) {
        Employee employee = jdbcTemplate.queryForObject(
                "SELECT * FROM employees WHERE emp_no = ?",
                employeeRowMapper,
                id);
        return employee;

    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = jdbcTemplate.query("SELECT * FROM employees LIMIT 1000",
                employeeRowMapper);

        return employeeList;
    }

    public Page<Employee> getEmployeePages(Pageable pageable){
        List<Employee> employees = getEmployeeList();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Employee> list;

        if (employees.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, employees.size());
            list = employees.subList(startItem, toIndex);
        }

        Page<Employee> employeePage = new PageImpl<Employee>(list, PageRequest.of(currentPage, pageSize), employees.size());

        return employeePage;
    }


}


