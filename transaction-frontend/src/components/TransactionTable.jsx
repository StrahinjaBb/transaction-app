import { useState, useEffect } from 'react';
import { getTransactions } from '../api/transactionApi';
import Status from './Status';

const TransactionTable = () => {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const data = await getTransactions();
        setTransactions(data);
      } catch (err) {
        setError(err.message || 'Error while creating a transaction.');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchTransactions();
  }, []);

  const formatDate = (dateString) => {
    if (!dateString) return 'Date unknown';
    
    try {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
      return new Date(dateString).toLocaleDateString('en-US', options);
    } catch (e) {
      console.error('Error while formatting date: ', e);
      return dateString;
    }
  };

  const formatAmount = (amount) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'EUR'
    }).format(amount);
  };

  if (loading) {
    return <div className="loading">Transactions loading...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  if (transactions.length === 0) {
    return <div className="no-data">No available transactions.</div>;
  }

  return (
    <div className="transaction-table-container">
      <table className="transaction-table">
        <thead>
          <tr>
            <th>Transaction Date</th>
            <th>Account number</th>
            <th>Account Holder Name</th>
            <th>Amount</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={`${transaction.accountNumber}-${transaction.date}-${transaction.amount}`}>
              <td>{formatDate(transaction.date)}</td>
              <td>{transaction.accountNumber}</td>
              <td>{transaction.accountHolder}</td>
              <td>{formatAmount(transaction.amount)}</td>
              <td>
                <Status status={transaction.status} />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TransactionTable;