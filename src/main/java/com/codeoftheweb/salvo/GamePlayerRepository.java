package com.codeoftheweb.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Integer>{

        List<GamePlayer> findAllById(Integer id);
        List<GamePlayer> findAllByCreationDate(String creationDate);

        }
