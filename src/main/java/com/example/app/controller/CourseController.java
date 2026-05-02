package com.example.app.controller;

import com.example.app.domain.Course;
import com.example.app.service.CourseService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public String listCourses(Model model) {
    model.addAttribute("courses", courseService.findAllCourses());
    return "courses/list";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("course", new Course());
    model.addAttribute("formTitle", "Add Course");
    model.addAttribute("formAction", "/courses");
    return "courses/form";
  }

  @PostMapping
  public String createCourse(
    @Valid @ModelAttribute("course") Course course,
    BindingResult bindingResult,
    Model model,
    RedirectAttributes redirectAttributes
  ) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("formTitle", "Add Course");
      model.addAttribute("formAction", "/courses");
      return "courses/form";
    }

    try {
      courseService.createCourse(course);
      redirectAttributes.addFlashAttribute("successMessage", "Course added successfully.");
      return "redirect:/courses";
    } catch (DataIntegrityViolationException ex) {
      bindingResult.rejectValue("code", "duplicate", "Course code must be unique.");
      model.addAttribute("formTitle", "Add Course");
      model.addAttribute("formAction", "/courses");
      return "courses/form";
    }
  }

  @GetMapping("/{id}/edit")
  public String showUpdateForm(@PathVariable Long id, Model model) {
    model.addAttribute("course", courseService.findCourse(id));
    model.addAttribute("formTitle", "Update Course");
    model.addAttribute("formAction", "/courses/" + id);
    return "courses/form";
  }

  @PostMapping("/{id}")
  public String updateCourse(
    @PathVariable Long id,
    @Valid @ModelAttribute("course") Course course,
    BindingResult bindingResult,
    Model model,
    RedirectAttributes redirectAttributes
  ) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("formTitle", "Update Course");
      model.addAttribute("formAction", "/courses/" + id);
      return "courses/form";
    }

    try {
      courseService.updateCourse(id, course);
      redirectAttributes.addFlashAttribute("successMessage", "Course updated successfully.");
      return "redirect:/courses";
    } catch (DataIntegrityViolationException ex) {
      bindingResult.rejectValue("code", "duplicate", "Course code must be unique.");
      model.addAttribute("formTitle", "Update Course");
      model.addAttribute("formAction", "/courses/" + id);
      return "courses/form";
    }
  }
}
