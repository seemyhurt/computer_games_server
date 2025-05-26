package info.trsis.games.rest;

import java.util.List;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import info.trsis.games.model.GameDTO;
import info.trsis.games.service.GameService;
import info.trsis.games.model.OperationStatusDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GameRestController 
{
    private final GameService gameService;

    @Operation(summary = "Get all games", description = "Get all games", tags={ "Games" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "List of all games", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameDTO.class)))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/games", produces = { "application/json" }, method = RequestMethod.GET)

    ResponseEntity<?> gamesGet()
    {
        try {
            List<GameDTO> games = gameService.listAll();
            return ResponseEntity.ok(games);
        // } catch (AuthenticationException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.UNAUTHORIZED)
        //         .body(new OperationStatusDTO("Authentication required"));
        // } catch (AccessDeniedException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.FORBIDDEN)
        //         .body(new OperationStatusDTO("Access denied"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new OperationStatusDTO("Failed to get games, error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Delete a game", description = "Delete a game by id", tags={ "Games" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Game deleted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/games/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
    
    ResponseEntity<OperationStatusDTO> gamesIdDelete(@Parameter(in = ParameterIn.PATH, description = "Game id", required=true, schema=@Schema()) @PathVariable("id") Integer id)
    {
        try {
            if (id == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid game id"));

            gameService.delete(id);
            return ResponseEntity.ok(new OperationStatusDTO("Game deleted successfully"));
        // } catch (AuthenticationException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.UNAUTHORIZED)
        //         .body(new OperationStatusDTO("Authentication required"));
        // } catch (AccessDeniedException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.FORBIDDEN)
        //         .body(new OperationStatusDTO("Access denied"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new OperationStatusDTO("Failed to delele game, error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Get a game by ID", description = "Get a game by ID", tags={ "Games" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Game finded successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/games/{id}", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<?> gamesIdGet(@Parameter(in = ParameterIn.PATH, description = "Game id", required=true, schema=@Schema()) @PathVariable("id") Integer id)
    {
        try {
            if (id == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid game id"));

            GameDTO game = gameService.findById(id);
            if (game == null)
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new OperationStatusDTO("Game not found"));

            return ResponseEntity.ok(game);
        // } catch (AuthenticationException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.UNAUTHORIZED)
        //         .body(new OperationStatusDTO("Authentication required"));
        // } catch (AccessDeniedException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.FORBIDDEN)
        //         .body(new OperationStatusDTO("Access denied"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new OperationStatusDTO("Failed to find game, error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Create a new game", description = "Create a new game", tags={ "Games" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Game created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) })
    @RequestMapping(value = "/games", produces = { "application/json" }, consumes = { "application/x-www-form-urlencoded" }, method = RequestMethod.POST)
    ResponseEntity<OperationStatusDTO> gamesPost(
        @Parameter(in = ParameterIn.DEFAULT, description = "Game title", required=true,schema=@Schema()) @RequestParam(value="title", required=true)  String title, 
        @Parameter(in = ParameterIn.DEFAULT, description = "Game developer Id", required=true,schema=@Schema()) @RequestParam(value="developer", required=true)  Integer developer, 
        @Parameter(in = ParameterIn.DEFAULT, description = "Game publisher Id", required=true,schema=@Schema()) @RequestParam(value="publisher", required=true)  Integer publisher, 
        @Parameter(in = ParameterIn.DEFAULT, description = "Game price", required=true,schema=@Schema()) @RequestParam(value="price", required=true)  Double price, 
        @Parameter(in = ParameterIn.DEFAULT, description = "Game release date", required=true,schema=@Schema()) @RequestParam(value="releaseDate", required=true)  LocalDate releaseDate)
    {
        try {
            GameDTO game = gameService.add(title, price, releaseDate, developer, publisher);
            if (game == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid input"));

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new OperationStatusDTO("Game created successfully"));
        // } catch (AuthenticationException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.UNAUTHORIZED)
        //         .body(new OperationStatusDTO("Authentication required"));
        // } catch (AccessDeniedException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.FORBIDDEN)
        //         .body(new OperationStatusDTO("Access denied"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new OperationStatusDTO("Failed to create game, error: " + e.getMessage()));
        }
    }
}
