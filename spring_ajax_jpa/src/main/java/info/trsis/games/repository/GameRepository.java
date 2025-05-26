package info.trsis.games.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import info.trsis.games.storage.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> 
{    
    default List<Game> listAll()
    {
        return StreamSupport.stream(findAll().spliterator(), false)
            .collect(Collectors.toList());
    }
}
