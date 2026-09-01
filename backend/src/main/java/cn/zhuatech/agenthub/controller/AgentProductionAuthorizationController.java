/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.agenthub.controller;

import cn.zhuatech.agenthub.common.ApiResponse;
import cn.zhuatech.agenthub.service.AgentProductionAuthorizationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/agenthub")
public class AgentProductionAuthorizationController {
    private final AgentProductionAuthorizationService service;
    public AgentProductionAuthorizationController(AgentProductionAuthorizationService service) { this.service = service; }
    @PostMapping("/production-authorization")
    public ApiResponse<AgentProductionAuthorizationService.Assessment> assess(
            @Valid @RequestBody AgentProductionAuthorizationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
