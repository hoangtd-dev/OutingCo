/** Roles defined in the OutingCo brief (Module 1 — Authentication & Nền tảng). */
export type UserRole =
  'super_admin' | 'provider_admin' | 'event_coordinator' | 'case_manager' | 'support_worker'

/** Outing lifecycle states (Module 2 — Outing / Event / Workshop). */
export type OutingStatus = 'draft' | 'published' | 'in_process' | 'complete' | 'cancelled'
