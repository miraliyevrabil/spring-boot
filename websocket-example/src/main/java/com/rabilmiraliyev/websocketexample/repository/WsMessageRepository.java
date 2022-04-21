package com.rabilmiraliyev.websocketexample.repository;

import com.rabilmiraliyev.websocketexample.model.WsMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WsMessageRepository extends JpaRepository<WsMessage,Long> {

}
