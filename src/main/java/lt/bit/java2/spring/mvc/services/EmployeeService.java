package lt.bit.java2.spring.mvc.services;

import lt.bit.java2.spring.mvc.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeService {

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
        List<Employee> employeeList = jdbcTemplate.query("SELECT * FROM employees LIMIT 10",
                employeeRowMapper);

        Iterator it = employeeList.iterator();
        while (it.hasNext()) {
            Employee e = (Employee) it.next();
            System.out.println(e.getEmpNo() + " " + e.getFirstName() + " " + e.getLastName());
        }

        return employeeList;
    }

}
