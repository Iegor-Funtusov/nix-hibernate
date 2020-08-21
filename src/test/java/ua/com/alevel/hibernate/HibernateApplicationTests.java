package ua.com.alevel.hibernate;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ua.com.alevel.hibernate.io.data.Problem;
import ua.com.alevel.hibernate.service.content.PathfinderService;

import java.util.List;

@SpringBootTest
class HibernateApplicationTests {

    @Autowired
    private PathfinderService pathfinderService;

    @Test
    void contextLoads() {
        Problem problem = pathfinderService.load();
        List<Problem.Solution> solutions = problem.solve();
        pathfinderService.consume(solutions);
    }
}
