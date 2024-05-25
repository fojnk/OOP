package org.example.services;


import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.example.models.Student;
import org.example.models.Task;
import org.example.models.TaskResult;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Render {
    @SneakyThrows
    public static void render(List<Task> tasks, HashMap<Student, HashMap<Task, TaskResult>> tasksResults) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setClassForTemplateLoading(Render.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("tasks", tasks);
        root.put("tasksResults", tasksResults);

        Template temp = cfg.getTemplate("tmp.ftl");
        Writer out = new OutputStreamWriter(new FileOutputStream("src/main/resources/index.html"));
        temp.process(root, out);
    }
}