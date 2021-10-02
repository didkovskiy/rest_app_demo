package com.github.didkovskiy.rest_app_demo.repository;

import com.github.didkovskiy.rest_app_demo.repository.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
