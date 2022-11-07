package Kodlama.io.Devs.business.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubTechnologyRequest {
    private String name;
    private int programmingLanguageId;
}
