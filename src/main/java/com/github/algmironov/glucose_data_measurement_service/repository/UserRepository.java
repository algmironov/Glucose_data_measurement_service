package com.github.algmironov.glucose_data_measurement_service.repository;

import com.github.algmironov.glucose_data_measurement_service.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository(value = "users")
public interface UserRepository extends JpaRepository<User, Long> {

//    boolean exists(User user);

    @NotNull
    List<User> findAll();
}
