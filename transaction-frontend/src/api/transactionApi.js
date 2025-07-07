import API from "../api/apiConfig";

export const getTransactions = async () => {
  try {
    const response = await API.get('/transactions');
    return response.data;
  } catch (error) {
    console.error('Error while fetching transactions:', error);
    throw error;
  }
};

export const addTransaction = async (transactionData) => {
  try {
    const response = await API.post('/transactions', transactionData);
    return response.data;
  } catch (error) {
    console.error('Error while creating a transaction:', error);
    throw error;
  }
};
