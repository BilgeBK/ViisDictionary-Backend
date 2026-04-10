package com.bilgekdere.ViisDictionary.repository;

import com.bilgekdere.ViisDictionary.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByLanguageId(Long languageId);
    Boolean existsByLanguageCode(String languageCode);
}
