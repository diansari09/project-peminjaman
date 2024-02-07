package com.project.technicaltest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LokerUpdateRequest {

    private  Long id;

    private Integer jumlahLoker;

    private Integer lamaPeminjaman;

    private Long userId;

}
