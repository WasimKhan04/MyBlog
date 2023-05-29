package com.Crud_Example.controller;

import com.Crud_Example.payload.ClientDto;
import com.Crud_Example.payload.ClientResponse;
import com.Crud_Example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@Valid @RequestBody ClientDto clientDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Object>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
        ClientDto saveClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(saveClient,HttpStatus.CREATED);
    }
    //localhost:9091/api/clients?pageNo=0&pageSize=10&sortBy=id&sortDir=asc
    @GetMapping
    public ClientResponse getAllClient(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir){

       return  clientService.getClient(pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<ClientDto> getClientByEmail(@PathVariable("email") String email){
        ClientDto clientByEmail = clientService.getClientByEmail(email);
        return new ResponseEntity<>(clientByEmail, HttpStatus.OK);
    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<ClientDto> getOneClient(@PathVariable("id") String id){
        ClientDto oneClient = clientService.getOneClient(id);
        return new ResponseEntity<>(oneClient, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateOneClient(@RequestBody ClientDto clientDto, @PathVariable("id") String id){
        ClientDto updatedClient = clientService.updateClient(clientDto, id);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") String id){
        clientService.deleteClient(id);
        return new ResponseEntity<>("Client Details Deleted successfully", HttpStatus.OK);
    }
}
