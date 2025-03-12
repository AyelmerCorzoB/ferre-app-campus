package com.ferreappcampus.domain.repositories;

import java.util.List;

import com.ferreappcampus.domain.entities.Eps;

public interface EpsRepository {

    void save(Eps eps);
    Eps findById(int id);
    List<Eps> findAl();
    void update(Eps eps);
    void delete(int id);

}
