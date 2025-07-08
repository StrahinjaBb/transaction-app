import AddTransactionModal from './components/AddTransactionModal';
import TransactionTable from './components/TransactionTable';

const App = () => {
  return (
    <div className="app-container">
      <TransactionTable />
      <br/>
      <AddTransactionModal />
    </div>
  );
}

export default App;