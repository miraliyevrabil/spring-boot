package com.rabilmiraliyev.model;

import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Person {

    @Id
    @SequenceGenerator(name = "seq_person", allocationSize = 1)
    @GeneratedValue(generator = "seq_person", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 30, name = "fisrtname")
    private String fisrtname;

    @Column(length = 30, name = "lastname")
    private String lastname;


    @OneToMany
    @JoinColumn(name = "person_adress_id")
    private List<Adress> adresses;

}