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
import com.ed_health_backend_v1.dto.FacultyDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.mapper.FacultyMapper;
import com.ed_health_backend_v1.modal.Faculty;
import com.ed_health_backend_v1.modal.School;
import com.ed_health_backend_v1.repository.FacultyRepository;
import com.ed_health_backend_v1.repository.SchoolRepositorys;

@Service
public class FacultyService {
    @Autowired
    SchoolRepositorys schoolRepositorys;
    @Autowired
    FacultyRepository facultyRepository;
    private FacultyMapper facultyMapper = new FacultyMapper();

    public ResponseEntity<String> create(FacultyDTO facultyDto) {
        if (facultyDto.getName().equals(""))
            return new ResponseEntity<>("Faculty name is required", HttpStatus.BAD_REQUEST);
        if (facultyDto.getSchoolId().equals(""))
            return new ResponseEntity<>("School is required", HttpStatus.BAD_REQUEST);
        try {
            School school = schoolRepositorys.findById(UUID.fromString(facultyDto.getSchoolId())).orElseThrow();
            Faculty faculty = facultyRepository
                    .save(new Faculty(facultyDto.getName(), school));
            return new ResponseEntity<>(faculty.getName() + " added succesful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save information check your information and try again",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update(FacultyDTO facultyDto) {
        if (facultyDto.getName().equals(""))
            return new ResponseEntity<>("Faculty name is required", HttpStatus.BAD_REQUEST);
        if (facultyDto.getId().equals(""))
            return new ResponseEntity<>("School is required", HttpStatus.BAD_REQUEST);
        else if (facultyDto.getSchoolId().equals(""))
            return new ResponseEntity<>("School is required", HttpStatus.BAD_REQUEST);
        try {
            School school = schoolRepositorys.findById(UUID.fromString(facultyDto.getSchoolId())).orElseThrow();
            Faculty faculty = facultyRepository
                    .save(new Faculty(UUID.fromString(facultyDto.getId()), facultyDto.getName(), school));
            return new ResponseEntity<>(faculty.getName() + " added succesful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save information check your information and try again",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Pagination<FacultyDTO> getAllFaculty(String search, String sort, int pageNumber, int pageSize) {
        if (search.length() == 0) {
            Page<Faculty> page = facultyRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<FacultyDTO> list = page.getContent().stream().map(facultyMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        } else {
            Page<Faculty> page = facultyRepository.findAllByNameContainingIgnoreCase(search,
                    PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<FacultyDTO> list = page.getContent().stream().map(facultyMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        }
    }

    public FacultyDTO findFacultyById(String facultyId) {
        Faculty t = facultyRepository.findById(UUID.fromString(facultyId)).orElse(null);
        return new FacultyDTO(t.getId().toString(), t.getName(), t.getSchool().getId().toString(),
                t.getSchool().getName());
    }

    public ResponseEntity<String> delete(UUID fromString) {
        try {
            Faculty faculty = facultyRepository.findById(fromString).orElseThrow();
            facultyRepository.delete(faculty);
            return new ResponseEntity<>(faculty.getName() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid faculty", HttpStatus.BAD_REQUEST);
        }
    }

    public List<FacultyDTO> findSchoolFaculty(String schoolId) {
        School school = schoolRepositorys.findById(UUID.fromString(schoolId)).orElse(null);
        return facultyRepository.findAllBySchool(school).stream().map(facultyMapper).toList();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Pagination<FacultyDTO> getAllFacultyFromSameSchool(String search, String schoolId, String sort,
            int pageNumber, int pageSize) {
        School school = schoolRepositorys.findById(UUID.fromString(schoolId)).orElse(null);
        if (search.length() == 0) {
            Page<Faculty> page = facultyRepository.findAllBySchool(school,PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<FacultyDTO> list = page.getContent().stream().map(facultyMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        } else {
            Page<Faculty> page = facultyRepository.findAllBySchoolAndNameContainingIgnoreCase(school,search,
                    PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<FacultyDTO> list = page.getContent().stream().map(facultyMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        }
    }
}
