package Kodlama.io.Devs.business.responses;

import Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSubTechnologyResponse {
    private int id;
    private String name;
    private ProgrammingLanguage programmingLanguage;
}
