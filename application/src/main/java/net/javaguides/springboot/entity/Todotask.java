package net.javaguides.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
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

    @Column(name = "title", nullable = false)
    private String title;    
    
    @Column(name = "description")
    private String description;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "priority")
    private String priority;
    
    @Column(name = "planned_start_date")
    private String planned_start_date;
    
    @Column(name = "planned_end_date")
    private String planned_end_date;
    
    @Column(name = "actual_start_date")
    private String actual_start_date;
    
    @Column(name = "actual_end_date")
    private String actual_end_date;

    @Column(name = "progress")
    private String progress;

    @Column(name = "status")
    private String status;
    
    @Column(name = "complete_flg")
    private String complete_flg;
}