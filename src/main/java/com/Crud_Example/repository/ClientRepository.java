package com.Crud_Example.repository;

import com.Crud_Example.entity.Client;
import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClientRepository extends ArangoRepository<Client, String> {

    Optional<Client> findByEmail(String email);


    Boolean existsByEmail(String email);



}
