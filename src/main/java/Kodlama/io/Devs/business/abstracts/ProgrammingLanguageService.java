package Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Devs.business.requests.CreateProgrammingLanguageRequest;
import Kodlama.io.Devs.business.responses.GetAllProgrammingLanguagesResponse;
import Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;

public interface ProgrammingLanguageService {
    void add(CreateProgrammingLanguageRequest programmingLanguageRequest) throws Exception;
    void update(ProgrammingLanguage programmingLanguage) throws Exception;
    void delete(int programmingLanguageId) throws Exception;
    List<GetAllProgrammingLanguagesResponse> getAll();
    GetAllProgrammingLanguagesResponse getById(int programmingLanguageId) throws Exception;
}
