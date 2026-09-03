---
name: certs
description: >-
  Use this skill whenever the user types "certs", "/certs", or asks to open, launch, or view their AI certification dashboard and countdown status in AGY.
---

# Certification Dashboard & Status Command

When the user types `certs` or invokes this skill:

1. **Launch the Browser Dashboard:**
   Run the terminal command:
   ```bash
   open "/Users/sanidhyarath007/SR Projects/GeminiSpace/ai-certification-platform/dashboard.html"
   ```

2. **Render the In-Chat Command Center Card:**
   Display the real-time days remaining and target dates:
   - **CCDV-F (Claude Certified Developer - Foundations):** September 29, 2026
   - **CCAR-F (Claude Certified Architect - Foundations):** October 19, 2026
   - **CCAR-P (Claude Certified Architect - Professional):** October 26, 2026

3. **Check-In on Active Drills:**
   Prompt the developer for today's milestone module or review topic.
