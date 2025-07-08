import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import TransactionsPage from './pages/TransactionsPage'

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/transactions" replace />} />
        <Route path="/transactions" element={<TransactionsPage />} />
        <Route path="*" element={<Navigate to="/transactions" replace />} />
      </Routes>
    </Router>
  );
}

export default App;