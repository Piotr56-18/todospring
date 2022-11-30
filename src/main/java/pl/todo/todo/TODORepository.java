package pl.todo.todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TODORepository extends JpaRepository<TODO, Integer> {
}
