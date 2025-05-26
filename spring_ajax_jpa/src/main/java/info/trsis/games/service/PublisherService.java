package info.trsis.games.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.trsis.games.storage.Publisher;
import info.trsis.games.model.PublisherDTO;
import info.trsis.games.repository.PublisherRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherService 
{
    private final ObjectMapper objectMapper;
    private final PublisherRepository publisherRepository;

    public List<PublisherDTO> listAll() 
    {
        return publisherRepository.listAll().stream()
            .map(game -> objectMapper.convertValue(game, PublisherDTO.class))
            .collect(Collectors.toList());

    }

    public void delete(Integer id) 
    {
        publisherRepository.deleteById(id);
    }

    public PublisherDTO add(        
        String name, 
        String county) 
    {
        return objectMapper.convertValue(
            publisherRepository.save(
                new Publisher(name, county)), 
                PublisherDTO.class
        );
    }

    public PublisherDTO findById(Integer id) 
    {
        var pub = publisherRepository.findById(id);
        return pub.map(p -> objectMapper.convertValue(p, PublisherDTO.class)).orElse(null);
    }
}
