package Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kodlama.io.Devs.business.abstracts.ProgrammingLanguageService;
import Kodlama.io.Devs.business.requests.programmingLanguage.CreateProgrammingLanguageRequest;
import Kodlama.io.Devs.business.responses.GetAllProgrammingLanguagesResponse;
import Kodlama.io.Devs.entities.concretes.ProgrammingLanguage;

@RestController
@RequestMapping("/api/programminglanguages")
public class ProgrammingLanguagesController {
    
    private ProgrammingLanguageService programmingLanguageService;

    public ProgrammingLanguagesController(ProgrammingLanguageService programmingLanguageService) {
        this.programmingLanguageService = programmingLanguageService;
    }

    @GetMapping("/getAll")
    public List<GetAllProgrammingLanguagesResponse> getAll() {
        return this.programmingLanguageService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetAllProgrammingLanguagesResponse getById(@PathVariable int id) throws Exception {
        return this.programmingLanguageService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
        this.programmingLanguageService.add(createProgrammingLanguageRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody ProgrammingLanguage programmingLanguage) throws Exception {
        this.programmingLanguageService.update(programmingLanguage);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) throws Exception {
        this.programmingLanguageService.delete(id);
    }

}
