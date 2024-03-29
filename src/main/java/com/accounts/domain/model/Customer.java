package com.accounts.domain.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.ALL;

/**
 * customer table java object
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GenericGenerator(name = "customer_generator", strategy = "increment")
    @GeneratedValue(generator = "customer_generator")
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String email;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @ToString.Exclude
    @OneToOne(optional = true, cascade = ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonBackReference
    private BankAccount bankAccount;
}
