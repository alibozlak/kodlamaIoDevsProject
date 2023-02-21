package Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import Kodlama.io.Devs.business.responses.GetByIdProgrammingLanguageResponse;
import lombok.AllArgsConstructor;
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
import Kodlama.io.Devs.business.requests.programmingLanguage.UpdateProgrammingLanguageRequest;
import Kodlama.io.Devs.business.responses.GetAllProgrammingLanguagesResponse;

@RestController
@RequestMapping("/api/programminglanguages")
@AllArgsConstructor
public class ProgrammingLanguagesController {
    
    private ProgrammingLanguageService programmingLanguageService;

    @GetMapping("/getAll")
    public List<GetAllProgrammingLanguagesResponse> getAll() {
        return this.programmingLanguageService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdProgrammingLanguageResponse getById(@PathVariable int id) throws Exception {
        return this.programmingLanguageService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
        this.programmingLanguageService.add(createProgrammingLanguageRequest);
    }

    @PutMapping
    public void update(@RequestBody UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception {
        this.programmingLanguageService.update(updateProgrammingLanguageRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) throws Exception {
        this.programmingLanguageService.delete(id);
    }

}
