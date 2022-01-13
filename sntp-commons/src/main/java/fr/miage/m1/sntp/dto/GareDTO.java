package fr.miage.m1.sntp.dto;

public class GareDTO {
    private Long id;
    private String nomGare;

    public GareDTO() {

    }

    public GareDTO(Long id, String nomGare) {
        this.id = id;
        this.nomGare = nomGare;
    }

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
