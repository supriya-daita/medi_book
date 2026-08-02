import React, { useState } from 'react';

export default function PatientManagement() {
  const [formData, setFormData] = useState({ name: '', email: '', age: '', phone: '', medicalHistory: '' });
  const [discountAge, setDiscountAge] = useState('');
  const [calculatedDiscount, setCalculatedDiscount] = useState(null);
  const [message, setMessage] = useState(null);

  const handleRegister = (e) => {
    e.preventDefault();
    setMessage({ type: 'success', text: `Registered ${formData.name} (${formData.email}) successfully!` });
    setFormData({ name: '', email: '', age: '', phone: '', medicalHistory: '' });
  };

  const handleCheckDiscount = (e) => {
    e.preventDefault();
    const age = parseInt(discountAge, 10);
    if (isNaN(age) || age < 0 || age > 150) {
      setMessage({ type: 'error', text: 'Age must be between 0 and 150' });
      setCalculatedDiscount(null);
      return;
    }

    let discount = 0.0;
    if (age <= 12) discount = 10.0;
    else if (age >= 60) discount = 20.0;

    setCalculatedDiscount({ age, discount });
    setMessage(null);
  };

  return (
    <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '24px' }}>
      {/* Patient Registration Form */}
      <div style={{ backgroundColor: '#fff', borderRadius: '12px', padding: '24px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)' }}>
        <h3 style={{ marginTop: 0, color: '#1e293b' }}>Register New Patient</h3>
        {message && (
          <div style={{ padding: '10px 14px', borderRadius: '6px', marginBottom: '16px', backgroundColor: message.type === 'success' ? '#dcfce7' : '#fee2e2', color: message.type === 'success' ? '#166534' : '#991b1b', fontSize: '14px' }}>
            {message.text}
          </div>
        )}
        <form onSubmit={handleRegister} style={{ display: 'flex', flexDirection: 'column', gap: '14px' }}>
          <div>
            <label style={{ display: 'block', fontSize: '13px', fontWeight: 600, color: '#475569', marginBottom: '4px' }}>Full Name</label>
            <input type="text" required value={formData.name} onChange={e => setFormData({ ...formData, name: e.target.value })} style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid #cbd5e1', boxSizing: 'border-box' }} placeholder="e.g. John Doe" />
          </div>
          <div>
            <label style={{ display: 'block', fontSize: '13px', fontWeight: 600, color: '#475569', marginBottom: '4px' }}>Email Address</label>
            <input type="email" required value={formData.email} onChange={e => setFormData({ ...formData, email: e.target.value })} style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid #cbd5e1', boxSizing: 'border-box' }} placeholder="e.g. john@example.com" />
          </div>
          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
            <div>
              <label style={{ display: 'block', fontSize: '13px', fontWeight: 600, color: '#475569', marginBottom: '4px' }}>Age (0 - 150)</label>
              <input type="number" required min="0" max="150" value={formData.age} onChange={e => setFormData({ ...formData, age: e.target.value })} style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid #cbd5e1', boxSizing: 'border-box' }} placeholder="35" />
            </div>
            <div>
              <label style={{ display: 'block', fontSize: '13px', fontWeight: 600, color: '#475569', marginBottom: '4px' }}>Phone (10 digits)</label>
              <input type="tel" required pattern="\d{10}" value={formData.phone} onChange={e => setFormData({ ...formData, phone: e.target.value })} style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid #cbd5e1', boxSizing: 'border-box' }} placeholder="9876543210" />
            </div>
          </div>
          <div>
            <label style={{ display: 'block', fontSize: '13px', fontWeight: 600, color: '#475569', marginBottom: '4px' }}>Medical History (Optional)</label>
            <textarea rows="3" value={formData.medicalHistory} onChange={e => setFormData({ ...formData, medicalHistory: e.target.value })} style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid #cbd5e1', boxSizing: 'border-box' }} placeholder="Notes or history..."></textarea>
          </div>
          <button type="submit" style={{ backgroundColor: '#10b981', color: '#fff', border: 'none', padding: '10px 18px', borderRadius: '6px', cursor: 'pointer', fontWeight: 600, marginTop: '8px' }}>
            Register Patient
          </button>
        </form>
      </div>

      {/* BVA Discount Calculator Tester */}
      <div style={{ backgroundColor: '#fff', borderRadius: '12px', padding: '24px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)' }}>
        <h3 style={{ marginTop: 0, color: '#1e293b' }}>BVA Discount Rule Evaluator</h3>
        <p style={{ color: '#64748b', fontSize: '14px', marginBottom: '16px' }}>
          Test the `calculateDiscount(age)` service logic boundaries directly:
        </p>

        <form onSubmit={handleCheckDiscount} style={{ display: 'flex', gap: '10px', marginBottom: '20px' }}>
          <input
            type="number"
            placeholder="Enter age (-1 to 151)"
            value={discountAge}
            onChange={e => setDiscountAge(e.target.value)}
            style={{ flex: 1, padding: '8px 12px', borderRadius: '6px', border: '1px solid #cbd5e1' }}
          />
          <button type="submit" style={{ backgroundColor: '#3b82f6', color: '#fff', border: 'none', padding: '8px 16px', borderRadius: '6px', cursor: 'pointer', fontWeight: 600 }}>
            Evaluate
          </button>
        </form>

        {calculatedDiscount && (
          <div style={{ backgroundColor: '#f8fafc', borderRadius: '8px', padding: '16px', border: '1px solid #e2e8f0' }}>
            <h4 style={{ margin: '0 0 8px 0', color: '#0f172a' }}>Evaluation Result</h4>
            <div style={{ fontSize: '14px', color: '#334155' }}>
              <p style={{ margin: '4px 0' }}><strong>Input Age:</strong> {calculatedDiscount.age}</p>
              <p style={{ margin: '4px 0' }}><strong>Discount Percentage:</strong> <span style={{ color: '#059669', fontWeight: 700 }}>{calculatedDiscount.discount}%</span></p>
              <p style={{ margin: '4px 0', fontSize: '13px', color: '#64748b' }}>
                <strong>Bracket Category:</strong> {calculatedDiscount.age <= 12 ? 'Child (10%)' : calculatedDiscount.age >= 60 ? 'Senior Citizen (20%)' : 'Standard Adult (0%)'}
              </p>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
