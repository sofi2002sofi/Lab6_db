package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.UserEntity;
import com.didula.dto.UserDTO;
import com.didula.service.implementation.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class UserController implements ControllerWithDTO<UserDTO, UserEntity> {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/users")
  public ResponseEntity<List<UserDTO>> getUsers() {
    List<UserEntity> userEntities = userService.getAll();
    List<UserDTO> userDTOs = createDTOs(userEntities);
    return new ResponseEntity<>(userDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/users/{userId}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
    UserEntity userEntity = userService.getById(userId);
    UserDTO userDTO = createDTO(userEntity);
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/users")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserEntity userEntity) {
    UserEntity newUser = userService.create(userEntity);
    UserDTO userDTO = createDTO(newUser);
    return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/users/{userId}")
  public ResponseEntity<UserDTO> updateUser(@RequestBody UserEntity userEntity, @PathVariable Integer userId) {
    UserEntity newUser = userService.update(userEntity, userId);
    UserDTO userDTO = createDTO(newUser);
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/users/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
    userService.delete(userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<UserDTO> createDTOs(Iterable<UserEntity> userEntities) {
    List<UserDTO> userDTOs = new ArrayList<>();
    for (UserEntity userEntity : userEntities) {
      UserDTO userDTO = new UserDTO(userEntity);
      userDTOs.add(userDTO);
    }
    return userDTOs;
  }

  @GetMapping(value = "/users/genders/{genderId}")
  public ResponseEntity<List<UserDTO>> getUserByGenderId(
          @PathVariable Integer genderId) {
    Set<UserEntity> userEntities =
            userService.getUserByGenderId(genderId);
    List<UserDTO> userDTOs = createDTOs(userEntities);

    return new ResponseEntity<>(userDTOs, HttpStatus.OK);
  }

  @Override
  public UserDTO createDTO(UserEntity userEntity) {
    return new UserDTO(userEntity);
  }
}
