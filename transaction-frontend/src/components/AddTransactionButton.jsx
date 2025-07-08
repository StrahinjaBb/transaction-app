import { useState } from 'react';
import AddTransactionModal from './AddTransactionModal';

const AddTransactionButton = ({ onTransactionAdded }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  return (
    <>
      <button 
        className="add-transaction-btn"
        onClick={() => setIsModalOpen(true)}
      >
        Add New Transaction
      </button>

      <AddTransactionModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onTransactionAdded={onTransactionAdded}
      />
    </>
  );
};

export default AddTransactionButton;