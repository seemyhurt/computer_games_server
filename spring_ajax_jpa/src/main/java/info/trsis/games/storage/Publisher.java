package info.trsis.games.storage;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PUBLISHER")
public class Publisher implements Serializable 
{
    public Publisher(
        String name, 
        String county)
    {
        setName(name);
        setCounty(county);
    }

    @Id
    @Column(name = "PUBLISHER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PUBLISHER_NAME")
    private String name;

    @Column(name = "COUNTRY")
    private String country;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
        
    public String getCounty() { return country; }
    public void setCounty(String country) { this.country = country; }
}

