package ua.com.alevel.hibernate.service.system;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import ua.com.alevel.hibernate.dao.ConnectionDao;
import ua.com.alevel.hibernate.dao.LocationDao;
import ua.com.alevel.hibernate.dao.SubProblemDao;
import ua.com.alevel.hibernate.entity.Connection;
import ua.com.alevel.hibernate.entity.Location;
import ua.com.alevel.hibernate.entity.SubProblem;
import ua.com.alevel.hibernate.util.UploadUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:45 PM
 */

@Service
public class InitDbService {

    private final LocationDao locationDao;
    private final ConnectionDao connectionDao;
    private final SubProblemDao subProblemDao;

    public InitDbService(LocationDao locationDao, ConnectionDao connectionDao, SubProblemDao subProblemDao) {
        this.locationDao = locationDao;
        this.connectionDao = connectionDao;
        this.subProblemDao = subProblemDao;
    }

    public void init() {

        Collection<File> files = FileUtils.listFiles(
                new File(UploadUtil.getPath(UploadUtil.Folder.CSV)),
                new String[] { "csv" }, true
        );

        List<List<String>> locations = new ArrayList<>();
        List<List<String>> connections = new ArrayList<>();
        List<List<String>> problems = new ArrayList<>();

        for (File file : files) {
            try(CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()))) {
                String[] values;

                while ((values = reader.readNext()) != null) {
                    if (file.getName().endsWith("locations.csv")) {
                        locations.add(Arrays.asList(values));
                    }
                    if (file.getName().endsWith("connections.csv")) {
                        connections.add(Arrays.asList(values));
                    }
                    if (file.getName().endsWith("problems.csv")) {
                        problems.add(Arrays.asList(values));
                    }
                }

            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
            }
        }

        createLocation(locations);
        createConnection(connections);
        createProblem(problems);
    }

    private void createLocation(List<List<String>> list) {
        List<Location> locations = locationDao.findAll();
        if (locations.size() != list.size()) {
            for (List<String> strings : list) {
                Location location = new Location();
                location.setName(strings.get(0));
                locationDao.create(location);
            }
        }
    }

    private void createConnection(List<List<String>> list) {
        List<Connection> connections = connectionDao.findAll();
        if (connections.size() != list.size()) {
            Connection connection = new Connection();
            Location locationFrom;
            Location locationTo;
            for (List<String> strings : list) {
                locationFrom = locationDao.findById(Integer.parseInt(strings.get(0)));
                connection.setFrom(locationFrom);
                locationTo = locationDao.findById(Integer.parseInt(strings.get(1)));
                connection.setTo(locationTo);
                Integer cost = Integer.parseInt(strings.get(2));
                connection.setCost(cost);
                connectionDao.create(connection);
            }
        }
    }

    private void createProblem(List<List<String>> list) {
        List<SubProblem> problems = subProblemDao.findAll();
        if (problems.size() != list.size()) {
            SubProblem problem = new SubProblem();
            Location locationFrom;
            Location locationTo;
            for (List<String> strings : list) {
                locationFrom = locationDao.findById(Integer.parseInt(strings.get(0)));
                problem.setFrom(locationFrom);
                locationTo = locationDao.findById(Integer.parseInt(strings.get(1)));
                problem.setTo(locationTo);
                subProblemDao.create(problem);
            }
        }
    }
}
