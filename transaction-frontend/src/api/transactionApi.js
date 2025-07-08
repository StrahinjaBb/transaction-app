import API from "../api/apiConfig";

export const getTransactions = async () => {
  try {
    const response = await API.get('/transaction');
    return response.data;
  } catch (error) {
    console.error('Error while fetching transactions:', error);
    throw error;
  }
};

export const addTransaction = async (transactionData) => {
  try {
    const response = await API.post('/transaction', transactionData);
    return response.data;
  } catch (error) {
    console.error('Error while creating a transaction:', error);
    throw error;
  }
};
