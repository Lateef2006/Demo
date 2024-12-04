package in.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.task.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}

