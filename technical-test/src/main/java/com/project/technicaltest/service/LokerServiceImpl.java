package com.project.technicaltest.service;

import com.project.technicaltest.dto.LokerRequest;
import com.project.technicaltest.dto.LokerResponse;
import com.project.technicaltest.dto.LokerUpdateRequest;
import com.project.technicaltest.model.Loker;
import com.project.technicaltest.model.Users;
import com.project.technicaltest.repository.LokerRepository;
import com.project.technicaltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LokerServiceImpl implements LokerService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LokerRepository lokerRepository;

    @Override
    public LokerResponse create(LokerRequest request) {
        Optional<Users> users = userRepository.findById(request.getUserId());
        if(users.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }

        if(users.get().getCounter() < 2) {
            users.get().setCounter(1);
            userRepository.save(users.get());
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "password expired");
        }

        Loker loker = new Loker();
        loker.setJumlahLoker(request.getJumlahLoker());
        loker.setLamaPeminjaman(request.getLamaPeminjaman());
        loker.setDeposit((float) (10000 * request.getLamaPeminjaman()));
        loker.setDenda(denda(request.getLamaPeminjaman()));
        loker.setUsers(users.get());
        lokerRepository.save(loker);

        return LokerResponse.builder()
                .denda(loker.getDenda())
                .lamaPeminjaman(loker.getLamaPeminjaman())
                .jumlahLoker(loker.getJumlahLoker())
                .deposit(loker.getDeposit())
                .build();
    }

    @Override
    public LokerResponse update(LokerUpdateRequest request) {
        Optional<Loker> loker = lokerRepository.findById(request.getId());
        if(loker.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "loker not found");
        }

        if(loker.get().getUsers().getCounter() > 2){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "password expired");
        }

        Optional<Users> users = userRepository.findById(loker.get().getId());
        if(users.isPresent()){
            int counter = users.get().getCounter();
            users.get().setCounter( counter+=1);
            userRepository.save(users.get());
        }

        return LokerResponse.builder()
                .deposit(loker.get().getDeposit())
                .jumlahLoker(loker.get().getJumlahLoker())
                .lamaPeminjaman(loker.get().getLamaPeminjaman())
                .denda(loker.get().getDenda())
                .build();
    }

    private float denda(int lamaPeminjaman){
        if(lamaPeminjaman == 1){
            return 0;
        } else {
            float denda = 0;
            for(int i=1; i < lamaPeminjaman; i++){
                denda += 5000;
            }
            return denda;
        }
    }
}
