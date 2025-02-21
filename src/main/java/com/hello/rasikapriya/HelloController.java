/*package com.hello.rasikapriya;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController 
{
    @RequestMapping("/hello")
    public String hello()
    {
        return "Hello Rasika";
    }
    @GetMapping("/abcd")
    public String getStudentName(@RequestParam String studentname,@RequestParam int studentage)
    {
    return "good morning "+studentname+"    "+studentage; 
    }

}*/

/*import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Add a base path for endpoints
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Harini Sree Ramesh";
    }

    @GetMapping("/abcd")
    public String getStudentName(@RequestParam String studentname, @RequestParam int studentage) {
        return "Good morning " + studentname + "    " + studentage;
    }

    // POST mapping to add a student
    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student) {
        return "Student added: " + student.getName() + ", Age: " + student.getAge();
    }
}*/


package com.hello.rasikapriya;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController

public class HelloController
{
    @GetMapping("/abc")
    public String abc()
    {
        return "Hello";
    }
    @GetMapping("/abi")
    public String method()
    {
        return "Good Morning";
    }

    //RequestParameter
    @GetMapping("/ccc")
    public String getMethodNmae(@RequestParam String param){
        return new String();
    }
    //http://localhost:8080/abcd?studentname=rasika&studentage=19
    @GetMapping("/abcd")
    public String getStudentName(@RequestParam String studentname,@RequestParam int studentage){
        return"good morning"+studentname+studentage;
    }
    
    @GetMapping("/{name}")
    public String example(@PathVariable String name){
        return "welcome"+name;
    }
    
}