package com.bilgekdere.ViisDictionary.service;

import com.bilgekdere.ViisDictionary.entity.Language;
import com.bilgekdere.ViisDictionary.entity.Translation;
import com.bilgekdere.ViisDictionary.repository.LanguageRepository;
import com.bilgekdere.ViisDictionary.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslationService {

    private final TranslationRepository translationRepository;
    private final LanguageRepository languageRepository;
    private final LanguageService languageService;

    public String translate(Long sourceLanguageId, Long targetLanguageId, String originalText) {
        String trimmedText = originalText.trim();
        Language sourceLanguage = languageRepository.findByLanguageId(sourceLanguageId).orElseThrow(() -> new IllegalArgumentException("This is not valid code"));
        Language targetLanguage = languageRepository.findByLanguageId(targetLanguageId).orElseThrow(() -> new IllegalArgumentException("This is not valid code"));

        Optional<Translation> findTranslationWithOriginalText = translationRepository.findBySourceLanguageIdAndTargetLanguageIdAndOriginalTextIgnoreCase(sourceLanguage.getLanguageId(),targetLanguage.getLanguageId(),originalText);

        if(findTranslationWithOriginalText.isPresent()) {
            return findTranslationWithOriginalText.get().getTranslatedText();
        }

        Optional<Translation> findTranslationWithTranslatedText = translationRepository.findBySourceLanguageIdAndTargetLanguageIdAndTranslatedTextIgnoreCase(targetLanguageId, sourceLanguageId, trimmedText);

        if (findTranslationWithTranslatedText.isPresent()) {
            return findTranslationWithTranslatedText.get().getOriginalText();
        }
        return "Not find the word";
    }


    public Translation addTranslation(Translation translation) {
        Translation newTranslation = new Translation();
        boolean sourceLanguageCodeValid = languageService.isValidLanguageCode(translation.getSourceLanguageId());
        boolean targetLanguageCodeValid = languageService.isValidLanguageCode(translation.getTargetLanguageId());
        boolean isExistTranslatedText = translationRepository.existsByTranslatedTextAndTargetLanguageId(translation.getTranslatedText(), translation.getTargetLanguageId());
        boolean isExistOriginalText = translationRepository.existsByOriginalTextAndSourceLanguageId(translation.getOriginalText(), translation.getSourceLanguageId());
        if (sourceLanguageCodeValid && targetLanguageCodeValid && !isExistTranslatedText && !isExistOriginalText) {
            newTranslation.setSourceLanguageId(translation.getSourceLanguageId());
            newTranslation.setTranslationId(translation.getTargetLanguageId());
            newTranslation.setOriginalText(translation.getOriginalText());
            newTranslation.setTranslatedText(translation.getTranslatedText());
            return translationRepository.save(translation);
        }
        return translation;
    }

    public Translation updateTranslation(Long translationId, Translation translation) {
        try {
            var findTranslation = translationRepository.findById(translationId);
            if (findTranslation.isPresent()) {
                findTranslation.get().setSourceLanguageId(translation.getSourceLanguageId());
                findTranslation.get().setTranslationId(translation.getTranslationId());
                findTranslation.get().setOriginalText(translation.getOriginalText());
                findTranslation.get().setTranslatedText(translation.getTranslatedText());
                return translationRepository.save(findTranslation.get());
            } else {
                return new Translation();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteTranslation(Long translationId) {
        try {
            var findTranslation = translationRepository.findById(translationId);
            if (findTranslation.isPresent()) {
                translationRepository.delete(findTranslation.get());
                return "relevant translation record has been deleted";
            } else {
                return "relevant translation could not find. Please check again and retry";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Translation> getAllTranslation(Long sourceLanguageId, Long targetLanguageId) {
        return translationRepository.findBySourceLanguageIdAndTargetLanguageId(sourceLanguageId,targetLanguageId);
    }

    public List<Translation> getTranslationsByLanguage(Long languageId) {
        return translationRepository.findBySourceLanguageIdOrTargetLanguageId(languageId, languageId);
    }
}