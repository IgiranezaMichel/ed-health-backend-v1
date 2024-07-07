package com.ed_health_backend_v1.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {
private List<T> content;
private int pageNumber;
private int pageSize;
}
