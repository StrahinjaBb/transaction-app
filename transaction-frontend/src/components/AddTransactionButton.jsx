import { useState } from 'react';
import AddTransactionModal from './AddTransactionModal';

const AddTransactionButton = ({ onTransactionAdded }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  return (
    <div>
      <button 
        className="add-transaction-btn"
        onClick={() => setIsModalOpen(true)}
      >
        Add Transaction
      </button>

      <AddTransactionModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onTransactionAdded={onTransactionAdded}
      />
    </div>
  );
};

export default AddTransactionButton;