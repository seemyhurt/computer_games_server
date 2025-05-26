package info.trsis.games.service;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.trsis.games.storage.Game;
import info.trsis.games.model.GameDTO;
import info.trsis.games.repository.GameRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService 
{    
    private final ObjectMapper objectMapper;
    private final GameRepository gameRepository;

    public List<GameDTO> listAll() 
    {
        return gameRepository.listAll().stream()
            .map(game -> objectMapper.convertValue(game, GameDTO.class))
            .collect(Collectors.toList());

    }

    public void delete(Integer id) 
    {
        gameRepository.deleteById(id);
    }

    public GameDTO add(        
        String title, 
        double price, 
        LocalDate releaseDate, 
        Integer developer, 
        Integer publisher) 
    {
        return objectMapper.convertValue(
            gameRepository.save(
                new Game(title, price, releaseDate, developer, publisher)), 
                GameDTO.class
        );
    }

    public GameDTO findById(Integer id) 
    {
        var game = gameRepository.findById(id);
        return game.map(g -> objectMapper.convertValue(g, GameDTO.class)).orElse(null);
    }
}
