package br.com.pipa.userscorepointscontrol.controller;

import br.com.pipa.userscorepointscontrol.exception.DataNotFoundException;
import br.com.pipa.userscorepointscontrol.model.HighScoreListModel;
import br.com.pipa.userscorepointscontrol.model.UserPointRequest;
import br.com.pipa.userscorepointscontrol.model.UserScore;
import br.com.pipa.userscorepointscontrol.service.UserScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(value = "Rest API para controlar Score Points de Usuarios", tags = {"Scores"})
public class ScoreController {

    private final UserScoreService scoreService;

    @PostMapping("/score")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
        }
    )
    public ResponseEntity<Void> inputScore(@Valid @RequestBody UserPointRequest userPointRequest){
        scoreService.addUserPoint(userPointRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}/position")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    }
    )
    public ResponseEntity<UserScore> getPosition(@PathVariable("userId") Integer inputUserId) throws DataNotFoundException {
        return ResponseEntity.ok(scoreService.getUserPosition(inputUserId));
    }

    @GetMapping("/highscorelist")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    }
    )
    public ResponseEntity<HighScoreListModel> getScoreList() throws DataNotFoundException {
        return ResponseEntity.ok(scoreService.getScoreListModel());
    }

}
