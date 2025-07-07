// src/components/AddTransactionModal.jsx
import { useState } from 'react';
import { addTransaction } from '../api/transactionApi';

const AddTransactionModal = ({ isOpen, onClose, onTransactionAdded }) => {
  const [formData, setFormData] = useState({
    transactionDate: '',
    accountNumber: '',
    accountHolderName: '',
    amount: ''
  });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setIsSubmitting(true);

    try {
      if (!formData.transactionDate || !formData.accountNumber || 
          !formData.accountHolderName || !formData.amount) {
        throw new Error('Mandatory fields');
      }

      await addTransaction({
        ...formData,
        amount: parseFloat(formData.amount)
      });

      onTransactionAdded();
      handleClose();
    } catch (err) {
      setError(err.message || 'Error while creating a transaction.');
      console.error(err);
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleClose = () => {
    setFormData({
      transactionDate: '',
      accountNumber: '',
      accountHolderName: '',
      amount: ''
    });
    setError('');
    onClose();
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal">
        <div className="modal-header">
          <h2>Add new transaction</h2>
          <button onClick={handleClose} className="close-btn">&times;</button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="transactionDate">Transaction date:</label>
            <input
              type="date"
              id="transactionDate"
              name="transactionDate"
              value={formData.transactionDate}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="accountNumber">Account number:</label>
            <input
              type="text"
              id="accountNumber"
              name="accountNumber"
              value={formData.accountNumber}
              onChange={handleChange}
              placeholder="npr. 1234-5678-9012"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="accountHolderName">Account holder name:</label>
            <input
              type="text"
              id="accountHolderName"
              name="accountHolderName"
              value={formData.accountHolderName}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="amount">Amount:</label>
            <input
              type="number"
              id="amount"
              name="amount"
              value={formData.amount}
              onChange={handleChange}
              step="0.01"
              min="0"
              required
            />
          </div>

          {error && <div className="error-message">{error}</div>}

          <div className="modal-actions">
            <button 
              type="button" 
              onClick={handleClose}
              className="cancel-btn"
            >
              Cancel
            </button>
            <button 
              type="submit" 
              disabled={isSubmitting}
              className="submit-btn"
            >
              {isSubmitting ? 'Adding...' : 'Add transaction'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddTransactionModal;