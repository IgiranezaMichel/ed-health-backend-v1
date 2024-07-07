package com.ed_health_backend_v1.services;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ed_health_backend_v1.dto.ControlPanelDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.dto.SchoolDTO;
import com.ed_health_backend_v1.mapper.SchoolMapper;
import com.ed_health_backend_v1.modal.AccountHolder;
import com.ed_health_backend_v1.modal.Location;
import com.ed_health_backend_v1.modal.School;
import com.ed_health_backend_v1.repository.SchoolRepositorys;

@Service
public class SchoolServices {
    @Autowired
    private ControlPanelServices controlPanelServices;
    @Autowired
    private LocationService locationService;
    @Autowired
    private AccountHolderServices accountHolderServices;
    @Autowired
    private SchoolRepositorys schoolRepository;
    private SchoolMapper schoolMapper = new SchoolMapper();

    public ResponseEntity<String> create(SchoolDTO schoolSto, String email) {
        School school = new School();
        if (email.length() == 0)
            return new ResponseEntity<>("Please add a correct account holder", HttpStatus.BAD_REQUEST);
        else if (schoolSto.getLocation().length() == 0)
            return new ResponseEntity<>("Please add a correct Location", HttpStatus.BAD_REQUEST);
        else if (schoolSto.getName().length() == 0)
            return new ResponseEntity<>("Please add a correct Location", HttpStatus.BAD_REQUEST);
        else {
            Location location = locationService.findLocationById(UUID.fromString(schoolSto.getLocation()));
            AccountHolder accountHolder2 = accountHolderServices.findByEmail(email);
            if (accountHolder2 == null)
                return new ResponseEntity<>("Please add a correct account holder", HttpStatus.BAD_REQUEST);
            if (schoolSto.getId().toString().equals("")) {
                school = schoolRepository.save(new School(schoolSto.getName(), schoolSto.getLogo().split("base64,")[1], location));
            } else {
                school =schoolRepository.save( new School(UUID.fromString(schoolSto.getId()), schoolSto.getName(), schoolSto.getLogo().split("base64,")[1],
                location));
            }
            controlPanelServices.create(new ControlPanelDTO(email, school.getId().toString()));
        }
        school = schoolRepository.save(school);
        return new ResponseEntity<>(school.getName() + " ,created successful", HttpStatus.CREATED);
    }

    public ResponseEntity<String> update(SchoolDTO school) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Pagination<SchoolDTO> getAllSchool(String search, String sort, int pageNumber, int pageSize) {
        if (search.length() == 0) {
            Page<School> page = schoolRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<SchoolDTO> list = page.getContent().stream().map(schoolMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        } else {
            Page<School> page = schoolRepository.findAllByNameContainingIgnoreCase(search,
                    PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<SchoolDTO> list = page.getContent().stream().map(schoolMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        }
    }

    public ResponseEntity<String> delete(UUID id) {
        try {
            School school = schoolRepository.findById(id).orElse(null);
            schoolRepository.delete(school);
            return new ResponseEntity<>(school.getName() + " deleted sucessful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Wrong school credentials try again", HttpStatus.BAD_REQUEST);
        }
    }

    public SchoolDTO findSchoolById(String schoolId) {
        School t = schoolRepository.findById(UUID.fromString(schoolId)).orElse(null);
        return new SchoolDTO(t.getId().toString(), t.getName(),
                "data:image/png;base64," + Base64.getEncoder().encodeToString(t.getLogo()),
                t.getLocation().getLocation().getName(), t.getLocation().getName(), t.getName());
    }

}
