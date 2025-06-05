# sepidan-common

**sepidan-common** is a shared library for Sepidan services, built using Spring. It centralizes common code such as DTOs, constants, exceptions, utilities, and logging tools to reduce duplication and improve maintainability across projects.

---

## 📁 Package Structure

Located at:  
`sepidan-common/src/main/java/net/sepidan/common`

Sub-packages:

- `constant` – Application-wide constant definitions
- `dto` – Shared Data Transfer Objects
- `exception` – Common exception classes and base handling
- `helper` – General-purpose helper classes
- `logger` – Logging utilities
- `util` – Utility functions (date handling, conversions, etc.)

---

## ⚙️ Dependencies

This module relies on commonly used libraries such as:

- Spring Context
- Lombok
- Apache Commons Lang
- Jalali Calendar (for Persian date handling)
- Redisson (for Redis integration)
- Jackson Annotations

Ensure versions are managed via your parent project's dependency management.

---

## ✅ Usage

Import `sepidan-common` into your Spring-based service and use shared components:

```java
import net.sepidan.common.dto.Kafka;
import net.sepidan.common.util.DateUtils;
