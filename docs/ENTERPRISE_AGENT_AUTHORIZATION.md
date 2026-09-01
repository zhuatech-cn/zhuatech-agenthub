# 企业级 Agent 生产授权

`POST /api/enterprise/agenthub/production-authorization` 对 Agent 的责任人、工具最小权限、凭据、安全测试、人工审批、审计、紧急停止、评测阈值、容量和成本进行上线门禁。

接口返回 `AUTHORIZE`、`PILOT` 或 `BLOCKED`。生产环境应把 Agent、提示词、模型、工具清单、策略版本与审批记录固化，并对高影响动作保留人工确认和一键停止能力。
