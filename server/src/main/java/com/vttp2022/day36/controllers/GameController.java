package com.vttp2022.day36.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vttp2022.day36.models.Comment;
import com.vttp2022.day36.services.GameService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@Controller
@RequestMapping(path = "/api")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping(path = "/games")
    @ResponseBody
    public ResponseEntity<String> getGames() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        gameService.listGame().stream()
                .forEach(v -> {
                    arrBuilder.add(v.toGameSummary());
                });
        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    @GetMapping(path = "/game/{gid}")
    @ResponseBody
    public ResponseEntity<String> getGameByGid(@PathVariable Integer gid) {
        Optional<List<Comment>> opt = gameService.getCommentByGid(gid);
        if (opt.isEmpty())
            return ResponseEntity
                    .status(404)
                    .body(
                            Json.createObjectBuilder()
                                    .add("message", "Cannot find comments for game id %s".formatted(gid))
                                    .build().toString());
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        opt.get().stream()
                .forEach(v -> {
                    arrBuilder.add(v.toCommentSummary());
                });
        return ResponseEntity.ok(arrBuilder.build().toString());
    }

}
