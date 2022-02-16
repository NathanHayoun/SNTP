package fr.miage.m1.sntp.dto;

/**
 * @author Nathan Hayoun
 */
public class GareDTO {
    private Long id;
    private String nomGare;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomGare() {
        return nomGare;
    }

    public void setNomGare(String nomGare) {
        this.nomGare = nomGare;
    }

    @Override
    public String toString() {
        return "GareDTO{" +
                "id=" + id +
                ", nomGare='" + nomGare + '\'' +
                '}';
    }
}
