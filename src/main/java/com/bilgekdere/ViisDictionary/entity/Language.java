package com.bilgekdere.ViisDictionary.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity
@NoArgsConstructor
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long languageId;

    @Setter
    @Getter
    @Column(name = "language_code")
    private String languageCode;

    @Setter
    @Getter
    @Column(name = "language_name")
    private String languageName;

}
