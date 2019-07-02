package com.tts.DoneItt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tts.DoneItt.Models.Task;
import com.tts.DoneItt.Repositories.TaskRepository;

@RequestMapping
@Controller
public class TaskController {
		
	@Autowired
	TaskRepository taskRepository;
	
	//Getting The Home Page
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("tasks", taskRepository.findAll());
		return "index";
	}
	
	
	//Getting The New Task Page
	@GetMapping("/task/newTask")
	public String createTask(Model model) {
		model.addAttribute("task", new Task());
		return"newTask";
	}
	
	//Saving The New Task and Going Back To The Home Page
	@PostMapping("/task/newTask/save")
	public String saveNewTask(Task task, Model model) {
		Task saveTask = taskRepository.save(task);
		model.addAttribute("task", saveTask);
		return "redirect:/ ";
	}
	
	//Showing The Specific Task
		@GetMapping("/task/{id}")
		public String show(@PathVariable Long id, Task task, Model model) {
			Task showTask = taskRepository.findById(id).orElse(null);
			model.addAttribute("task", showTask);
			model.addAttribute("title",showTask.getTitle());
			model.addAttribute("description",showTask.getDescription());
			model.addAttribute("creator", showTask.getCreator());
			return "task";
		}
	
	//Updating A Specific Task
		@PutMapping("/task/{id}/update")
		public String update(@PathVariable Long id, Task task, Model model) {
			Task updateTask = taskRepository.findById(id).orElse(null);
			model.addAttribute("task", updateTask);
			return "edit";
		}
		
		
	//Delete Task
		@DeleteMapping("/task/{id}/delete")
		public String delete(@PathVariable Long id) {
			taskRepository.deleteById(id);
			return "redirect:/";
		}
		
}
