package ru.kpfu.itis.skatingblog.models;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.skatingblog.models.enums.Role;
import ru.kpfu.itis.skatingblog.models.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Length(max = 255)
    private String title;

    @Length(max = 255)
    @Column(nullable = false)
    private String url;


    @Length(max = 255)
    @Column(nullable = false, name = "video_url")
    private String videoUrl;

    @Length(max = 255)
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Instant created;

    @Column
    private Long views;



}
