package com.github.didkovskiy.rest_app_demo.controller;

import com.github.didkovskiy.rest_app_demo.exception.NotFoundException;
import com.github.didkovskiy.rest_app_demo.repository.MessageRepository;
import com.github.didkovskiy.rest_app_demo.repository.entity.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    private final MessageRepository messageRepository;

    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @PutMapping
    public Message add(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @PatchMapping("{id}")
    public Message edit(@RequestBody Message message) {
        Optional<Message> messageFromDB = messageRepository.findById(message.getId());
        if (messageFromDB.isPresent()) {
            Message messageOld = messageFromDB.get();
            messageOld.setText(message.getText());
            return messageRepository.save(message);
        } else throw new NotFoundException();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        Message message = messageRepository.getById(id);
        messageRepository.delete(message);
    }

}
