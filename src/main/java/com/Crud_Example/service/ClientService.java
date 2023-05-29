package com.Crud_Example.service;

import com.Crud_Example.entity.Client;
import com.Crud_Example.payload.ClientDto;
import com.Crud_Example.payload.ClientResponse;

import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto clientDto);

    ClientResponse getClient(int pageNo, int pageSize, String sortBy, String sortDir);

    ClientDto getOneClient(String id);

    ClientDto updateClient(ClientDto clientDto, String id);

    void deleteClient(String id);

    ClientDto getClientByEmail(String email);
}
