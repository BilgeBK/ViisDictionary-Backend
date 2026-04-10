package com.bilgekdere.ViisDictionary.controller;

import com.bilgekdere.ViisDictionary.entity.Translation;
import com.bilgekdere.ViisDictionary.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor
public class TranslationController {
    private final TranslationService translationService;

    @PostMapping
    public ResponseEntity<String> translate(@RequestBody Translation translation) {
        String translatedText = translationService.translate(translation.getSourceLanguageId(), translation.getTargetLanguageId(),translation.getOriginalText());
        return ResponseEntity.ok(translatedText);
    }

    @PostMapping("/add")
    public ResponseEntity<Translation> addTranslate(@RequestBody Translation translation) {
        if(translation != null) {
            Translation newTranslate =  translationService.addTranslation(translation);
            return ResponseEntity.ok(newTranslate);
        }
        return ResponseEntity.badRequest().body(new Translation());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Translation> updateTranslate(@PathVariable Long id, @RequestBody Translation translation) {
        Translation updateTranslation = translationService.updateTranslation(id, translation);
        return ResponseEntity.ok(updateTranslation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTranslate(@PathVariable Long id) {
        String deleteTranslation = translationService.deleteTranslation(id);
        return ResponseEntity.ok(deleteTranslation);
    }

    @GetMapping("/allTranslation/{sourceLanguageId}/{targetLanguageId}")
    public ResponseEntity<List<Translation>> allTranslation(@PathVariable Long sourceLanguageId, @PathVariable Long targetLanguageId) {
        List<Translation> allTranslation = translationService.getAllTranslation(sourceLanguageId, targetLanguageId);
        return ResponseEntity.ok(allTranslation);
    }

    @GetMapping("/byLanguage/{id}")
    public ResponseEntity<List<Translation>> getByLanguage(@PathVariable Long id) {
        return ResponseEntity.ok(translationService.getTranslationsByLanguage(id));
    }
}
