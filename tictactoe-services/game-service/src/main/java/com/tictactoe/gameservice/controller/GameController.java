package com.tictactoe.gameservice.controller;

import com.tictactoe.domain.Game;
import com.tictactoe.gameservice.service.GameService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.QueryParam;

/**
 * User: aleksey
 * Date: 28/11/2018
 * Time: 08:49
 */
@RestController
@RequestMapping("v1/games")
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("")
  public Flux<Game> getGames() {
    return gameService.getAllGames();
  }

  @GetMapping("long")
  public Flux<Game> getGamesLong() {
    return gameService.getAllGamesLong();
  }

  @PostMapping("{userId}/{opponentId}")
  public Mono<?> createGame(
      @PathVariable("userId") String userId,
      @PathVariable("opponentId") String opponentId,
      @QueryParam("black") Boolean black
  ) {
    return gameService.createGame(userId, opponentId, black);
  }
}