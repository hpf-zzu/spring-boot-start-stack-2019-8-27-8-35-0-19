package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static List<employee> employees = new ArrayList<employee>(){{
        add(new employee(1,"xiaoming",21,"Male"));
        add(new employee(2,"xiaohong",22,"famale"));

    }};



    @GetMapping
    public ResponseEntity<List<employee>> getEmployees(@RequestParam(value = "name",required = false) String namelike){
//        List<employee> employees = new ArrayList<employee>();
//        employee employee1 = new employee();
//        employee1.setAge(12);
//        employee1.setId(1);
//        employee1.setGender("man");
//        employee1.setName("hi");
//        employees.add(employee1);
//        employee employee2 = new employee();
//        employee2.setAge(12);
//        employee2.setId(1);
//        employee2.setGender("man");
//        employee2.setName("ihu");
//        employees.add(employee2);
        if(namelike == null){
            return ResponseEntity.ok(employees);
        }
        List<employee> employeesAnswer = new ArrayList<>();
        for(int i = 0 ; i < employees.size(); i ++){
            if(employees.get(i).getName().contains(namelike)){
                employeesAnswer.add(employees.get(i));
            }
        }
        return ResponseEntity.ok(employeesAnswer);
    }
    public ResponseEntity<String> getAll(@PathVariable String userName) {

        return ResponseEntity.ok("Hello:" + userName);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<employee> getEmployee(@PathVariable int id){
        List<employee> employees = new ArrayList<employee>();
        employee employee1 = new employee();
        employee1.setAge(12);
        employee1.setId(1);
        employee1.setGender("man");
        employee1.setName("hi");
        employees.add(employee1);
        employee employee2 = new employee();
        employee2.setAge(13);
        employee2.setId(2);
        employee2.setGender("man");
        employee2.setName("hii");
        employees.add(employee2);
        for(int i = 0; i < employees.size(); i ++){
            if(employees.get(i).getId() == id){
                return ResponseEntity.ok(employees.get(i));
            }
        }
        return null;
    }

    @PostMapping(consumes = "application/json")
    //constructor default
    public ResponseEntity<employee> addEmployee(@RequestBody employee employeeInput){
        employees.add(new employee(3,"hh",21,"male"));
        employees.add(employeeInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
