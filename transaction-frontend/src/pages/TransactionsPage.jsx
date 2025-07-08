import { useState } from 'react';
import TransactionTable from '../components/TransactionTable';
import AddTransactionButton from '../components/AddTransactionButton';

const TransactionsPage = () => {
  const [refreshKey, setRefreshKey] = useState(0);

  const handleTransactionAdded = () => {
    setRefreshKey(prev => prev + 1);
  };

  return (
    <div className="transactions-page">
      <h1>Transactions</h1>
      <TransactionTable key={refreshKey} />
      <br/>
      <AddTransactionButton onTransactionAdded={handleTransactionAdded} />
    </div>
  );
};

export default TransactionsPage;