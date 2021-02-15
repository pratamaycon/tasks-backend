package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void naoDeveSalvarTarefasSemDescricao() {
		Task todo = new Task();
		//todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now());
		controller = new TaskController();
		try {
			controller.save(todo);
			fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefasSemData() {
		Task todo = new Task();
		todo.setTask("Descricao");
		//todo.setDueDate(LocalDate.now());
		controller = new TaskController();
		try {
			controller.save(todo);
			fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefasComDataPassada() {
		Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.of(2010, 01, 01));
		controller = new TaskController();
		try {
			controller.save(todo);
			fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTarefasComSucesso() throws ValidationException {
		Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		Mockito.verify(taskRepo).save(todo);
	}
	
}
