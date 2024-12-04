package in.task.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.task.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
	  List<City> findByStateStateCode(String stateCode);
}
