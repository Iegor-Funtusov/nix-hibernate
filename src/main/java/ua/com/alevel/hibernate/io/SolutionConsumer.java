package ua.com.alevel.hibernate.io;

import ua.com.alevel.hibernate.io.data.Problem;

import java.util.Collection;

public interface SolutionConsumer {

    void consume(Collection<Problem.Solution> solutions);
}
