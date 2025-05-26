package info.trsis.games.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import info.trsis.games.storage.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Integer> 
{
    default List<Publisher> listAll()
    {
        return StreamSupport.stream(findAll().spliterator(), false)
            .collect(Collectors.toList());
    }
}
