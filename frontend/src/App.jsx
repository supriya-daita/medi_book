import React, { useState } from 'react';
import Dashboard from './pages/Dashboard';
import PatientManagement from './pages/PatientManagement';

export default function App() {
  const [activeTab, setActiveTab] = useState('dashboard');

  return (
    <div style={{ fontFamily: 'Segoe UI, Tahoma, Geneva, Verdana, sans-serif', backgroundColor: '#f4f6f9', minHeight: '100vh', margin: 0 }}>
      {/* Header Navigation */}
      <header style={{ backgroundColor: '#1e293b', color: '#fff', padding: '16px 32px', display: 'flex', justifyContent: 'space-between', alignItems: 'center', boxShadow: '0 2px 8px rgba(0,0,0,0.15)' }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
          <span style={{ fontSize: '24px' }}>🏥</span>
          <h1 style={{ margin: 0, fontSize: '20px', fontWeight: 600, letterSpacing: '0.5px' }}>MediBook — Hospital Management</h1>
        </div>
        <nav style={{ display: 'flex', gap: '16px' }}>
          <button
            onClick={() => setActiveTab('dashboard')}
            style={{
              background: activeTab === 'dashboard' ? '#3b82f6' : 'transparent',
              color: '#fff',
              border: 'none',
              padding: '8px 16px',
              borderRadius: '6px',
              cursor: 'pointer',
              fontWeight: 500,
              transition: 'background 0.2s'
            }}
          >
            Dashboard
          </button>
          <button
            onClick={() => setActiveTab('patients')}
            style={{
              background: activeTab === 'patients' ? '#3b82f6' : 'transparent',
              color: '#fff',
              border: 'none',
              padding: '8px 16px',
              borderRadius: '6px',
              cursor: 'pointer',
              fontWeight: 500,
              transition: 'background 0.2s'
            }}
          >
            Patient Management (Sprint 1)
          </button>
        </nav>
      </header>

      {/* Main Container */}
      <main style={{ maxWidth: '1200px', margin: '32px auto', padding: '0 24px' }}>
        {activeTab === 'dashboard' && <Dashboard onNavigatePatients={() => setActiveTab('patients')} />}
        {activeTab === 'patients' && <PatientManagement />}
      </main>
    </div>
  );
}
