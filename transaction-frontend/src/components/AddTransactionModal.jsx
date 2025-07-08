import { useState } from 'react';
import { addTransaction } from '../api/transactionApi';

const AddTransactionModal = ({ isOpen, onClose, onTransactionAdded }) => {
  const [formData, setFormData] = useState({
    date: '',
    accountNumber: '',
    accountHolder: '',
    amount: ''
  });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState('');

  const validateFormData = ({ date, accountNumber, accountHolder, amount }) => {
    const parsedDate = new Date(date);
    if (isNaN(parsedDate.getTime())) {
      throw new Error('Invalid date');
    }

    const accountNumberRegex = /^\d{4}-\d{4}-\d{4}$/;
    if (!accountNumberRegex.test(accountNumber)) {
      throw new Error('Account number must be in format xxxx-xxxx-xxxx, where x is a number');
    }

    if (!accountHolder.trim()) {
      throw new Error('Account holder cannot be empty');
    }

    const numericAmount = parseFloat(amount);
    if (isNaN(numericAmount) || numericAmount <= 0) {
      throw new Error('Amount must be a positive number');
    }
  };

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
      if (!formData.date || !formData.accountNumber || 
          !formData.accountHolder || !formData.amount) {
        throw new Error('Mandatory fields');
      }

      validateFormData(formData);

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
      date: '',
      accountNumber: '',
      accountHolder: '',
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
            <label htmlFor="date">Transaction date:</label>
            <input
              type="date"
              id="date"
              name="date"
              value={formData.date}
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
            <label htmlFor="accountHolder">Account holder name:</label>
            <input
              type="text"
              id="accountHolder"
              name="accountHolder"
              value={formData.accountHolder}
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