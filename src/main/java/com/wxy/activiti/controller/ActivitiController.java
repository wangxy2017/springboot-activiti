package com.wxy.activiti.controller;

import com.wxy.activiti.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-10 下午3:06
 * @Description TODO
 **/
@RestController
@RequestMapping("/activiti")
@Slf4j
public class ActivitiController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     * 发布流程
     *
     * @return
     */
    @GetMapping("/deploy")
    public CommonResponse deploy() {
        String path = "processes/MyProcess.bpmn20.xml";
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource(path)
                .deploy();
        log.info("发布流程 deploy = {}", deploy);
        return new CommonResponse(1, "success", deploy.getId());
    }

    /**
     * 启动流程
     *
     * @return
     */
    @GetMapping("/start")
    public CommonResponse start() {
        String key = "myProcess";
        ProcessInstance process = runtimeService.startProcessInstanceByKey(key);
        log.info("启动流程 process = {}", process.getId());
        return new CommonResponse(1, "success", process.getId());
    }

    /**
     * 完成任务
     *
     * @return
     */
    @GetMapping("/complete")
    public CommonResponse complete() {
        List<Task> list = taskService.createTaskQuery().list();
        Task task = list.get(0);
        taskService.complete(task.getId());
        log.info("完成任务 task = {}", task.getId());
        return new CommonResponse(1, "success", task.getId());
    }

    /**
     * 查询任务
     *
     * @return
     */
    @GetMapping("/list")
    public CommonResponse list() {
        List<Task> list = taskService.createTaskQuery().list();
        log.info("查询任务 list = {}", list);
        return new CommonResponse(1, "success", list.size());
    }
}
