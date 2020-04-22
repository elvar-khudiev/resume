package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceInter userService;

    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "nid", required = false) Integer nid
    ) {
        List<User> users = userService.getAll(name, surname, nid);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/foo")
    public ResponseEntity<ResponseDTO> getFoo(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "nid", required = false) Integer nid
    ) {
        List<User> users = userService.getAll(name, surname, nid);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUser(
            @PathVariable("id") int id) {
        User user = userService.getById(id);

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
    }

    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> addUser (@RequestBody UserDTO userDTO) {

        User user = new User();

        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());

        userService.add(user);

        UserDTO resultDTO = new UserDTO();

        resultDTO.setId(userDTO.getId());
        resultDTO.setName(userDTO.getName());
        resultDTO.setSurname(userDTO.getSurname());

        return ResponseEntity.ok(ResponseDTO.of(resultDTO, "Successfully added"));
    }

    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(
            @PathVariable("id") int id) {

        User user = userService.getById(id);
        userService.delete(id);

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "Successfully deleted"));
    }
}
