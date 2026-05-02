package com.example.app.controller;

import com.example.app.domain.Student;
import com.example.app.service.CourseService;
import com.example.app.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;
  private final CourseService courseService;

  public StudentController(StudentService studentService, CourseService courseService) {
    this.studentService = studentService;
    this.courseService = courseService;
  }

  @GetMapping
  public String listStudents(Model model) {
    model.addAttribute("students", studentService.findAllStudents());
    model.addAttribute("studentCourseDetails", studentService.findStudentCourseDetails());
    return "students/list";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("student", new Student());
    addStudentFormAttributes(model, "Add Student", "/students");
    return "students/form";
  }

  @PostMapping
  public String createStudent(
    @Valid @ModelAttribute("student") Student student,
    BindingResult bindingResult,
    @RequestParam(value = "course.id", required = false) Long courseId,
    Model model,
    RedirectAttributes redirectAttributes
  ) {
    validateCourseSelection(courseId, bindingResult);
    if (bindingResult.hasErrors()) {
      addStudentFormAttributes(model, "Add Student", "/students");
      return "students/form";
    }

    try {
      studentService.createStudent(student, courseId);
      redirectAttributes.addFlashAttribute("successMessage", "Student added successfully.");
      return "redirect:/students";
    } catch (DataIntegrityViolationException ex) {
      bindingResult.rejectValue("email", "duplicate", "Student email must be unique.");
      addStudentFormAttributes(model, "Add Student", "/students");
      return "students/form";
    }
  }

  @GetMapping("/{id}/edit")
  public String showUpdateForm(@PathVariable Long id, Model model) {
    model.addAttribute("student", studentService.findStudent(id));
    addStudentFormAttributes(model, "Update Student", "/students/" + id);
    return "students/form";
  }

  @PostMapping("/{id}")
  public String updateStudent(
    @PathVariable Long id,
    @Valid @ModelAttribute("student") Student student,
    BindingResult bindingResult,
    @RequestParam(value = "course.id", required = false) Long courseId,
    Model model,
    RedirectAttributes redirectAttributes
  ) {
    validateCourseSelection(courseId, bindingResult);
    if (bindingResult.hasErrors()) {
      student.setId(id);
      addStudentFormAttributes(model, "Update Student", "/students/" + id);
      return "students/form";
    }

    try {
      studentService.updateStudent(id, student, courseId);
      redirectAttributes.addFlashAttribute("successMessage", "Student updated successfully.");
      return "redirect:/students";
    } catch (DataIntegrityViolationException ex) {
      student.setId(id);
      bindingResult.rejectValue("email", "duplicate", "Student email must be unique.");
      addStudentFormAttributes(model, "Update Student", "/students/" + id);
      return "students/form";
    }
  }

  private void addStudentFormAttributes(Model model, String formTitle, String formAction) {
    model.addAttribute("courses", courseService.findAllCourses());
    model.addAttribute("formTitle", formTitle);
    model.addAttribute("formAction", formAction);
  }

  private void validateCourseSelection(Long courseId, BindingResult bindingResult) {
    if (courseId == null) {
      bindingResult.rejectValue("course", "required", "Please select a course.");
    }
  }
}
