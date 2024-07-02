package ru.practicum.stats.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "applications")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private long id;
    @Column(name = "app")
    private String app;

    public Application(String app) {
        this.app = app;
    }
}
