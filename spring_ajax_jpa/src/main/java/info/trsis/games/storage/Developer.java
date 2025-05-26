package info.trsis.games.storage;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "DEVELOPER")
public class Developer implements Serializable 
{ 
    public Developer(
        String name, 
        String county, 
        LocalDate foundedDate)
    {
        setName(name);
        setCounty(county);
        setFoundedDate(foundedDate);
    }

    @Id
    @Column(name = "DEVELOPER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DEVELOPER_NAME")
    private String name;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "FOUNDED_DATE")
    private LocalDate foundedDate;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
        
    public String getCounty() { return country; }
    public void setCounty(String country) { this.country = country; }

    public LocalDate getFoundedDate() { return foundedDate; }
    public void setFoundedDate(LocalDate foundedDate) { this.foundedDate = foundedDate; }
}
