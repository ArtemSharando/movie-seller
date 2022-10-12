package com.lufoxt.movieseller.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;

    //TODO encode password
    private String password;

    @OneToMany(mappedBy = "customer")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Order> orders;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Movie> purchasedMovies;
}
