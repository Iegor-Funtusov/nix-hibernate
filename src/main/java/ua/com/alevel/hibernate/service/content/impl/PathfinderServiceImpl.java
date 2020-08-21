package ua.com.alevel.hibernate.service.content.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.hibernate.dao.ConnectionDao;
import ua.com.alevel.hibernate.dao.LocationDao;
import ua.com.alevel.hibernate.dao.SolutionDao;
import ua.com.alevel.hibernate.dao.SubProblemDao;
import ua.com.alevel.hibernate.entity.Connection;
import ua.com.alevel.hibernate.entity.Location;
import ua.com.alevel.hibernate.entity.Solution;
import ua.com.alevel.hibernate.entity.SubProblem;
import ua.com.alevel.hibernate.io.data.Problem;
import ua.com.alevel.hibernate.io.data.ProblemBuilder;
import ua.com.alevel.hibernate.service.content.PathfinderService;

import java.sql.Types;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 9:20 PM
 */

@Service
@Transactional
public class PathfinderServiceImpl implements PathfinderService {

    private final LocationDao locationDao;
    private final ConnectionDao connectionDao;
    private final SubProblemDao subProblemDao;
    private final SolutionDao solutionDao;

    public PathfinderServiceImpl(LocationDao locationDao, ConnectionDao connectionDao, SubProblemDao subProblemDao, SolutionDao solutionDao) {
        this.locationDao = locationDao;
        this.connectionDao = connectionDao;
        this.subProblemDao = subProblemDao;
        this.solutionDao = solutionDao;
    }

    @Override
    public Problem load() {
        List<Location> locations = locationDao.findAll();
        List<SubProblem> subProblems = subProblemDao.findAll();
        List<Connection> connections = connectionDao.findAll();
        int size = locations.size();
        ProblemBuilder problemBuilder = new ProblemBuilder(size);

        int index = 0;

        Map<Integer, Integer> indexById = new HashMap<>();
        for (Location location : locations) {
            problemBuilder.name(index, location.getName());
            indexById.put(location.getId(), index);
            index++;
        }

        for (Connection connection : connections) {
            int from = indexById.get(connection.getFrom().getId());
            int to = indexById.get(connection.getTo().getId());
            problemBuilder.connect(from, to, connection.getCost());
        }

        for (SubProblem subProblem : subProblems) {
            problemBuilder.solve(
                    subProblem.getId(),
                    indexById.get(subProblem.getFrom().getId()),
                    indexById.get(subProblem.getTo().getId())
            );
        }

        return problemBuilder.build();
    }

    @Override
    public void consume(Collection<Problem.Solution> solutions) {
        solutionDao.deleteAll();
        for (Problem.Solution problemSolution : solutions) {
            int problemId = problemSolution.getProblemId();
            Solution solution = new Solution();
            solution.setProblemId(problemId);
            solution.setProblem(subProblemDao.findById(problemId));
            if (problemSolution instanceof Problem.RouteFound) {
                solution.setCost(((Problem.RouteFound)problemSolution).getDistance());
            } else {
                solution.setCost(Types.INTEGER);
            }
            solutionDao.create(solution);
        }
    }
}
