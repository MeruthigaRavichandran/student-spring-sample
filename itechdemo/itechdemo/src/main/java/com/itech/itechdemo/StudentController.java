package com.itech.itechdemo;

import com.itech.itechdemo.model.Course;
import com.itech.itechdemo.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class StudentController {
List<Student> studentList = new ArrayList<>();

@RequestMapping(value = "/student/all")
    List<Student> getStudentList(){
    List<Student> studentList1 = getInput();
    return studentList1;
}

@RequestMapping(value = "/student/{sno}")
public ResponseEntity getStudId(@PathVariable("sno") Integer sno){
    List<Student> sl1 = getInput();
   /* for (Student s : sl1) {
        if (s.getSno() == sno) {
            ResponseEntity responseEntity = new ResponseEntity(s, HttpStatus.OK);
            return responseEntity;
        }
    }
    */

    Optional<Student> ss = sl1.stream().filter(i -> i.getSno() == sno).findFirst();
    if(ss.isPresent()) {
        ResponseEntity responseEntity = new ResponseEntity(ss, HttpStatus.OK);
        return responseEntity;
    }
    Map<String,String> map = new HashMap<>();
    map.put("message","Student not found");
    ResponseEntity responseEntity = new ResponseEntity(map,HttpStatus.NOT_FOUND);
    return responseEntity;
}

@RequestMapping(value = "/student",method = RequestMethod.POST)
public ResponseEntity createStud(@RequestBody Student s){
    boolean b = addStud(s);
    Map<String, String> map = new HashMap<>();
    ResponseEntity responseEntity;
    if(b) {
        map.put("message", "Student added succesfully");
         responseEntity = new ResponseEntity(map, HttpStatus.CREATED);
        return responseEntity;
    }
    else
    {
        map.put("message", "Student already exist!");
         responseEntity = new ResponseEntity(map, HttpStatus.ALREADY_REPORTED);
        return responseEntity;
    }
}

@RequestMapping(value = "/student", method = RequestMethod.PUT)
public ResponseEntity updateStud(@RequestBody Student ss)
{
    boolean b = updateMyStud(ss);
    Map<String, String> map = new HashMap<>();
    ResponseEntity responseEntity;
    if(b) {
        map.put("message", "Student updated succesfully");
        responseEntity = new ResponseEntity(map, HttpStatus.OK);
        return responseEntity;
    }
    else
    {
        map.put("message", "Student not found for update!");
       responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}

    private boolean updateMyStud(Student ss) {
    List<Student> sll = getInput();
        Iterator<Student> iterator = sll.iterator();
        boolean removed = false;

        while(iterator.hasNext()) {
            Student stud = iterator.next();
            if(stud.getSno() == ss.getSno()) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if(removed) {
            sll.add(ss);
        }
        return removed;
    }

    private boolean addStud(Student s) {
    List<Student> stud =getInput();
    Optional<Student> s1 = stud.stream().filter(i -> i.getSno()==s.getSno()).findAny();
        Map<String,String> map = new HashMap<>();
    if(!s1.isPresent()) {
        stud.add(s);
        return true;
    }
        return false;
    }


    private List<Student> getInput() {
    if(studentList.isEmpty())
    {
        Student s1 = new Student();
        s1.setSno(12);
        s1.setName("Simren");
        Course c = new Course();
        c.setCourse("Btech");
        c.setBranch("CSE");
        c.setYos(2);
        s1.setCourse(c);
        List<String> li = new ArrayList();
        li.add(0,"DSD");
        li.add(1,"JAVA");
        li.add(2,"Maths");
        s1.setSubjects(li);

        Student s2 = new Student();
        s2.setSno(14);
        s2.setName("Reema");
        Course c1 = new Course();
        c1.setCourse("Btech");
        c1.setBranch("IT");
        c1.setYos(3);
        s2.setCourse(c1);
        List<String> l = new ArrayList();
        l.add(0,"CO");
        l.add(1,"DS");
        l.add(2,"JAVA");
        s2.setSubjects(l);
        studentList.add(s1);
        studentList.add(s2);
    }
    return studentList;
    }
}