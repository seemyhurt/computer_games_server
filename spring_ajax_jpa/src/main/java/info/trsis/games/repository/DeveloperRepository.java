package info.trsis.games.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import info.trsis.games.storage.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer, Integer> 
{
    default List<Developer> listAll()
    {
        return StreamSupport.stream(findAll().spliterator(), false)
                    .collect(Collectors.toList());
    }
}
