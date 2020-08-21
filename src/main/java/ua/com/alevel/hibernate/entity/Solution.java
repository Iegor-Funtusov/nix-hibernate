package ua.com.alevel.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:06 PM
 */

@Getter
@Setter
@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @Column(name = "problem_id")
    private Integer problemId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "problem_id")
    private SubProblem problem;

    private Integer cost;
}
