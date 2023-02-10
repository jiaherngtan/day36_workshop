package com.vttp2022.day36.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.vttp2022.day36.models.Comment;
import com.vttp2022.day36.models.Game;

@Repository
public class GameRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> listGame() {

        Criteria c = new Criteria();

        Query q = Query.query(c).limit(30);

        return mongoTemplate.find(q, Document.class, "game")
                .stream()
                .map(d -> Game.create(d))
                .toList();
    }

    public Optional<List<Comment>> getCommentByGid(Integer gid) {

        Criteria c = Criteria.where("gid").is(gid);

        Query q = Query.query(c);

        return Optional.of(mongoTemplate.find(q, Document.class, "comment")
                .stream()
                .map(d -> Comment.create(d))
                .toList());
    }

}
