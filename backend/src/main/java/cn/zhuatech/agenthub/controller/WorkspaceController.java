/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.agenthub.controller;

import cn.zhuatech.agenthub.agent.AgentGovernanceService;
import cn.zhuatech.agenthub.agent.AgentRuntime;
import cn.zhuatech.agenthub.common.ApiResponse;
import cn.zhuatech.agenthub.dto.AgentHubDto.*;
import cn.zhuatech.agenthub.service.AgentHubService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/shopfloor")
@PreAuthorize("hasAnyRole('DOMAIN_USER','ADMIN')")
public class WorkspaceController {
    private final AgentHubService service;
    private final AgentRuntime runtime;
    private final AgentGovernanceService governance;

    public WorkspaceController(AgentHubService service, AgentRuntime runtime, AgentGovernanceService governance) {
        this.service = service;
        this.runtime = runtime;
        this.governance = governance;
    }

    @GetMapping("/dashboard")
    public ApiResponse<Dashboard> dashboard() { return ApiResponse.ok(service.shopfloorDashboard()); }

    @PostMapping("/work-orders/{id}/reports")
    public ApiResponse<ReportResult> report(@PathVariable Long id, @Valid @RequestBody ReportRequest request) {
        return ApiResponse.ok("反馈提交成功", service.report(id, request));
    }

    @PostMapping("/agent-preview")
    public ApiResponse<AgentRuntime.AgentResult> preview(@RequestBody Map<String, String> body) {
        return ApiResponse.ok(runtime.run(new AgentRuntime.AgentRequest(body.getOrDefault("objective", "梳理今日待办"), Map.of("mode", "demo"))));
    }

    @PostMapping("/agent-preflight")
    public ApiResponse<AgentGovernanceService.PreflightResult> preflight(@Valid @RequestBody AgentGovernanceService.PreflightRequest request) {
        return ApiResponse.ok("治理检查完成", governance.preflight(request));
    }
}
