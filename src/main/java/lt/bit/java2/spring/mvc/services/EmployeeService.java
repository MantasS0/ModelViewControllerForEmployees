package lt.bit.java2.spring.mvc.services;

import lt.bit.java2.spring.mvc.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EmployeeRowMapper employeeRowMapper;


    /**
     * Returns one Employee class object by empNo
     * @param id Employee number (emp_no (in DB) or empNo (in Employee class))
     * @return Returns one Employee class object by empNo
     */
    public Employee getEmployeeById(int id) {
        Employee employee = jdbcTemplate.queryForObject(
                "SELECT * FROM employees WHERE emp_no = ?",
                employeeRowMapper,
                id);
        return employee;

    }

    /**
     * Basic check for @param faults and limits pageSize to 100.
     * Uses 2 queries to get the list of Employees from DB (only for current page)
     * and to get total count of rows in DB table.
     * Returns one page of Employee as PageResult<Employee>
     *     PageResult class constructor also generates variables:
     *     int pageCount,
     *     boolean firstPage,
     *     boolean lastPage,
     *     int rangeFrom,
     *     int rangeTo from given parameters.
     * @param pageSize set page size (number of rows)
     * @param pageNumber set page number (1..)
     * @return Returns one page of Employee as PageResult<Employee>
     */
    public PageResult<Employee> getRequestedEmployeePage(int pageSize, int pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageSize < 1) {
            pageSize = 1;
        }
        else if (pageSize > 100) pageSize = 100;
        List<Employee> employees = jdbcTemplate.query("SELECT * FROM employees LIMIT ? OFFSET ?", employeeRowMapper, pageSize, (pageNumber - 1) * pageSize);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employees", Integer.class);
        int recordCount = count == null ? 0 : count;

        return new PageResult<>(pageSize, pageNumber, recordCount, employees);
    }


}


