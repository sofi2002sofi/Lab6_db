package com.didula.controller.implementation;

import com.didula.controller.ControllerWithDTO;
import com.didula.domain.MessageEntity;
import com.didula.dto.MessageEntityDTO;
import com.didula.service.implementation.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class MessageController implements ControllerWithDTO<MessageEntityDTO, MessageEntity> {
  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping(value = "/messages")
  public ResponseEntity<List<MessageEntityDTO>> getMessages() {
    List<MessageEntity> messageEntities = messageService.getAll();
    List<MessageEntityDTO> messageEntityDTOs = createDTOs(messageEntities);
    return new ResponseEntity<>(messageEntityDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/messages/{messageId}")
  public ResponseEntity<MessageEntityDTO> getMessage(@PathVariable Integer messageId) {
    MessageEntity messageEntity = messageService.getById(messageId);
    MessageEntityDTO messageEntityDTO = createDTO(messageEntity);
    return new ResponseEntity<>(messageEntityDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/messages")
  public ResponseEntity<MessageEntityDTO> createMessage(@RequestBody MessageEntity messageEntity) {
    MessageEntity newMessage = messageService.create(messageEntity);
    MessageEntityDTO messageEntityDTO = createDTO(newMessage);
    return new ResponseEntity<>(messageEntityDTO, HttpStatus.CREATED);
  }

  @PutMapping(value = "/messages/{messageId}")
  public ResponseEntity<MessageEntityDTO> updateMessage(@RequestBody MessageEntity messageEntity, @PathVariable Integer messageId) {
    MessageEntity newMessage = messageService.update(messageEntity, messageId);
    MessageEntityDTO messageEntityDTO = createDTO(newMessage);
    return new ResponseEntity<>(messageEntityDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/messages/{messageId}")
  public ResponseEntity<Void> deleteMessage(@PathVariable Integer messageId) {
    messageService.delete(messageId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<MessageEntityDTO> createDTOs(Iterable<MessageEntity> messageEntities) {
    List<MessageEntityDTO> messageEntityDTOs = new ArrayList<>();
    for (MessageEntity messageEntity : messageEntities) {
      MessageEntityDTO messageEntityDTO = new MessageEntityDTO(messageEntity);
      messageEntityDTOs.add(messageEntityDTO);
    }
    return messageEntityDTOs;
  }

  @GetMapping(value = "/messages/coursesInfo/{courseInfoId}")
  public ResponseEntity<List<MessageEntityDTO>> getTestByCourseInfoId(
          @PathVariable Integer courseInfoId) {
    Set<MessageEntity> messageEntities =
            messageService.getMessageByCourseInfoId(courseInfoId);
    List<MessageEntityDTO> messageEntityDTOs = createDTOs(messageEntities);

    return new ResponseEntity<>(messageEntityDTOs, HttpStatus.OK);
  }

  @Override
  public MessageEntityDTO createDTO(MessageEntity messageEntity) {
    return new MessageEntityDTO(messageEntity);
  }
}
