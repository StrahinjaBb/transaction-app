import { useState } from 'react';
import AddTransactionModal from './components/AddTransactionModal';

const App = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleTransactionAdded = () => {
    console.log('New transaction added.');
  };

  return (
    <div>
      <button onClick={() => setIsModalOpen(true)}>Dodaj transakciju</button>
      
      <AddTransactionModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onTransactionAdded={handleTransactionAdded}
      />
    </div>
  );
}

export default App;