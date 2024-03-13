package net.javaguides.springboot.entity;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "todotasks")
public class Todotask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "title")
    private String title;    
    
    @Column(name = "description")
    private String description;

    @Column(name = "user_id", nullable = false)
    private String user_id;
    
    @Column(name = "planned_start_date")
    private String planned_start_date;
    
    @Column(name = "planned_end_date")
    private String planned_end_date;
    
    @Column(name = "actual_start_date")
    private String actual_start_date;
    
    @Column(name = "actual_end_date")
    private String actual_end_date;

}