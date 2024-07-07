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
import com.ed_health_backend_v1.dto.HospitalDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.mapper.HospitalMapper;
import com.ed_health_backend_v1.modal.Hospital;
import com.ed_health_backend_v1.modal.Location;
import com.ed_health_backend_v1.repository.HospitalRepository;

@Service
public class HospitalServices {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private LocationService locationService;
    private HospitalMapper hospitalMapper = new HospitalMapper();

    public ResponseEntity<String> create(HospitalDTO hospitalDTO) {
        if (hospitalDTO.getLocation().equals(""))
            return new ResponseEntity<>("Please specify correct location", HttpStatus.SEE_OTHER);
        Location location = locationService.findLocationById(UUID.fromString(hospitalDTO.getLocation()));
        if (hospitalDTO.getLogo().equals(""))
            return new ResponseEntity<>("Please add hospital logo", HttpStatus.SEE_OTHER);
        Hospital hospital = hospitalRepository
                .save(new Hospital(hospitalDTO.getName(), hospitalDTO.getLogo().split("base64,")[1], location));
        return new ResponseEntity<>(hospital.getName() + " added succesful", HttpStatus.CREATED);
    }

    public ResponseEntity<String> update(HospitalDTO hospitalDTO) {
        Hospital hospital = new Hospital();
        if (hospitalDTO.getId().equals(null))
            return new ResponseEntity<>("Please specify correct hospital", HttpStatus.NOT_FOUND);
        if (hospitalDTO.getLocation().equals(""))
            return new ResponseEntity<>("Please specify correct location", HttpStatus.NOT_FOUND);
        Location location = locationService.findLocationById(UUID.fromString(hospitalDTO.getLocation()));
        if (hospitalDTO.getLogo().equals(""))
            return new ResponseEntity<>("Please add hospital logo", HttpStatus.NOT_FOUND);
        if (hospitalDTO.getId().equals("")) {
            hospital = hospitalRepository.save(new Hospital(hospitalDTO.getName(),
                    hospitalDTO.getLogo().split("base64,")[1], location));
        } else
            hospital = hospitalRepository.save(new Hospital(UUID.fromString(hospitalDTO.getId()), hospitalDTO.getName(),
                    hospitalDTO.getLogo().split("base64,")[1], location));
        return new ResponseEntity<>(hospital.getName() + " added succesful", HttpStatus.CREATED);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Pagination<HospitalDTO> getAllHospital(String search, String sort, int pageNumber, int pageSize) {
        if (search.length() == 0) {
            Page<Hospital> page = hospitalRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<HospitalDTO> list = page.getContent().stream().map(hospitalMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        } else {
            Page<Hospital> page = hospitalRepository.findAllByNameContainingIgnoreCase(search,
                    PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<HospitalDTO> list = page.getContent().stream().map(hospitalMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        }
    }

    public ResponseEntity<String> delete(UUID fromString) {
        try {
            Hospital hospital = hospitalRepository.findById(fromString).orElseThrow();
            hospitalRepository.delete(hospital);
            return new ResponseEntity<>(hospital.getName() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid hospital", HttpStatus.BAD_REQUEST);
        }
    }

    public HospitalDTO findHospitalById(String hospitalId) {
        Hospital t = hospitalRepository.findById(UUID.fromString(hospitalId)).orElse(null);
        return new HospitalDTO(t.getId().toString(), t.getName(),
                "data:image/png;base64," + Base64.getEncoder().encodeToString(t.getLogo()),
                t.getLocation().getLocation().getName(), t.getLocation().getName(), t.getName());
    }
}
