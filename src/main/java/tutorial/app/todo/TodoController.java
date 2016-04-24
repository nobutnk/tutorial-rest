package tutorial.app.todo;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import tutorial.app.todo.TodoForm.TodoCreate;
import tutorial.app.todo.TodoForm.TodoDelete;
import tutorial.app.todo.TodoForm.TodoFinish;
import tutorial.app.todo.TodoForm.TodoUpdate;
import tutorial.app.todo.TodoForm.TodoUpdateForm;
import tutorial.domain.model.Todo;
import tutorial.domain.service.todo.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {
    
    @Inject
    Mapper beanMapper;
    
    @Inject
    TodoService todoService;

    @ModelAttribute
    public TodoForm setUpForm() {
        TodoForm form = new TodoForm();
        return form;
    }

    @RequestMapping(value = "list")
    public String list(Model model) {
        Collection<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "todo/list";
    }
    
    @RequestMapping(value = "list", params= "csv", method = RequestMethod.GET)
    public String listCsv(Model model) {
        Collection<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "todoCsvView";
    }
    
    @RequestMapping(value = "create", params = "form", method = RequestMethod.GET)
    public String createForm(Model model) {
        return "todo/create";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Validated({ Default.class, TodoCreate.class }) TodoForm todoForm,
            BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "todo/create";
        }

        Todo todo = beanMapper.map(todoForm, Todo.class);

        try {
            todoService.create(todo);
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Created successfully!")));
        return "redirect:/todo/list";
    }
    
    @RequestMapping(value = "update", params = "form", method = RequestMethod.GET)
    public String updateForm(
            @Validated({ Default.class, TodoUpdateForm.class }) TodoForm form,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        Todo todo;
        try {
            todo = todoService.findOne(form.getTodoId());
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        form = beanMapper.map(todo, TodoForm.class);
        model.addAttribute("todoForm", form);
        return "todo/update";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(
            @Validated({ Default.class, TodoUpdate.class }) TodoForm todoForm,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes) {
        
        if (bindingResult.hasErrors()) {
            return list(model);
        }

        Todo todo = beanMapper.map(todoForm, Todo.class);
        
        try {
            todoService.update(todo);
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Finished successfully!")));
        return "redirect:/todo/update?form&todoId=" + todo.getTodoId();
    }
    
    @RequestMapping(value = "finish", method = RequestMethod.POST)
    public String finish(
            @Validated({ Default.class, TodoFinish.class }) TodoForm todoForm,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return list(model);
        }
        
        Todo todo = beanMapper.map(todoForm, Todo.class);

        try {
            todoService.finish(todo.getTodoId(), todo.getUpdatedAt());
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Finished successfully!")));
        return "redirect:/todo/list";
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(
            @Validated({ Default.class, TodoDelete.class }) TodoForm todoForm,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return list(model);
        }
        
        Todo todo = beanMapper.map(todoForm, Todo.class);

        try {
            todoService.delete(todo.getTodoId(), todo.getUpdatedAt());
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Deleted successfully!")));
        return "redirect:/todo/list";
    }

}
