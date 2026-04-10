package com.bilgekdere.ViisDictionary.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "translations")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translation_id")
    private Long translationId;

    @Column(name = "source_language_id")
    @Getter
    @Setter
    private Long sourceLanguageId;

    @Column(name = "target_language_id")
    @Getter
    @Setter
    private Long targetLanguageId;

    @Column(name = "original_text")
    @Getter
    @Setter
    private String originalText;

    @Column(name = "translated_text")
    @Getter
    @Setter
    private String translatedText;
}