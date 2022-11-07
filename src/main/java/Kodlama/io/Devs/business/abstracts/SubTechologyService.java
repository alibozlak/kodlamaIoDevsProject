package Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Devs.business.requests.CreateSubTechnologyRequest;
import Kodlama.io.Devs.business.requests.CreateSubTechnologyRequestWithoutProgrammingLanguageName;
import Kodlama.io.Devs.business.responses.GetAllSubTechnologyResponse;

public interface SubTechologyService {
    void add(CreateSubTechnologyRequest subTechnologyRequest) throws Exception;
    void delete(int subTechnologyId) throws Exception;
    void update(CreateSubTechnologyRequestWithoutProgrammingLanguageName subTechnologyRequestWithoutProgrammingLanguageName) throws Exception;
    List<GetAllSubTechnologyResponse> getAll();
}
