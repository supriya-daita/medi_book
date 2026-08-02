import React from 'react';

export default function Dashboard({ onNavigatePatients }) {
  return (
    <div>
      <div style={{ backgroundColor: '#fff', borderRadius: '12px', padding: '32px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)', marginBottom: '24px' }}>
        <h2 style={{ margin: '0 0 12px 0', color: '#0f172a' }}>Welcome to MediBook System MVP</h2>
        <p style={{ color: '#475569', margin: 0, fontSize: '15px', lineHeight: '1.6' }}>
          This application serves as a benchmark test project for validating <strong>kb-scanner</strong>'s automated test generation engine across Unit, Integration, BVA, and Mocking scenarios.
        </p>
      </div>

      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))', gap: '20px' }}>
        <div style={{ backgroundColor: '#fff', borderRadius: '10px', padding: '24px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)', borderLeft: '4px solid #3b82f6' }}>
          <h3 style={{ margin: '0 0 8px 0', color: '#1e293b', fontSize: '16px' }}>Sprint 1 Core Features</h3>
          <p style={{ color: '#64748b', fontSize: '14px', margin: '0 0 16px 0' }}>Patient Registration, Unique Email Validation, Age BVA Discount Engine.</p>
          <button onClick={onNavigatePatients} style={{ backgroundColor: '#3b82f6', color: '#fff', border: 'none', padding: '8px 16px', borderRadius: '6px', cursor: 'pointer', fontWeight: 500 }}>
            Open Patient Management →
          </button>
        </div>

        <div style={{ backgroundColor: '#fff', borderRadius: '10px', padding: '24px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)', borderLeft: '4px solid #10b981' }}>
          <h3 style={{ margin: '0 0 8px 0', color: '#1e293b', fontSize: '16px' }}>BVA Target: calculateDiscount</h3>
          <p style={{ color: '#64748b', fontSize: '14px', margin: 0 }}>
            Evaluates age brackets (0–12 = 10%, 13–59 = 0%, 60–150 = 20%). Ideal target for unit & parameterized test generation.
          </p>
        </div>

        <div style={{ backgroundColor: '#fff', borderRadius: '10px', padding: '24px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)', borderLeft: '4px solid #8b5cf6' }}>
          <h3 style={{ margin: '0 0 8px 0', color: '#1e293b', fontSize: '16px' }}>Sprint 2 Roadmap</h3>
          <p style={{ color: '#64748b', fontSize: '14px', margin: 0 }}>
            Appointments, Doctor Schedules, Prescription Dosage (1–5000mg BVA), Auto-Billing, and 11 Custom Exceptions.
          </p>
        </div>
      </div>
    </div>
  );
}
