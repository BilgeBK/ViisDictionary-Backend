package com.bilgekdere.ViisDictionary.controller;

import com.bilgekdere.ViisDictionary.entity.Language;
import com.bilgekdere.ViisDictionary.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    /**
     * Fetches all the languages in the language table
     *
     * @return List of languages
     */
    @GetMapping("/allLanguages")
    public ResponseEntity<List<Language>> getAllLanguages() {
        return ResponseEntity.ok().body(languageService.getAllLanguages());
    }

    @PostMapping("/addLanguage")
    public ResponseEntity<Language> addLanguage(@RequestBody Language language) {
        return ResponseEntity.ok().body(languageService.addLanguage(language));
    }

    @PutMapping("/updateLanguage/{languageId}")
    public ResponseEntity<Language> updateLanguage(@PathVariable Long languageId, @RequestBody Language language) {
        return ResponseEntity.ok().body(languageService.updateLanguage(languageId, language));
    }

    @DeleteMapping("/deleteLanguage/{languageId}")
    public ResponseEntity<String> deleteLanguage(@PathVariable Long languageId) {
        return ResponseEntity.ok().body(languageService.deleteLanguage(languageId));
    }
}
