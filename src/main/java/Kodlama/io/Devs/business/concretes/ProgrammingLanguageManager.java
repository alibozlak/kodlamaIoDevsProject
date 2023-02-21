package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import Kodlama.io.Devs.business.responses.GetByIdProgrammingLanguageResponse;
import Kodlama.io.Devs.core.mappers.modelMapper.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.ProgrammingLanguageService;
import Kodlama.io.Devs.business.requests.programmingLanguage.CreateProgrammingLanguageRequest;
import Kodlama.io.Devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import Kodlama.io.Devs.business.responses.GetAllProgrammingLanguagesResponse;
import Kodlama.io.Devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;

@Service
@AllArgsConstructor
public class ProgrammingLanguageManager implements ProgrammingLanguageService {

    private ProgrammingLanguageRepository programmingLanguageRepository;
    private ModelMapperService modelMapperService;

    private boolean isNameEmpty(ProgrammingLanguage programmingLanguage) {
        if (programmingLanguage.getName().isBlank() || programmingLanguage.getName().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isNameRepeat(ProgrammingLanguage programmingLanguage) {
        List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepository.findAll();
        for (ProgrammingLanguage _programmingLanguage : programmingLanguages) {
            if (programmingLanguage.getName().equalsIgnoreCase(_programmingLanguage.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isValid(ProgrammingLanguage programmingLanguage) throws Exception {
        if (isNameEmpty(programmingLanguage)) {
            throw new Exception("Programlama Dili ismi boş olamaz!");
        }
        if (isNameRepeat(programmingLanguage)) {
            throw new Exception(programmingLanguage.getName() + " isminde bir programlama dili var!");
        }
        return true;
    }

    private boolean isExistId(int programmingLanguageId) throws Exception {
        if (this.programmingLanguageRepository.existsById(programmingLanguageId)) {
            return true;
        }
        throw new Exception(programmingLanguageId + " IDli bir programlama dili yok!");
    }

    @Override
    public void add(CreateProgrammingLanguageRequest programmingLanguageRequest) throws Exception {
        ProgrammingLanguage programmingLanguage = this.modelMapperService.forRequest()
                .map(programmingLanguageRequest,ProgrammingLanguage.class);

        if (this.isValid(programmingLanguage)) {
            this.programmingLanguageRepository.save(programmingLanguage);
        }
    }

    @Override
    public void update(UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception {
        ProgrammingLanguage programmingLanguage = this.modelMapperService.forRequest()
                .map(updateProgrammingLanguageRequest,ProgrammingLanguage.class);
        
        if (this.isValid(programmingLanguage) && this.isExistId(programmingLanguage.getId())) {
            this.programmingLanguageRepository.save(programmingLanguage);
        }
    }

    @Override
    public void delete(int programmingLanguageId) throws Exception {
        if (this.isExistId(programmingLanguageId)) {
            this.programmingLanguageRepository.deleteById(programmingLanguageId);
        }
    }

    @Override
    public List<GetAllProgrammingLanguagesResponse> getAll() {
        List<ProgrammingLanguage> programmingLanguages = this.programmingLanguageRepository.findAll();
        List<GetAllProgrammingLanguagesResponse> getAllProgrammingLanguagesResponses = programmingLanguages.stream()
                .map(programmingLanguage -> this.modelMapperService.forResponse()
                        .map(programmingLanguage, GetAllProgrammingLanguagesResponse.class)).toList();
        return getAllProgrammingLanguagesResponses;
    }

    @Override
    public GetByIdProgrammingLanguageResponse getById(int programmingLanguageId) throws Exception {
        if (this.isExistId(programmingLanguageId)) {
            ProgrammingLanguage programmingLanguage =
                    this.programmingLanguageRepository.getReferenceById(programmingLanguageId);
            GetByIdProgrammingLanguageResponse getByIdProgrammingLanguageResponse =
                    this.modelMapperService.forResponse().map(programmingLanguage,GetByIdProgrammingLanguageResponse.class);
            return getByIdProgrammingLanguageResponse;
        }
        return null;
    }

    @Override
    public boolean existsProgrammingLangageId(int programmingLanguageId) {
        if (this.programmingLanguageRepository.existsById(programmingLanguageId)) {
            return true;
        }
        return false;
    }
    
}
