package br.com.github.vediniz.repository;

import br.com.github.vediniz.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
