package com.project.technicaltest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loker")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Loker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer jumlahLoker;

    private Integer lamaPeminjaman;

    private Float deposit;

    private Float denda;

    private Float bayarDenda;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
}
