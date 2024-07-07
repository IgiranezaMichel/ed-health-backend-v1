package com.ed_health_backend_v1.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ed_health_backend_v1.dto.LicenceDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.mapper.LicenceMapper;
import com.ed_health_backend_v1.modal.Licence;

@Service
public class LicenceServices {
    @Autowired
    private com.ed_health_backend_v1.repository.LicenceRepository licenceRepository;
    private LicenceMapper licenceMapper=new LicenceMapper();
    public ResponseEntity<String> create(LicenceDTO licenceDTO) {
        if (licenceDTO.getLicenceYear().length() == 0)
            return new ResponseEntity<>("Licence year is required", HttpStatus.BAD_REQUEST);
        else if (licenceDTO.getDeadline().length() == 0)
            return new ResponseEntity<>("Deadline is required", HttpStatus.BAD_REQUEST);
        else {
            Licence licence = new Licence(licenceDTO.getLicenceYear(), licenceDTO.getMonth(),
                    LocalDate.parse(licenceDTO.getDeadline()), LocalDateTime.now());
            Licence savedLicence = licenceRepository.save(licence);
            return new ResponseEntity<>(
                    savedLicence.getLicenceYear() + " cohort " + savedLicence.getCohortMonth() + " added successful",
                    HttpStatus.CREATED);
        }
    }

    public ResponseEntity<String> update(LicenceDTO licenceDTO) {
        if (licenceDTO.getLicenceYear().length() == 0)
        return new ResponseEntity<>("Licence year is required", HttpStatus.BAD_REQUEST);
    else if (licenceDTO.getDeadline().length() == 0)
        return new ResponseEntity<>("Deadline is required", HttpStatus.BAD_REQUEST);
    else {
        Licence licence = new Licence(UUID.fromString(licenceDTO.getId()),licenceDTO.getLicenceYear(), licenceDTO.getMonth(),
                LocalDate.parse(licenceDTO.getDeadline()), LocalDateTime.now());
        Licence savedLicence = licenceRepository.save(licence);
        return new ResponseEntity<>(
                savedLicence.getLicenceYear() + " cohort " + savedLicence.getCohortMonth() + " updated successful",
                HttpStatus.CREATED);
    }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Pagination<LicenceDTO> getAllLicence(String search, String sort, int pageNumber, int pageSize) {
         if (search.length() == 0) {
            Page<Licence> page = licenceRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<LicenceDTO> list = page.getContent().stream().map(licenceMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        } else {
            Page<Licence> page = licenceRepository.findAllByLicenceYearContainingIgnoreCase(search,
                    PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<LicenceDTO> list = page.getContent().stream().map(licenceMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        }
    }

    public ResponseEntity<String> delete(String id) {
      try {
        Licence licence=licenceRepository.findById(UUID.fromString(id)).orElseThrow();
        licenceRepository.delete(licence);
        return new ResponseEntity<>(licence.getLicenceYear()+" removed successful",HttpStatus.OK);
    }
     catch (Exception e) {
        return new ResponseEntity<>("Wrong licence try  again",HttpStatus.BAD_REQUEST);
      }
    }
    public LicenceDTO findLicenceById(String id) {
      try {
        Licence licence=licenceRepository.findById(UUID.fromString(id)).orElseThrow();
        return new LicenceDTO(licence.getId(),licence.getLicenceYear(),licence.getCohortMonth(),licence.getDeadline(),licence.getTimeStamp());
      } catch (Exception e) {
        return null;
      }
    }
}
