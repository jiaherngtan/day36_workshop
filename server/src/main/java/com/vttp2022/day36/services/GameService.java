package com.vttp2022.day36.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vttp2022.day36.models.Comment;
import com.vttp2022.day36.models.Game;
import com.vttp2022.day36.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> listGame() {
        return gameRepository.listGame();
    }

    public Optional<List<Comment>> getCommentByGid(Integer gid) {
        return gameRepository.getCommentByGid(gid);
    }

}
