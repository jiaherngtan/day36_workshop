package com.vttp2022.day36.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {

    private Integer gid;
    private String name;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game [gid=" + gid + ", name=" + name + "]";
    }

    public static Game create(Document d) {
        Game game = new Game();
        game.setGid(d.getInteger("gid"));
        game.setName(d.getString("name"));
        return game;
    }

    public JsonObject toGameSummary() {
        return Json.createObjectBuilder()
                .add("gid", gid)
                .add("name", name)
                .build();
    }

}
