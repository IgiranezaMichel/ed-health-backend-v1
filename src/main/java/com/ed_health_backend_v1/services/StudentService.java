package com.ed_health_backend_v1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ed_health_backend_v1.dto.AccountHolderDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.dto.StudentDTO;
import com.ed_health_backend_v1.enums.Role;
import com.ed_health_backend_v1.enums.StudentStatus;
import com.ed_health_backend_v1.mapper.StudentMapper;
import com.ed_health_backend_v1.modal.AccountHolder;
import com.ed_health_backend_v1.modal.Department;
import com.ed_health_backend_v1.modal.School;
import com.ed_health_backend_v1.modal.Student;
import com.ed_health_backend_v1.repository.AccountHolderRepository;
import com.ed_health_backend_v1.repository.DepartmentRepository;
import com.ed_health_backend_v1.repository.SchoolRepositorys;
import com.ed_health_backend_v1.repository.StudentRepository;
@Service
public class StudentService {
 private  @Autowired StudentRepository studentRepository;
 private  @Autowired SchoolRepositorys schoolRepositorys;
 private  @Autowired AccountHolderRepository accountHolderRepository;
 private  @Autowired AccountHolderServices accountHolderServices;
 private  @Autowired DepartmentRepository departmentRepository;
private StudentMapper studentMapper=new StudentMapper();
public ResponseEntity<String>create(StudentDTO studentDTO){
       try {
        if(studentDTO.getName().length()==0) return new ResponseEntity<>("Student name is required",HttpStatus.BAD_REQUEST);
        else if(studentDTO.getDateOfBirth().length()==0) return new ResponseEntity<>("Date of birth is required",HttpStatus.BAD_REQUEST);
        else if(studentDTO.getGender().length()==0) return new ResponseEntity<>("Student Gender is required",HttpStatus.BAD_REQUEST);
        else if(studentDTO.getPhoneNumber().length()==0) return new ResponseEntity<>("Phone number is required",HttpStatus.BAD_REQUEST);
        else if(studentDTO.getProfilePicture().length()==0) return new ResponseEntity<>("Profile picture is required",HttpStatus.BAD_REQUEST);
        else if(studentDTO.getDepartmentId().length()==0) return new ResponseEntity<>("Department is required",HttpStatus.BAD_REQUEST);
         else if(studentDTO.getEmail().length()==0) return new ResponseEntity<>("Email is required",HttpStatus.BAD_REQUEST);
        else{
                Department department=departmentRepository.findById(UUID.fromString(studentDTO.getDepartmentId())).orElseThrow();
                School school=department.getFaculty().getSchool();
                AccountHolder accountHolder=accountHolderRepository.findByEmail(studentDTO.getEmail());
                // change account holder role
                if(accountHolder==null){
                 AccountHolderDTO accountholderDto=  new AccountHolderDTO(studentDTO.getName(), studentDTO.getGender(), studentDTO.getEmail(), studentDTO.getPhoneNumber(), studentDTO.getProfilePicture(), studentDTO.getDateOfBirth(), Role.ROLE_STUDENT);
                 accountHolder=accountHolderServices.createAccount(accountholderDto, null);
                 Student student=new Student(accountHolder, StudentStatus.ACTIVE, department, school);
                 return new ResponseEntity<>(student.getAccountHolder().getName()+" saved successful",HttpStatus.CREATED);
          }
           Student student=new Student(accountHolder, StudentStatus.ACTIVE, department, school);
           Student student2=studentRepository.save(student);
           return new ResponseEntity<>(student2.getAccountHolder().getName()+" saved successful",HttpStatus.CREATED);
        }
       } catch (Exception e) {
        return new ResponseEntity<>("Student already exist",HttpStatus.BAD_REQUEST);
       }
}
@SuppressWarnings({ "unchecked", "rawtypes" })
public Pagination<StudentDTO> getAllStudentStudyAtSameSchool(String schoolId, String search, String sort,
        int pageNumber, int pageSize) {
     School school=schoolRepositorys.findById(UUID.fromString(schoolId)).orElse(null);
     if (search.length() == 0) {
            Page<Student> page = studentRepository.findAllBySchool(school,PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
           System.out.print(page.getContent().size());
            List<StudentDTO> list = page.getContent().stream().map(studentMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        } else {
            Page<Student> page = studentRepository.findAllBySchoolAndAccountHolderNameContainingIgnoreCase(school,search,
                    PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<StudentDTO> list = page.getContent().stream().map(studentMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        }
}

}
