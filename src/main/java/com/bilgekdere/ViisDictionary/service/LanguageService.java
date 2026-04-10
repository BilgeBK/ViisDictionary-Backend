package com.bilgekdere.ViisDictionary.service;

import com.bilgekdere.ViisDictionary.entity.Language;
import com.bilgekdere.ViisDictionary.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;


    /**
     * get all languages
     *
     * @return Language list
     */
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    /**
     *
     * @param language
     * @return Language
     */
    public Language addLanguage(Language language) {
        try {
            var isExistLanguage = languageRepository.existsByLanguageCode(language.getLanguageCode());
            if (language != null && !isExistLanguage) {
                return languageRepository.save(language);
            } else {
                return language;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Language updateLanguage(Long id, Language language) {
        try {
            Optional<Language> findLanguage = languageRepository.findById(id);
            if (findLanguage.isPresent()){
                findLanguage.get().setLanguageCode(language.getLanguageCode());
                findLanguage.get().setLanguageName(language.getLanguageName());
                return languageRepository.save(findLanguage.get());
            } else {
                return new Language();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteLanguage(Long id) {
        try {
            Optional<Language> findLanguage = languageRepository.findById(id);
            if (findLanguage.isPresent()) {
                languageRepository.delete(findLanguage.get());
                return "Relevant language record has been deleted.";
            } else {
                return "Relevant language could not find. Please check again and retry";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidLanguageCode(Long languageId) {
        for(var language: getAllLanguages()) {
            if(language.getLanguageId().equals(languageId)) {
                return true;
            }
        }
        return false;
    }
}