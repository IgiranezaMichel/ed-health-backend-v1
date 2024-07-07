package com.ed_health_backend_v1.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenceDTO {
    private String id;
    private String licenceYear;
    private Month month;
    private String deadline;
    private String timeStamp;
    public LicenceDTO(UUID id, String licenceYear, Month month, LocalDate deadline, LocalDateTime timeStamp) {
        DateTimeFormatter deadlineFormatter=DateTimeFormatter.ofPattern("dd-MMMM-yyy");
        DateTimeFormatter timeStampFormatter=DateTimeFormatter.ofPattern("dd-MMMM-yyy MM:ss a");
        this.id = id.toString();
        this.licenceYear = licenceYear;
        this.month = month;
        this.deadline = deadlineFormatter.format(deadline);
        this.timeStamp =timeStampFormatter.format(timeStamp);
    }
}
