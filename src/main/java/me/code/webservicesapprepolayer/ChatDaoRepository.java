package me.code.webservicesapprepolayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatDaoRepository extends JpaRepository<Message, Integer> {

    Optional<Message> findByContent(String content);

}
