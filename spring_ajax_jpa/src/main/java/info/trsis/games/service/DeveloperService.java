package info.trsis.games.service;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.trsis.games.storage.Developer;
import info.trsis.games.model.DeveloperDTO;
import info.trsis.games.repository.DeveloperRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeveloperService 
{
    private final ObjectMapper objectMapper;
    private final DeveloperRepository developerRepository;

    public List<DeveloperDTO> listAll() 
    {
        return developerRepository.listAll().stream()
            .map(game -> objectMapper.convertValue(game, DeveloperDTO.class))
            .collect(Collectors.toList());

    }

    public void delete(Integer id) 
    {
        developerRepository.deleteById(id);
    }

    public DeveloperDTO add(        
        String name, 
        String county, 
        LocalDate foundedDate) 
    {
        return objectMapper.convertValue(
            developerRepository.save(
                new Developer(name, county, foundedDate)), 
                DeveloperDTO.class
        );
    }

    public DeveloperDTO findById(Integer id) 
    {
        var dev = developerRepository.findById(id);
        return dev.map(d -> objectMapper.convertValue(d, DeveloperDTO.class)).orElse(null);
    }    
}
