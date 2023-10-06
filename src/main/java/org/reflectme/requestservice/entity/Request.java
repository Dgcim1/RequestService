package org.reflectme.requestservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "comment", length = 16384)
    private String comment;

    @Column
    private boolean active;

    @Column(name = "creation_timestamp")
    private Timestamp creationTimestamp;
}
