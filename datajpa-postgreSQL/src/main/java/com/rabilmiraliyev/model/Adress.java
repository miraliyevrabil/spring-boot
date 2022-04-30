package com.rabilmiraliyev.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "person_adress")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Adress implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_person_adress", allocationSize = 1)
    @GeneratedValue(generator = "seq_person_adress", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 150, name = "adres")
    private String adres;

    @Enumerated
    private AdresType adresType;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "person_adress_id")
    private Person person;

    public enum AdresType {
        HOME,
        WORK,
        OTHER
    }
}
