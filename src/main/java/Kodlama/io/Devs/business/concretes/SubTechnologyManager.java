package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.SubTechologyService;
import Kodlama.io.Devs.business.requests.CreateSubTechnologyRequest;
import Kodlama.io.Devs.business.requests.CreateSubTechnologyRequestWithoutProgrammingLanguageName;
import Kodlama.io.Devs.business.responses.GetAllSubTechnologyResponse;
import Kodlama.io.Devs.dataAccess.abstracts.ProgrammingLanguageRepository;
import Kodlama.io.Devs.dataAccess.abstracts.SubTechnologyRepository;
import Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;
import Kodlama.io.Devs.entities.concretes.SubTechnology;

@Service
public class SubTechnologyManager implements SubTechologyService {

    private SubTechnologyRepository subTechnologyRepository;
    private ProgrammingLanguageRepository programmingLanguageRepository;

    public SubTechnologyManager(SubTechnologyRepository subTechnologyRepository,
            ProgrammingLanguageRepository programmingLanguageRepository) {
        this.subTechnologyRepository = subTechnologyRepository;
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public void add(CreateSubTechnologyRequest subTechnologyRequest) throws Exception {
        if (!isNameEmpty(subTechnologyRequest.getName()) && !isNameRepeat(subTechnologyRequest.getName()) && 
        isExistProgrammingLanguageId(subTechnologyRequest.getProgrammingLanguageId())) {
            SubTechnology subTechnology = new SubTechnology();
            subTechnology.setName(subTechnologyRequest.getName());

            ProgrammingLanguage programmingLanguage = this.programmingLanguageRepository
            .getReferenceById(subTechnologyRequest.getProgrammingLanguageId());
            subTechnology.setProgrammingLanguage(programmingLanguage);

            this.subTechnologyRepository.save(subTechnology);
        }
    }

    @Override
    public void delete(int subTechnologyId) throws Exception {
        if (isExistSubTechnologyId(subTechnologyId)) {
            this.subTechnologyRepository.deleteById(subTechnologyId);
        }
    }

    @Override
    public void update(CreateSubTechnologyRequestWithoutProgrammingLanguageName subTechnologyRequestWithoutProgrammingLanguageName) throws Exception {
        if (!isNameEmpty(subTechnologyRequestWithoutProgrammingLanguageName.getSubTechnologyName()) && 
        !isNameRepeat(subTechnologyRequestWithoutProgrammingLanguageName.getSubTechnologyName()) && 
        isExistSubTechnologyId(subTechnologyRequestWithoutProgrammingLanguageName.getSubTechnologyId()) && 
        isExistProgrammingLanguageId(subTechnologyRequestWithoutProgrammingLanguageName.getProgrammingLanguageId())) {
            SubTechnology subTechnology = new SubTechnology();
            subTechnology.setId(subTechnologyRequestWithoutProgrammingLanguageName.getSubTechnologyId());
            subTechnology.setName(subTechnologyRequestWithoutProgrammingLanguageName.getSubTechnologyName());
            
            ProgrammingLanguage programmingLanguage 
            = this.programmingLanguageRepository.getReferenceById(subTechnologyRequestWithoutProgrammingLanguageName.getProgrammingLanguageId());
            subTechnology.setProgrammingLanguage(programmingLanguage);

            this.subTechnologyRepository.save(subTechnology);
        }
    }

    @Override
    public List<GetAllSubTechnologyResponse> getAll() {
        List<GetAllSubTechnologyResponse> getAllSubTechnologyResponses 
        = new ArrayList<>();
        for (SubTechnology subTechnology : this.subTechnologyRepository.findAll()) {
            GetAllSubTechnologyResponse getAllSubTechnologyResponse 
            = new GetAllSubTechnologyResponse();

            getAllSubTechnologyResponse.setId(subTechnology.getId());
            getAllSubTechnologyResponse.setName(subTechnology.getName());
            getAllSubTechnologyResponse.setProgrammingLanguage(subTechnology.getProgrammingLanguage());

            getAllSubTechnologyResponses.add(getAllSubTechnologyResponse);
        }
        return getAllSubTechnologyResponses;
    }

    private boolean isNameEmpty(String subTechnologyName) throws Exception {
        if (subTechnologyName.isBlank() || subTechnologyName.isEmpty()) {
            throw new Exception("Alt Teknoloji ismi boş olamaz!");
        }
        return false;
    }

    private boolean isNameRepeat(String subTechnologyName) throws Exception {
        for (SubTechnology subTechnology : this.subTechnologyRepository.findAll()) {
            if (subTechnologyName.equalsIgnoreCase(subTechnology.getName())) {
                throw new Exception(subTechnologyName + " isminde bir alt teknoloji ismi var!");
            }
        }
        return false;
    }

    private boolean isExistProgrammingLanguageId(int programmingLanguageId) throws Exception {
        if (this.programmingLanguageRepository.existsById(programmingLanguageId)) {
            return true;
        }
        throw new Exception(programmingLanguageId + " IDli bir programlama dili yok!");
    }

    private boolean isExistSubTechnologyId(int subTechnologyId) throws Exception {
        if (this.subTechnologyRepository.existsById(subTechnologyId)) {
            return true;
        }
        throw new Exception(subTechnologyId + " IDli bir alt teknoloji yok!");
    }
    
}
