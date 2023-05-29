package com.Crud_Example.service;

import com.Crud_Example.entity.Client;
import com.Crud_Example.exception.ResourceNotFoundException;
import com.Crud_Example.payload.ClientDto;
import com.Crud_Example.payload.ClientResponse;
import com.Crud_Example.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {


    ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {


        Client client = mapToEntity(clientDto);
        Client saveClient = clientRepository.save(client);
        return mapToDto(saveClient);
    }

    @Override
    public ClientResponse getClient(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Client> all = clientRepository.findAll(pageable);
        List<Client> content = all.getContent();
        List<ClientDto> clientDtos = content.stream().map(client -> mapToDto(client)).collect(Collectors.toList());

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setContent(clientDtos);
        clientResponse.setPageNo(all.getNumber());
        clientResponse.setPageSize(all.getSize());
        clientResponse.setTotalElements(all.getTotalElements());
        clientResponse.setTotalPages(all.getTotalPages());
        clientResponse.setLast(all.isLast());
        return clientResponse;
    }

    @Override
    public ClientDto getOneClient(String id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        return mapToDto(client);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, String id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clients", "id", id));

        client.setId(clientDto.getId());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setCity(clientDto.getCity());
        client.setEmail(clientDto.getEmail());
        client.setMobile(clientDto.getMobile());
        Client updatedClient = clientRepository.save(client);
       return mapToDto(updatedClient);
    }

    @Override
    public void deleteClient(String id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client", "id", id)
        );
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto getClientByEmail(String email) {
        Boolean existsByEmail = clientRepository.existsByEmail(email);
        if (existsByEmail) {
            Client clientByEmail = clientRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Client", "email", email));
            return mapToDto(clientByEmail);
        } else {
            throw new ResourceNotFoundException("Email", "email", email);
        }
    }

    private ClientDto mapToDto(Client saveClient) {
        ClientDto dto= ClientDto.builder()
                .id(saveClient.getId())
                .firstName(saveClient.getFirstName())
                .lastName(saveClient.getLastName())
                .city(saveClient.getCity())
                .email(saveClient.getEmail())
                .mobile(saveClient.getMobile())
                .build();
//        dto.setId(saveClient.getId());
//        dto.setFirstName(saveClient.getFirstName());
//        dto.setLastName(saveClient.getLastName());
//        dto.setCity(saveClient.getCity());
//        dto.setEmail(saveClient.getEmail());
//        dto.setMobile(saveClient.getMobile());
        return dto;

    }

    private Client mapToEntity(ClientDto clientDto) {
        Client client= Client.builder()
                .id(clientDto.getId())
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .city(clientDto.getCity())
                .email(clientDto.getEmail())
                .mobile(clientDto.getMobile())
                .build();
//        client.setId(clientDto.getId());
//        client.setFirstName(clientDto.getFirstName());
//        client.setLastName(clientDto.getLastName());
//        client.setCity(clientDto.getCity());
//        client.setEmail(clientDto.getEmail());
//        client.setMobile(clientDto.getMobile());
        return client;
    }
}
