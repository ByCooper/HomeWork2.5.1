package com.homework.getemployeecontrol;
import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("name") String name, @RequestParam("lastName") String lastName)
    {
        Employee person = new Employee(name, lastName);
        try {
            employeeService.addPerson(person);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            System.out.println(e.getMessage());
        }
        return " ";
    }
    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("name") String name,
                                   @RequestParam("lastName") String lastName)
    {
        Employee person = new Employee(name, lastName);
        try {
            employeeService.deletePerson(person);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return person;
    }

    @GetMapping("/find")
    public Employee searchEmployee(@RequestParam("name") String name,
                                   @RequestParam("lastName") String lastName)
    {
        Employee person = new Employee(name, lastName);
        try {
            employeeService.searchPerson(person);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return person;
    }

    @GetMapping("/all")
    public void allEmployee() {
        employeeService.showAllPersons();
    }
}
