package in.task.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.task.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, String> {
	List<State> findByCountryCountryCode(String countryCode);
}

