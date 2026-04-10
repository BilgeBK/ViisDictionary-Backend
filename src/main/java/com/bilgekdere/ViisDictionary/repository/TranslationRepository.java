package com.bilgekdere.ViisDictionary.repository;

import com.bilgekdere.ViisDictionary.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    Optional<Translation> findBySourceLanguageIdAndTargetLanguageIdAndOriginalTextIgnoreCase(Long sourceLanguageId, Long targetLanguageId, String originalText);
    Optional<Translation> findBySourceLanguageIdAndTargetLanguageIdAndTranslatedTextIgnoreCase(Long targetLanguageId, Long sourceLanguageId, String translatedText);
    boolean existsByTranslatedTextAndTargetLanguageId(String translatedText, Long targetLanguageId);
    boolean existsByOriginalTextAndSourceLanguageId(String originalText, Long sourceLanguageId);
    List<Translation> findBySourceLanguageIdAndTargetLanguageId(Long sourceId, Long targetId);
    List<Translation> findBySourceLanguageIdOrTargetLanguageId(Long sourceId, Long targetId);
}
